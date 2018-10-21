package spothero.demo.api;

import org.junit.Test;
import spothero.demo.model.Day;
import spothero.demo.model.ParkingRange;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

public class IntegrationTest {

    private ParkingRates rates = ParkingRates.testData();
    private ParkingRange range;


    @Test
    public void testcase1() {
        range = new ParkingRange("2015-07-01T07:00:00Z", "2015-07-01T12:00:00Z");
        ParkingComputer computer = new ParkingComputer(rates, range);
        assertEquals("1500", computer.compute());
    }


    @Test
    public void findRateForDay() {
        ParkingRates.Rate monRate = new ParkingRates.Rate("mon", null, null);
        rates = new ParkingRates();
        rates.rates = Collections.singletonList(monRate);

        ParkingComputer computer = new ParkingComputer(rates, range);

        assertSame(monRate, computer.ratesForDay(Day.mon).get(0));
    }

    @Test
    public void testRatesForDay_testData() {
        ParkingComputer computer = new ParkingComputer(rates, null);

        List<ParkingRates.Rate> mondayRates = computer.ratesForDay(Day.mon);

        assertEquals(2, mondayRates.size());
        assertEquals("0900-2100", mondayRates.get(0).times);
        assertEquals("1500", mondayRates.get(0).price);
        assertEquals("0100-0500", mondayRates.get(1).times);
        assertEquals("1000", mondayRates.get(1).price);
    }
}
