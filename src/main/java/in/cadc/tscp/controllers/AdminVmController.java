package in.cadc.tscp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import in.cdac.tscp.entity.Hod_Approval_Status;
import in.cdac.tscp.service.imp.VMRequestService;

@RestController
@RequestMapping("adminvm")
public class AdminVmController {

	@Autowired
	VMRequestService vMRequestService;
	
	@PutMapping(path = "/{id}/{status}")
	public void approveRequest(@PathVariable Long id, @PathVariable Hod_Approval_Status status) {

		vMRequestService.updateVmStatus(status, id);
	}
	
}
