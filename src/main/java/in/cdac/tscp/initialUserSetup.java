package in.cdac.tscp;

import java.util.Arrays;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import in.cdac.tscp.entity.AuthorityEntity;
import in.cdac.tscp.entity.Hod_Approval_Status;
import in.cdac.tscp.entity.RoleEntity;
import in.cdac.tscp.entity.UserEntity;
import in.cdac.tscp.repositories.AuthorityRepository;
import in.cdac.tscp.repositories.RoleRepository;
import in.cdac.tscp.repositories.UserRepository;

@Component
public class initialUserSetup {

	@Autowired
	AuthorityRepository authorityRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	UserRepository userRepository;

	@EventListener
	@Transactional
	public void onApplicationEvent(ApplicationReadyEvent event) {
		System.out.println("Harish");

		AuthorityEntity readAuthority = createAuthority("READ_AUTHORITY");
		AuthorityEntity writeAuthority = createAuthority("WRITE_AUTHORITY");
		AuthorityEntity deleteAuthority = createAuthority("DELETE_AUTHORITY");

		RoleEntity roleUser = createRole("ROLE_USER", Arrays.asList(readAuthority, writeAuthority));
		RoleEntity roleAdmin = createRole("ROLE_ADMIN", Arrays.asList(readAuthority, writeAuthority, deleteAuthority));
		RoleEntity roleCloud = createRole("ROLE_CLOUD", Arrays.asList(readAuthority, writeAuthority));
		RoleEntity roleHOD = createRole("ROLE_HOD", Arrays.asList(readAuthority, writeAuthority, deleteAuthority));

		if (roleAdmin == null)
			return;

		if (userRepository.findByEmail("admin@test.com") == null) {
			UserEntity adminUser = new UserEntity();
			adminUser.setEmail("admin@test.com");
			adminUser.setDept("101");
			adminUser.setEmpid("844864");
			adminUser.setName("Default_Admin");
			adminUser.setEncryptedPassword(bCryptPasswordEncoder.encode("admin"));
			adminUser.setRoles(Arrays.asList(roleAdmin));
			adminUser.setStatus(Hod_Approval_Status.APPROVED);
			adminUser.setRole("ROLE_ADMIN");
			userRepository.save(adminUser);
		}
	}

	private AuthorityEntity createAuthority(String name) {
		AuthorityEntity authority = authorityRepository.findByName(name);
		if (authority == null) {
			authority = new AuthorityEntity(name);
			authorityRepository.save(authority);
		}

		return authority;
	}

	private RoleEntity createRole(String name, Collection<AuthorityEntity> authorities) {

		RoleEntity role = roleRepository.findByName(name);

		if (role == null) {
			role = new RoleEntity(name);
			role.setAuthorities(authorities);
			roleRepository.save(role);
		}

		return role;
	}

}
