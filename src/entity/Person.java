package entity;

import java.io.Serializable;

//TRUPTI
public class Person implements Serializable {

		private long idperson;//person id which is unique
		private String pass;//password
		private String fname;//firstname
		private String lname;//lastname
		private String address;
		private String city;
		private String state;
		private String zipcode;
		private String persontype;// A -admin, S-student, I-instructor
		private Instructor instructor; 
		
			
		public Person(long idperson, String pass, String fname, String lname, String address, String city, String state, String zipcode, String persontype)
		{
			this.idperson = idperson;
			this.pass = pass;
			this.fname = fname;
			this.lname = lname;
				
			this.address = address;
			this.city = city;
			this.state = state;
			this.zipcode = zipcode;
			this.persontype = persontype;
			
		}
		
		public Person(){
			
		}
		
		public long getPersonId() {
			return idperson;
		}
		public void setPersonId(long idperson) {
			this.idperson = idperson;
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
		public Instructor getInstructor() {
			return instructor;
		}
		public void setInstructor(Instructor instructor) {
			this.instructor = instructor;
		}
	
}
