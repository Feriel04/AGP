package simulation;

import java.util.ArrayList;
import java.util.List;

import domain.*;
import process.*;

public class TestOffer {

    public static void initializeEntities() {  
        
        ArrayList<Site> siteList1 = new ArrayList<Site>();
        siteList1.add(new Site("A beautiful historic site.", "loisir", "Hostel Name", "123 Hostel St.",
                 100, 10, 20, "http://example.com/picture.jpg", 3));

        siteList1.add(new Site("A scenic park with a lake.", "loisir", "Lake Park", "456 Park Ave.",
                 50, 15, 25, "http://example.com/lake.jpg", 4));
        
        ArrayList<Site> siteList2 = new ArrayList<Site>();

        siteList2.add(new Site("A famous historical museum.", "culture", "History Museum", "789 Museum Rd.",
                 75, 8, 12, "http://example.com/museum.jpg", 4));

        siteList2.add(new Site("A cozy cafe with great coffee.", "food", "Coffee Corner", "321 Cafe Lane",
                 20, 5, 15, "http://example.com/cafe.jpg", 4));
        
        
        ArrayList<Site> siteList3 = new ArrayList<Site>();
        
        siteList3.add(new Site("A beautiful beach resort.", "loisir", "Beach Resort", "789 Beachfront Rd.",
                 150, 30, 40, "http://example.com/beach.jpg", 4));

        siteList3.add(new Site("A historic castle with guided tours.", "culture", "Castle Tours", "567 Castle Hill",
                 90, 12, 18, "http://example.com/castle.jpg", 4));

        siteList3.add(new Site("A popular shopping mall.", "shopping", "Mega Mall", "101 Shopper's Lane",
                 40, 7, 22, "http://example.com/mall.jpg", 4));




        ActivitySorter sorter= new ActivitySorter(siteList1);
        ArrayList<Site> siteListe = sorter.getActivities();
        siteListe=sorter.sortSitesByMinimumDistance(siteListe);
        
        Hostel myHostel = new Hostel();

        myHostel.setName("Hostel Name");
        myHostel.setAddress("123 Hostel St.");
        myHostel.setPrice(100);
        myHostel.setPositionx(10);
        myHostel.setPositiony(20);
        myHostel.setUrlPicture("http://example.com/picture.jpg");
        myHostel.setScore(9.5);
        
        ArrayList<Excursion> excursionList = new ArrayList<Excursion>();
        excursionList.add(new Excursion(siteList1, myHostel, myHostel));
        excursionList.add(new Excursion(siteList2, myHostel,myHostel));
        excursionList.add(new Excursion(siteList3, myHostel,myHostel));

        /*
        for (Excursion excursion1 : excursionList) {
        	System.out.println("-----------------------------");
            System.out.println(excursion1);  
        }
        */
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        
        ArrayList<Offer> offers = OfferBuilder.buildOffers(excursionList, 5);

        // Affichez les détails des offres générées
        displayOffers(offers);
        
        
    }
    


    public static void main(String[] args) {
        initializeEntities();
        
        
    }
    
    private static void displayOffers(ArrayList<Offer> offers) {
        // Affichez les détails des offres générées
        for (int i = 0; i < offers.size(); i++) {
            System.out.println("Jour " + (i + 1) + ":");
            Offer offer = offers.get(i);
            for (int j = 0; j < offer.getExcursions().size(); j++) {
                System.out.println("Excursion " + (j + 1) + ":");
                System.out.println(offer.getExcursions().get(j));
            }
            System.out.println("Prix total de l'offre: " + offer.getTotalPrice());
            System.out.println("Score total de l'offre: " + offer.calculateScoreOffer());
            System.out.println("-----");
        }
    }

}
