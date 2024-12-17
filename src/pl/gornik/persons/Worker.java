package pl.gornik.persons;

import pl.gornik.exceptions.InvalidDataException;

public class Worker extends Person {
    private String stanowisko;
    private String przedmiotWykladany;
    private boolean dostepDoPaneluZarzadzania;

    public Worker(String login, String haslo, String imie, String nazwisko, String pesel, String dataUrodzenia, String nrTelefonu, String adres,
                     String stanowisko, String przedmiotWykladany, boolean dostepDoPaneluZarzadzania) {
        super(login, haslo, imie, nazwisko, pesel, dataUrodzenia, nrTelefonu, adres);
        if (stanowisko == null || stanowisko.isEmpty()) {
            throw new InvalidDataException("Stanowisko nie może być puste.");
        }
        this.stanowisko = stanowisko;
        this.przedmiotWykladany = przedmiotWykladany;
        this.dostepDoPaneluZarzadzania = dostepDoPaneluZarzadzania;
    }

    @Override
    public void wyswietlInformacje() {
        super.wyswietlInformacje();
        System.out.println("Stanowisko: " + stanowisko);
        System.out.println("Przedmiot wykładany: " + przedmiotWykladany);
        System.out.println("Dostęp do panelu zarządzania: " + (dostepDoPaneluZarzadzania ? "Tak" : "Nie"));
    }
}