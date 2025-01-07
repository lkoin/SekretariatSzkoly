package pl.gornik;

import pl.gornik.controller.ViewController;
import pl.gornik.persons.Graduate;
import pl.gornik.persons.Person;
import pl.gornik.persons.PersonsInitialize;
import pl.gornik.persons.SchoolClass;

import java.util.ArrayList;
import java.util.List;

import static pl.gornik.persons.PersonsInitialize.*;

public class Main {
    public static void main(String[] args) {

        //Hasło do panelu zarządzania dla osoby z uprawnieniami to login: admin hasło: admin
        //Hasło do panelu zarządzania dla ucznia bez uprawnieniami to login: uczen hasło: uczen


        List<Person> persons = new ArrayList<>();
        List<SchoolClass> schoolClasses = new ArrayList<>();
        List<Graduate> graduates = new ArrayList<>();

        initializeClasses(schoolClasses);
        initializeTeachers(persons);
        PersonsInitialize.assignTeachersToClasses(persons, schoolClasses);
        initializeStudents(persons, schoolClasses);
        initializeGraduates(graduates);

        ViewController viewController = new ViewController(persons, schoolClasses,graduates);
        viewController.displayLoginMenu();
    }










}
