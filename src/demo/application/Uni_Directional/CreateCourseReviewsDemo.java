package demo.application.Uni_Directional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import demo.entity.Course;
import demo.entity.Instructor;
import demo.entity.InstructorDetail;
import demo.entity.Review;

public class CreateCourseReviewsDemo 
{
	public static void main(String[] args) 
	{
		// create a factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).addAnnotatedClass(Review.class).buildSessionFactory();
		
		// create a session
		Session session = factory.getCurrentSession();
		
		try {
			// start a transaction
			Transaction tx = session.beginTransaction();
			
			// Create a course
			Course course = new Course("Hibernate");
			
			// Create the reviews
			Review review1 = new Review("great course");
			Review review2 = new Review("Good explanation");
			Review review3 = new Review("Nice delivery");
			
			System.out.println(review1);
			
			// add some reviews
			course.addReview(review1);
			course.addReview(review2);
			course.addReview(review3);
			
			// save the course ... leverage the CascadeType.ALL to save the reviews too
			System.out.println("Saving the Course : "+course);
			System.out.println("Reviews are "+course.getReviews());
			
			session.save(course);
			
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
