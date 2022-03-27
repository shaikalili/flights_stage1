package DAO;

import POJO.Administrator;
import POJO.Poco;
import POJO.Users;
import sql.SqlStatment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdministratorDao extends SqlStatment implements Dao {
    List<Poco> adminList = new ArrayList<>();
    @Override
    public List<Poco> getAll() {
        try {
            ResultSet result = super.stm.executeQuery
                    ("SELECT * FROM \"Adminstrators\"");
            while (result.next()) {
                adminList.add(new Administrator
                        (result.getInt("id"),
                                result.getString("First_Name")
                                , result.getString("Last_Name")
                                , result.getInt("User_Id")
                        ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return adminList;
    }

    @Override
    public Poco getById(int id) {
        Administrator admin=null;
        try {
            var result = super.stm.executeQuery
                    ("SELECT * FROM \"Adminstrators\" WHERE id=" + id);
            result.next();
            admin=(new Administrator
                    (result.getInt("id"),
                            result.getString("First_Name")
                            , result.getString("Last_Name")
                            , result.getInt("User_Id")
                    ));


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return admin;
    }

    @Override
    public boolean Add(Poco poco) {
        if(poco instanceof Administrator){
            Administrator admin=(Administrator) poco;
            try{
                var result=super.stm.executeUpdate("INSERT INTO \"Adminstrators\"(\"First_Name\",\"Last_Name\"" +
                        ",\"User_Id\")" +
                        "VALUES " +
                        "('"+admin.firstName+"','"+admin.lastName+"',"+admin.userId+")");

                return true;
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        System.out.println("this is not a admin");
        return false;
    }

    @Override
    public boolean Update(Poco poco, int id) {
        if(poco instanceof Administrator){
            Administrator admin=(Administrator) poco;
            int x = 0;
            try {
                x = super.stm.executeUpdate("UPDATE \"Adminstrators\" SET \"First_Name\""+"='"+admin.firstName+"',"+
                        " \"Last_Name\"='" +admin.lastName + "'" +
                        ",\"User_Id\"=" + admin.userId + ""+
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
            res=super.stm.executeUpdate("DELETE from \"Adminstrators\" WHERE id = "+id);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return (res ==0?false:true) ;
    }
}
