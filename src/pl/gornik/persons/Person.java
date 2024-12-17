package pl.gornik.persons;

import pl.gornik.exceptions.InvalidDataException;

public class Person {
    private String login;
    private String password;
    private String name;
    private String surname;
    private String pesel;
    private String birthDate;
    private String phoneNumber;
    private String adres;

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

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }
}