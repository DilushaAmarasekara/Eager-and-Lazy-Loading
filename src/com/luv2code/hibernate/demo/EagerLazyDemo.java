package com.luv2code.hibernate.demo;

 import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;


public class EagerLazyDemo {

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
        	 
        	 //option 1 : call the getter method before the session is closed
        	 
        	 // get the instructor from db
        	 int theId = 1;
        	 Instructor tempInstructor = session.get(Instructor.class, theId);
        	 
        	 System.out.println("EagerCode:Instructor " + tempInstructor);
        	 
         	 System.out.println("EagerCode:Courses " + tempInstructor.getCourses()); 	 
        	 //commit transaction
        	 session.getTransaction().commit();
        	 
        	 session.close();
         	 //get courses for the instructor
        	 System.out.println("\n The session is closed \n");
        	 System.out.println("EagerCode:Courses " + tempInstructor.getCourses());
        	 
        	 System.out.println("EagerCode:Done...");
        	 
         }finally {
        	 //add clean up code 
        	
			factory.close();
		}
         
	}	
	
}
