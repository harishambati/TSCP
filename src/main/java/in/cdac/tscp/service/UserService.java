package in.cdac.tscp.service;



import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import in.cdac.tscp.dto.UserDto;
import in.cdac.tscp.entity.Hod_Approval_Status;
import in.cdac.tscp.entity.UserEntity;
import in.cdac.tscp.model.response.UserApprovalRest;


public interface UserService extends UserDetailsService {

	
 UserDto createUser(UserDto user);
 UserDto getUser(String email);
 UserDto getUserByUserId(Long userId);
 UserDto updateUser(Long userId, UserDto user);
 void deleteUser(Long userId);
 String getUserStatus(String email); 
 void updateUserStatus(Hod_Approval_Status status, Long Id);
// Optional <UserEntity> findById(Long id);
 List<UserEntity> getUsersAwaitingApproval(String status);
}
