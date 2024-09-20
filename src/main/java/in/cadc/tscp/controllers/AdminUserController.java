package in.cadc.tscp.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.cdac.tscp.entity.Hod_Approval_Status;
import in.cdac.tscp.entity.UserEntity;
import in.cdac.tscp.model.response.UserApprovalRest;
import in.cdac.tscp.service.UserService;

@RestController
@RequestMapping("admin")
public class AdminUserController {

	@Autowired
	UserService userService;  
	
	@PutMapping(path = "/{id}/{status}")
	public void approveUser(@PathVariable Long id, @PathVariable Hod_Approval_Status status) {

		userService.updateUserStatus(status, id);
	}
	
	@GetMapping(path = "/usersawaitingapproval")
	public List<UserApprovalRest> getUserWaitingforApproval() {

		
		
		List<UserApprovalRest> returnValue = new ArrayList<>();
		
		List<UserEntity> userlist = userService.getUsersAwaitingApproval(Hod_Approval_Status.PENDING.name());
		
		for(UserEntity user : userlist ) 
		{
			UserApprovalRest userapp = new UserApprovalRest();
			BeanUtils.copyProperties(user, userapp);
			returnValue.add(userapp);
		}
		
		return returnValue;
	}
}
