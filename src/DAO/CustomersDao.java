package DAO;

import POJO.Customer;
import POJO.Flight;
import POJO.Poco;
import sql.SqlStatment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomersDao extends SqlStatment implements Dao {
    List<Poco> CustomersList = new ArrayList<>();
    @Override
    public List<Poco> getAll() {
        try {
            ResultSet result = super.stm.executeQuery
                    ("SELECT * FROM \"Customers\"");
            while (result.next()) {
                CustomersList.add(new Customer
                        (result.getInt("id"),
                                result.getString("First_Name")
                                , result.getString("Last_Name")
                                , result.getString("Adress")
                                , result.getString("Phone_No")
                                , result.getString("Credit_Card_Number")
                                ,result.getInt("User_Id")
                        ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return CustomersList;
    }

    @Override
    public Poco getById(int id) {
        Customer customer=null;
        try {
            ResultSet result = super.stm.executeQuery
                    ("SELECT * FROM \"Customers\" Where id="+id);
            result.next();
            customer=new Customer
                    (result.getInt("id"),
                            result.getString("First_Name")
                            , result.getString("Last_Name")
                            , result.getString("Adress")
                            , result.getString("Phone_No")
                            , result.getString("Credit_Card_Number")
                            ,result.getInt("User_Id")
                    );

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return customer;
    }

    @Override
    public boolean Add(Poco poco) {
        if(poco instanceof Customer){
            Customer customer=(Customer) poco;
            try{
                var result=super.stm.executeUpdate("INSERT INTO \"Customers\"(\"First_Name\",\"Last_Name\"" +
                        ",\"Adress\"," +
                        "\"Phone_No\",\"Credit_Card_Number\",\"User_Id\")" +
                        "VALUES " +
                        "('"+customer.firstName+"','"+customer.lastName+"','"+customer.address
                        +"','"+ customer.phoneNumber+"',"+"'"+customer.creditCardNumber+"',"+""+customer.userId+")");

                return true;
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        System.out.println("this is not a customer");
        return false;
    }

    @Override
    public boolean Update(Poco poco, int id) {
        if(poco instanceof Customer){
            Customer customer=(Customer) poco;
            int x = 0;
            try {
                x = super.stm.executeUpdate("UPDATE \"Customers\" SET \"First_Name\""+"='"+customer.firstName+"',"+
                        " \"Last_Name\"='" + customer.lastName + "'" +
                        ",\"Adress\"='" + customer.address +
                        "', \"Phone_No\" ='" + customer.phoneNumber +
                        "' , \"Credit_Card_Number\"="+"'"+customer.creditCardNumber+"'" +
                        ", \"User_Id\" =" + ""+customer.userId+""+
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
            res=super.stm.executeUpdate("DELETE from \"Customers\" WHERE id = "+id);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return (res ==0?false:true) ;
    }

    public Customer get_customer_by_username(String _username){
        Customer customer=null;
        try {
            ResultSet result = super.stm.executeQuery
                    ("SELECT * FROM get_customer_by_username('"+_username+"')");
            result.next();
            customer=new Customer
                    (result.getInt("id"),
                            result.getString("First_Name")
                            , result.getString("Last_Name")
                            , result.getString("Adress")
                            , result.getString("Credit_Card_Number")
                            , result.getString("Phone_No")
                            ,result.getInt("User_Id")
                    );

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return customer;
    }

}
