package in.cdac.tscp.model.response;



import in.cdac.tscp.entity.Hod_Approval_Status;

public class UserApprovalRest 
{

	
	
	private long id;
	
	
	private String name;
	
	
	private String email;
	
	
	private String empid;
	
	
	private String dept;
	

	private Hod_Approval_Status status = Hod_Approval_Status.PENDING; 
	
	private String role;

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
