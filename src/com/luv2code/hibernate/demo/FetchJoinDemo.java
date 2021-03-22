package com.luv2code.hibernate.demo;



import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;


public class FetchJoinDemo {

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
        	 
        	 //option 1 : Hibernate query with HQL
        	 
        	 // get the instructor from db
        	 int theId = 1;

        	 Query <Instructor> query = 
        			 session.createQuery("select i from Instructor i "
      					 + "JOIN FETCH i.courses "
        					 + "where i.id=:theInstructorId",
        					 Instructor.class);
        	 
        	 //set parameter on the query
        	 query.setParameter("theInstructorId",theId);
        	 
        	 //execute query and get the instructor
        	 Instructor tempInstructor=query.getSingleResult();
        	 
        	 System.out.println("EagerCode:Instructor " + tempInstructor);
        	 
	 
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
