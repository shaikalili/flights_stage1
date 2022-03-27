package POJO;

public class AirlineCompanies implements Poco {
    public int id;
    public String name;
    public int countryId;
    public int userId;

    public AirlineCompanies(){}

    public AirlineCompanies(int id, String name, int countryId, int userId) {
        this.id = id;
        this.name = name;
        this.countryId = countryId;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "AirlineCompanies{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countryId=" + countryId +
                ", userId=" + userId +
                '}';
    }
}
