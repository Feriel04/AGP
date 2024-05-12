package simulation;

import java.util.ArrayList;

import domain.Activity;
import domain.Hostel;
import domain.Site;
import process.ActivitySorter;

public class tetsActivitySorter {
	
	public static void main(String[] args) {
		
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
        
        
        ArrayList<Site> siteList = new ArrayList<Site>();
        siteList.add(new Site("A beautiful historic site.", "loisir", "Hostel Name",
                "HST123", 100, 10, 20, "http://example.com/picture.jpg", 3));
        siteList.add(new Site("A scenic park with a lake.", "loisir", "Lake Park",
                "LP123", 50, 15, 25, "http://example.com/lake.jpg", 1));

        siteList.add(new Site("A famous historical museum.", "culture", "History Museum",
                "MUS456", 75, 8, 12, "http://example.com/museum.jpg", 2));

        siteList.add(new Site("A cozy cafe with great coffee.", "food", "Coffee Corner",
                "CAF789", 20, 5, 15, "http://example.com/cafe.jpg", 4));

        siteList.add(new Site("A beautiful beachfront resort.", "loisir", "Beach Resort",
                "BR123", 150, 30, 40, "http://example.com/beach.jpg", 4));

        siteList.add(new Site("A historic castle with guided tours.", "culture", "Castle Tours",
                "CAS789", 90, 12, 18, "http://example.com/castle.jpg", 2));

        siteList.add(new Site("A popular shopping mall.", "shopping", "Mega Mall",
                "MM456", 40, 7, 22, "http://example.com/mall.jpg", 1));


       

       

        
        ActivitySorter sorter= new ActivitySorter(siteList);
        ArrayList<Site> siteListe = sorter.getActivities();
        siteListe=sorter.sortSitesByMinimumDistance(siteListe);
        
        for (Site site3 : siteListe) {
        	System.out.println(site3);
        	}
    }
        
}