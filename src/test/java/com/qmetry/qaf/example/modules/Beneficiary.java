package com.qmetry.qaf.example.modules;

public class Beneficiary {
	private String lastName;
	private String middleName;
	private String firstName;
	private String gender;
	private String dob;
	private String relationship;
	private String irrevocable;
	private String shareAllocation;
	private String countryOfBirth;
	private String citizenship;

	public Beneficiary() {

	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}
	
	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	
	public String getIrrevocable() {
		return irrevocable;
	}

	public void setIrrevocable(String irrevocable) {
		this.irrevocable = irrevocable;
	}
	
	public String getShareAllocation() {
		return shareAllocation;
	}
	
	public void setShareAllocation(String shareAllocation) {
		this.shareAllocation = shareAllocation;
	}
	
	public String getCountryOfBirth() {
		return countryOfBirth;
	}
	
	public void setCountryOfBirth(String countryOfBirth) {
		this.countryOfBirth = countryOfBirth;
	}
	
	public String getCitizenship() {
		return citizenship;
	}
	
	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}
}
