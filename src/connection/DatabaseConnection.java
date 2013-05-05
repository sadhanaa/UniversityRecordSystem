package connection;

import java.sql.*;
import java.util.*;

import entity.Course;
import entity.Person;
import entity.Instructor;
import entity.PersonCourseMap;
import connection.Configuration;
import com.sun.security.auth.login.ConfigFile;

public class DatabaseConnection {

	Connection con = null;
	ResultSet rs, rs1, rs2, rs3;
	ConnectionPool connectionPool = null;
	PreparedStatement ps, ps1, ps2, ps3;

	public DatabaseConnection() {

		// CP: Connection pooling start

		
		/* connectionPool = ConnectionPool.getInstance();
		 try { 
			 con =connectionPool.getConnection();
		      if (!con.isClosed())
			 System.out.println("Successfully Connected!!!");
		 }catch (SQLException e)
		 { // TODO Auto-generated catch block
		  e.printStackTrace(); 
		  }
		*/

		// reular without connection pool
		try {

//			Class.forName("com.mysql.jdbc.Driver").newInstance();
//			con = DriverManager.getConnection(
//					"jdbc:mysql://localhost:3306/cmpe273", "root", "root");
//			if (!con.isClosed()) {
//				System.out.println("Successfully Connected!!!");
//			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// VIEW ALL PERSONS : TRUPTI

	public Person[] ViewAllPersons() throws SQLException {

		String query;
		String querycount;
		int Count = 0;
		int index = 0;
		Person[] allpersons = null;

		try {
			query = "select * from person";
			querycount = "select count(1) from person";

			ps = con.prepareStatement(querycount);
			rs = ps.executeQuery();

			if (rs.next()) {

				Count = rs.getInt(1);
			}

			allpersons = new Person[Count];

			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				Person person = new Person();
				person.setPersonId(rs.getLong("idperson"));
				person.setPassword(rs.getString("pass"));
				person.setFirstName(rs.getString("fname"));
				person.setLastName(rs.getString("lname"));
				person.setAddress(rs.getString("address"));
				person.setCity(rs.getString("city"));
				person.setState(rs.getString("state"));
				person.setZipCode(rs.getString("zipcode"));
				person.setPersonType(rs.getString("persontype"));
				allpersons[index] = person;
				index++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
     	           ps.close();
     	          // connectionPool.free(con);
            } catch (SQLException e) {
               // TODO Auto-generated catch block
                 e.printStackTrace();
              } 
		}
		return allpersons;

	}

	// VIEW ALL STUDENTS : TRUPTI

	public Person[] ViewAllStudents() throws SQLException {

		String query;
		String querycount;
		int Count = 0;
		int index = 0;
		Person[] allstudents = null;

		try {
			query = "select * from person where persontype = 'S'";
			querycount = "select count(1) from person";

			ps = con.prepareStatement(querycount);
			rs = ps.executeQuery();

			if (rs.next()) {

				Count = rs.getInt(1);
			}

			allstudents = new Person[Count];

			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				Person student = new Person();
				student.setPersonId(rs.getLong("idperson"));
				student.setPassword(rs.getString("pass"));
				student.setFirstName(rs.getString("fname"));
				student.setLastName(rs.getString("lname"));
				student.setAddress(rs.getString("address"));
				student.setCity(rs.getString("city"));
				student.setState(rs.getString("state"));
				student.setZipCode(rs.getString("zipcode"));
				student.setPersonType(rs.getString("persontype"));
				allstudents[index] = student;
				index++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
     	           ps.close();
     	          // connectionPool.free(con);
            } catch (SQLException e) {
               // TODO Auto-generated catch block
                 e.printStackTrace();
              } 
		}
		return allstudents;

	}

	// VIEW ALL INSTRUCTURS : TRUPTI

