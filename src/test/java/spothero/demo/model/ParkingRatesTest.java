package spothero.demo.model;

import org.junit.Test;
import spothero.demo.model.ParkingRates;

import static org.junit.Assert.*;

public class ParkingRatesTest {
    
    @Test public void parseFromJson() {
        ParkingRates parkingRates = ParkingRates.testData();
        assertEquals(5, parkingRates.rates.size());
        assertEquals("wed", parkingRates.rates.get(2).days);
        assertEquals("0100-0700", parkingRates.rates.get(4).times);
        assertEquals("1500", parkingRates.rates.get(0).price);
    }

}