package in.cdac.tscp.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import in.cdac.tscp.entity.Hod_Approval_Status;
import in.cdac.tscp.entity.UserEntity;
import in.cdac.tscp.entity.VMRequestEntity;
import in.cdac.tscp.model.response.UserApprovalRest;

@Transactional
public interface UserRepository extends JpaRepository<UserEntity, Long> 
{
 
	UserEntity findByEmail(String email);
	
	@Modifying
	@Query("UPDATE users SET status = ?1 where id = ?2")
	int setStatusForUser(Hod_Approval_Status status, Long id);
	
	
	

	@Transactional
	@Query(value = "Select * from  users where status = ?1", nativeQuery = true )
	List<UserEntity> getUsersAwaitingApproval(String status);
}