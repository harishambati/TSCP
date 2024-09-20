package in.cadc.tscp.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import in.cdac.tscp.entity.VMRequestEntity;
import in.cdac.tscp.model.response.VmCount;
import in.cdac.tscp.model.response.VmNameRest;
import in.cdac.tscp.security.SecurityConstants;
import in.cdac.tscp.service.imp.VMRequestService;
import io.jsonwebtoken.Jwts;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@RestController
@RequestMapping("vmrequests")
public class RequestController {
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	@Autowired
	VMRequestService vMRequestService;

	@RequestMapping(value = "{id}/getvmname", method = RequestMethod.GET)
	public VmNameRest getvmname(@PathVariable Long id) 
	{
		VmNameRest vmNameRest = new VmNameRest();
		VMRequestEntity vMRequestEntity = vMRequestService.getVMById(id);
		vmNameRest.setVnname(vMRequestEntity.getVm_name());
		
		return vmNameRest;
	}
	
	
	@PostMapping
	public void createVmRequest(@RequestBody VMRequestEntity vMRequestEntity,
			@RequestHeader(value = "Authorization") String authHeader) {

		String user = null;
		user = Jwts.parser().setSigningKey(SecurityConstants.TOKEN_SECRET)
				.parseClaimsJws(authHeader.replace(SecurityConstants.TOKEN_PREFIX, "")).getBody().getSubject();
		vMRequestEntity.setRequested_by(user);
		vMRequestService.addVmRequest(vMRequestEntity);

	}
	
	//Cloud team assigning values
	@PutMapping(path="/{id}")
	public void UpdateVmRequest(@PathVariable Long id, @RequestBody VMRequestEntity vMRequestEntity) 
	{
		vMRequestService.updateVmRequest(vMRequestEntity, id);
		
	}
	
	// User Dashboard controllers
	@RequestMapping(value = "/runningcount", method = RequestMethod.GET)
	public VmCount userRunningSystemsCount(@RequestHeader(value = "Authorization") String authHeader) 
	{
		String user = null;
		user = Jwts.parser().setSigningKey(SecurityConstants.TOKEN_SECRET)
				.parseClaimsJws(authHeader.replace(SecurityConstants.TOKEN_PREFIX, "")).getBody().getSubject();
		
		VmCount count = new VmCount(vMRequestService.getUserRunningCount(user));
		return count;
	}
	
	@RequestMapping(value = "/userrequestedcount", method = RequestMethod.GET)
	public VmCount userRequestedSystemsCount(@RequestHeader(value = "Authorization") String authHeader) 
	{
		String user = null;
		user = Jwts.parser().setSigningKey(SecurityConstants.TOKEN_SECRET)
				.parseClaimsJws(authHeader.replace(SecurityConstants.TOKEN_PREFIX, "")).getBody().getSubject();
		
		VmCount count = new VmCount(vMRequestService.getUserRequestedCount(user));
		return count;
	}


	@RequestMapping(value = "/usertotalcount", method = RequestMethod.GET)
	public VmCount userTotalSystemsCount(@RequestHeader(value = "Authorization") String authHeader) 
	{
		String user = null;
		user = Jwts.parser().setSigningKey(SecurityConstants.TOKEN_SECRET)
				.parseClaimsJws(authHeader.replace(SecurityConstants.TOKEN_PREFIX, "")).getBody().getSubject();
		VmCount count = new VmCount( vMRequestService.getUserTotalCount(user));
		return count;
		
	}
	
	@RequestMapping(value = "/abouttoexpirecount", method = RequestMethod.GET)
	public VmCount userExpiryCount(@RequestHeader(value = "Authorization") String authHeader) 
	{
		String user = null;
		user = Jwts.parser().setSigningKey(SecurityConstants.TOKEN_SECRET)
				.parseClaimsJws(authHeader.replace(SecurityConstants.TOKEN_PREFIX, "")).getBody().getSubject();
		VmCount count = new VmCount( vMRequestService.getUserExpiryCount(user));
		return count;
		
	} 
	
	
	
	@RequestMapping(value = "/userrunning", method = RequestMethod.GET)
	public List<VMRequestEntity> userRunningSystems(@RequestHeader(value = "Authorization") String authHeader) 
	{
		String user = null;
		user = Jwts.parser().setSigningKey(SecurityConstants.TOKEN_SECRET)
				.parseClaimsJws(authHeader.replace(SecurityConstants.TOKEN_PREFIX, "")).getBody().getSubject();
		
		return vMRequestService.getUserRunningVMS(user);
	}
	
	@RequestMapping(value = "/userrequested", method = RequestMethod.GET)
	public List<VMRequestEntity> userRequestedSystems(@RequestHeader(value = "Authorization") String authHeader) 
	{
		String user = null;
		user = Jwts.parser().setSigningKey(SecurityConstants.TOKEN_SECRET)
				.parseClaimsJws(authHeader.replace(SecurityConstants.TOKEN_PREFIX, "")).getBody().getSubject();
		
		return vMRequestService.getUserRequestedVMS(user);
	}
	
	@RequestMapping(value = "/userallvms", method = RequestMethod.GET)
	public List<VMRequestEntity> userAllSystems(@RequestHeader(value = "Authorization") String authHeader) 
	{
		String user = null;
		user = Jwts.parser().setSigningKey(SecurityConstants.TOKEN_SECRET)
				.parseClaimsJws(authHeader.replace(SecurityConstants.TOKEN_PREFIX, "")).getBody().getSubject();
		
		return vMRequestService.getUserAllVMS(user);
	}
	
