package entity;

import java.io.Serializable;

//TRUPTI
public class Instructor implements Serializable {

	
	private long idperson;//person id which is unique
	private String department;
	private String officeHr;
	private String location;
	private String pass;//password
	private String fname;//firstname
	private String lname;//lastname
	private String address;
	private String city;
	private String state;
	private String zipcode;
	private String persontype;
			
	public Instructor(){
		
	}

	public Instructor(long idperson, String department, String officeHr, String location)
	{
		this.idperson = idperson;
		this.department = department;
		this.officeHr = officeHr;
		this.location = location;
	}
	
	public long getPersonId() {
		return idperson;
	}
	public void setPersonId(long idperson) {
		this.idperson = idperson;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getOfficeHr() {
		return officeHr;
	}
	public void setOffficeHr(String officeHr) {
		this.officeHr = officeHr;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getPassword() {
		return pass;
	}
	public void setPassword(String pass) {
		this.pass = pass;
	}
	public String getFirstName() {
		return fname;
	}
	public void setFirstName(String fname) {
		this.fname = fname;
	}
	public String getLastName() {
		return lname;
	}
	public void setLastName(String lname) {
		this.lname = lname;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipcode;
	}
	public void setZipCode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getPersonType() {
		return persontype;
	}
	public void setPersonType(String persontype) {
		this.persontype = persontype;
	}

}
