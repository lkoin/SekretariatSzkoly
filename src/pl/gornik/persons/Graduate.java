package pl.gornik.persons;

public class Graduate extends Person {
    private final String graduationYear;

    public Graduate(String login, String password, String firstName, String lastName, String pesel, String birthDate, String phoneNumber, String address, String graduationYear) {
        super(login, password, firstName, lastName, pesel, birthDate, phoneNumber, address);
        this.graduationYear = graduationYear;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Absolwent: Rok ukończenia szkoły: " + graduationYear);
        System.out.println("--------------------------");
    }
}