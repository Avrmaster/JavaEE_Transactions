package data.dao;

import data.entities.Lecture;

import java.util.List;

public interface LectureService {

    public Lecture addLecture(Lecture lecture);

    public Lecture getLecture(int id);

    public void saveLecture(Lecture lecture);

    public List<Lecture> getLectures();

}
