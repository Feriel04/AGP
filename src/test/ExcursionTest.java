package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import domain.Excursion;
import domain.Hostel;
import domain.Site;

import java.util.ArrayList;

public class ExcursionTest {

    private Excursion excursion;
    private Site site1;
    private Site site2;
    private Hostel hotelStart;
    private Hostel hotelEnd;
    
    @Before
    public void setUp() {
        site1 = new Site() {
            public float getPrice() { return 50; }
            public double getScore() { return 4.5; }
            public String getName() { return "Site 1"; }
            public String getType() { return "Type 1"; }
        };
        
        site2 = new Site() {
            public float getPrice() { return 100; }
            public double getScore() { return 3.5; }
            public String getName() { return "Site 2"; }
            public String getType() { return "Type 2"; }
        };
        
        hotelStart = new Hostel() {
            public String getName() { return "Hotel Start"; }
        };
        
        hotelEnd = new Hostel() {
            public String getName() { return "Hotel End"; }
        };
        
        ArrayList<Site> activities = new ArrayList<>();
        activities.add(site1);
        activities.add(site2);
        excursion = new Excursion(activities, hotelStart, hotelEnd);
    }
    
    @Test
    public void testCalculatePriceExcursion() {
        int expectedPrice = 150; // 50 + 100
        assertEquals("The total price should be the sum of all activities' prices", expectedPrice, excursion.calculatePriceExcursion());
    }
    
    @Test
    public void testCalculateScoreExcursion() {
        double expectedScore = 8.0; // 4.5 + 3.5
        assertEquals("The total score should be the sum of all activities' scores", expectedScore, excursion.calculateScoreExcursion(), 0.0);
    }
    
    @Test
    public void testAddActivities() {
        Site newSite = new Site() {
            public float getPrice() { return 30; }
            public double getScore() { return 2.0; }
            public String getName() { return "Site 3"; }
            public String getType() { return "Type 3"; }
        };
        excursion.addActivities(newSite);
        assertEquals("The total price should be updated after adding a new activity", 180, excursion.calculatePriceExcursion());
        assertEquals("The total score should be updated after adding a new activity", 10.0, excursion.calculateScoreExcursion(), 0.0);
    }
    
    @Test
    public void testToString() {
        String result = excursion.toString();
        assertTrue("The toString method should contain the hotel's name", result.contains(hotelStart.getName()));
        assertTrue("The toString method should contain the hotelEnd's name", result.contains(hotelEnd.getName()));
        assertTrue("The toString method should contain the name of each site", result.contains(site1.getName()) && result.contains(site2.getName()));
    }
}
