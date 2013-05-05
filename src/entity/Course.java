package entity;

import java.io.Serializable;

//TRUPTI
public class Course implements Serializable {


	private long idcourse;//course id which is unique
	private String name;// title of course
	private String section;//the course section number
	private String hours;//time of the class for this course 
	private String loc;//location is usually a class number where this course is taught
	private Person[] courseusers;//all persons associated with course 	
	private Instructor[] courseteachers;
	public Course(){
		
	}

	public Course(long idcourse, String name, String section, String hours, String loc)
	{
		this.idcourse = idcourse;
		this.name = name;
		this.section = section;
		this.hours = hours;
		this.loc = loc;
		
	}
	
	public long getCourseId() {
		return idcourse;
	}
	public void setCourseId(long idcourse) {
		this.idcourse = idcourse;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getHours() {
		return hours;
	}
	public void setHours(String hours) {
		this.hours = hours;
	}
	public String getLocation() {
		return loc;
	}
	public void setLocation(String loc) {
		this.loc = loc;
	}

	public Person[] getCourseUsers() {
		return courseusers;
	}
	public void setCourseUsers(Person[] courseusers) {
		this.courseusers = courseusers;
	}
	public Instructor[] getCourseTeachers() {
		return courseteachers;
	}
	public void setCourseTeachers(Instructor[] courseteachers) {
		this.courseteachers = courseteachers;
	}
}
