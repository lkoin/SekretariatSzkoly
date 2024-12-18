package pl.gornik.persons;

import pl.gornik.exceptions.PersonIsAlreadyDefinedToClassException;
import pl.gornik.exceptions.InvalidDataException;

import java.util.ArrayList;
import java.util.List;

public class SchoolClass {
    private String className; // Zmieniono z classNumber na className

    private List<Student> students;
    private List<Worker> teachers;

    public SchoolClass(String className, List<Student> students, List<Worker> teachers) {
        if (className == null || className.isEmpty()) {
            throw new InvalidDataException("Nazwa klasy nie może być pusta.");
        }
        this.className = className;

        this.students = students != null ? students : new ArrayList<>();
        this.teachers = teachers != null ? teachers : new ArrayList<>();
    }



    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
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

    public void addStudent(Student student) throws PersonIsAlreadyDefinedToClassException {
        if (students.contains(student)) {
            throw new PersonIsAlreadyDefinedToClassException("Uczeń " + student.getName() + " " + student.getSurname() + " jest już przypisany do tej klasy.");
        }
        students.add(student);
    }

    public void addTeacher(Worker teacher) throws PersonIsAlreadyDefinedToClassException {
        if (teachers.contains(teacher)) {
            throw new PersonIsAlreadyDefinedToClassException("Nauczyciel " + teacher.getName() + " " + teacher.getSurname() + " jest już przypisany do tej klasy.");
        }
        teachers.add(teacher);
    }

}
