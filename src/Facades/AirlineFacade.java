package Facades;

import POJO.AirlineCompanies;
import POJO.Flight;

import java.util.List;

public class AirlineFacade extends AnonymousFacade implements Facade{
    private LoginToken loginToken;
    private AirlineCompanies airlineCompanies;

    public AirlineFacade(LoginToken loginToken) {
        this.setLoginToken(loginToken);
        this.setAirlineCompanies();
    }
    public LoginToken getLoginToken() {
        return loginToken;

    }

    public void setLoginToken(LoginToken loginToken) {
        this.loginToken = loginToken;
    }

    public AirlineCompanies getAirlineCompanies() {
        return airlineCompanies;
    }

    public void setAirlineCompanies() {
        this.airlineCompanies = airlineCompanyDao.get_airline_by_username(loginToken.name);
    }

    public List<Flight> get_my_flights(){
        return flightsDao.get_flights_by_airline_id(this.getAirlineCompanies().id);
    }

    public void Update_airline(AirlineCompanies airlineCompanies){
        if(airlineCompanies.id==this.getAirlineCompanies().id) {
            airlineCompanyDao.Update(airlineCompanies, this.getAirlineCompanies().id);
        }
        else
            System.out.println("cant update to an other airline company");
    }

    public void add_flight(Flight flight){
        if(flight.airlineCompanyId==this.getAirlineCompanies().id){
            flightsDao.Add(flight);
        }
        else
            System.out.println("cant add flight to an other airline company");
    }
    public void update_flight(Flight flight){
        if(flight.airlineCompanyId==this.getAirlineCompanies().id){
            flightsDao.Update(flight, flight.id);
        }
        else
            System.out.println("cant update flight to an other airline company");
    }
    public void remove_flight(Flight flight){
        if(flight.airlineCompanyId==this.getAirlineCompanies().id){
            flightsDao.Delete(flight.id);
        }
        else
            System.out.println("cant delete flight to an other airline company");
    }
}
