package Facades;

import DAO.TicketsDao;
import POJO.Customer;
import POJO.Flight;
import POJO.Tickets;

import java.util.List;

public class CustomerFacade extends AnonymousFacade implements Facade {
   private LoginToken loginToken;
   private Customer customer;
   TicketsDao ticketsDao=new TicketsDao();

    public CustomerFacade(LoginToken loginToken) {
        this.setLoginToken(loginToken);
        this.setCustomer();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer() {
        this.customer = customersDao.get_customer_by_username(loginToken.name);
    }

    public LoginToken getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(LoginToken loginToken) {
        this.loginToken = loginToken;
    }

    public void UpdateCustomer(Customer customer){
        if(customer.id==this.getCustomer().id) {
            super.customersDao.Update(this.getCustomer(), this.getCustomer().id);
        }
        else
            System.out.println("you can not update tickets that don't belong to you");

    }
    public void addTicket(Tickets tickets){
        if(tickets.customerId==this.getCustomer().id) {
            ticketsDao.Add(tickets);
            Flight flight= (Flight) flightsDao.getById(tickets.flightId);
            flight.ticketsRemaining-=1;
            flightsDao.Update(flight, tickets.flightId);
        }
        else
            System.out.println("you can not add tickets that don't belong to you");
    }
    public void removeTicket(Tickets tickets){
        if(tickets.customerId==this.getCustomer().id) {
            ticketsDao.Delete(tickets.id);
            Flight flight = (Flight) flightsDao.getById(tickets.flightId);
            flight.ticketsRemaining += 1;
            flightsDao.Update(flight, tickets.flightId);
        }
        else
            System.out.println("you can not delete tickets that don't belong to you");
    }
    public List<Tickets> get_my_tickets(){
      return ticketsDao.get_tickets_by_customer(this.getCustomer().id);
    }
}
