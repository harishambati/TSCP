package in.cdac.tscp.model.request;


import in.cdac.tscp.entity.Hod_Approval_Status;



public class UserDetailsRequest {

	//private Long id;

	private String name; 

	private String empid;

	private String dept;

	private String email;
	
	private  String password;
	
	private String role;
	
	private Hod_Approval_Status status = Hod_Approval_Status.PENDING ;

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
	
}