	@RequestMapping(value = "/abouttoexpire", method = RequestMethod.GET)
	public List<VMRequestEntity> userExpiry(@RequestHeader(value = "Authorization") String authHeader) 
	{
		String user = null;
		user = Jwts.parser().setSigningKey(SecurityConstants.TOKEN_SECRET)
				.parseClaimsJws(authHeader.replace(SecurityConstants.TOKEN_PREFIX, "")).getBody().getSubject();
		
		return vMRequestService.getUserExpiryList(user);
	}
	
	//HOD Dashboard Count controllers
	@RequestMapping(value = "/hodrunningcount", method = RequestMethod.GET)
	public VmCount hodRunningSystemsCount() 
	{
		VmCount count = new VmCount(vMRequestService.getHodRunningCount());
		//return vMRequestService.getHodRunningCount();
		
		return count;
	}
	
	@RequestMapping(value = "/hodtotalvmrequestscount", method = RequestMethod.GET)
	public VmCount hodtotalVMRequestsCount() 
	{
		VmCount count = new VmCount(vMRequestService.getHodAllRequestsCount());
		return count;
	}
	
	@RequestMapping(value = "/waitingapprovalcount", method = RequestMethod.GET)
	public VmCount waitingHodApprovalCount() 
	{
		VmCount count = new VmCount(vMRequestService.waitingForHodApprovalCount());
		return count;
	}
	
	@RequestMapping(value = "/hodapprovedrequestscount", method = RequestMethod.GET)
	public VmCount HodApprovedRequestCount() 
	{
		VmCount count = new VmCount(vMRequestService.getHodApprovedRequestCount());
		return count;
	}
	
	
	//HOD Value controllers
	@RequestMapping(value = "/hodrunning", method = RequestMethod.GET)
	public List<VMRequestEntity> hodRunningSystems() 
	{
		return vMRequestService.getHodRunning();
	}
	
	@RequestMapping(value = "/hodtotalvmrequests", method = RequestMethod.GET)
	public List<VMRequestEntity> hodtotalVMRequests() 
	{
		return vMRequestService.getHodAllRequests();
	}
	
	@RequestMapping(value = "/waitingapproval", method = RequestMethod.GET)
	public List<VMRequestEntity> waitingHodApproval() 
	{
		return vMRequestService.waitingForHodApproval();
	}
	
	@RequestMapping(value = "/hodapprovedrequests", method = RequestMethod.GET)
	public List<VMRequestEntity> HodApprovedRequest() 
	{
		return vMRequestService.getHodApprovedRequest();
	}
	
	
	//cloud team count controllers
	
	@RequestMapping(value = "/requestedbyhodcount", method = RequestMethod.GET)
	public VmCount approvedByHodCount() 
	{
		VmCount count = new VmCount(vMRequestService.getVMSRequestedByHodCount());
		return count;
	}
	
	@RequestMapping(value = "/allvmscount", method = RequestMethod.GET)
	public VmCount getAllVMSCount() 
	{
		VmCount count = new VmCount(vMRequestService.getAllVMSCount());
		return count;
	}
	
	// cloud team value controllers
	
	@RequestMapping(value = "/requestedbyhod", method = RequestMethod.GET)
	public List<VMRequestEntity> approvedByHod() 
	{
		return vMRequestService.getVMSRequestedByHod();
	}
	
	@RequestMapping(value = "/allvmscountcloud", method = RequestMethod.GET)
	public List<VMRequestEntity> getAllVMS() 
	{
		return vMRequestService.getAllVMS();
	}
	
//	@RequestMapping(value = "/sendMail", method = RequestMethod.POST)
//	public void sendMail(@RequestBody VMRequestEntity vMRequestEntity ,HttpServletRequest request, HttpServletResponse response) 
//	{
//		long id = vMRequestEntity.getId();
//		String user = vMRequestEntity.getRequested_by();
//		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//        Date date = new Date();
//		SimpleMailMessage msg = new SimpleMailMessage();
//        msg.setTo(user);
//        msg.setSubject("VM Details");
//        msg.setText("VM Details"+ System.lineSeparator() + vMRequestEntity.toString());
//        try 
//        {
//        	System.out.println(user);
//        	javaMailSender.send(msg);
//        	System.out.println(dateFormat.format(date) + " " + id );
//         	vMRequestService.setDeliveryDate(dateFormat.format(date), id);
//        	
//        }
//        
//       catch(Exception e) {
//    	   response.setStatus(403);
//    	   response.addHeader("Message", e.getMessage());
//       }
        
        
        @RequestMapping(value = "{vm_id}/sendMail",  method = RequestMethod.POST)
    	public void sendMail(@PathVariable Long vm_id, HttpServletRequest request, HttpServletResponse response) 
    	{
        	
        	VMRequestEntity vMRequestEntity = vMRequestService.getVMById(vm_id);
        	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        	Date date = new Date();
    		SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(vMRequestEntity.getRequested_by());
            msg.setSubject("VM Details");
            msg.setText("VM Details"+ System.lineSeparator() + vMRequestEntity.toString());
            try 
            {
            	System.out.println(vMRequestEntity.getRequested_by());
            	javaMailSender.send(msg);
            	System.out.println(dateFormat.format(date) + " " + vm_id );
             	vMRequestService.setDeliveryDate(dateFormat.format(date), vm_id);
            	
            }
            
           catch(Exception e) {
        	   response.setStatus(403);
        	   response.addHeader("Message", e.getMessage());
           }
        
	}
}
