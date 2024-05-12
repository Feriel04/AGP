package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import domain.Excursion;
import domain.Offer;

import java.util.ArrayList;

public class OfferTest {

    private Offer offer;
    private Excursion excursion1;
    private Excursion excursion2;
    
    @Before
    public void setUp() {
        excursion1 = new Excursion() {
            public int calculatePriceExcursion() { return 100; }
            public double calculateScoreExcursion() { return 4.5; }
        };
        
        excursion2 = new Excursion() {
            public int calculatePriceExcursion() { return 200; }
            public double calculateScoreExcursion() { return 3.5; }
        };
        
        ArrayList<Excursion> excursions = new ArrayList<>();
        excursions.add(excursion1);
        excursions.add(excursion2);
        offer = new Offer(excursions);
    }
    
    @Test
    public void testCalculatePriceOffer() {
        int expectedPrice = 300; // 100 + 200
        assertEquals("The total price should be the sum of all excursions' prices", expectedPrice, offer.calculatePriceOffer());
    }
    
    @Test
    public void testCalculateScoreOffer() {
        double expectedScore = 8.0; // 4.5 + 3.5
        assertEquals("The total score should be the sum of all excursions' scores", expectedScore, offer.calculateScoreOffer(), 0.0);
    }
    
    @Test
    public void testAddExcursion() {
        Excursion newExcursion = new Excursion() {
            public int calculatePriceExcursion() { return 50; }
            public double calculateScoreExcursion() { return 2.0; }
        };
        offer.addExcursion(newExcursion);
        assertEquals("The total price should be updated after adding a new excursion", 350, offer.calculatePriceOffer());
        assertEquals("The total score should be updated after adding a new excursion", 10.0, offer.calculateScoreOffer(), 0.0);
    }
}
