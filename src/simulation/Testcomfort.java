package simulation;

import domain.Activity;
import domain.Hostel;
import domain.Site;
import domain.Position; // Assuming Position is also a class you have
import domain.Transport; // Assuming Transport is also a class you have
import domain.AutoBus;
import domain.Boat;
import simulation.SimulationUtility;
public class Testcomfort {

    public static void initializeEntities() {

        Site site = new Site();
        site.setDescription("A beautiful historic site.");
        site.setType("loisir");
        site.setName("Hostel Name");
        site.setAddress("123 Hostel St.");
        site.setPrice(100);
        site.setPositionx(10);
        site.setPositiony(20);
        site.setUrlPicture("http://example.com/picture.jpg");
        site.setScore(3.5);
        
        Hostel myHostel = new Hostel();

        myHostel.setName("Hostel Name");
        myHostel.setAddress("123 Hostel St.");
        myHostel.setPrice(100);
        myHostel.setPositionx(10);
        myHostel.setPositiony(20);
        myHostel.setUrlPicture("http://example.com/picture.jpg");
        myHostel.setScore(9.5);
        

     
        AutoBus autobus= new AutoBus(10);
        Activity activity = new Activity(site,autobus);

        System.out.println(SimulationUtility.calculComfort(autobus,activity,site));
    }
    


    public static void main(String[] args) {
        initializeEntities();
    }
}
