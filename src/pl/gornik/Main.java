package pl.gornik;

import pl.gornik.controller.ViewController;
import pl.gornik.exceptions.InvalidDataException;
import pl.gornik.exceptions.AddingToSchoolClassException;
import pl.gornik.persons.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Person> persons = new ArrayList<>();
        List<SchoolClass> schoolClasses = new ArrayList<>();


        SchoolClass class1A = new SchoolClass("1A");
        SchoolClass class1B = new SchoolClass("1B");
        SchoolClass class1C = new SchoolClass("1C");
        SchoolClass class2A = new SchoolClass("2A");
        SchoolClass class2B = new SchoolClass("2B");
        SchoolClass class2C = new SchoolClass("2C");
        SchoolClass class3A = new SchoolClass("3A");
        SchoolClass class3B = new SchoolClass("3B");
        SchoolClass class3C = new SchoolClass("3C");
        SchoolClass class3D = new SchoolClass("3D");
        SchoolClass class4A = new SchoolClass("4A");
        SchoolClass class4B = new SchoolClass("4B");
        SchoolClass class4C = new SchoolClass("4C");
        SchoolClass class5A = new SchoolClass("5A");
        SchoolClass class5B = new SchoolClass("5B");
        SchoolClass class5C = new SchoolClass("5C");
        schoolClasses.add(class1A);
        schoolClasses.add(class1B);
        schoolClasses.add(class1C);
        schoolClasses.add(class2A);
        schoolClasses.add(class2C);
        schoolClasses.add(class3A);
        schoolClasses.add(class3B);
        schoolClasses.add(class3C);
        schoolClasses.add(class3D);
        schoolClasses.add(class4A);
        schoolClasses.add(class4B);
        schoolClasses.add(class4C);
        schoolClasses.add(class5A);
        schoolClasses.add(class5B);
        schoolClasses.add(class5C);

        try {
            Worker teacher1 = new Worker("teacher1", "password123", "Jan", "Kowalski", "12345678901", "1985-05-20", "123-456-789", "ul. Przykładowa 1", "Nauczyciel", "Matematyka", true);
            persons.add(teacher1);
        } catch (InvalidDataException e) {
            System.err.println("Błąd danych przy dodawaniu nauczyciela 1: " + e.getMessage());
        }


        try {
            List<String> rozszerzenia1 = new ArrayList<>();
            rozszerzenia1.add("Matematyka");
            rozszerzenia1.add("Informatyka");
            List<String> rodzice1 = new ArrayList<>();
            rodzice1.add("Marek Kowalski");
            rodzice1.add("Elżbieta Kowalska");
            Student student1 = new Student("student1", "studentpass1", "Piotr", "Kowalski", "11223344556", "2006-08-30", "555-666-777", "ul. Przykładowa 3", class1A, rozszerzenia1, rodzice1);
            persons.add(student1);
            class1A.addStudent(student1);
        } catch (InvalidDataException | AddingToSchoolClassException e) {
            System.err.println("Błąd danych przy dodawaniu ucznia 1: " + e.getMessage());
        }


        ViewController viewController = new ViewController(persons, schoolClasses);
        viewController.displayLoginMenu();
    }
}
