package com.example.mapping.map;

import com.example.mapping.map.dao.AppDAO;
import com.example.mapping.map.entity.Course;
import com.example.mapping.map.entity.Instructor;
import com.example.mapping.map.entity.InstructorDetail;
import com.example.mapping.map.entity.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class MapApplication {

	public static void main(String[] args) {
		SpringApplication.run(MapApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner -> {
//			createInstructor(appDAO);
//			findInstructor(appDAO);
//			deleteInstructor(appDAO);
//			findInstructorDetail(appDAO);
//			deleteInstructorDetail(appDAO);
//			createInstructorWithCourses(appDAO);
//			findInstructorWithCourses(appDAO);
//			findCoursesForInstructor(appDAO);
//			findInstructorWithCoursesJoinFetch(appDAO);
//			updateInsturctor(appDAO);
//			updateCourse(appDAO);
//			deleteCourse(appDAO);

//			createCourseAndReviews(appDAO);
//			retrieveCourseAndReviews(appDAO);
//			deleteCourseAndReviews(appDAO);
		};
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int id = 10;
		appDAO.deleteCourseById(id);
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {
		int id = 10;
		Course course = appDAO.findCourseAndReviewsByCourseId(id);

		System.out.println(course);
		System.out.println(course.getReviews());
	}

	private void createCourseAndReviews(AppDAO appDAO) {
		Course course = new Course("some course");

		course.addReview(new Review("I like the course!"));
		course.addReview(new Review("Cool! I like the course!"));
		course.addReview(new Review("I don't like the course!"));

		System.out.println(course.getReviews());
		appDAO.save(course);
	}

	private void deleteCourse(AppDAO appDAO) {
		int id = 10;
		
		// simple, huh?
		appDAO.deleteCourseById(id);
	}

	private void updateCourse(AppDAO appDAO) {
		int id = 10;
		Course course = appDAO.findCourseById(id);

		course.setTitle("huh? I'm changed!");
		appDAO.update(course);
	}

	private void updateInsturctor(AppDAO appDAO) {
		int id = 14;
		Instructor instructor = appDAO.findInstructorById(14);
		instructor.setLastName("hahaha");
		appDAO.update(instructor);
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int id = 14;
		Instructor instructor = appDAO.findInstructorByIdJoinFetch(id);

		System.out.println("The instructor is: " + instructor);
		System.out.println("The courses are: " + instructor.getCourses());
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int id = 14;

		Instructor instructor = appDAO.findInstructorById(id);
		// since fetch type is lazy, it will retrieve instructor without the courses.
		List<Course> courses = appDAO.findCoursesByInstructorId(id);

		instructor.setCourses(courses);
		System.out.println("Courses: " + instructor.getCourses());
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int id = 14;
		System.out.println("Find id " + id + "...");

		Instructor instructor = appDAO.findInstructorById(id);
		System.out.println("The courses are: " + instructor.getCourses());
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		Instructor tmpInstructor = new Instructor("Jay", "Le", "jayle@quotient.com");
		InstructorDetail tmpInstructorDetail = new InstructorDetail(
				"http://www.youtube.com", "play clarinet!"
		);

		// associate objects
		tmpInstructor.setInstructorDetail(tmpInstructorDetail);

		Course course1 = new Course("web dev");
		Course course2 = new Course("algo");

		tmpInstructor.add(course1);
		tmpInstructor.add(course2);

		// save the instructor
		System.out.println("Saving...");
		System.out.println("The courses: " + tmpInstructor.getCourses());
		appDAO.save(tmpInstructor);
		System.out.println("Done.");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int id = 2;
		System.out.println("Deleting...");
		appDAO.deleteInstructorDetailById(id);
	}

	private void findInstructorDetail(AppDAO appDAO) {
		int id = 2;
		System.out.println("Finding...");
		InstructorDetail tmp = appDAO.findInstructorDetailById(id);
		System.out.println(tmp.getInstructor());
	}

	private void deleteInstructor(AppDAO appDAO) {
		int id = 1;
		System.out.println("Deleting...");
		appDAO.deleteInstructorById(id);
	}

	private void findInstructor(AppDAO appDAO) {
		int id = 1;
		System.out.println("finding...");
		Instructor instructor = appDAO.findInstructorById(id);
		System.out.println(instructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
		Instructor tmpInstructor = new Instructor("Yuanhe", "Li", "yuli@quotient.com");
		InstructorDetail tmpInstructorDetail = new InstructorDetail(
				"http://www.google.com", "play clarinet"
		);

		// associate objects
		tmpInstructor.setInstructorDetail(tmpInstructorDetail);

		// save the instructor
		// This will also save InsturctorDetail object!
		System.out.println("Saving...");
		appDAO.save(tmpInstructor);
		System.out.println("Done");
	}

}
