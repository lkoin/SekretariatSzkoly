package pl.gornik;

import pl.gornik.controller.ViewController;
import pl.gornik.exceptions.InvalidDataException;
import pl.gornik.exceptions.PersonIsAlreadyDefinedToClassException;
import pl.gornik.persons.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Person> persons = new ArrayList<>();
        List<SchoolClass> schoolClasses = new ArrayList<>();


        try {
            Worker teacher1 = new Worker("teacher1", "password123", "Jan", "Kowalski", "12345678901", "1985-05-20", "123-456-789", "ul. Przykładowa 1", "Nauczyciel", "Matematyka", true);
            persons.add(teacher1);
        } catch (InvalidDataException e) {
            System.err.println("Błąd danych przy dodawaniu nauczyciela 1: " + e.getMessage());
        }

        try {
            Worker teacher2 = new Worker("teacher2", "password456", "Anna", "Nowak", "98765432101", "1990-06-15", "987-654-321", "ul. Przykładowa 2", "Nauczyciel", "Fizyka", false);
            persons.add(teacher2);
        } catch (InvalidDataException e) {
            System.err.println("Błąd danych przy dodawaniu nauczyciela 2: " + e.getMessage());
        }


        try {
            List<String> rozszerzenia1 = new ArrayList<>();
            rozszerzenia1.add("Matematyka");
            rozszerzenia1.add("Informatyka");
            List<String> rodzice1 = new ArrayList<>();
            rodzice1.add("Marek Kowalski");
            rodzice1.add("Elżbieta Kowalska");
            Student student1 = new Student("student1", "studentpass1", "Piotr", "Kowalski", "11223344556", "2006-08-30", "555-666-777", "ul. Przykładowa 3", "1A", rozszerzenia1, rodzice1);
            persons.add(student1);
        } catch (InvalidDataException e) {
            System.err.println("Błąd danych przy dodawaniu ucznia 1: " + e.getMessage());
        }

        try {
            List<String> rozszerzenia2 = new ArrayList<>();
            rozszerzenia2.add("Biologia");
            rozszerzenia2.add("Chemia");
            List<String> rodzice2 = new ArrayList<>();
            rodzice2.add("Jan Nowak");
            rodzice2.add("Maria Nowak");
            Student student2 = new Student("student2", "studentpass2", "Katarzyna", "Nowak", "22334455667", "2006-09-14", "888-999-000", "ul. Przykładowa 4", "2B", rozszerzenia2, rodzice2);
            persons.add(student2);
        } catch (InvalidDataException e) {
            System.err.println("Błąd danych przy dodawaniu ucznia 2: " + e.getMessage());
        }


        //----------------------------------------------------------------------------
        // Naprawa Błedow z przypisywaniem do klasy i z wyswietlaniem ucznia po klasie
        //----------------------------------------------------------------------------

        try {
            List<Student> students1A = new ArrayList<>();
            List<Worker> teachers1A = new ArrayList<>();
            SchoolClass class1A = new SchoolClass("1A",  students1A, teachers1A);
            schoolClasses.add(class1A);


            for (Person person : persons) {
                if (person instanceof Student) {
                    Student student = (Student) person;
                    if ("1A".equals(student.getStudentClass())) {
                        class1A.addStudent(student);
                    }
                }
            }


            for (Person person : persons) {
                if (person instanceof Worker) {
                    Worker teacher = (Worker) person;
                    class1A.addTeacher(teacher);
                }
            }

        } catch (PersonIsAlreadyDefinedToClassException e) {
            System.err.println("Błąd przy przypisywaniu osób do klasy: " + e.getMessage());
        }


        ViewController viewController = new ViewController(persons, schoolClasses);
        viewController.displayLoginMenu();
    }
}
