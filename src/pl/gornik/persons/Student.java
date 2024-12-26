package pl.gornik.persons;

import pl.gornik.exceptions.InvalidDataException;
import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
    private SchoolClass schoolClass; // Zmieniamy typ z String na SchoolClass
    private List<String> advancedSubjects;
    private List<String> parents;

    public Student(String login, String password, String firstName, String lastName, String pesel, String birthDate, String phoneNumber, String address,
                   SchoolClass schoolClass, List<String> advancedSubjects, List<String> parents) {
        super(login, password, firstName, lastName, pesel, birthDate, phoneNumber, address);
        if (schoolClass == null) {
            throw new InvalidDataException("Klasa nie może być pusta.");
        }
        this.schoolClass = schoolClass;
        this.advancedSubjects = advancedSubjects != null ? advancedSubjects : new ArrayList<>();
        this.parents = parents != null ? parents : new ArrayList<>();
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Klasa: " + schoolClass.getName());
        System.out.println("Rozszerzenia: " + String.join(", ", advancedSubjects));
        System.out.println("Rodzice: " + String.join(", ", parents));
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }


}