	public Instructor[] ViewAllInstructors() throws SQLException {

		String query, query2;
		String querycount;
		int Count = 0;
		int index = 0;
		Instructor[] allinstructor = null;

		try {
			query = "select * from person where persontype = 'I'";
			querycount = "select count(1) from person";
			query2 = "select * from instructor";

			ps = con.prepareStatement(querycount);
			rs = ps.executeQuery();

			if (rs.next()) {

				Count = rs.getInt(1);
			}

			allinstructor = new Instructor[Count];

			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			ps1 = con.prepareStatement(query2);
			rs1 = ps1.executeQuery();

			while (rs.next()) {
				Instructor instructor = new Instructor();
				instructor.setPersonId(rs.getLong("idperson"));
				instructor.setPassword(rs.getString("pass"));
				instructor.setFirstName(rs.getString("fname"));
				instructor.setLastName(rs.getString("lname"));
				instructor.setAddress(rs.getString("address"));
				instructor.setCity(rs.getString("city"));
				instructor.setState(rs.getString("state"));
				instructor.setZipCode(rs.getString("zipcode"));
				instructor.setPersonType(rs.getString("persontype"));

				if (rs1.next()) {
					instructor.setDepartment(rs1.getString("department"));
					instructor.setOffficeHr(rs1.getString("officeHr"));
					instructor.setLocation(rs1.getString("location"));
				}

				allinstructor[index] = instructor;
				index++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
     	     
                ps.close();
                ps1.close();
                // connectionPool.free(con);
            } catch (SQLException e) {
               // TODO Auto-generated catch block
                 e.printStackTrace();
              } 
		}
		return allinstructor;
	}

