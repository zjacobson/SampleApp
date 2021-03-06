package spothero.demo.api;

import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import spothero.demo.model.ParkingRates;
import spothero.demo.model.Rate;
import spothero.demo.model.ParkingRange;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ParkingComputerTest {

    @Mock private ParkingRates rates;
    @Mock private ParkingRange range;
    private ParkingComputer computer;

    @Before
    public void setup() {
        computer = new ParkingComputer(rates, range);
    }



    @Test
    public void testRangeInRate() {
        // 2015-07-01T07:00:00Z (Wed) to 2015-07-01T12:00:00Z should yield 1500
        when(range.startTime()).thenReturn(LocalTime.parse("7:00:00"));
        when(range.endTime()).thenReturn(LocalTime.parse("12:00:00"));
        Rate rate = new Rate("mon,tues,wed,thurs", "0600-1300", "1500");

        boolean inRate = computer.rangeInRate(rate);
        assertTrue(inRate);
    }

}