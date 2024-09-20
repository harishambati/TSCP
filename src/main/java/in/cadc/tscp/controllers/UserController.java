package in.cadc.tscp.controllers;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import in.cdac.tscp.dto.UserDto;
import in.cdac.tscp.entity.Hod_Approval_Status;
import in.cdac.tscp.exceptions.UserServiceException;
import in.cdac.tscp.model.request.UserDetailsRequest;
import in.cdac.tscp.model.response.ErrorMessages;
import in.cdac.tscp.model.response.OperationStatusModel;
import in.cdac.tscp.model.response.UserRest;
import in.cdac.tscp.service.UserService;


@RestController
@RequestMapping("users")
//@CrossOrigin(origins = "*",allowedHeaders = "*")
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping(path = "/{id}")
	public UserRest getUser(@PathVariable Long id) {
		UserRest returnValue = new UserRest();

		UserDto userDto = userService.getUserByUserId(id);
		BeanUtils.copyProperties(userDto, returnValue);

		return returnValue;
	}

//	@PostMapping
//	public UserRest createUser(@RequestBody UserDetailsRequest userdetails) throws Exception {
//		UserRest returnValue = new UserRest();
//		if (userdetails.getName().isEmpty()) {
//			throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
//		}
//
//		UserDto userdto = new UserDto();
//		BeanUtils.copyProperties(userdetails, userdto);
//
//		UserDto createdUser = userService.createUser(userdto);
//		BeanUtils.copyProperties(createdUser, returnValue);
//
//		return returnValue;
//	}

	
	
	@PostMapping
	public UserRest createUser(@RequestBody UserDetailsRequest userdetails) throws Exception {
		UserRest returnValue = new UserRest();
		if (userdetails.getName().isEmpty()) {
			throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		}

		UserDto userdto = new UserDto();
		BeanUtils.copyProperties(userdetails, userdto);
		userdto.setRoles(new HashSet<>(Arrays.asList(userdetails.getRole())));
		UserDto createdUser = userService.createUser(userdto);
		BeanUtils.copyProperties(createdUser, returnValue);

		return returnValue;
	}
	
	
	@PutMapping(path = "/{id}")
	public UserRest updateUser(@PathVariable Long id, @RequestBody UserDetailsRequest userdetails) {
		UserRest returnValue = new UserRest();
		if (userdetails.getName().isEmpty()) {
			throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		}

		UserDto userdto = new UserDto();
		BeanUtils.copyProperties(userdetails, userdto);
		
		UserDto updateUser = userService.updateUser(id, userdto);
		BeanUtils.copyProperties(updateUser, returnValue);

		return returnValue;
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteUser(@PathVariable Long id) {
		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName("DELETE");
		userService.deleteUser(id);
		returnValue.setOperationResult("SUCCESS");
		return returnValue;

	}

	@PutMapping(path = "/{id}/{status}")
	public void approveUser(@PathVariable Long id, @PathVariable Hod_Approval_Status status) {

		userService.updateUserStatus(status, id);
	}

	
	
	
}
