package POJO;

public class Tickets implements Poco{
    public int id;
    public int flightId;
    public int customerId;

    public Tickets(int id, int flightId, int customerId) {
        this.id = id;
        this.flightId = flightId;
        this.customerId = customerId;
    }

    public Tickets(){}

    @Override
    public String toString() {
        return "Tickets{" +
                "id=" + id +
                ", flightId=" + flightId +
                ", customerId=" + customerId +
                '}';
    }
}
