package structure;

/**
 * Class User
 * @author Marc García
 * @version 1.0
 */
public class User {
    private String username, name;
    private int yearOfBirth;
    private Mailbox box;

    /**
     * Constructor with 4 parameters
     * @param username username of user
     * @param name name of user
     * @param yearOfBirth year of birth
     * @param box mailbox object
     */
    public User(String username, String name, int yearOfBirth, Mailbox box) {
        this.username = username;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.box = box;
    }

    /**
     * Method to get a mail box
     * @return mailbox
     */
    public Mailbox getBox() {
        return box;
    }

    /**
     * Method to get username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Method to modify usernames
     * @param username username of user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Method to get a name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Method to get a year of birth
     * @return year
     */
    public int getYearOfBirth() {
        return yearOfBirth;
    }
}
