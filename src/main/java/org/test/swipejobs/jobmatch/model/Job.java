package org.test.swipejobs.jobmatch.model;

import java.util.ArrayList;

public class Job {

	private int id;
	private boolean driverLicenseRequired = false;
	private ArrayList<String> requiredCertificates = new ArrayList<String>();
	private double longitude;
    private double latitude;
    
    public Job() {
    	
    }
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isDriverLicenseRequired() {
		return driverLicenseRequired;
	}
	public void setDriverLicenseRequired(boolean driverLicenseRequired) {
		this.driverLicenseRequired = driverLicenseRequired;
	}
	public ArrayList<String> getRequiredCertificates() {
		return requiredCertificates;
	}
	public void setRequiredCertificates(ArrayList<String> requiredCertificates) {
		this.requiredCertificates = requiredCertificates;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
    
    
  }
