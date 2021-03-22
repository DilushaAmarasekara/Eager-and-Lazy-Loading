package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;


public class CreateDemo {

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
        	
        	 //create the object
        	 
      /*  	 Instructor tempInstructor = new Instructor("dilusha","amarasekara","diluchamo.c@gmail.com");
        	 
        	 InstructorDetail tempInstructorDetail =new InstructorDetail("hittube","coding");*/
        	 
        	 Instructor tempInstructor = new Instructor("kavithma","amarasekara","kavithma.c@gmail.com");
        	 
        	 InstructorDetail tempInstructorDetail =new InstructorDetail("kavitube","singing");
        	 
        	 //associate the object
        
        	 tempInstructor.setInstructorDetail(tempInstructorDetail);
        	 
        	 //start s transaction
        	 session.beginTransaction();
        	 
        	//save the instructor
        	 System.out.println("saving instructor : "+tempInstructor);
        	 session.save(tempInstructor);
        	 
        	 //commit transaction
        	 session.getTransaction().commit();
        	 System.out.println("Done...");
        	 
         }finally {
			factory.close();
		}
         
	}	
	
}
