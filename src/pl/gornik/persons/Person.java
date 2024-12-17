package pl.gornik.persons;

import pl.gornik.exceptions.InvalidDataException;

import java.time.LocalDate;

public class Person {
    private String login;
    private String haslo;
    private String imie;
    private String nazwisko;
    private String pesel;
    private String dataUrodzenia;
    private String nrTelefonu;
    private String adres;

    public Person(String login, String haslo, String imie, String nazwisko, String pesel, String dataUrodzenia, String nrTelefonu, String adres) {
        if (imie == null || imie.isEmpty()) {
            throw new InvalidDataException("Imię nie może być puste.");
        }
        if (nazwisko == null || nazwisko.isEmpty()) {
            throw new InvalidDataException("Nazwisko nie może być puste.");
        }
        if (pesel == null || pesel.isEmpty() || !pesel.matches("\\d{11}")) {
            throw new InvalidDataException("PESEL musi być 11-cyfrowym ciągiem.");
        }
        if (dataUrodzenia == null || dataUrodzenia.isEmpty() || !dataUrodzenia.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new InvalidDataException("Data urodzenia musi być w formacie RRRR-MM-DD.");
        }

        this.login = login;
        this.haslo = haslo;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.dataUrodzenia = dataUrodzenia;
        this.nrTelefonu = nrTelefonu;
        this.adres = adres;
    }

    public void wyswietlInformacje() {
        System.out.println("Imię: " + imie);
        System.out.println("Nazwisko: " + nazwisko);
        System.out.println("PESEL: " + pesel);
        System.out.println("Data urodzenia: " + dataUrodzenia);
        System.out.println("Numer telefonu: " + nrTelefonu);
        System.out.println("Adres: " + adres);
    }
}