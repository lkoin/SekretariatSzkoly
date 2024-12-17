package pl.gornik.persons;

import java.util.List;

public class Class {
    private String profil;
    private int nrKlasy;
    private List<Student> listaUczniow;
    private List<Worker> listaNauczycieli;

    public Class(String profil, int nrKlasy, List<Student> listaUczniow, List<Worker> listaNauczycieli) {
        this.profil = profil;
        this.nrKlasy = nrKlasy;
        this.listaUczniow = listaUczniow;
        this.listaNauczycieli = listaNauczycieli;
    }

    public void wyswietlInformacje() {
        System.out.println("Profil: " + profil);
        System.out.println("Numer klasy: " + nrKlasy);
        System.out.println("Uczniowie: ");
        listaUczniow.forEach(Student::wyswietlInformacje);
        System.out.println("Nauczyciele: ");
        listaNauczycieli.forEach(Worker::wyswietlInformacje);
    }
}