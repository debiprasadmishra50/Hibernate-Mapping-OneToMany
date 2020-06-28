package demo.application.Uni_Directional;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import demo.entity.Course;
import demo.entity.Instructor;
import demo.entity.InstructorDetail;
import demo.entity.Review;

public class DeleteCourseReviewsDemo 
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
			
			// get the course WITH ID 4, print the course, print course reviews
			int id = 4; 
			Course course = session.get(Course.class, id);
			
			System.out.println("Deleting the course and reviews : \n");
			System.out.println(course);
			
			List<Review> reviews = course.getReviews();
			for (Review review : reviews) {
				System.out.println(review);
			}
			
			// delete the course
			session.delete(course);
			
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
