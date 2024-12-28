package pl.gornik;

import pl.gornik.controller.ViewController;
import pl.gornik.exceptions.InvalidDataException;
import pl.gornik.exceptions.AddingToSchoolClassException;
import pl.gornik.persons.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //Hasło do panelu zarządzania dla osoby z uprawnieniami to login: admin hasło: admin
        //Hasło do panelu zarządzania dla ucznia bez uprawnieniami to login: uczen hasło: uczen


        List<Person> persons = new ArrayList<>();
        List<SchoolClass> schoolClasses = new ArrayList<>();
        List<Graduate> graduates = new ArrayList<>();

        initializeClasses(schoolClasses);
        initializeTeachers(persons);
        initializeStudents(persons, schoolClasses);
        initializeGraduates(graduates);

        ViewController viewController = new ViewController(persons, schoolClasses,graduates);
        viewController.displayLoginMenu();
    }









    private static void initializeClasses(List<SchoolClass> schoolClasses) {
        String[] classNames = {"1A", "1B", "1C", "2A", "2B", "2C", "3A", "3B", "3C", "3D", "4A", "4B", "4C", "5A", "5B", "5C"};
        for (String className : classNames) {
            schoolClasses.add(new SchoolClass(className));
        }
    }

    private static void initializeGraduates(List<Graduate> graduates) {
        try {
            Graduate graduate = new Graduate("o.kaminska", "gradpass3", "Oliwia", "Kamińska", "01020334567", "2001-02-03", "777-123-456", "ul. Sienkiewicza 15", "2021");
            graduates.add(graduate);
        } catch (InvalidDataException e) {
            System.err.println("Błąd przy dodawaniu absolwenta: " + e.getMessage());
        }

        try {
            Graduate graduate = new Graduate("f.adamski", "gradpass4", "Filip", "Adamski", "01040556789", "2001-04-05", "777-234-567", "ul. Słowackiego 7", "2021");
            graduates.add(graduate);
        } catch (InvalidDataException e) {
            System.err.println("Błąd przy dodawaniu absolwenta: " + e.getMessage());
        }

        try {
            Graduate graduate = new Graduate("j.pawlak", "gradpass5", "Julia", "Pawlak", "01060778901", "2001-06-07", "777-345-678", "ul. Kopernika 11", "2021");
            graduates.add(graduate);
        } catch (InvalidDataException e) {
            System.err.println("Błąd przy dodawaniu absolwenta: " + e.getMessage());
        }

        try {
            Graduate graduate = new Graduate("b.jaworski", "gradpass6", "Bartosz", "Jaworski", "01081990123", "2001-08-19", "777-456-789", "ul. Reymonta 4", "2021");
            graduates.add(graduate);
        } catch (InvalidDataException e) {
            System.err.println("Błąd przy dodawaniu absolwenta: " + e.getMessage());
        }
    }

    private static void initializeStudents(List<Person> persons, List<SchoolClass> schoolClasses) {
        try {
            Student student = new Student("m.lewandowski", "stud345", "Mateusz", "Lewandowski", "07030323456", "2007-03-03", "666-123-456", "ul. Długa 21", schoolClasses.get(0),
                    List.of("Fizyka", "Chemia"), List.of("Magdalena Lewandowska", "Tomasz Lewandowski"));
            persons.add(student);
            schoolClasses.get(0).addStudent(student);
        } catch (InvalidDataException | AddingToSchoolClassException e) {
            System.err.println("Błąd przy dodawaniu ucznia: " + e.getMessage());
        }

        try {
            Student student = new Student("z.szymanska", "stud456", "Zofia", "Szymańska", "07050445678", "2007-05-04", "666-234-567", "ul. Krótka 5", schoolClasses.get(1),
                    List.of("Polski", "Angielski"), List.of("Barbara Szymańska", "Marek Szymański"));
            persons.add(student);
            schoolClasses.get(1).addStudent(student);
        } catch (InvalidDataException | AddingToSchoolClassException e) {
            System.err.println("Błąd przy dodawaniu ucznia: " + e.getMessage());
        }

        try {
            Student student = new Student("uczen", "uczen", "Kacper", "Woźniak", "07081556789", "2007-08-15", "666-345-678", "ul. Mickiewicza 9", schoolClasses.get(0),
                    List.of("Geografia", "Historia"), List.of("Joanna Woźniak", "Robert Woźniak"));
            persons.add(student);
            schoolClasses.get(0).addStudent(student);
        } catch (InvalidDataException | AddingToSchoolClassException e) {
            System.err.println("Błąd przy dodawaniu ucznia: " + e.getMessage());
        }



        try {
            Student student = new Student("a.nowicki", "stud678", "Antoni", "Nowicki", "07100767890", "2007-10-07", "666-456-789", "ul. Prusa 3", schoolClasses.get(1),
                    List.of("Matematyka", "Fizyka"), List.of("Katarzyna Nowicka", "Paweł Nowicki"));
            persons.add(student);
            schoolClasses.get(1).addStudent(student);
        } catch (InvalidDataException | AddingToSchoolClassException e) {
            System.err.println("Błąd przy dodawaniu ucznia: " + e.getMessage());
        }

        try {
            Student student = new Student("h.mazurek", "stud789", "Hanna", "Mazurek", "07112878901", "2007-11-28", "666-567-890", "ul. Wyspiańskiego 6", schoolClasses.get(0),
                    List.of("Biologia", "Chemia"), List.of("Agnieszka Mazurek", "Krzysztof Mazurek"));
            persons.add(student);
            schoolClasses.get(0).addStudent(student);
        } catch (InvalidDataException | AddingToSchoolClassException e) {
            System.err.println("Błąd przy dodawaniu ucznia: " + e.getMessage());
        }

        try {
            Student student = new Student("j.adamczyk", "stud890", "Jan", "Adamczyk", "07020989012", "2007-02-09", "666-678-901", "ul. Asnyka 14", schoolClasses.get(1),
                    List.of("Polski", "Historia"), List.of("Monika Adamczyk", "Andrzej Adamczyk"));
            persons.add(student);
            schoolClasses.get(1).addStudent(student);
        } catch (InvalidDataException | AddingToSchoolClassException e) {
            System.err.println("Błąd przy dodawaniu ucznia: " + e.getMessage());
        }

        try {
            Student student = new Student("o.witkowski", "stud901", "Oliwier", "Witkowski", "07040590123", "2007-04-05", "666-789-012", "ul. Norwida 8", schoolClasses.get(0),
                    List.of("Geografia", "Angielski"), List.of("Dorota Witkowska", "Marcin Witkowski"));
            persons.add(student);
            schoolClasses.get(0).addStudent(student);
        } catch (InvalidDataException | AddingToSchoolClassException e) {
            System.err.println("Błąd przy dodawaniu ucznia: " + e.getMessage());
        }

        try {
            Student student = new Student("l.sikora", "stud012", "Laura", "Sikora", "07060601234", "2007-06-06", "666-890-123", "ul. Orzeszkowej 17", schoolClasses.get(1),
                    List.of("Matematyka", "Informatyka"), List.of("Elżbieta Sikora", "Adam Sikora"));
            persons.add(student);
            schoolClasses.get(1).addStudent(student);
        } catch (InvalidDataException | AddingToSchoolClassException e) {
            System.err.println("Błąd przy dodawaniu ucznia: " + e.getMessage());
        }

        try {
            Student student = new Student("m.grabowski", "stud123", "Marcel", "Grabowski", "07091212345", "2007-09-12", "666-901-234", "ul. Konopnickiej 22", schoolClasses.get(0),
                    List.of("Fizyka", "Geografia"), List.of("Anna Grabowska", "Piotr Grabowski"));
            persons.add(student);
            schoolClasses.get(0).addStudent(student);
        } catch (InvalidDataException | AddingToSchoolClassException e) {
            System.err.println("Błąd przy dodawaniu ucznia: " + e.getMessage());
        }

        try {
            Student student = new Student("n.pawlowski", "stud234", "Nina", "Pawłowska", "07111523456", "2007-11-15", "666-012-345", "ul. Żeromskiego 13", schoolClasses.get(1),
                    List.of("Biologia", "Polski"), List.of("Marta Pawłowska", "Jan Pawłowski"));
            persons.add(student);
            schoolClasses.get(1).addStudent(student);
        } catch (InvalidDataException | AddingToSchoolClassException e) {
            System.err.println("Błąd przy dodawaniu ucznia: " + e.getMessage());
        }
    }

    private static void initializeTeachers(List<Person> persons) {
        try {
            Worker worker = new Worker("admin", "admin", "Admin", "Admin", "12345678901", "1985-07-15", "555-123-789", "ul. Lipowa 12", "Nauczyciel", "Fizyka", true);
            persons.add(worker);
        } catch (InvalidDataException e) {
            System.err.println("Błąd przy dodawaniu nauczyciela: " + e.getMessage());
        }

        try {
            Worker worker = new Worker("a.wojcik", "pass456", "Adam", "Wójcik", "85071523456", "1985-07-15", "555-123-789", "ul. Lipowa 12", "Nauczyciel", "Fizyka", true);
            persons.add(worker);
        } catch (InvalidDataException e) {
            System.err.println("Błąd przy dodawaniu nauczyciela: " + e.getMessage());
        }

        try {
            Worker worker = new Worker("e.dabrowska", "teach789", "Ewa", "Dąbrowska", "88092834567", "1988-09-28", "555-234-890", "ul. Kwiatowa 3", "Nauczyciel", "Polski", true);
            persons.add(worker);
        } catch (InvalidDataException e) {
            System.err.println("Błąd przy dodawaniu nauczyciela: " + e.getMessage());
        }

        try {
            Worker worker = new Worker("p.zielinski", "edu123", "Piotr", "Zieliński", "79031645678", "1979-03-16", "555-345-901", "ul. Polna 8", "Nauczyciel", "Geografia", true);
            persons.add(worker);
        } catch (InvalidDataException e) {
            System.err.println("Błąd przy dodawaniu nauczyciela: " + e.getMessage());
        }

        try {
            Worker worker = new Worker("m.kowalczyk", "edu456", "Maria", "Kowalczyk", "82051267890", "1982-05-12", "555-456-012", "ul. Szkolna 15", "Nauczyciel", "Chemia", true);
            persons.add(worker);
        } catch (InvalidDataException e) {
            System.err.println("Błąd przy dodawaniu nauczyciela: " + e.getMessage());
        }
    }
}
