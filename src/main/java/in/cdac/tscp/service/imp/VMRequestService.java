package in.cdac.tscp.service.imp;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;

import in.cdac.tscp.dto.UserDto;
import in.cdac.tscp.entity.Hod_Approval_Status;
import in.cdac.tscp.entity.UserEntity;
import in.cdac.tscp.entity.VMRequestEntity;
import in.cdac.tscp.entity.Vm_Running_Status;
import in.cdac.tscp.repositories.VMRequestRepository;

@Service
public class VMRequestService 
{
	@Autowired
	VMRequestRepository vMRequestRepository;
	
	public void addVmRequest(VMRequestEntity vMRequestEntity) 
	{
		vMRequestRepository.save(vMRequestEntity);
		
	}
	
	
	
	public VMRequestEntity getVMById(Long id) {

		Optional<VMRequestEntity> optional = vMRequestRepository.findById(id);
		//UserDto returnValue = new UserDto();
		VMRequestEntity vMRequestEntity = null;
		//UserEntity userEntity = null;
		if (optional.isPresent()) {
			vMRequestEntity = optional.get();
			//BeanUtils.copyProperties(vMRequestEntity, returnValue);
		}

		else 
		{
			System.out.println("User Not Found");
		}

		return vMRequestEntity;

	}
	
	public void updateVmRequest(VMRequestEntity vMRequestEntity, Long id ) 
	{
		VMRequestEntity oldRecord = vMRequestRepository.getById(id);
		
		if(vMRequestEntity.getVm_name() != null ) {
			oldRecord.setVm_name(vMRequestEntity.getVm_name());
		}
		
		if(vMRequestEntity.getIp() != null ) {
			oldRecord.setIp(vMRequestEntity.getIp());
		}
		
		if(vMRequestEntity.getVnc_access() != null ) {
			oldRecord.setVnc_access(vMRequestEntity.getVnc_access());;
		}
		
		if(vMRequestEntity.getExpiry_date() != null ) {
			oldRecord.setExpiry_date(vMRequestEntity.getExpiry_date());;
		}
		
		if(vMRequestEntity.getOs() != null ) {
			oldRecord.setOs(vMRequestEntity.getOs());
		}
		
		if(vMRequestEntity.getHdd() != null ) {
			oldRecord.setHdd(vMRequestEntity.getHdd());
		}
		
		if(vMRequestEntity.getRam() != null ) {
			oldRecord.setRam(vMRequestEntity.getRam());
		}
		
		if(vMRequestEntity.getRequired_days() != null ) {
			oldRecord.setRequired_days(vMRequestEntity.getRequired_days());
		}
		
		if(vMRequestEntity.getVcpu() != null ) {
			oldRecord.setVcpu(vMRequestEntity.getVcpu());
		}
		
		if(vMRequestEntity.getStatus() != null ) {
			oldRecord.setStatus(vMRequestEntity.getStatus());
		}
		
		if(vMRequestEntity.isVm_delivered() != null ) {
			oldRecord.setVm_delivered(vMRequestEntity.isVm_delivered());
		}
		
		vMRequestRepository.save(oldRecord);
	}
	
	//user Service methods
	public int getUserRunningCount(String user) 
	{
		return vMRequestRepository.getUserRunningCount(user, Vm_Running_Status.RUNNING.name());
		//return vMRequestRepository.getUserRunningCount();
	}
	
	public int getUserRequestedCount(String user) 
	{
		return vMRequestRepository.getUserRunningCount(user, Vm_Running_Status.REQUESTED.name());
	}

	public int getUserTotalCount(String user)
	{
		return vMRequestRepository.getUserTotalCount(user);
	}
	
	public int getUserExpiryCount(String user)
	{
		return vMRequestRepository.getUserAboutExpiryCount(user);
	}
	
	
	public List<VMRequestEntity> getUserRunningVMS(String user)
	{
		return vMRequestRepository.getUserVMS(user, Vm_Running_Status.RUNNING.name());
	}
	
	public List<VMRequestEntity> getUserRequestedVMS(String user)
	{
		return vMRequestRepository.getUserVMS(user, Vm_Running_Status.REQUESTED.name());
	}
	
	public List<VMRequestEntity> getUserAllVMS(String user)
	{
		return vMRequestRepository.getUserAllVMS(user);
	}
	
	public List<VMRequestEntity> getUserExpiryList(String user)
	{
		return vMRequestRepository.getUserAboutExpiryList(user);
	}
	
	//Hod Service methods
	
	//HOD Dashboard Count methods
	public int getHodRunningCount() 
	{
		return vMRequestRepository.getHodRunningCount(Vm_Running_Status.RUNNING.name());
	}
	
	public int getHodAllRequestsCount() 
	{
		return vMRequestRepository.getHodAllVMsRequestsCount();
	}
	
    public int waitingForHodApprovalCount() 
    {
    	return vMRequestRepository.waitingForApprovalVMSCount(Hod_Approval_Status.PENDING.name());
    }
	
    public int getHodApprovedRequestCount() 
    {
    	return vMRequestRepository.getHodApprovedCount(Hod_Approval_Status.APPROVED.name());
    }

    //HOD Dashboard value methods
    
    public List<VMRequestEntity> getHodRunning() 
	{
		return vMRequestRepository.getHodRunning(Vm_Running_Status.RUNNING.name());
	}
    
    public List<VMRequestEntity> getHodAllRequests() 
	{
		return vMRequestRepository.getHodAllVMsRequests();
	}
    
    public List<VMRequestEntity> waitingForHodApproval() 
    {
    	return vMRequestRepository.waitingForApprovalVMS(Hod_Approval_Status.PENDING.name());
    }
    
    public List<VMRequestEntity> getHodApprovedRequest() 
    {
    	return vMRequestRepository.getHodApproved(Hod_Approval_Status.APPROVED.name());
    }
    
    
    //cloud team dashboard count methods
    
    public int getVMSRequestedByHodCount()
	{
		return vMRequestRepository.getVMSRequestedByHodCount(Hod_Approval_Status.APPROVED.name());
	}
    
    
    public int getAllVMSCount()
	{
		return vMRequestRepository.getAllVMSCountCloudCount(Hod_Approval_Status.APPROVED.name());
	}
    
  //cloud team dashboard value methods
    
    public List<VMRequestEntity> getVMSRequestedByHod()
   	{
   		return vMRequestRepository.getVMSRequestedByHod(Hod_Approval_Status.APPROVED.name());
   	}
       
       
    public List<VMRequestEntity> getAllVMS()
   	{
   		return vMRequestRepository.getAllVMSCountCloud(Hod_Approval_Status.APPROVED.name());
   	}
       
     //Hod VM Request approval method
       
   
	public void updateVmStatus(Hod_Approval_Status status, Long Id) {
		vMRequestRepository.setStatusForVM(status.name(), Id);

	} 
    
	public void setDeliveryDate(String date, long id) 
	{
		vMRequestRepository.setDeliveredStatus(date,  true, id);
	}
}
