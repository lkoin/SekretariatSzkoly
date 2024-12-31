package pl.gornik.persons;

import pl.gornik.exceptions.InvalidDataException;

public class Person {
    private final String login;
    private final String password;
    private final String name;
    private final String
            surname;
    private final String pesel;
    private final String
            birthDate;
    private final String phoneNumber;
    private final String adres;

    public Person(String login, String password, String name, String surname, String pesel, String birthDate, String phoneNumber, String adres) {
        if (name == null || name.isEmpty()) {
            throw new InvalidDataException("Imię nie może być puste.");
        }
        if (surname == null || surname.isEmpty()) {
            throw new InvalidDataException("Nazwisko nie może być puste.");
        }
        if (pesel == null || pesel.isEmpty() || !pesel.matches("\\d{11}")) {
            throw new InvalidDataException("PESEL musi być 11-cyfrowym ciągiem.");
        }
        if (birthDate == null || birthDate.isEmpty() || !birthDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new InvalidDataException("Data urodzenia musi być w formacie RRRR-MM-DD.");
        }

        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.adres = adres;
    }

    public void showInfo() {
        System.out.println("Imię: " + name);
        System.out.println("Nazwisko: " + surname);
        System.out.println("PESEL: " + pesel);
        System.out.println("Data urodzenia: " + birthDate);
        System.out.println("Numer telefonu: " + phoneNumber);
        System.out.println("Adres: " + adres);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPesel() {
        return pesel;
    }
}