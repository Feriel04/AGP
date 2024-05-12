package simulation;

import java.util.ArrayList;

import domain.Hostel;
import domain.Site;
import process.ExcursionBuilder;
import process.Preferences;
import domain.Excursion;

public class testExcursion {

    public static void main(String[] args) {
    	ExcursionBuilder builder= new ExcursionBuilder();
       
    	ArrayList<Site> siteList1 = new ArrayList<Site>();
        siteList1.add(new Site("A beautiful historic site.", "loisir", "Hostel Name", "123 Hostel St.",
                 100, 10, 20, "http://example.com/picture.jpg", 3));

        siteList1.add(new Site("A scenic park with a lake.", "loisir", "Lake Park", "456 Park Ave.",
                 50, 15, 25, "http://example.com/lake.jpg", 4));
        

        siteList1.add(new Site("A famous historical museum.", "culture", "History Museum", "789 Museum Rd.",
                 75, 8, 12, "http://example.com/museum.jpg", 4));

        siteList1.add(new Site("A cozy cafe with great coffee.", "food", "Coffee Corner", "321 Cafe Lane",
                 20, 5, 15, "http://example.com/cafe.jpg", 4));
        
        
        siteList1.add(new Site("A beautiful beach resort.", "loisir", "Beach Resort", "789 Beachfront Rd.",
                 150, 30, 40, "http://example.com/beach.jpg", 4));

        siteList1.add(new Site("A historic castle with guided tours.", "culture", "Castle Tours", "567 Castle Hill",
                 90, 12, 18, "http://example.com/castle.jpg", 4));

        siteList1.add(new Site("A popular shopping mall.", "shopping", "Mega Mall", "101 Shopper's Lane",
                 40, 7, 22, "http://example.com/mall.jpg", 4));
        Hostel myHostel = new Hostel();

        myHostel.setName("Hostel");
        myHostel.setAddress("123 Hostel St.");
        myHostel.setPrice(100);
        myHostel.setPositionx(10);
        myHostel.setPositiony(20);
        myHostel.setUrlPicture("http://example.com/picture.jpg");
        myHostel.setScore(9.5);
        
        Hostel myHostel1 = new Hostel();

        myHostel1.setName("Hostel Name");
        myHostel1.setAddress("123 Hostel St.");
        myHostel1.setPrice(100);
        myHostel1.setPositionx(10);
        myHostel1.setPositiony(20);
        myHostel1.setUrlPicture("http://example.com/picture.jpg");
        myHostel1.setScore(9.5);
        
        ArrayList<Hostel> hostel= new ArrayList<Hostel>() ;
        hostel.add(myHostel);
        hostel.add(myHostel1);
        
        Preferences preferences= new Preferences("1", 12, "luxe", 500, 1500,"intense" ,
                "luxe","Autobus","intense");
        ArrayList<Excursion> excursion=builder.Excursionscreator(siteList1,hostel,preferences);
        //System.out.println(excursion);
        
        
        
        
    }

}