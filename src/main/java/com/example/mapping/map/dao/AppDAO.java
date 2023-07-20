package com.example.mapping.map.dao;


import java.util.List;
import com.example.mapping.map.entity.Course;
import com.example.mapping.map.entity.Instructor;
import com.example.mapping.map.entity.InstructorDetail;
import org.springframework.transaction.annotation.Transactional;

public interface AppDAO {
    void save(Instructor instructor);

    Instructor findInstructorById(int id);

    void deleteInstructorById(int id);

    InstructorDetail findInstructorDetailById(int id);

    void deleteInstructorDetailById(int id);

    List<Course> findCoursesByInstructorId(int id);

    Instructor findInstructorByIdJoinFetch(int id);

    void update(Instructor instructor);

    void update(Course course);

    Course findCourseById(int id);

    void deleteCourseById(int id);

    void save(Course course);

    Course findCourseAndReviewsByCourseId(int id);
}
