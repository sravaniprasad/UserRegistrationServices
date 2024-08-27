package com.chengannagari.s.dashboard.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="HealthStatus")
public class HealthCheckStatus {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String firstName;
	private String bugs;
	private String deploymentTimeline;
	private String status;
	private int serviceRate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getBugs() {
		return bugs;
	}
	public void setBugs(String bugs) {
		this.bugs = bugs;
	}
	public String getDeploymentTimeline() {
		return deploymentTimeline;
	}
	public void setDeploymentTimeline(String deploymentTimeline) {
		this.deploymentTimeline = deploymentTimeline;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getServiceRate() {
		return serviceRate;
	}
	public void setServiceRate(int serviceRate) {
		this.serviceRate = serviceRate;
	}
	public HealthCheckStatus(int id, String firstName, String bugs, String deploymentTimeline, String status,
			int serviceRate) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.bugs = bugs;
		this.deploymentTimeline = deploymentTimeline;
		this.status = status;
		this.serviceRate = serviceRate;
	}
	public HealthCheckStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
