package pl.gornik.persons;
import pl.gornik.exceptions.InvalidDataException;
import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
    private String klasa;
    private List<String> rozszerzenia;
    private List<String> rodzice;

    public Student(String login, String haslo, String imie, String nazwisko, String pesel, String dataUrodzenia, String nrTelefonu, String adres,
                 String klasa, List<String> rozszerzenia, List<String> rodzice) {
        super(login, haslo, imie, nazwisko, pesel, dataUrodzenia, nrTelefonu, adres);
        if (klasa == null || klasa.isEmpty()) {
            throw new InvalidDataException("Klasa nie może być pusta.");
        }
        this.klasa = klasa;
        this.rozszerzenia = rozszerzenia != null ? rozszerzenia : new ArrayList<>();
        this.rodzice = rodzice != null ? rodzice : new ArrayList<>();
    }

    @Override
    public void wyswietlInformacje() {
        super.wyswietlInformacje();
        System.out.println("Klasa: " + klasa);
        System.out.println("Rozszerzenia: " + String.join(", ", rozszerzenia));
        System.out.println("Rodzice: " + String.join(", ", rodzice));
    }
}
