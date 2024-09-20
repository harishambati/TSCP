package in.cdac.tscp.dto;

import java.io.Serializable;
import java.util.Collection;

import in.cdac.tscp.entity.Hod_Approval_Status;

public class UserDto implements Serializable
{
	
	private static final long serialVersionUID = -5832657330186994400L;
	private long id;
	private String name;
	private String email;
	private String empid;
	private String dept;
	private String password;
	private String encryptedPassword;
//	private String emailVerificationToken;
//	private Boolean emailVerificationStatus;
	private Hod_Approval_Status status;
	
	private String role; 
	
	private Collection<String> roles;
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
//	public String getEmailVerificationToken() {
//		return emailVerificationToken;
//	}
//	public void setEmailVerificationToken(String emailVerificationToken) {
//		this.emailVerificationToken = emailVerificationToken;
//	}
//	public Boolean getEmailVerificationStatus() {
//		return emailVerificationStatus;
//	}
//	public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
//		this.emailVerificationStatus = emailVerificationStatus;
//	}
	public Hod_Approval_Status getStatus() {
		return status;
	}
	public void setStatus(Hod_Approval_Status status) {
		this.status = status;
	}
	public Collection<String> getRoles() {
		return roles;
	}
	public void setRoles(Collection<String> roles) {
		this.roles = roles;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	
}
