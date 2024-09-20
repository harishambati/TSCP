package in.cdac.tscp.service.imp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import in.cdac.tscp.dto.UserDto;
import in.cdac.tscp.entity.Hod_Approval_Status;
import in.cdac.tscp.entity.RoleEntity;
import in.cdac.tscp.entity.UserEntity;
import in.cdac.tscp.exceptions.UserServiceException;
import in.cdac.tscp.model.response.ErrorMessages;
import in.cdac.tscp.model.response.UserApprovalRest;
import in.cdac.tscp.repositories.RoleRepository;
import in.cdac.tscp.repositories.UserRepository;
import in.cdac.tscp.security.UserPrincipal;
import in.cdac.tscp.service.UserService;

@Service
public class UserServiceimpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder bcryptPasswordEncoder;

	@Autowired
	RoleRepository  roleRepository;
	
	@Override
	public UserDto createUser(UserDto user) {

		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);

		userEntity.setEncryptedPassword(bcryptPasswordEncoder.encode(user.getPassword()));

		
		//Set Roles
		Collection<RoleEntity> roleEntities = new HashSet<>();
		
		for(String role: user.getRoles()) {
			RoleEntity roleEntity = roleRepository.findByName(role);
			if(roleEntity != null) 
			{
				roleEntities.add(roleEntity);
			}
		}
		
		userEntity.setRoles(roleEntities);
		
		UserEntity storedUserDetails = userRepository.save(userEntity);

		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(storedUserDetails, returnValue);

		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
		{
			throw new UsernameNotFoundException(email);
		}
			
		return new UserPrincipal(userEntity);
		//return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUser(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
			throw new UsernameNotFoundException(email);
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
	}

	@Override
	public UserDto getUserByUserId(Long id) {

		Optional<UserEntity> optional = userRepository.findById(id);
		UserDto returnValue = new UserDto();
		UserEntity userEntity = null;
		if (optional.isPresent()) {
			userEntity = optional.get();
			BeanUtils.copyProperties(userEntity, returnValue);
		}

		else {
			throw new RuntimeException("Employee not found for id: " + id);
		}

		return returnValue;

	}

	@Override
	public UserDto updateUser(Long userId, UserDto user) {

		Optional<UserEntity> optional = userRepository.findById(userId);
		UserDto returnValue = new UserDto();
		UserEntity userEntity = null;
		if (optional.isPresent()) {
			userEntity = optional.get();
			BeanUtils.copyProperties(userEntity, returnValue);
			userEntity.setName(user.getName());
			userEntity.setEmail(user.getEmail());
			userRepository.save(userEntity);

			UserEntity updatedUserDetails = userRepository.save(userEntity);
			BeanUtils.copyProperties(updatedUserDetails, returnValue);
		}

		else {
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		}
		return returnValue;
	}

	@Override
	public void deleteUser(Long userId) {
		Optional<UserEntity> optional = userRepository.findById(userId);
		UserEntity userEntity = null;
		if (optional.isPresent()) {
			userEntity = optional.get();
			userRepository.delete(userEntity);
		}

		else {
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		}

	}

	@Override
	public String getUserStatus(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
			throw new UsernameNotFoundException(email);
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(userEntity, returnValue);

		return returnValue.getStatus().name();
	}

	@Override
	public void updateUserStatus(Hod_Approval_Status status, Long Id) {
		userRepository.setStatusForUser(status, Id);

	}
	
	@Override
	public List<UserEntity> getUsersAwaitingApproval(String status) {
		return userRepository.getUsersAwaitingApproval(status);

	}

}