	// DISPLAY COURSE INFORMATION : TRUPTI
	public Course CourseDetail(long idcourse) throws SQLException {

		String query = "";
		String query1 = "";
		String query2 = "";
		String query3 = "";

		Course courseInfo = new Course();

		List<Instructor> instructorInfo = new ArrayList<Instructor>();
		int index = 0;
		long[] personid = null;
		try {

			query = "select * from course where idcourse= ?";
			ps = con.prepareStatement(query);
			ps.setLong(1, idcourse);

			rs = ps.executeQuery();

			if (rs.next()) {
				courseInfo = new Course();
				courseInfo.setCourseId(rs.getInt(1));
				courseInfo.setName(rs.getString(2));
				courseInfo.setSection(rs.getString(3));
				courseInfo.setHours(rs.getString(4));
				courseInfo.setLocation(rs.getString(5));

			} else {
				System.out.println("Course with id = " + idcourse
						+ " does not exist.");
				return null;
			}

			query1 = "select idperson from personcoursemap where idcourse = ?";
			query2 = "select fname, lname from person where idperson = ? and persontype='I'";
			query3 = "select * from instructor where idperson= ?";

			// search for the all related person to course
			ps1 = con.prepareStatement(query1);
			ps1.setLong(1, idcourse);
			rs1 = ps1.executeQuery();

			// get the count of no of persons related to course
			int count = 0;
			while (rs1.next()) {
				count++;
			}
			personid = new long[count];
			rs1.beforeFirst();// reset pointer on top of the list

			while (rs1.next()) {
				personid[index] = rs1.getInt(1);
				index++;
			}

			for (int i = 0; i < index - 1; i++) {
				Instructor tempInstructor = new Instructor();
				// get instructor name info
				ps2 = con.prepareStatement(query2);
				ps2.setLong(1, personid[i]);
				rs2 = ps2.executeQuery();

				if (rs2.next()) {
					tempInstructor.setFirstName(rs2.getString(1));
					tempInstructor.setLastName(rs2.getString(2));

					// get instructor official detail
					ps3 = con.prepareStatement(query3);
					ps3.setLong(1, personid[i]);
					rs3 = ps3.executeQuery();
					if (rs3.next()) {
						tempInstructor.setPersonId(rs3.getLong(1));
						tempInstructor.setDepartment(rs3.getString(2));
						tempInstructor.setOffficeHr(rs3.getString(3));
						tempInstructor.setLocation(rs3.getString(4));
					}
				}

				instructorInfo.add(tempInstructor);

			}

			courseInfo.setCourseTeachers(instructorInfo
					.toArray(new Instructor[instructorInfo.size()]));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
     	      
                ps.close();
                ps1.close();
                ps2.close();
                ps3.close();
                // connectionPool.free(con);
            } catch (SQLException e) {
               // TODO Auto-generated catch block
                 e.printStackTrace();
              } 
		}
		return courseInfo;
	}

	// DISPLAY PERSON INFORMATION : TRUPTI
	public Person PersonDetail(long idperson) throws SQLException {

		String query,query1= "";
		Person personInfo = null;
		try {

			query = "select * from person where idperson= ?";

			ps = con.prepareStatement(query);
			ps.setLong(1, idperson);

			rs = ps.executeQuery();

			if (rs.next()) {
				personInfo = new Person();
				personInfo.setPersonId(rs.getInt(1));
				personInfo.setPassword(rs.getString(2));
				personInfo.setFirstName(rs.getString(3));
				personInfo.setLastName(rs.getString(4));
				personInfo.setAddress(rs.getString(5));
				personInfo.setCity(rs.getString(6));
				personInfo.setState(rs.getString(7));
				personInfo.setZipCode(rs.getString(8));
				personInfo.setPersonType(rs.getString(9));

				if(personInfo.getPersonType().equals("I"))
				{
					query1 = "select * from instructor where idperson= ?";

					ps1 = con.prepareStatement(query1);
					ps1.setLong(1, idperson);
					rs1= ps1.executeQuery();
					if(rs1.next())
					{
						Instructor instructorinfo = new Instructor();
						instructorinfo.setPersonId(rs1.getLong(1));
						instructorinfo.setDepartment(rs1.getString(2));
						instructorinfo.setOffficeHr(rs1.getString(3));
						instructorinfo.setLocation(rs1.getString(4));
						
						personInfo.setInstructor(instructorinfo);
					}
					else
					{
						System.out.println("Intsructor detail for Intsructor with id = " + idperson
								+ " didn't found.");
					}
				}
							
			} else {
				System.out.println("Person with id = " + idperson
						+ " does not exist.");
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
     	      
                ps.close();
                ps1.close();
                // connectionPool.free(con);
            } catch (SQLException e) {
               // TODO Auto-generated catch block
                 e.printStackTrace();
              } 
		}
		return personInfo;
	}

	// UPDATE PERSON : TRUPTI
	public boolean UpdatePerson(long idperson, String pass, String fname,
			String lname, String address, String city, String state,
			String zipcode, String persontype, String department, String officeHr, String location) throws SQLException {
		int rowcount = 0;
		boolean result = false;
        String query,query1="";

		try {
			ps = (PreparedStatement) con
					.prepareStatement("update person set pass=?, fname=?, lname=?, address=?, city=?, state=?, zipCode=?, persontype =? where idperson=?");

			ps.setString(1, pass);
			ps.setString(2, fname);
			ps.setString(3, lname);
			ps.setString(4, address);
			ps.setString(5, city);
			ps.setString(6, state);
			ps.setString(7, zipcode);
			ps.setString(8, persontype);
			ps.setLong(9, idperson);
			rowcount = ps.executeUpdate();

			if (rowcount > 0) {
			   if(persontype =="I")
			   {
				   query ="select * from instructor where idperson =?";
				   ps1 =con.prepareStatement(query);
				   ps1.setLong(1, idperson);
				   rs1=ps1.executeQuery();
				   if(rs1.next())
				   {
					   query1 = "update instructor set department=?, officeHr=?, location=? where idperson=?";
					   ps2=con.prepareStatement(query1);
					   ps2.setString(1, department);
					   ps2.setString(2,officeHr);
					   ps2.setString(3, location);
					   ps2.setLong(4, idperson);
					   rowcount =0;
					   rowcount =ps2.executeUpdate();
					   if(rowcount>0)
					   {
						   result=true;
					   }
				   }
				   else
				   {
					   query1="insert into instructor (idperson,department,officeHr,location) values(?,?,?,?)";
					   ps2=con.prepareStatement(query1);
					   ps2.setLong(1, idperson);
					   ps2.setString(2, department);
					   ps2.setString(3,officeHr);
					   ps2.setString(4, location);
					   rowcount = 0;
					   rowcount =ps2.executeUpdate();
					   if(rowcount>0)
					   {
						   result=true;
					   }
				   }
				   
			   }
			   else
				   result= true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
     	      
                ps.close();
                ps1.close();
                ps2.close();
                // connectionPool.free(con);
            } catch (SQLException e) {
               // TODO Auto-generated catch block
                 e.printStackTrace();
              } 
		}
		return result;
	}


