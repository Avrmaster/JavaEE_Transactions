package worker;

import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import data.entities.Lecture;
import data.dao.LectureService;

public class LectureWorker {

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    LectureService lecturesDAO;

    public Lecture addLecture(final Lecture lecture) {
        return transactionTemplate.execute(transactionStatus -> {
            try {
                lecturesDAO.addLecture(lecture);
                System.out.println("Lecture has been added: " + lecture);
                return lecture;
            } catch (RuntimeException e) {
                transactionStatus.setRollbackOnly();
                throw e;
            }
        });
    }

    public List<Lecture> getLecturesList() {
        return transactionTemplate.execute(transactionStatus -> {
            try {
                return lecturesDAO.getLectures();
            } catch (RuntimeException e) {
                transactionStatus.setRollbackOnly();
                throw e;
            }
        });
    }

}
