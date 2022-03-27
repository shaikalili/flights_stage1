package POJO;

public class Administrator implements Poco{
    public int id;
    public String firstName;
    public String lastName;
    public int userId;

    public Administrator (){}

    public Administrator(int id, String firstName, String lastName, int userId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userId=" + userId +
                '}';
    }
}