// ***********************************************************************************
	
	// CREATE STUDENT : VINISHA
	
		public long createStudent(String pass, String fname, String lname,
				String address, String city, String state, String zipcode,
				String persontype) {
			int rowcount = 0;
			boolean result = false;
			long studentId = -1;

			try {
				ps = (PreparedStatement) con
						.prepareStatement("insert into person(pass, fname, lname, address, city, state, zipcode, persontype) VALUES(?,?,?,?,?,?,?,?)");

				ps.setString(1, pass);
				ps.setString(2, fname);
				ps.setString(3, lname);
				ps.setString(4, address);
				ps.setString(5, city);
				ps.setString(6, state);
				ps.setString(7, zipcode);
				ps.setString(8, persontype);
				rowcount = ps.executeUpdate();

				if (rowcount > 0) {
					result = true;
					System.out.println("New Student has been created....");
				}
				if(result)
				{
					ps = (PreparedStatement) con
							.prepareStatement("select idperson from person where fname= ? and lname = ? and zipcode = ?");

					
					ps.setString(1, fname);
					ps.setString(2, lname);
					ps.setString(3, zipcode);
					rs = ps.executeQuery();

					if (rs.next()) {
						studentId = rs.getInt(1);
					} else {
						System.out.println("Person with fname= " + fname + ", lname= " + lname+ ", zipcode= "+zipcode
								+ " does not exist.");
						return studentId;
					}
				}
				else 
				{
					System.out.println("New student unable to create....");
					return studentId;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
         	       // connectionPool.free(con);
                    ps.close();
                } catch (SQLException e) {
                   // TODO Auto-generated catch block
                     e.printStackTrace();
                  } 
			}

			return studentId;
		}
	
		
	// REMOVE/REPLACE Instructor: VINISHA
		
	public boolean removeReplaceInstructor(long removeInsructor,
			long replaceInstructor, long idcourse) {
		int rowcount = 0;
		boolean result = false;

		try {
			ps = (PreparedStatement) con
					.prepareStatement("UPDATE personcoursemap SET idperson= ? WHERE idperson=? and idcourse=?");

			ps.setLong(1, replaceInstructor);
			ps.setLong(2, removeInsructor);
			ps.setLong(3, idcourse);
			rowcount = ps.executeUpdate();

			if (rowcount > 0) {
				result = true;
				System.out.println("Instructor with ID: " + removeInsructor
						+ "has been replaced with" + replaceInstructor
						+ "for course ID: " + idcourse);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
     	       // connectionPool.free(con);
                ps.close();
            } catch (SQLException e) {
               // TODO Auto-generated catch block
                 e.printStackTrace();
              } 
		}
		return result;
	}
	
	
	// DELETE STUDENT:VINISHA
	
		public boolean deleteStudent(long idperson) {
			int rowcount = 0;
			boolean result = false;

			try {
				ps = (PreparedStatement) con
						.prepareStatement("delete from person where idperson=?");

				ps.setLong(1, idperson);
				rowcount = ps.executeUpdate();

				if (rowcount > 0) {
					result = true;
					System.out.println("Student with ID: " + idperson + "has been deleted...");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
         	       // connectionPool.free(con);
                    ps.close();
                    } catch (SQLException e) {
                   // TODO Auto-generated catch block
                     e.printStackTrace();
                  } 
			}

			return result;
		}
		

		// CREATE INSTRUCTOR:VINISHA
		
		public boolean createInstructor(String pass, String fname, String lname,
				String address, String city, String state, String zipcode,
				String persontype, String department, String officeHr, String location) {
			int rowcount = 0;
			boolean result = false;

			try {
				
				long idperson = this.createStudent(pass, fname, lname, address, city, state, zipcode, persontype);
				
				if(idperson != -1)
				{
					
				}
				else
				{
					System.out.println("Not able to create record into person table........");
				}
				
				ps = (PreparedStatement) con
						.prepareStatement("insert into instructor(idperson, department, officeHr, location) VALUES(?,?,?,?)");

				ps.setLong(1, idperson);
				ps.setString(2, department);
				ps.setString(3, officeHr);
				ps.setString(4, location);
				rowcount = ps.executeUpdate();

				if (rowcount > 0) {
					result = true;
					System.out.println("New Instructor has been created....");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
            	       // connectionPool.free(con);
                       ps.close();
                   } catch (SQLException e) {
                      // TODO Auto-generated catch block
                        e.printStackTrace();
                     } 
			}

			return result;
		}
		
		// DELETE INSTRUCTOR: VINISHA
		
		public boolean deleteInstructor(long idperson) {
			
			boolean result = false;
			result = deleteStudent(idperson);
			if(result)
			{
				System.out.println("Instructor: " + idperson + " has been deleted");
			}
			else{
				System.out.println("Error in delerint Instructor: " + idperson );
			}
			return result;
		}
		
		
		// ASSIGN COURSE TO INSTRUCTOR : VINISHA
		
		public boolean assignCourseToInstructor(long idperson, long idcourse) {	
			
			int rowcount = 0;
			boolean result = false;
			long studentId = -1;

			try {
				ps = (PreparedStatement) con
						.prepareStatement("insert into personcoursemap(idperson, idcourse) VALUES(?,?)");

				ps.setLong(1, idperson);
				ps.setLong(2, idcourse);
				rowcount = ps.executeUpdate();

				if (rowcount > 0) {
					result = true;
					System.out.println("Course" + idcourse + " has been Assigned to " + idperson);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
            	       // connectionPool.free(con);
                          ps.close();
                     } catch (SQLException e) {
                         // TODO Auto-generated catch block
                         e.printStackTrace();
                       } 
			}
			return result;
			
		}
		
		//VIEW ALL COURSES : VINISHA
		
		public Course[] viewAllCourse(){
			
			String query;
			String countQuery;
			int Count = 0;
			int index=0;
			Course[] courseInfo = null;
					
			try {
					query = "select * from course";
					countQuery = "select count(1) from course";
					
					ps= con.prepareStatement(countQuery);
					rs= ps.executeQuery();
					
					if (rs.next()) {
						 Count = rs.getInt(1);
					}
					
					courseInfo = new Course[Count];
					
					ps= con.prepareStatement(query);
					rs= ps.executeQuery();
					
					while (rs.next()) {
						Course course = new Course();
						course.setCourseId(rs.getInt(1));
						course.setName(rs.getString(2));
						course.setSection(rs.getString(3));
						course.setHours(rs.getString(4));
						course.setLocation(rs.getString(5));
						courseInfo[index] = course;
						index++;
						
					}
					
				}
			 catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				try {
               	       // connectionPool.free(con);
                       ps.close();
               } catch (SQLException e) {
                       // TODO Auto-generated catch block
                       e.printStackTrace();
               } 
			}
			return courseInfo; 
		}
		
		
		// GET ADMIN :VINISHA
		
		public String getAdminUname()
		{
			return Configuration.ADMIN_UNAME;
		}
		
		public String getAdminPass()
		{
			return Configuration.ADMIN_PASS;
		}
		
