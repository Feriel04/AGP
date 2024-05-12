package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import domain.AutoBus;

public class AutoBusTest {

    private AutoBus autobus;
    
    @Before
    public void setUp() {
        autobus = new AutoBus();
    }
    
    @Test
    public void testCalculatePriceDistance() {
        int distance = 100; // exemple de distance
        autobus.setDistance(distance);
        int expectedPrice = Math.round(autobus.PRICE_PER_KILOMETER_AUTOBUS * distance);
        assertEquals("The price should be correctly calculated based on distance", expectedPrice, autobus.calculatePriceDistance());
    }
    
    @Test
    public void testSetAndGetDistance() {
        int distance = 150;
        autobus.setDistance(distance);
        assertEquals("The getDistance method should return the value set by setDistance", distance, autobus.getDistance());
    }
    
    @Test
    public void testSetAndGetType() {
        int type = 1; // exemple de type
        autobus.setType(type);
        assertEquals("The getType method should return the value set by setType", type, autobus.getType());
    }
    
    // Test for calculatetimeTravel if implemented later
    // @Test
    // public void testCalculatetimeTravel() {
    //     // test logic here
    // }
}

