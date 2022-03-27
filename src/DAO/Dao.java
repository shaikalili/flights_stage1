package DAO;

import POJO.Flight;
import POJO.Poco;

import java.util.List;

public interface Dao {
    public List<Poco> getAll();
    public Poco getById(int id);
    public  boolean Add(Poco poco);
    public  boolean Update(Poco poco,int id);
    public boolean Delete(int id);
}
