package DAO;

import POJO.Country;
import POJO.Poco;
import POJO.Tickets;
import POJO.UserRoles;
import sql.SqlStatment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryDao extends SqlStatment implements Dao {
    List<Poco> CountriesList=new ArrayList<>();
    @Override
    public List<Poco> getAll() {
        try {
            ResultSet result = super.stm.executeQuery
                    ("SELECT * FROM \"Countries\"");
            while (result.next()) {
                CountriesList.add(new Country
                        (result.getInt("id"),
                                result.getString("name")
                        ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return CountriesList;
    }

    @Override
    public Poco getById(int id) {
        Country country=null;
        try {
            var result = super.stm.executeQuery
                    ("SELECT * FROM \"Countries\" WHERE id=" + id);
            result.next();
            country=(new Country
                    (result.getInt("id"),
                            result.getString("name")
                    ));


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return country;
    }

    @Override
    public boolean Add(Poco poco) {
        if(poco instanceof Country){
            Country country=(Country) poco;
            try{
                var result=super.stm.executeUpdate("INSERT INTO \"Countries\"(\"name\")" +
                        "VALUES " +
                        "('"+country.name+"')");

                return true;
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        System.out.println("this is not a country");
        return false;
    }

    @Override
    public boolean Update(Poco poco, int id) {
        if(poco instanceof Country){
            Country country=(Country) poco;
            int x = 0;
            try {
                x = super.stm.executeUpdate("UPDATE \"Countries\" SET \"name\""+"='"+country.name+"'"+
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
            res=super.stm.executeUpdate("DELETE from \"Countries\" WHERE id = "+id);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return (res ==0?false:true) ;
    }
}
