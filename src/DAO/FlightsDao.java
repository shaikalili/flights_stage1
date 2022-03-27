package DAO;

import POJO.Customer;
import POJO.Flight;
import POJO.Poco;
import POJO.Tickets;
import sql.SqlStatment;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlightsDao extends SqlStatment implements Dao{

    List<Poco> FlightList = new ArrayList<>();
    @Override
    public List<Poco> getAll() {
        try {
            ResultSet result = super.stm.executeQuery
                    ("SELECT * FROM \"Flights\"");
            while (result.next()) {
                FlightList.add(new Flight
                        (result.getInt("id"),
                                result.getInt("Airline_Company_Id")
                                , result.getInt("Origin_Country_Id")
                                , result.getInt("Destination_Country_Id")
                                , result.getInt("Remaining_Tickets")
                                , result.getString("Depature_Time")
                                ,result.getString("Landing_Time")
                        ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return FlightList;
    }

    @Override
    public Poco getById(int id) {
        Flight flight=null;
        try {
            var result = super.stm.executeQuery
                    ("SELECT * FROM \"Flights\" WHERE id=" + id);
            result.next();
            flight=(new Flight
                    (result.getInt("id"),
                            result.getInt("Airline_Company_Id")
                            , result.getInt("Origin_Country_Id")
                            , result.getInt("Destination_Country_Id")
                            , result.getInt("Remaining_Tickets")
                            , result.getString("Depature_Time")
                            ,result.getString("Landing_Time")
                    ));


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return flight;
    }

    @Override
    public boolean Add(Poco poco) {
        if(poco instanceof Flight){
            Flight flight=(Flight) poco;
            if(isFlight(flight))
                return false;
            try{
                var result=super.stm.executeUpdate("INSERT INTO \"Flights\"(\"Airline_Company_Id\",\"Origin_Country_Id\"" +
                        ",\"Destination_Country_Id\"," +
                        "\"Remaining_Tickets\",\"Depature_Time\",\"Landing_Time\")" +
                        "VALUES " +
                        "("+flight.airlineCompanyId+","+flight.originCountryId+","+flight.destinationCountryId
                        +","+flight.ticketsRemaining+","+"'"+flight.departureTime+"',"+"'"+flight.landingTime+"')");

                return true;
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        System.out.println("this is not a flight");
        return false;
    }

    @Override
    public boolean Update(Poco poco, int id) {
        if(poco instanceof Flight) {
            Flight flight = (Flight) poco;
            if(isFlight(flight))
                return false;
            int x = 0;
            try {
                x = super.stm.executeUpdate("UPDATE \"Flights\" SET \"Airline_Company_Id\""+"="+flight.airlineCompanyId+","+
                        " \"Origin_Country_Id\"=" + flight.originCountryId + "" +
                        ",\"Destination_Country_Id\"=" + flight.destinationCountryId +
                        ", \"Remaining_Tickets\" =" + flight.ticketsRemaining +
                        " , \"Depature_Time\"="+"'"+flight.departureTime+"'" +
                        ", \"Landing_Time\" =" + "'"+flight.landingTime+"'"+
                        "where id=" + id + "");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return x!=0;
        }
       return false;
    }

    @Override
    public boolean Delete(int id) {
        int res=0;
        try {
            res=super.stm.executeUpdate("DELETE from \"Flights\" WHERE id = "+id);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return (res ==0?false:true) ;
    }

    public List<Flight> get_flights_by_parameters(int _origin_counry_id,int _detination_country_id,
                                            String date)
    {
        List<Flight> flights=new ArrayList<>();
        try {
            var result = super.stm.executeQuery
                    ("SELECT * FROM get_flights_by_parameters("+_origin_counry_id+","+_detination_country_id+"," +
                            "'"+date+"')");
            while (result.next()) {
                flights.add(new Flight
                        (result.getInt("id"),
                                result.getInt("Airline_Company_Id")
                                , result.getInt("Origin_Country_Id")
                                , result.getInt("Destination_Country_Id")
                                , result.getInt("Remaining_Tickets")
                                , result.getString("Depature_Time")
                                ,result.getString("Landing_Time")
                        ));
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return flights;
    }
    public List<Flight> get_flights_by_airline_id(int id){
        List<Flight> flights=new ArrayList<>();
        try {
            ResultSet result = super.stm.executeQuery
                    ("select * from get_flights_by_airline_id("+id+")");
            while (result.next()) {
                flights.add(new Flight
                        (result.getInt("id"),
                                result.getInt("Airline_Company_Id")
                                , result.getInt("Origin_Country_Id")
                                , result.getInt("Destination_Country_Id")
                                , result.getInt("Remaining_Tickets")
                                , result.getString("Depature_Time")
                                ,result.getString("Landing_Time")
                        ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return flights;
    }

    public List<Flight> get_arrival_flights(int id){
        List<Flight> flights=new ArrayList<>();
        try {
            ResultSet result = super.stm.executeQuery
                    ("select * from get_arrival_flights("+id+")");
            while (result.next()) {
                flights.add(new Flight
                        (result.getInt("id"),
                                result.getInt("Airline_Company_Id")
                                , result.getInt("Origin_Country_Id")
                                , result.getInt("Destination_Country_Id")
                                , result.getInt("Remaining_Tickets")
                                , result.getString("Depature_Time")
                                ,result.getString("Landing_Time")
                        ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return flights;
    }
    public List<Flight> get_departure_flights(int id){
        List<Flight> flights=new ArrayList<>();
        try {
            ResultSet result = super.stm.executeQuery
                    ("select * from get_departure_flights("+id+")");
            while (result.next()) {
                flights.add(new Flight
                        (result.getInt("id"),
                                result.getInt("Airline_Company_Id")
                                , result.getInt("Origin_Country_Id")
                                , result.getInt("Destination_Country_Id")
                                , result.getInt("Remaining_Tickets")
                                , result.getString("Depature_Time")
                                ,result.getString("Landing_Time")
                        ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return flights;
    }

    public List<Flight> getFlightsByOriginCountryId(int country_id){
        List<Flight> flights= new ArrayList<>();
        getAll().stream().forEach(a->flights.add((Flight) a));
        var result=flights.stream().filter(a->a.originCountryId==country_id);
        List<Flight> flightList = result.collect(Collectors.toList());
        return flightList;
    }

    public List<Flight> getFlightsByDestinationCountryId(int country_id){
        List<Flight> flights= new ArrayList<>();
        getAll().stream().forEach(a->flights.add((Flight) a));
        var result= flights.stream().filter(a->a.destinationCountryId==country_id);
        List<Flight> flightList = result.collect(Collectors.toList());
        return flightList;
    }
    public List<Flight> getFlightsByDepartureDate(String date){
        List<Flight> flights= new ArrayList<>();
        getAll().stream().forEach(a->flights.add((Flight) a));
        var result= flights.stream().filter(a->a.departureTime.split(" ")[0].equals(date));
        List<Flight> flightList = result.collect(Collectors.toList());
        return flightList;
    }
    public List<Flight> getFlightsByLandingDate(String date){
        List<Flight> flights= new ArrayList<>();
        getAll().stream().forEach(a->flights.add((Flight) a));
        var result=flights.stream().filter(a->a.landingTime.split(" ")[0].equals(date));
        List<Flight> flightList = result.collect(Collectors.toList());
        return flightList;
    }
    public List<Flight> getFlightsByCustomer(Customer customer){
        TicketsDao ticketsDao=new TicketsDao();
        List<Tickets> tickets=ticketsDao.get_tickets_by_customer(customer.id);
        List<Flight> flights= new ArrayList<>();
        tickets.forEach(a->flights.add((Flight)getById(a.flightId)));
        return flights;
    }

    public boolean isFlight(Flight flight){
        Timestamp departure= Timestamp.valueOf(flight.departureTime);
        Timestamp landing=Timestamp.valueOf(flight.landingTime);
        if(departure.after(landing)){
            System.out.println("departure have to be befor landing");
            return true;
        }
        if (flight.ticketsRemaining < 0){
            System.out.println("tickets can not be less then zero");
            return true;
        }

        if(flight.originCountryId==flight.destinationCountryId){
            System.out.println("origin and destination can not be the same country");
            return true;
        }
        return false;
    }

}
