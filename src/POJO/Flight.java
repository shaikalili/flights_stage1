package POJO;

public class Flight implements Poco{
    public int id;
    public int airlineCompanyId;
    public int originCountryId;
    public int destinationCountryId;
    public int ticketsRemaining;
    public String departureTime;
    public String landingTime;

    public Flight(){}
    public Flight(int id, int airlineCompanyId, int originCountryId, int destinationCountryId,
                  int ticketsRemaining, String departureTime, String landingTime) {
        this.id = id;
        this.airlineCompanyId = airlineCompanyId;
        this.originCountryId = originCountryId;
        this.destinationCountryId = destinationCountryId;
        this.ticketsRemaining = ticketsRemaining;
        this.departureTime = departureTime;
        this.landingTime = landingTime;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", airlineCompanyId=" + airlineCompanyId +
                ", originCountryId=" + originCountryId +
                ", destinationCountryId=" + destinationCountryId +
                ", departureTime='" + departureTime + '\'' +
                ", landingTime='" + landingTime + '\'' +
                ", ticketsRemaining=" + ticketsRemaining +
                '}';
    }
}
