package in.cdac.tscp.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;



@Entity(name="users")
public class UserEntity implements Serializable {


	private static final long serialVersionUID = -4029342096486319561L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false, unique=true)
	private String email;
	
	@Column(nullable=false)
	private String empid;
	
	@Column(nullable=false)
	private String dept;
	
	@Column(nullable=false)
	private String encryptedPassword;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Hod_Approval_Status status = Hod_Approval_Status.PENDING; 
	
	@ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
	@JoinTable(name="users_roles", 
			joinColumns = @JoinColumn(name="users_id", referencedColumnName="id"), 
			inverseJoinColumns=@JoinColumn(name="roles_id",referencedColumnName="id"))
	private Collection<RoleEntity> roles;
	
	@Column(nullable=false)
	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Collection<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(Collection<RoleEntity> roles) {
		this.roles = roles;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Hod_Approval_Status getStatus() {
		return status;
	}

	public void setStatus(Hod_Approval_Status status) {
		this.status = status;
	}

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	
}
