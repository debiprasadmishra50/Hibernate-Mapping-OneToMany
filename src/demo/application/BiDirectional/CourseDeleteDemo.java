package demo.application.BiDirectional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import demo.entity.Course;
import demo.entity.Instructor;
import demo.entity.InstructorDetail;

public class CourseDeleteDemo 
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
			
			// get a course
			int id = 3; // delete the course with id of 3
			Course course = session.get(Course.class, id);
			
			System.out.println(course);
			
			// delete the course
			session.delete(course);
			
			//commit transaction
			tx.commit();
			System.out.println("Done");
			
		}finally {
			session.close();
			factory.close();
		}
		
	}
}
