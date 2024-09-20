package in.cdac.tscp.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import in.cdac.tscp.entity.AuthorityEntity;
import in.cdac.tscp.entity.RoleEntity;
import in.cdac.tscp.entity.UserEntity;

public class UserPrincipal implements UserDetails {

	
	private static final long serialVersionUID = 2933389686432764545L;

	UserEntity userEntity;
	public UserPrincipal(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
//		List<GrantedAuthority> authorities = new ArrayList<>();
//		List<AuthorityEntity> authoritiesEntities = new ArrayList<>();

		Collection<GrantedAuthority> authorities = new HashSet<>();
		Collection<AuthorityEntity> authoritiesEntities = new HashSet<>();
		
		//Get user Roles
		Collection<RoleEntity> roles = userEntity.getRoles();
		
		if(roles == null) return authorities;
		
		roles.forEach((role) -> {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
			authoritiesEntities.addAll(role.getAuthorities());
		});
		
		authoritiesEntities.forEach((authorityEntity) -> {
			authorities.add(new SimpleGrantedAuthority(authorityEntity.getName()));
		}
			);
		
		return authorities;
	
	}
		

	@Override
	public String getPassword() {
		
		return this.userEntity.getEncryptedPassword();
	}

	@Override
	public String getUsername() {
		
		return this.userEntity.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
