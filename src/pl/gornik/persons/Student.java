package pl.gornik.persons;

import pl.gornik.exceptions.InvalidDataException;
import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
    private String studentClass;
    private List<String> advancedSubjects;
    private List<String> parents;

    public Student(String login, String password, String firstName, String lastName, String pesel, String birthDate, String phoneNumber, String address,
                   String studentClass, List<String> advancedSubjects, List<String> parents) {
        super(login, password, firstName, lastName, pesel, birthDate, phoneNumber, address);
        if (studentClass == null || studentClass.isEmpty()) {
            throw new InvalidDataException("Klasa nie może być pusta.");
        }
        this.studentClass = studentClass;
        this.advancedSubjects = advancedSubjects != null ? advancedSubjects : new ArrayList<>();
        this.parents = parents != null ? parents : new ArrayList<>();
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Klasa: " + studentClass);
        System.out.println("Rozszerzenia: " + String.join(", ", advancedSubjects));
        System.out.println("Rodzice: " + String.join(", ", parents));
    }


    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public List<String> getAdvancedSubjects() {
        return advancedSubjects;
    }

    public void setAdvancedSubjects(List<String> advancedSubjects) {
        this.advancedSubjects = advancedSubjects;
    }

    public List<String> getParents() {
        return parents;
    }

    public void setParents(List<String> parents) {
        this.parents = parents;
    }
}
