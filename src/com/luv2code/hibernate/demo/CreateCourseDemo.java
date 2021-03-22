package com.luv2code.hibernate.demo;

 import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;


public class CreateCourseDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//create session factory
         SessionFactory factory	= new Configuration()
        		 				.configure("hibernate.cfg.xml")
        		 				.addAnnotatedClass(Instructor.class)
        		 				.addAnnotatedClass(InstructorDetail.class)
        		 				.addAnnotatedClass(Course.class)
        		 				.buildSessionFactory();
		//create session
         Session session =factory.getCurrentSession();
    
         try {       	 
      	 
        	 //start s transaction
        	 session.beginTransaction();
        	 
        	 // get the instructor from db
        	 int theId = 1;
        	 Instructor tempInstructor = session.get(Instructor.class, theId);
        	 
        	 // create some courses
        	 Course tempCourse1 = new Course("course - 01");
        	 Course tempCourse2 = new Course("course - 02");
        	 
        	 // add courses to instructor
        	 tempInstructor.add(tempCourse1);
        	 tempInstructor.add(tempCourse2);
        	 
        	 //save the course
        	 session.save(tempCourse1);
        	 session.save(tempCourse2);
        	 
        	 //commit transaction
        	 session.getTransaction().commit();
        	 System.out.println("Done...");
        	 
         }finally {
        	 //add clean up code 
        	 session.close();
			factory.close();
		}
         
	}	
	
}
