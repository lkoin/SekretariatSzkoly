package pl.gornik.controller;
import pl.gornik.persons.*;

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
            scanner.nextLine(); // Konsumpcja znaku nowej linii

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
                case 1 -> displayAllPersons();
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

    private void displayAllPersons() {
        System.out.println("\nLista wszystkich osób:");
        persons.forEach(Person::showInfo);
    }

    private void addNewPerson(Scanner scanner) {
        System.out.println("\nDodawanie nowej osoby:");
    }

    private void assignStudentToClass(Scanner scanner) {
        System.out.println("\nPrzypisywanie ucznia do klasy...");
    }

    private void sortLists(Scanner scanner) {
        System.out.println("\nSortowanie list...");
    }

    private void searchPerson(Scanner scanner) {
        System.out.println("\nSzukaj osoby...");
    }
}
