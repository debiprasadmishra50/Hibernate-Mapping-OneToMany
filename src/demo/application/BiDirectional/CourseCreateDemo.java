package demo.application.BiDirectional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import demo.entity.Course;
import demo.entity.Instructor;
import demo.entity.InstructorDetail;

public class CourseCreateDemo 
{
	public static void main(String[] args) 
	{
		// create a factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();
		
		// create a session
		Session session = factory.getCurrentSession();
		
		try {
			// start a transaction
			Transaction tx = session.beginTransaction();
			
			// get the instructor from DB
			int id = 1; // will get the instructor with id 1 from instructor table
			Instructor instructor = session.get(Instructor.class, id);
			
			// create some courses
			Course course1 = new Course("JAVA SE - Core Java");
			Course course2 = new Course("JAVA EE - Advance Java");
//			Course course3 = new Course("PHP"); // This is gonna be deleted in future as instructor does not know PHP
			
			// add the courses to the instructor
			instructor.add(course1);
			instructor.add(course2);
//			instructor.add(course3);
			
			// save the courses
			session.save(course1);
			session.save(course2);
//			session.save(course3);
			
			//commit transaction
			tx.commit();
			System.out.println("Done");
			
		}finally {
			// clean up the resources
			session.close();
			factory.close();
		}
		
	}
}
