package com.example.mapping.map.dao;

import com.example.mapping.map.entity.Course;
import com.example.mapping.map.entity.Instructor;
import com.example.mapping.map.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{

    // define entityManager

    // inject Entity Manager using ctor injection
    private EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional // since we're performing updates "persisting the entity"
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
        // it will also save Instructor Detail because of Cascade.ALL.
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }


    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        InstructorDetail tmp = findInstructorDetailById(id);
        tmp.getInstructor().setInstructorDetail(null);
        entityManager.remove(tmp);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "FROM Course WHERE instructor.id = :data", Course.class
                // data is a variable, denoted by :
        );
        query.setParameter("data", id);

        List<Course> courses = query.getResultList();

        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
        TypedQuery<Instructor> query = entityManager.createQuery(
                "SELECT i FROM Instructor i JOIN FETCH i.courses " +
                        "JOIN FETCH i.instructorDetail WHERE i.id = :data",
                Instructor.class
        );
        query.setParameter("data", id);
        Instructor instructor = query.getSingleResult();
        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void update(Course course) {
        entityManager.merge(course);
    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        Course course = entityManager.find(Course.class, id);
        entityManager.remove(course);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor instructor = findInstructorById(id);
        List<Course> courses = instructor.getCourses();

        // break association with the course.
        // Otherwise, there'll be constraint violation error.
        for(Course course: courses){
            course.setInstructor(null);
        }
        entityManager.remove(instructor);
    }
}
