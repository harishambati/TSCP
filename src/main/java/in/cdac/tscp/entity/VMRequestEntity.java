package in.cdac.tscp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "VMRequests")
public class VMRequestEntity {


	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;

	@Column(nullable = false)
	String vm_name;

	String ip;

	String vnc_access;
	
	String requested_by;
	
	String expiry_date;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	Vm_Running_Status status =  Vm_Running_Status.REQUESTED;

	
	@Column(nullable = false)
	String os;

	@Column(nullable = false)
	String hdd;

	@Column(nullable = false)
	String ram;

	@Column(nullable = false)
	String required_days;

	String vcpu;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	Hod_Approval_Status hod_approval  = Hod_Approval_Status.PENDING;
	
	Boolean vm_delivered = false;
	
	String delivered_date;
	
	public String getDelivered_date() {
		return delivered_date;
	}

	public void setDelivered_date(String delivered_date) {
		this.delivered_date = delivered_date;
	}

	public Boolean getVm_delivered() {
		return vm_delivered;
	}

	public Boolean isVm_delivered() {
		return vm_delivered;
	}

	public void setVm_delivered(Boolean vm_delivered) {
		this.vm_delivered = vm_delivered;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getVm_name() {
		return vm_name;
	}

	public void setVm_name(String vm_name) {
		this.vm_name = vm_name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getVnc_access() {
		return vnc_access;
	}

	public void setVnc_access(String vnc_access) {
		this.vnc_access = vnc_access;
	}

	public String getExpiry_date() {
		return expiry_date;
	}

	public void setExpiry_date(String expiry_date) {
		this.expiry_date = expiry_date;
	}

	

	public Vm_Running_Status getStatus() {
		return status;
	}

	public void setStatus(Vm_Running_Status status) {
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

	public String getRequired_days() {
		return required_days;
	}

	public void setRequired_days(String required_days) {
		this.required_days = required_days;
	}

	public String getVcpu() {
		return vcpu;
	}

	public void setVcpu(String vcpu) {
		this.vcpu = vcpu;
	}

	public Hod_Approval_Status getHod_approval() {
		return hod_approval;
	}

	public void setHod_approval(Hod_Approval_Status hod_approval) {
		this.hod_approval = hod_approval;
	}

	public String getRequested_by() {
		return requested_by;
	}

	public void setRequested_by(String requested_by) {
		this.requested_by = requested_by;
	}

	@Override
	public String toString() {
		return "VM Name : " + vm_name + System.lineSeparator() +
				"IP : " + ip + System.lineSeparator() +
				"OS : " + os +  System.lineSeparator() +
				"CPU : " + vcpu +   System.lineSeparator() +                    
				"RAM : " + ram +   System.lineSeparator() +
				"HDD  :" + hdd +  System.lineSeparator() +
				"VNC ACCESS : " + vnc_access +  System.lineSeparator() +
				"EXPIRY DATE : " + expiry_date +   System.lineSeparator() +
				"STATUS : " + status; 
	}

	

	

}
