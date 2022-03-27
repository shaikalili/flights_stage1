package Facades;

import DAO.AirlineCompanyDao;
import DAO.CountryDao;
import DAO.FlightsDao;
import DAO.UsersDao;
import POJO.*;

import java.util.List;

public abstract class FacadeBase {
    FlightsDao flightsDao=new FlightsDao();
    AirlineCompanyDao airlineCompanyDao=new AirlineCompanyDao();
    CountryDao countryDao=new CountryDao();
    UsersDao usersDao=new UsersDao();

   public List<Poco> get_all_flights() { return flightsDao.getAll(); }
   public Flight get_flight_by_id(int id) { return (Flight) flightsDao.getById(id); }

   public List<Flight> get_flights_by_parameters(int origin_country_id,int destination_country_id,String date)
   { return flightsDao.get_flights_by_parameters(origin_country_id,destination_country_id,date); }

    public List<Poco> get_all_airlines(){ return airlineCompanyDao.getAll(); }
    public AirlineCompanies get_airline_by_id(int id){return (AirlineCompanies) airlineCompanyDao.getById(id);}
    public AirlineCompanies get_airline_by_name(String name){return (AirlineCompanies) airlineCompanyDao.getAirlinesByName(name);}
    public List<Poco> get_all_countries(){return countryDao.getAll();}
    public Country get_country_by_id(int id){return (Country) countryDao.getById(id);}
    public void create_new_user(Users users){usersDao.Add(users);}



}
