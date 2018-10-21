package spothero.demo.api;

import org.junit.Test;
import spothero.demo.model.Day;
import spothero.demo.model.ParkingRange;
import spothero.demo.model.ParkingRates;
import spothero.demo.model.Rate;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class IntegrationTest {

    private ParkingRates rates = ParkingRates.testData();
    private ParkingRange range;


    @Test
    public void testcase1() {
        range = new ParkingRange("2015-07-01T07:00:00Z", "2015-07-01T12:00:00Z");
        ParkingComputer computer = new ParkingComputer(rates, range);
        assertEquals("1750", computer.compute().getPrice());
    }

    @Test
    public void testcase2() {
        range = new ParkingRange("2015-07-01T02:00:00Z", "2015-07-01T03:00:00Z");
        ParkingComputer computer = new ParkingComputer(rates, range);
        assertEquals("1000", computer.compute().getPrice());
    }


    @Test
    public void findRateForDay() {
        Rate monRate = new Rate("mon", null, null);
        rates = new ParkingRates(Collections.singletonList(monRate));

        ParkingComputer computer = new ParkingComputer(rates, range);

        assertSame(monRate, computer.ratesForDay(Day.mon, Day.mon).get(0));
    }

    @Test
    public void testRatesForDay_testData() {
        ParkingComputer computer = new ParkingComputer(rates, null);

        List<Rate> mondayRates = computer.ratesForDay(Day.mon, Day.mon);

        assertEquals(2, mondayRates.size());
        assertEquals("0900-2100", mondayRates.get(0).getTimes());
        assertEquals("1500", mondayRates.get(0).getPrice());
        assertEquals("0100-0500", mondayRates.get(1).getTimes());
        assertEquals("1000", mondayRates.get(1).getPrice());
    }
}
