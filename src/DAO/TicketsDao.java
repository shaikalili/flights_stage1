package DAO;

import POJO.Flight;
import POJO.Poco;
import POJO.Tickets;
import sql.SqlStatment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketsDao extends SqlStatment implements Dao{

    List<Poco> TicketsList = new ArrayList<>();
    @Override
    public List<Poco> getAll() {
        try {
            ResultSet result = super.stm.executeQuery
                    ("SELECT * FROM \"Tickets\"");
            while (result.next()) {
                TicketsList.add(new Tickets
                        (result.getInt("id"),
                                result.getInt("Flights_Id")
                                , result.getInt("Customers_Id")
                        ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return TicketsList;
    }

    @Override
    public Poco getById(int id) {
        Tickets ticket=null;
        try {
            var result = super.stm.executeQuery
                    ("SELECT * FROM \"Tickets\" WHERE id=" + id);
            result.next();
            ticket=(new Tickets
                    (result.getInt("id"),
                            result.getInt("Flights_Id")
                            , result.getInt("Customers_Id")
                    ));


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ticket;
    }

    @Override
    public boolean Add(Poco poco) {
        if(poco instanceof Tickets){
            Tickets ticket=(Tickets) poco;
            try{
                var result=super.stm.executeUpdate("INSERT INTO \"Tickets\"(\"Flights_Id\",\"Customers_Id\"" +
                       ")" +
                        "VALUES " +
                        "("+ticket.flightId+","+ticket.customerId+")");

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
        if(poco instanceof Tickets) {
            Tickets ticket = (Tickets) poco;
            int x = 0;
            try {
                x = super.stm.executeUpdate("UPDATE \"Tickets\" SET \"Flights_Id\""+"="+ticket.flightId+","+
                        " \"Customers_Id\"=" + ticket.customerId + "" +
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
            res=super.stm.executeUpdate("DELETE from \"Tickets\" WHERE id = "+id);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return (res ==0?false:true) ;
    }

    public List<Tickets> get_tickets_by_customer(int _customer_id){
        List<Tickets> TicketsList=new ArrayList<>();
        try {
            ResultSet result = super.stm.executeQuery
                    ("select * from get_tickets_by_customer("+_customer_id+")");
            while (result.next()) {
                TicketsList.add(new Tickets
                        (result.getInt("id"),
                                result.getInt("Flights_Id")
                                , result.getInt("Customers_Id")
                        ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return TicketsList;
    }
}
