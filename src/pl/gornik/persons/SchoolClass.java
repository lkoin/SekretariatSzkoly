package pl.gornik.persons;

import pl.gornik.exceptions.AddingToSchoolClassException;
import java.util.List;
import java.util.ArrayList;

public class SchoolClass {
    private  String
            name;

    private  List<Student> students;
    private  List<Worker> teachers;

    public SchoolClass(String name) {
        this.name = name.toUpperCase();
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
    }

    public void addStudent(Student student) throws AddingToSchoolClassException {
        if (student == null) {
            throw new AddingToSchoolClassException("Student nie może być null");
        }
        if (!students.contains(student)) {
            students.add(student);
            student.setSchoolClass(this);
        } else {
            throw new AddingToSchoolClassException("Uczeń już jest przypisany do tej klasy");
        }
    }

    public void addTeacher(Worker teacher) throws AddingToSchoolClassException {
        if (teacher != null && !teachers.contains(teacher)) {
            teachers.add(teacher);
        } else if (teacher == null) {
            throw new AddingToSchoolClassException("Nauczyciel nie może być null");
        }
    }


    public String getName() {
        return name;
    }



    public List<Student> getStudents() {
        return students;
    }

    public List<Worker> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Worker> teachers) {
        this.teachers = teachers;
    }
}

