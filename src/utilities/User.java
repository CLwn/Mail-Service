package utilities;

public class User {
    private String username, name;
    private int yearOfBirth;
    private Mailbox box;

    public User(String username, String name, int yearOfBirth, Mailbox box) {
        this.username = username;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.box = box;
    }

    public Mailbox getBox() {
        return box;
    }

    public void setBox(Mailbox box) {
        this.box = box;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
