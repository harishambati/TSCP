package in.cdac.tscp.model.request;

import in.cdac.tscp.entity.Hod_Approval_Status;

public class UserLoginRequestModel 
{
	private String email;
	private String password;
	
	private Hod_Approval_Status status;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Hod_Approval_Status getStatus() {
		return status;
	}
	public void setStatus(Hod_Approval_Status status) {
		this.status = status;
	}
	
	
}
