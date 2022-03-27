package POJO;

public class UserRoles implements Poco{
    public int id;
    public String roleName;

    public UserRoles(){}

    public UserRoles(int id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "UserRoles{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
