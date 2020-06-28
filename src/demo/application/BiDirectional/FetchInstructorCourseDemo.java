package demo.application.BiDirectional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import demo.entity.Course;
import demo.entity.Instructor;
import demo.entity.InstructorDetail;

public class FetchInstructorCourseDemo 
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
			
			System.out.println("Instructor is : "+instructor);
			
			// get the courses with the instructor
			System.out.println("Courses for the Instructor "+instructor.getFirstName()+" are "+instructor.getCourses());
			
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
