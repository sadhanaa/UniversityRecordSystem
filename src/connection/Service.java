package connection;

import java.sql.SQLException;

import javax.jws.WebService;

import entity.Course;
import entity.Person;
import entity.Instructor;
import entity.PersonCourseMap;

public class Service {

	DatabaseConnection db = new DatabaseConnection();

    //View All Persons: TRUPTI
	public Person[] viewAllPerson() throws SQLException {
		System.out.println("Inside service viewAllPerson!");
		 Person[] allpersons = db.ViewAllPersons();
		   return allpersons;
	}

	//View ALL Students: TRUPTI
 	public Person[] viewAllStudents() throws SQLException {
		System.out.println("Inside service viewAllStudents!");
		 Person[] allstudents = db.ViewAllStudents();
		   return allstudents;
	}
 	
	//View ALL Instructors: TRUPTI
  	 public Instructor[] viewAllInstructors() throws SQLException {
 			System.out.println("Inside service viewAllInstructors!");
 			 Instructor[] allinstructors = db.ViewAllInstructors();
 			   return allinstructors;
  	 }
	
	// Course Detail :TRUPTI
	public Course courseDetail(long idcourse) throws SQLException {
		System.out.println("Inside service CourseDetail!");
		Course courseInfo = null;

		courseInfo = db.CourseDetail(idcourse);
		return courseInfo;
	}

	// Person Detail :TRUPTI
	public Person personDetail(long idperson) throws SQLException {
		System.out.println("Inside service PersonDetail!");
		Person personInfo = null;

		personInfo = db.PersonDetail(idperson);
		return personInfo;
	}

	// Updtae Person :TRUPTI
	public boolean updatePerson(long idperson, String pass, String fname, String lname, String address, String city, String state, String zipcode, String persontype,String department, String officeHr, String location) throws SQLException {
		System.out.println("Inside service UpdatePerson!");
		boolean result = false;

		result = db.UpdatePerson(idperson, pass, fname, lname, address, city, state, zipcode, persontype,department,officeHr,location);
		return result;
	}
	
//****************************************************************************************************************************************
	
	
	// Create Student :VINISHA

		public long createStudent(String pass, String fname, String lname,
				String address, String city, String state, String zipcode,
				String persontype) {
			System.out.println("Creating Student record in database.......");
			long result;

			result = db.createStudent(pass, fname, lname, address, city, state,
					zipcode, persontype);
			return result;
		}

		// Delete Student :VINISHA

		public boolean deleteStudent(long idperson) {
			System.out.println("Deleting Student record in database.......");
			boolean result = false;

			result = db.deleteStudent(idperson);
			return result;
		}

		// Create Instructor :VINISHA

		public boolean createInstructor(String pass, String fname, String lname,
				String address, String city, String state, String zipcode,
				String persontype, String department, String officeHr,
				String location) {
			System.out.println("Creating Instructor record in database.......");
			boolean result = false;

			result = db.createInstructor(pass, fname, lname, address, city, state,
					zipcode, persontype, department, officeHr, location);
			return result;
		}

		// Delete Instructor :VINISHA

		public boolean deleteInstructor(long idperson) {
			System.out.println("Deleting Instructor record in database.......");
			boolean result = false;

			result = db.deleteStudent(idperson);
			return result;
		}

		// Assign a course to instructor :VINISHA

		public boolean assignCourseToInstructor(long idperson, long idcourse) {
			System.out.println("Assigning " + idcourse + " to " + idperson);
			boolean result = false;

			result = db.assignCourseToInstructor(idperson, idcourse);
			return result;
		}

		// remove and replace instructor :VINISHA

		public boolean removeReplaceInstructor(long removeInsructor,
				long replaceInstructor, long idcourse) {
			// System.out.println("Replacing " + removeInsructor + " with "
			// + replaceInstructor + "for course " + idcourse);
			boolean result = false;

			result = db
					.assignCourseToInstructor(removeInsructor, replaceInstructor);
			return result;
		}

		public Course[] viewAllCourse() {
			return db.viewAllCourse();
		}

		// GET ADMIN: VINISHA

		public String getAdminUname() {
			return db.getAdminUname();
		}

		public String getAdminPass() {
			return db.getAdminPass();
		}
	//********************************************************************************************************************************************************************************************************
		// Update Course :HETAL
		public boolean updateCourse(long idcourse, String name, String section, String hours, String loc) throws SQLException {
			System.out.println("Inside service UpdateCourse!");
			boolean result = false;

			result = db.UpdateCourse(idcourse, name, section, hours, loc);
			return result;
		}
		
		// Delete Course :HETAL
		public boolean deleteCourse(long idcourse) throws SQLException {
			System.out.println("Inside service DeleteCourse!");
			boolean result = false;

			result = db.deleteCourse(idcourse);
			return result;
		}
		
		// Create Course :HETAL
		public boolean createCourse(String name, String section, String hours,
	            String loc) throws SQLException {
			System.out.println("Inside service CreateCourse!");
			boolean result = false;

			result = db.createCourse(name, section, hours, loc);
			return result;
		}
		
		// Enroll Student :HETAL
		public boolean enrollStudent(Long idperson, Long idcourse) throws SQLException {
			System.out.println("Inside service EnrollStudent!");
			boolean result = false;

			result = db.enrollStudent(idperson, idcourse);
			return result;
		}
		
		// UnEnroll Student :HETAL
		public boolean unenrollStudent(Long idperson, Long idcourse) throws SQLException {
			System.out.println("Inside service UnenrollStudent!");
			boolean result = false;

			result = db.unenrollStudent(idperson, idcourse);
			return result;
		}
		
		// Search Person :HETAL
		public Person[] searchPerson(String attributeName, String attributeValue) throws SQLException {
			System.out.println("Inside service searchPerson!");
			
			Person personInfo[] = null;
			personInfo = db.searchPerson(attributeName, attributeValue);
			return personInfo;
		}
		
		// Search Course :HETAL
		public Course[] searchCourse(String attributeName, String attributeValue) throws SQLException {
			System.out.println("Inside service searchCourse!");
			
			Course courseInfo[] = null;
			courseInfo = db.searchCourse(attributeName, attributeValue);
			return courseInfo;
		}
		
		
}
