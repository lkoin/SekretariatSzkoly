package pl.gornik.controller;

import pl.gornik.exceptions.AddingToSchoolClassException;
import pl.gornik.exceptions.InvalidDataException;
import pl.gornik.persons.*;

import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ViewController {

        private final List<Person> persons;
        private final List<SchoolClass> schoolClasses;
        private final List<Graduate> graduates;

    public ViewController(List<Person> persons, List<SchoolClass> schoolClasses, List<Graduate> graduates) {
        this.persons = persons;
        this.schoolClasses = schoolClasses;
        this.graduates = graduates;
    }
    public void displayLoginMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Witaj w systemie zarządzania szkołą!");

        while (true) {
            System.out.println("\nMenu główne:");
            System.out.println("1. Zaloguj się");
            System.out.println("2. Wyjście");
            System.out.print("Wybierz opcję: ");

            int choice;

            try {
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1 -> login(scanner);
                        case 2 -> {
                            System.out.println("Do widzenia!");
                            return;
                        }
                        default -> System.out.println("Nieprawidłowy wybór. Wybierz opcję 1 lub 2.");
                    }
                } else {
                    System.out.println("Błąd: Wprowadź liczbę (1 lub 2).");
                    scanner.nextLine();
                }
            } catch (Exception e) {
                System.out.println("Wystąpił nieoczekiwany błąd. Spróbuj ponownie.");
                scanner.nextLine();
            }
        }
    }
    private void login(Scanner scanner) {
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Hasło: ");
        String password = scanner.nextLine();

        Person user = persons.stream()
                .filter(person -> person.getLogin().equals(login) && person.getPassword().equals(password))
                .findFirst()
                .orElse(null);

        if (user == null) {
            System.out.println("Nieprawidłowe dane logowania.");
        } else if (user instanceof Worker worker && worker.isHasManagementAccess()) {
            displayAdminMenu(scanner, worker);
        } else {
            displayUserMenu(user);
        }
    }

    private void displayAdminMenu(Scanner scanner, Worker admin) {
        System.out.println("\nWitaj w panelu administracyjnym, " + admin.getName() + "!");
        while (true) {
            System.out.println("\nPanel administracyjny:");
            System.out.println("1. Wyświetl wszystkie osoby");
            System.out.println("2. Dodaj nową osobę");
            System.out.println("3. Przypisz ucznia do klasy");
            System.out.println("4. Sortuj listy");
            System.out.println("5. Szukaj osoby");
            System.out.println("6. Wyloguj się");
            System.out.print("Wybierz opcję: ");

            int choice = 0;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
            scanner.nextLine();

            switch (choice) {
                case 1 -> displayAllPersons(scanner);
                case 2 -> addNewPerson(scanner);
                case 3 -> assignStudentToClass(scanner);
                case 4 -> sortLists(scanner);
                case 5 -> searchPerson(scanner);
                case 6 -> {
                    System.out.println("Wylogowano.");
                    return;
                }
                default -> System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        }
    }

    private void displayUserMenu(Person user) {
        System.out.println("\nWitaj, " + user.getSurname() + "!");
        System.out.println("Twoje informacje osobiste:");
        user.showInfo();
    }

    private void displayAllPersons(Scanner scanner) {
        System.out.println("\nOpcje wyświetlania:");
        System.out.println("1. Wszyscy pracownicy");
        System.out.println("2. Wszyscy uczniowie");
        System.out.println("3. Uczniowie przypisani do danej klasy");
        System.out.println("4. Pracownicy przypisani do danej klasy");
        System.out.println("5. Wyswietl liste absolwentow szkoły");
        System.out.print("Wybierz opcję: ");

        int choice = 0;
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
        }
        scanner.nextLine();

        switch (choice) {
            case 1 -> {
                System.out.println("\nLista wszystkich pracowników:");
                persons.stream()
                        .filter(person -> person instanceof Worker)
                        .forEach(Person::showInfo);
            }
            case 2 -> {
                System.out.println("\nLista wszystkich uczniów:");
                persons.stream()
                        .filter(person -> person instanceof Student)
                        .forEach(Person::showInfo);
            }
            case 3 -> {
                    System.out.print("Podaj nazwę klasy: ");
                    String className = scanner.nextLine();
                    SchoolClass targetClass = schoolClasses.stream()
                            .filter(schoolClass -> schoolClass.getName().equalsIgnoreCase(className))
                            .findFirst()
                            .orElse(null);

                    if (targetClass == null) {
                        System.out.println("Nie znaleziono klasy o podanej nazwie.");
                    } else {
                        System.out.println("\nLista uczniów w klasie " + className + ":");
                        if (targetClass.getStudents() != null && !targetClass.getStudents().isEmpty()) {
                            targetClass.getStudents().forEach(Student::showInfo);
                        } else {
                            System.out.println("Brak uczniów w tej klasie.");
                        }
                    }
                }

            case 4 -> {
                System.out.print("Podaj nazwę klasy: ");
                String className = scanner.nextLine();
                SchoolClass targetClass = schoolClasses.stream()
                        .filter(schoolClass -> schoolClass.getName().equalsIgnoreCase(className))
                        .findFirst()
                        .orElse(null);

                if (targetClass == null) {
                    System.out.println("Nie znaleziono klasy o podanej nazwie.");
                } else {
                    System.out.println("\nLista pracowników przypisanych do klasy " + className + ":");
                    targetClass.getTeachers().forEach(Worker::showInfo);
                }
            }
            case 5 -> {
                System.out.println("\nLista wszystkich absolwentów:");
                if (graduates != null && !graduates.isEmpty()) {
                    graduates.forEach(Graduate::showInfo);
                } else {
                    System.out.println("Brak absolwentów w bazie danych.");
                }
            }

        }
    }

    private void addNewPerson(Scanner scanner) {
        System.out.println("\nDodawanie nowej osoby:");
        try {

            System.out.print("Podaj imię: ");
            String firstName = scanner.nextLine();
            System.out.print("Podaj nazwisko: ");
            String lastName = scanner.nextLine();
            System.out.print("Podaj login: ");
            String login = scanner.nextLine();
            System.out.print("Podaj hasło: ");
            String password = scanner.nextLine();
            System.out.print("Podaj PESEL: ");
            String pesel = scanner.nextLine();
            System.out.print("Podaj datę urodzenia (yyyy-mm-dd): ");
            String birthDate = scanner.nextLine();
            System.out.print("Podaj numer telefonu: ");
            String phoneNumber = scanner.nextLine();
            System.out.print("Podaj adres: ");
            String address = scanner.nextLine();

            System.out.println("Czy to nauczyciel (N) czy uczeń (U)? (N/U): ");
            String type = scanner.nextLine().toUpperCase();

            if (type.equals("N")) {
                System.out.print("Podaj stanowisko: ");
                String position = scanner.nextLine();
                System.out.print("Podaj przedmiot wykładany: ");
                String subject = scanner.nextLine();
                System.out.print("Czy ma dostęp do panelu zarządzania? (true/false): ");
                boolean hasManagementAccess = scanner.nextBoolean();
                scanner.nextLine();
                persons.add(new Worker(login, password, firstName, lastName, pesel, birthDate, phoneNumber, address, position, subject, hasManagementAccess));
                System.out.println("Dodano nauczyciela.");
            } else if (type.equals("U")) {

                System.out.print("Podaj klasę: ");
                String className = scanner.nextLine();
                SchoolClass studentClass = findClassByName(className);

                if (studentClass == null) {
                    System.out.println("Nie znaleziono klasy o podanej nazwie. Tworzę nową klasę.");
                    studentClass = new SchoolClass(className);
                    schoolClasses.add(studentClass);
                }

                System.out.print("Podaj rozszerzenia (wpisz po przecinku): ");
                String[] subjects = scanner.nextLine().split(",");
                List<String> advancedSubjects = List.of(subjects);

                System.out.print("Podaj rodziców (wpisz po przecinku): ");
                String[] parentsArray = scanner.nextLine().split(",");
                List<String> parents = List.of(parentsArray);

                Student newStudent = new Student(login, password, firstName, lastName, pesel, birthDate, phoneNumber, address, studentClass, advancedSubjects, parents);
                persons.add(newStudent);
                studentClass.addStudent(newStudent);
                System.out.println("Dodano ucznia i przypisano do klasy " + className);
            } else {
                System.out.println("Nieznany typ osoby. Użyj 'N' dla nauczyciela lub 'U' dla ucznia.");
            }
        } catch (InvalidDataException e) {
            System.err.println("Błąd: " + e.getMessage());
        } catch (AddingToSchoolClassException e) {
            System.err.println("Błąd podczas przypisywania do klasy: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Wystąpił nieoczekiwany błąd: " + e.getMessage());
        }
    }


    public void assignStudentToClass(Scanner scanner) {
        System.out.println("Przypisywanie ucznia do klasy:");
        System.out.print("Podaj PESEL ucznia: ");
        String pesel = scanner.nextLine();

        Student student = findStudentByPesel(pesel);
        if (student != null) {
            System.out.println("Dostępne klasy:");
            for (SchoolClass schoolClass : schoolClasses) {
                System.out.println("Klasa: " + schoolClass.getName());
            }

            System.out.print("Wprowadź nazwę klasy: ");
            String className = scanner.nextLine().trim().toUpperCase();
            SchoolClass selectedClass = findClassByName(className);

            if (selectedClass != null) {
                try {
                    selectedClass.addStudent(student);
                    System.out.println("Uczeń został przypisany do klasy " + selectedClass.getName() + ".");
                } catch (AddingToSchoolClassException e) {
                    System.err.println("Błąd: " + e.getMessage());
                }
            } else {
                System.out.println("Nie znaleziono klasy o nazwie: " + className);
            }
        } else {
            System.out.println("Nie znaleziono ucznia o podanym PESEL.");
        }
    }

    private void sortLists(Scanner scanner) {
        System.out.println("\nSortowanie list:");
        System.out.println("1. Sortuj osoby alfabetycznie (A-Z)");
        System.out.println("2. Sortuj osoby alfabetycznie (Z-A)");
        System.out.print("Wybierz opcję: ");

        int choice = 0;
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
        }
        scanner.nextLine();

        if (choice == 1) {
            persons.sort(Comparator.comparing(Person::getSurname));
            System.out.println("Lista została posortowana (A-Z).");
        } else if (choice == 2) {
            persons.sort(Comparator.comparing(Person::getSurname).reversed());
            System.out.println("Lista została posortowana (Z-A).");
        } else {
            System.out.println("Nieprawidłowy wybór.");
        }
    }

    private void searchPerson(Scanner scanner) {
        System.out.println("\nSzukaj osoby:");
        System.out.print("Podaj login lub nazwisko: ");
        String query = scanner.nextLine();

        persons.stream()
                .filter(person -> person.getLogin().equalsIgnoreCase(query) || person.getSurname().equalsIgnoreCase(query))
                .forEach(Person::showInfo);
    }



    private Student findStudentByPesel(String pesel) {
        return persons.stream()
                .filter(person -> person instanceof Student)
                .map(person -> (Student) person)
                .filter(student -> student.getPesel().equals(pesel))
                .findFirst()
                .orElse(null);
    }

    private SchoolClass findClassByName(String className) {
        for (SchoolClass schoolClass : schoolClasses) {
            if (schoolClass.getName().equalsIgnoreCase(className)) {
                return schoolClass;
            }
        }
        return null;
    }
}

