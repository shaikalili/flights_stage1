package DAO;

import POJO.AirlineCompanies;
import POJO.Poco;
import POJO.Tickets;
import sql.SqlStatment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirlineCompanyDao extends SqlStatment implements Dao {
    List<Poco> airlineCompanyList=new ArrayList<>();
    @Override
    public List<Poco> getAll() {
        try {
            ResultSet result = super.stm.executeQuery
                    ("SELECT * FROM \"Airline_Companies\"");
            while (result.next()) {
                airlineCompanyList.add(new AirlineCompanies(
                        result.getInt("id"),
                                result.getString("name"),
                                result.getInt("Country_Id")
                                , result.getInt("User_Id")
                        ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return airlineCompanyList;
    }

    @Override
    public Poco getById(int id) {
        AirlineCompanies airlineCompanies=null;
        try {
            var result = super.stm.executeQuery
                    ("SELECT * FROM \"Airline_Companies\" WHERE id=" + id);
            result.next();
           airlineCompanies=new AirlineCompanies(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getInt("Country_Id")
                    , result.getInt("User_Id")
            );


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return airlineCompanies;
    }

    @Override
    public boolean Add(Poco poco) {
        if(poco instanceof AirlineCompanies){
            AirlineCompanies airlineCompanies=(AirlineCompanies) poco;
            try{
                var result=super.stm.executeUpdate("INSERT INTO \"Airline_Companies\"(\"name\",\"Country_Id\",\"User_Id\"" +
                        ")" +
                        "VALUES " +
                        "('"+airlineCompanies.name+"',"+airlineCompanies.countryId+","+airlineCompanies.userId+")");

                return true;
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        System.out.println("this is not a ticket");
        return false;
    }

    @Override
    public boolean Update(Poco poco, int id) {
        if(poco instanceof AirlineCompanies){
            AirlineCompanies airlineCompanies=(AirlineCompanies) poco;
            int x = 0;
            try {
                x = super.stm.executeUpdate("UPDATE \"Airline_Companies\" SET " +
                        "\"name\""+"='"+airlineCompanies.name+"',"+
                        "\"Country_Id\""+"="+airlineCompanies.countryId+","+
                        " \"User_Id\"=" + airlineCompanies.userId + "" +
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
            res=super.stm.executeUpdate("DELETE from \"Airline_Companies\" WHERE id = "+id);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return (res ==0?false:true) ;
    }

    public AirlineCompanies get_airline_by_username(String userName){
        AirlineCompanies airlineCompanies=null;
        try {
            var result = super.stm.executeQuery
                    ("SELECT * FROM get_airline_by_username('"+userName+"')");
            result.next();
            airlineCompanies=new AirlineCompanies(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getInt("Country_Id")
                    , result.getInt("User_Id")
            );


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return airlineCompanies;
    }

    public AirlineCompanies getAirlinesByCountry(int country_id){
        AirlineCompanies airlineCompanies=null;
        try {
            var result = super.stm.executeQuery
                    ("SELECT * FROM \"Airline_Companies\" WHERE \"Country_Id\"="+country_id);
            result.next();
            airlineCompanies=new AirlineCompanies(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getInt("Country_Id")
                    , result.getInt("User_Id")
            );


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return airlineCompanies;
    }

    public AirlineCompanies getAirlinesByName(String company_name){
        AirlineCompanies airlineCompanies=null;
        try {
            var result = super.stm.executeQuery
                    ("SELECT * FROM \"Airline_Companies\" WHERE \"name\"="+company_name);
            result.next();
            airlineCompanies=new AirlineCompanies(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getInt("Country_Id")
                    , result.getInt("User_Id")
            );


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return airlineCompanies;
    }


}
