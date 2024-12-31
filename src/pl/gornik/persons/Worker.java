package pl.gornik.persons;

import pl.gornik.exceptions.InvalidDataException;

public class Worker extends Person {
    private final String position;
    private final String subjectTaught;
    private final boolean hasManagementAccess;

    public Worker(String login, String password, String firstName, String lastName, String pesel, String birthDate, String phoneNumber, String address,
                  String position, String subjectTaught, boolean hasManagementAccess) {
        super(login, password, firstName, lastName, pesel, birthDate, phoneNumber, address);
        if (position == null || position.isEmpty()) {
            throw new InvalidDataException("Stanowisko nie moze byc puste");
        }
        this.position = position;
        this.subjectTaught = subjectTaught;
        this.hasManagementAccess = hasManagementAccess;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Stanowisko: " + position);
        System.out.println("Przedmiot wykladany: " + subjectTaught);
        System.out.println("Dostep do panelu zarządzania: " + (hasManagementAccess ? "Tak" : "Nie"));
        System.out.println("---------------------------");
    }


    public boolean isHasManagementAccess() {
        return hasManagementAccess;
    }


}
