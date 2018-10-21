package spothero.demo.api;

import org.junit.Test;
import spothero.demo.model.ParkingRange;

import static org.junit.Assert.assertEquals;

public class IntegrationTest {

    private ParkingRates rates = ParkingRates.testData();
    private ParkingRange range;


    @Test
    public void testcase1() {
        range = new ParkingRange("2015-07-01T07:00:00Z", "2015-07-01T12:00:00Z");
        ParkingComputer computer = new ParkingComputer(rates, range);
        assertEquals("1500", computer.compute());
    }
}
