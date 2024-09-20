package in.cdac.tscp.model.request;

import in.cdac.tscp.entity.Hod_Approval_Status;

public class VMRequestModel {

	long id;
	String vmname;
	String ip;
	String vncaccess;
	String expirydate;
	String status;
	String os;
	String hdd;
	String ram;
	String requireddays;
	String vcpu;
	Hod_Approval_Status hodapproval;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getVmname() {
		return vmname;
	}

	public void setVmname(String vmname) {
		this.vmname = vmname;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getVncaccess() {
		return vncaccess;
	}

	public void setVncaccess(String vncaccess) {
		this.vncaccess = vncaccess;
	}

	public String getExpirydate() {
		return expirydate;
	}

	public void setExpirydate(String expirydate) {
		this.expirydate = expirydate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getHdd() {
		return hdd;
	}

	public void setHdd(String hdd) {
		this.hdd = hdd;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getRequireddays() {
		return requireddays;
	}

	public void setRequireddays(String requireddays) {
		this.requireddays = requireddays;
	}

	public String getVcpu() {
		return vcpu;
	}

	public void setVcpu(String vcpu) {
		this.vcpu = vcpu;
	}

	public Hod_Approval_Status getHodapproval() {
		return hodapproval;
	}

	public void setHodapproval(Hod_Approval_Status hodapproval) {
		this.hodapproval = hodapproval;
	}

}
