package in.cdac.tscp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.RequestEntity;
import org.springframework.transaction.annotation.Transactional;

import in.cdac.tscp.dto.UserDto;
import in.cdac.tscp.entity.Hod_Approval_Status;
import in.cdac.tscp.entity.VMRequestEntity;



public interface VMRequestRepository extends JpaRepository<VMRequestEntity, Long>
{
	
	VMRequestEntity getVMById(Long userId);
	
	//user specific count methods
	
	@Transactional
	@Query(value = "Select count(*) from  vmrequests where requested_by = ?1 and status = ?2", nativeQuery = true )
	int getUserRunningCount(String useremail,  String status );
	
	
	@Transactional
	@Query(value = "Select count(*) from  vmrequests where requested_by = ?1", nativeQuery = true )
	int getUserTotalCount(String useremail);
	
	@Transactional
	@Query(value = "SELECT count(*)  from vmrequests where DATEDIFF(expiry_date, CURDATE()) < 5 AND requested_by = ?1", nativeQuery = true )
	int getUserAboutExpiryCount(String useremail);
	
	
	//user specific value methods
	@Transactional
	@Query(value = "Select * from  vmrequests where requested_by = ?1 and status = ?2", nativeQuery = true )
	List<VMRequestEntity> getUserVMS(String useremail,  String status);
		
	@Transactional
	@Query(value = "Select * from  vmrequests where requested_by = ?1", nativeQuery = true )
	List<VMRequestEntity> getUserAllVMS(String useremail);
	
	@Transactional
	@Query(value = "SELECT * from vmrequests where DATEDIFF(expiry_date, CURDATE()) < 5 AND requested_by = ?1", nativeQuery = true )
	List<VMRequestEntity> getUserAboutExpiryList(String useremail);
	
	//HOD Specific count Methods
	@Transactional
	@Query(value = "Select count(*) from  vmrequests where status = ?1", nativeQuery = true )
	int getHodRunningCount(String status);
	
	@Transactional
	@Query(value = "Select count(*) from  vmrequests;", nativeQuery = true )
	int getHodAllVMsRequestsCount();
	
	@Transactional
	@Query(value = "Select count(*) from  vmrequests where hod_approval = ?1", nativeQuery = true )
	int waitingForApprovalVMSCount(String status);
	
//	@Transactional
//	@Query(value = "Select count(*) from  vmrequests where hod_approval = ?1 and status = ?2", nativeQuery = true )
//	int getHodApprovedCount(String approval, String status);
	
	@Transactional
	@Query(value = "Select count(*) from  vmrequests where hod_approval = ?1 and vm_delivered = false", nativeQuery = true )
	int getHodApprovedCount(String approval);
	
	//HOD Specific value methods
	
	@Transactional
	@Query(value = "Select * from  vmrequests", nativeQuery = true )
	List<VMRequestEntity> getHodAllVMsRequests();
	
	@Transactional
	@Query(value = "Select * from  vmrequests where status = ?1", nativeQuery = true )
	List<VMRequestEntity> getHodRunning(String status);
	
	@Transactional
	@Query(value = "Select * from  vmrequests where hod_approval = ?1", nativeQuery = true )
	List<VMRequestEntity> waitingForApprovalVMS(String status);
	
	@Transactional
	@Query(value = "Select * from  vmrequests where hod_approval = ?1 and vm_delivered = false", nativeQuery = true )
	List<VMRequestEntity> getHodApproved(String approval);
	
	
	//cloud team specific count methods
	
	@Transactional
	@Query(value = "Select count(*) from  vmrequests where hod_approval = ?1 and vm_delivered = false", nativeQuery = true )
	int getVMSRequestedByHodCount(String approval);
	
	@Transactional
	@Query(value = "Select count(*) from vmrequests where hod_approval = ?1 and status in ('RUNNING', 'STOPPED', 'EXPIRED');", nativeQuery = true)
	int getAllVMSCountCloudCount(String approval);
	
	
	
	//cloud team specific count methods
	
	@Transactional
	@Query(value = "Select * from  vmrequests where hod_approval = ?1 and vm_delivered = false", nativeQuery = true )
	List<VMRequestEntity> getVMSRequestedByHod(String approval);
	
	@Transactional
	@Query(value = "Select * from vmrequests where hod_approval = ?1 and status in ('RUNNING', 'STOPPED', 'EXPIRED');", nativeQuery = true)
	List<VMRequestEntity> getAllVMSCountCloud(String approval);
	
	
	//@Modifying
//	@Query("select count(id) from vmrequests  where requested_by = ?1 and status='RUNNING'")
//	long getUserRunningCount(String useremail);
	
//	@Modifying
//	@Query(value = "select count(e) from vmrequests", nativeQuery = true)
//	long getUserRunningCount();
	
	//Hod VM approval method
//	@Transactional
//	@Modifying
//	@Query(value = "UPDATE vmrequests SET hod_approval = ?1 where id = ?2", nativeQuery = true)
//	int setStatusForVM(Hod_Approval_Status status, Long id);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE vmrequests SET hod_approval = ?1 where id = ?2", nativeQuery = true)
	int setStatusForVM(String status, Long id);

	
	//"UPDATE users SET status = ?1 where id = ?2"
	
	//"UPDATE vmrequests SET vm_delivered = ?2 where id = ?3"
	
	@Transactional
	@Modifying
	@Query(value  = "UPDATE vmrequests SET delivered_date = ?1 , vm_delivered = ?2 where id = ?3" , nativeQuery = true)
	void setDeliveredStatus(String date, boolean status,Long id);
}
