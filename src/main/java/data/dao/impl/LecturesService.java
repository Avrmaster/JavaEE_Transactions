package data.dao.impl;

import data.dao.LectureService;
import data.entities.Lecture;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class LecturesService implements LectureService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Lecture addLecture(Lecture lecture) {
        em.persist(lecture);
        return lecture;
    }

    @Override
    public Lecture getLecture(int id) {
        return em.find(Lecture.class, id);
    }

    @Override
    public void saveLecture(Lecture lecture) {
        em.merge(lecture);
    }

    @Override
    public List<Lecture> getLectures() {
        System.out.println("called JpaLectureDAO.getLectures()");
        Query query = em.createNamedQuery("Lecture.findAll", Lecture.class);
        return query.getResultList();
    }

}
