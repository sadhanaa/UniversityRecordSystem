package entity;
//TRUPTI
public class PersonCourseMap {
	
	private long idperson;//person id which is unique
	private long idcourse;//unique course id
	
			
	public PersonCourseMap()
	{
		
	}

	public PersonCourseMap(long idperson,long idcourse)
	{
		this.idperson = idperson;
		this.idcourse = idcourse;
		
	}
	
	public long getPersonId() {
		return idperson;
	}
	public void setPersonId(long idperson) {
		this.idperson = idperson;
	}
	public long getCourseId() {
		return idcourse;
	}
	public void setCourseId(long idcourse) {
		this.idcourse = idcourse;
	}
	
}
