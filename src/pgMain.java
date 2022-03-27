import Facades.AirlineFacade;
import Facades.AnonymousFacade;
import Facades.CustomerFacade;
import Facades.Facade;

public class pgMain {
    public static void main(String[] args) {
        AnonymousFacade anonymousFacade=new AnonymousFacade();
        Facade airline= anonymousFacade.loginUser("El-Al","elal");
        if(airline instanceof AirlineFacade){
            ((AirlineFacade) airline).get_my_flights().forEach(System.out::println);
        }
        System.out.println("/////////////////////////////");


    }
}
