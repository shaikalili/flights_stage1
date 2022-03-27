package DAO;

import POJO.Poco;
import POJO.Tickets;
import POJO.UserRoles;
import sql.SqlStatment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRoleDao extends SqlStatment implements Dao {
    List<Poco> UserRoleList = new ArrayList<>();
    @Override
    public List<Poco> getAll() {
        try {
            ResultSet result = super.stm.executeQuery
                    ("SELECT * FROM \"User_Roles\"");
            while (result.next()) {
                UserRoleList.add(new UserRoles
                        (result.getInt("id"),
                                result.getString("Role_Name")
                        ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return UserRoleList;
    }

    @Override
    public Poco getById(int id) {
       UserRoles userRoles=null;
        try {
            var result = super.stm.executeQuery
                    ("SELECT * FROM \"User_Roles\" WHERE id=" + id);
            result.next();
            userRoles=(new UserRoles
                    (result.getInt("id"),
                            result.getString("Role_Name")
                    ));


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userRoles;
    }

    @Override
    public boolean Add(Poco poco) {
        if(poco instanceof UserRoles){
            UserRoles userRoles=(UserRoles) poco;
            try{
                var result=super.stm.executeUpdate("INSERT INTO \"User_Roles\"(\"Role_Name\")" +
                        "VALUES " +
                        "('"+userRoles.roleName+"')");

                return true;
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        System.out.println("this is not a role");
        return false;
    }

    @Override
    public boolean Update(Poco poco, int id) {
        if(poco instanceof UserRoles){
            UserRoles userRoles=(UserRoles) poco;
            int x = 0;
            try {
                x = super.stm.executeUpdate("UPDATE \"User_Roles\" SET \"Role_Name\""+"='"+userRoles.roleName+"'"+
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
            res=super.stm.executeUpdate("DELETE from \"User_Roles\" WHERE id = "+id);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return (res ==0?false:true) ;
    }
}