//******************************************************************************************************************************************************************

		// UPDATE COURSE : HETAL
		public boolean UpdateCourse(long idcourse, String name, String section,
				String hours, String loc) throws SQLException {
			int rowcount = 0;
			boolean result = false;

			try {
				ps = (PreparedStatement) con
						.prepareStatement("update course set name=?, section=?, hours=?, loc=? where idcourse=?");

				ps.setString(1, name);
				ps.setString(2, section);
				ps.setString(3, hours);
				ps.setString(4, loc);
				ps.setLong(5, idcourse);
			
				rowcount = ps.executeUpdate();

				if (rowcount > 0) {
					result = true;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {

                try {
                	 // connectionPool.free(con);
                        ps.close();
                } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
			}
			return result;
		}
		
		//DELETE COURSE :HETAL
		public boolean deleteCourse(long idcourse) {
	        int rowcount = 0;
	        boolean result = false;

	        try {
	                ps = (PreparedStatement) con
	                                .prepareStatement("delete from course where idcourse=?");

	                ps.setLong(1, idcourse);
	                rowcount = ps.executeUpdate();

	                if (rowcount > 0) {
	                        result = true;
	                        System.out.println("Course with ID: " + idcourse + " has been deleted...");
	                }

	        } catch (SQLException e) {
	                e.printStackTrace();
	        } finally {
	               
	                try {
	                	 // connectionPool.free(con);
	                        ps.close();
	                } catch (SQLException e) {
	                        // TODO Auto-generated catch block
	                        e.printStackTrace();
	                }
	        }

	        return result;
		}
		
		//CREATE COURSE :HETAL
		public boolean createCourse(String name, String section, String hours,
	            String loc) {
	    int rowcount = 0;
	    boolean result = false;
	   

	    try {
	            ps = (PreparedStatement) con
	                            .prepareStatement("insert into course(name, section, hours, loc) VALUES(?,?,?,?)");

	            ps.setString(1, name);
	            ps.setString(2, section);
	            ps.setString(3, hours);
	            ps.setString(4, loc);
	            
	            rowcount = ps.executeUpdate();

	            if (rowcount > 0) {
	                    result = true;
	                    System.out.println("New Course has been created....");
	            }
	            

	    } catch (SQLException e) {
	            e.printStackTrace();
	    } finally {
	           
	            try {
	            	 // connectionPool.free(con);
	                    ps.close();
	            } catch (SQLException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	            }
	    }

	    return result;
		}
		
		
		//ENROLL A STUDENT:HETAL
		public boolean enrollStudent(long idperson, long idcourse) {        
	        
	        int rowcount = 0;
	        boolean result = false;

	        try {
	                ps = (PreparedStatement) con.prepareStatement("insert into personcoursemap(idperson, idcourse) VALUES(?,?)");

	                ps.setLong(1, idperson);
	                ps.setLong(2, idcourse);
	                rowcount = ps.executeUpdate();

	                if (rowcount > 0) {
	                        result = true;
	                        System.out.println("Student " + idperson + " has been enrolled to " + idcourse);
	                }

	        } catch (SQLException e) {
	                e.printStackTrace();
	        } finally {
	               
	                try {
	                	 // connectionPool.free(con);
	                        ps.close();
	                } catch (SQLException e) {
	                        // TODO Auto-generated catch block
	                        e.printStackTrace();
	                }
	        }
	        return result;
	        
		}
		
		//UNENROLL A STUDENT:HETAL
		public boolean unenrollStudent(long idperson, long idcourse) {        
	        
	        int rowcount = 0;
	        boolean result = false;

	        try {
	                ps = (PreparedStatement) con.prepareStatement("delete from personcoursemap where idperson =? and idcourse=?");

	                ps.setLong(1, idperson);
	                ps.setLong(2, idcourse);
	                rowcount = ps.executeUpdate();

	                if (rowcount > 0) {
	                        result = true;
	                        System.out.println("Student " + idperson + " has been unenrolled from the course " + idcourse);
	                }

	        } catch (SQLException e) {
	                e.printStackTrace();
	        } finally {
	               
	                try {
	                	 // connectionPool.free(con);
	                        ps.close();
	                } catch (SQLException e) {
	                        // TODO Auto-generated catch block
	                        e.printStackTrace();
	                }
	        }
	        return result;
	        
		}
		
		
		//SEARCH PERSON :HETAL
		public Person[] searchPerson(String attributeName, String attributeValue) throws SQLException
	    {
	            String query="";
	            int index=0, count=0;
	            Person[] personInfo = null;
	            try{

	                    query="select count(1) from person where "+attributeName+" LIKE '"+attributeValue+"%'"; // also check by having % in the beginning
	                    ps= con.prepareStatement(query);
	                    rs= ps.executeQuery();
	                    System.out.println(query);

	                    if (rs.next()) {
	                        count = rs.getInt(1);
	               }

	               personInfo = new Person[count];

	               query = "select * from movie where "+attributeName+" LIKE '"+attributeValue+"%'"; // also check by having % in the beginning
	               ps=con.prepareStatement(query);
	               rs = ps.executeQuery();

	               while(rs.next())
	               {
	                       Person person = new Person();
	                       person.setPersonId(rs.getLong(1));
	                       person.setPassword(rs.getString(2));
	                       person.setFirstName(rs.getString(3));
	                       person.setAddress(rs.getString(4));
	                       person.setCity(rs.getString(5));
	                       person.setState(rs.getString(6));
	                       person.setPersonType(rs.getString(7));
	                       personInfo[index] = person;
	                       index++;

	                       System.out.println(person.getPersonId()+" "+person.getPassword()+" "+person.getFirstName()+" "+person.getLastName()+ " " +
	                                       " "+person.getAddress()+" "+person.getCity()+" "+person.getState()+" "+person.getZipCode()+" "+person.getPersonType());
	               }
	               
	               if(index>0)
	               {
	                       System.out.println("Person found");
	               }
	               else
	               {
	                       System.out.println("Person not found");
	                       return null;
	               }
	       }
	       catch (SQLException e) {
	               e.printStackTrace();
	       }finally{
	               //connectionPool.free(con);
	    	   ps.close();
	       }
	       return personInfo;

	    }
	      
		
		//SEARCH Course :HETAL
		public Course[] searchCourse(String attributeName, String attributeValue) throws SQLException
	    {
	            String query="";
	            int index=0, count=0;
	            Course[] courseInfo = null;
	            try{

	                    query="select count(1) from course where "+attributeName+" LIKE '"+attributeValue+"%'"; // also check by having % in the beginning
	                    ps= con.prepareStatement(query);
	                    rs= ps.executeQuery();
	                    System.out.println(query);

	                    if (rs.next()) {
	                        count = rs.getInt(1);
	               }

	               courseInfo = new Course[count];

	               query = "select * from course where "+attributeName+" LIKE '"+attributeValue+"%'"; // also check by having % in the beginning
	               ps=con.prepareStatement(query);
	               rs = ps.executeQuery();

	               while(rs.next())
	               {
	                       Course course = new Course();
	                       course.setCourseId(rs.getLong(1));
	                       course.setName(rs.getString(2));
	                       course.setSection(rs.getString(3));
	                       course.setHours(rs.getString(4));
	                       course.setLocation(rs.getString(5));
	                       
	                       courseInfo[index] = course;
	                       index++;

	                       System.out.println(course.getCourseId()+" "+course.getName()+" "+course.getSection()+" "+course.getHours()+ " " +
	                                       " "+course.getLocation());
	               }
	               
	               if(index>0)
	               {
	                       System.out.println("Course found");
	               }
	               else
	               {
	                       System.out.println("Course not found");
	                       return null;
	               }
	       }
	       catch (SQLException e) {
	               e.printStackTrace();
	       }finally{
	               //connectionPool.free(con);
	               ps.close();
	       }
	       return courseInfo;

	    }
		
	
	
}
