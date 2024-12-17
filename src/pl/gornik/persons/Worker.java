package pl.gornik.persons;

import pl.gornik.exceptions.InvalidDataException;

public class Worker extends Person {
    private String position;
    private String subjectTaught;
    private boolean hasManagementAccess;

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
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSubjectTaught() {
        return subjectTaught;
    }

    public void setSubjectTaught(String subjectTaught) {
        this.subjectTaught = subjectTaught;
    }

    public boolean isHasManagementAccess() {
        return hasManagementAccess;
    }

    public void setHasManagementAccess(boolean hasManagementAccess) {
        this.hasManagementAccess = hasManagementAccess;
    }


}
