package demo.application.BiDirectional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import demo.entity.Course;
import demo.entity.Instructor;
import demo.entity.InstructorDetail;

public class InstructorCreateDemo 
{
	public static void main(String[] args) 
	{
		// create a factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();
		
		// create a session
		Session session = factory.getCurrentSession();
		
		try {
			// create the objects
			Instructor instructor = new Instructor("Debi Prasad", "Mishra", "debimishra@gmail.com");
			
			InstructorDetail instructorDetail = new InstructorDetail("www.debivicky.com/youtube", "To Do Nothing"); 
			
			
			// associate the objects together
			instructor.setInstructorDetail(instructorDetail);
			
			// start a transaction
			Transaction tx = session.beginTransaction();
			
			System.out.println("Saving Instructor : "+instructor);
			System.out.println("Saving InstructorDetail : "+instructorDetail);
			
			// Save the Instructor
			session.save(instructor); // Note : this will also save the details of InstructorDetail because of CascadeType.ALL
			
			//commit transaction
			tx.commit();
			System.out.println("Done");
			
		}finally {
			
			session.close();
			factory.close();
		}
		
	}
}
