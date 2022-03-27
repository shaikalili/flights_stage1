package DAO;

import POJO.Flight;
import POJO.Poco;
import POJO.Users;
import sql.SqlStatment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDao extends SqlStatment implements Dao {
    List<Poco> UsersList = new ArrayList<>();
    @Override
    public List<Poco> getAll() {
        try {
            ResultSet result = super.stm.executeQuery
                    ("SELECT * FROM \"Users\"");
            while (result.next()) {
                UsersList.add(new Users
                        (result.getInt("id"),
                                result.getString("Username")
                                , result.getString("Password")
                                , result.getString("Email")
                                , result.getInt("User_Role")
                        ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return UsersList;
    }

    @Override
    public Poco getById(int id) {
        Users user=null;
        try {
            var result = super.stm.executeQuery
                    ("SELECT * FROM \"Users\" WHERE id=" + id);
            result.next();
            user=(new Users
                    (result.getInt("id"),
                            result.getString("Username")
                            , result.getString("Password")
                            , result.getString("Email")
                            , result.getInt("User_Role")
                    ));


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    public boolean Add(Poco poco) {
        if(poco instanceof Users){
            Users user=(Users) poco;
            if(user.password.length()<6){
                System.out.println("password to short");
                return false;
            }
            try{
                var result=super.stm.executeUpdate("INSERT INTO \"Users\"(\"Username\",\"Password\"" +
                        ",\"Email\",\"User_Role\")" +
                        "VALUES " +
                        "('"+user.userName+"','"+user.password+"','"+user.eMail+"',"+user.userRole+")");

                return true;
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        System.out.println("this is not a User");
        return false;
    }

    @Override
    public boolean Update(Poco poco, int id) {
        if(poco instanceof Users) {
            Users users = (Users) poco;
            if(users.password.length()<6){
                System.out.println("password to short");
                return false;
            }
            int x = 0;
            try {
                x = super.stm.executeUpdate("UPDATE \"Users\" SET \"Username\""+"='"+users.userName+"',"+
                        " \"Password\"='" + users.password + "'" +
                        ",\"Email\"='" + users.eMail + "'"+
                        ", \"User_Role\" =" + users.userRole +
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
            res=super.stm.executeUpdate("DELETE from \"Users\" WHERE id = "+id);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return (res ==0?false:true) ;
    }
    public Users get_user_by_username(String _username){
        Users user=null;
        try {
            var result = super.stm.executeQuery
                    ("select * from get_user_by_username('"+_username+"')");
            result.next();
            user=(new Users
                    (result.getInt("id"),
                            result.getString("Username")
                            , result.getString("Password")
                            , result.getString("Email")
                            , result.getInt("User_Role")
                    ));


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
    public Users loginUser(String userName,String password){
        Users user=get_user_by_username(userName);
        if(user!=null){
            if(user.password.equals(password))
                return user;
            else {
                System.out.println("password is incorrect");
                return user=null;
            }
        }
            System.out.println("userName is incorrect, login failed");
            return user=null;
    }

}
