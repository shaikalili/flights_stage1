package Facades;

import DAO.CustomersDao;
import DAO.UserRoleDao;
import DAO.UsersDao;
import POJO.Customer;
import POJO.UserRoles;
import POJO.Users;

public class AnonymousFacade extends FacadeBase{
    UsersDao usersDao=new UsersDao();
    UserRoleDao userRoleDao=new UserRoleDao();
    CustomersDao customersDao=new CustomersDao();
    LoginToken loginToken;

    public Facade loginUser(String userName,String password){

        Users user=usersDao.loginUser(userName,password);
        if(user==null){
            return null;
        }
        else{
               UserRoles role=(UserRoles) userRoleDao.getById(user.userRole);
               loginToken= new LoginToken(user.id,user.userName,role);
           switch (role.id) {
               case 1:
                   return new CustomerFacade(loginToken);
               case 2:
                   return new AirlineFacade(loginToken);
               case 3:
                   return new AdminFacade(loginToken);
           }
        }
        return null;
    }

    public void add_Customer(Customer customer){
        customersDao.Add(customer);
    }
}
