package pl.gornik.persons;

public class Graduate extends Person {
    public Graduate(String login, String password, String firstName, String lastName, String pesel, String birthDate, String phoneNumber, String address) {
        super(login, password, firstName, lastName, pesel, birthDate, phoneNumber, address);
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Absolwent: brak dodatkowych informacji.");
    }


}
