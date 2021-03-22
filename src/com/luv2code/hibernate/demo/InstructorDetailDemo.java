package com.luv2code.hibernate.demo;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;


public class InstructorDetailDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//create session factory
         SessionFactory factory	= new Configuration()
        		 				.configure("hibernate.cfg.xml")
        		 				.addAnnotatedClass(Instructor.class)
        		 				.addAnnotatedClass(InstructorDetail.class)
        		 				.buildSessionFactory();
		//create session
         Session session =factory.getCurrentSession();
    
         try {
        	

        	 //start s transaction
        	 session.beginTransaction();
        	 
        	 //get the instructor detail object
        	 int theId=3;
        	 InstructorDetail tempInstructorDetail=session.get(InstructorDetail.class, theId);
        	 
        	 //print the instructor detail
        	 System.out.println("InstructorDetail "+ tempInstructorDetail);
        	 
        	 //print the associated instructor
        	System.out.println("The associated Instructor "+ tempInstructorDetail.getInstructor());
 
        	//now let's delete the instructorDetail
        	System.out.println("Delete the instructorDetail "+ tempInstructorDetail);
        	
        	//remove the associated object reference
        	//break bi-directional link
        	
        	tempInstructorDetail.getInstructor().setInstructorDetail(null);
        	
        	session.delete(tempInstructorDetail);
        	
        	 //commit transaction
        	 session.getTransaction().commit();
        	 System.out.println("Done...");
         }catch (Exception exe) {
			// TODO: handle exception
        	 exe.printStackTrace();	 
         }finally {
        	 //handle the connection issue
        	 session.close();
			factory.close();
		}
         
	}	
	
}
