package pl.gornik.persons;

import java.util.List;

public class SchoolClass {
    private String profile;
    private int classNumber;
    private List<Student> students;
    private List<Worker> teachers;

    public SchoolClass(String profile, int classNumber, List<Student> students, List<Worker> teachers) {
        this.profile = profile;
        this.classNumber = classNumber;
        this.students = students;
        this.teachers = teachers;
    }

    public void displayInformation() {
        System.out.println("Profil: " + profile);
        System.out.println("Numer klasy: " + classNumber);
        System.out.println("Uczniowie: ");
        students.forEach(Student::showInfo);
        System.out.println("Nauczyciele: ");
        teachers.forEach(Worker::showInfo);
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public int getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(int classNumber) {
        this.classNumber = classNumber;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Worker> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Worker> teachers) {
        this.teachers = teachers;
    }
}
