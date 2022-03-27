package Facades;

import DAO.AdministratorDao;
import POJO.Administrator;
import POJO.AirlineCompanies;
import POJO.Customer;
import POJO.Poco;

import java.util.List;

public class AdminFacade extends AnonymousFacade implements Facade{
    private LoginToken loginToken;
    AdministratorDao administratorDao=new AdministratorDao();

    public AdminFacade(LoginToken loginToken) {
        this.setLoginToken(loginToken);
    }
    public LoginToken getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(LoginToken loginToken) {
        this.loginToken = loginToken;
    }

    public List<Poco> get_all_customers(){
        return customersDao.getAll();
    }
    public void add_airline(AirlineCompanies airlineCompanies){
        airlineCompanyDao.Add(airlineCompanies);
    }
    public void add_customer(Customer customer){
        customersDao.Add(customer);
    }
    public void add_adminstrator(Administrator administrator){
        administratorDao.Add(administrator);
    }
    public void remove_airline(AirlineCompanies airlineCompanies){
        airlineCompanyDao.Delete(airlineCompanies.id);
    }
    public void remove_customer(Customer customer){
        customersDao.Delete(customer.id);
    }
    public void remove_adminstrator(Administrator administrator){
        administratorDao.Delete(administrator.id);
    }

}
