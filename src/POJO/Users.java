package POJO;

public class Users implements Poco{
    public int id;
    public String userName;
    public String password;
    public String eMail;
    public int userRole;

    public Users(){}

    public Users(int id, String userName, String password, String eMail, int userRole) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.eMail = eMail;
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", eMail='" + eMail + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}
