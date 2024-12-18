package pl.gornik.controller;
import pl.gornik.exceptions.InvalidDataException;
import pl.gornik.persons.*;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ViewController {
    private List<Person> persons;
    private List<SchoolClass> schoolClasses;

    public ViewController(List<Person> persons, List<SchoolClass> schoolClasses) {
        this.persons = persons;
        this.schoolClasses = schoolClasses;
    }

    public void displayLoginMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Witaj w systemie zarządzania szkołą!");

        while (true) {
            System.out.println("\nMenu główne:");
            System.out.println("1. Zaloguj się");
            System.out.println("2. Wyjście");
            System.out.print("Wybierz opcję: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> login(scanner);
                case 2 -> {
                    System.out.println("Do widzenia!");
                    return;
                }
                default -> System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
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

            int choice = scanner.nextInt();
            scanner.nextLine(); // Konsumpcja znaku nowej linii

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
        System.out.print("Wybierz opcję: ");

        int choice = scanner.nextInt();
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
                        .filter(schoolClass -> schoolClass.getClassName().equalsIgnoreCase(className))
                        .findFirst()
                        .orElse(null);

                if (targetClass == null) {
                    System.out.println("Nie znaleziono klasy o podanej nazwie.");
                } else {
                    System.out.println("\nLista uczniów w klasie " + className + ":");
                    targetClass.getStudents().forEach(Student::showInfo);
                }
            }
            case 4 -> {
                System.out.print("Podaj nazwę klasy: ");
                String className = scanner.nextLine();
                SchoolClass targetClass = schoolClasses.stream()
                        .filter(schoolClass -> schoolClass.getClassName().equalsIgnoreCase(className))
                        .findFirst()
                        .orElse(null);

                if (targetClass == null) {
                    System.out.println("Nie znaleziono klasy o podanej nazwie.");
                } else {
                    System.out.println("\nLista pracowników przypisanych do klasy " + className + ":");
                    targetClass.getTeachers().forEach(Worker::showInfo);
                }
            }
            default -> System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
        }
    }

    private void addNewPerson(Scanner scanner) {
        try {
            System.out.println("\nDodawanie nowej osoby:");
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
                String studentClass = scanner.nextLine();
                System.out.println("Podaj rozszerzenia (wpisz po przecinku): ");
                String[] subjects = scanner.nextLine().split(",");
                List<String> advancedSubjects = List.of(subjects);
                System.out.println("Podaj rodziców (wpisz po przecinku): ");
                String[] parentsArray = scanner.nextLine().split(",");
                List<String> parents = List.of(parentsArray);
                persons.add(new Student(login, password, firstName, lastName, pesel, birthDate, phoneNumber, address, studentClass, advancedSubjects, parents));
                System.out.println("Dodano ucznia.");
            } else {
                System.out.println("Nieprawidłowy typ.");
            }
        } catch (InvalidDataException e) {
            System.out.println("Błąd: " + e.getMessage());
        }
    }

    private void assignStudentToClass(Scanner scanner) {
        try {
            System.out.println("\nPrzypisywanie ucznia do klasy:");
            System.out.print("Podaj PESEL ucznia: ");
            String pesel = scanner.nextLine();

            Student student = (Student) persons.stream()
                    .filter(person -> person instanceof Student && person.getPesel().equals(pesel))
                    .findFirst()
                    .orElse(null);

            if (student == null) {
                System.out.println("Nie znaleziono ucznia z podanym PESEL-em.");
                return;
            }

            System.out.println("Dostępne klasy:");
            schoolClasses.forEach(schoolClass ->
                    System.out.println("Klasa: " + schoolClass.getClassName())
            );


            System.out.print("Wprowadź nazwę klasy: ");
            String className = scanner.nextLine();

            SchoolClass targetClass = schoolClasses.stream()
                    .filter(schoolClass -> schoolClass.getClassName().equalsIgnoreCase(className))
                    .findFirst()
                    .orElse(null);

            if (targetClass == null) {
                System.out.println("Nie znaleziono klasy o podanej nazwie.");
            } else {
                targetClass.getStudents().add(student);
                System.out.println("Uczeń został przypisany do klasy " + className + ".");
            }
        } catch (Exception e) {
            System.out.println("Błąd: " + e.getMessage());
        }
    }


    private void sortLists(Scanner scanner) {
        System.out.println("\nSortowanie list:");
        System.out.println("1. Sortuj osoby alfabetycznie (A-Z)");
        System.out.println("2. Sortuj osoby alfabetycznie (Z-A)");
        System.out.print("Wybierz opcję: ");

        int choice = scanner.nextInt();
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


}
