package spothero.demo.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingRangeTest {

    @Test
    public void testStartDay() {
        String start = "2015-07-02T07:00:00Z"; // (Thur)
        ParkingRange range = new ParkingRange(start, null);

        assertEquals(Day.thur, range.startDay());
    }

    @Test
    public void testEndDay() {
        String end = "2018-19-20T20:00:00Z"; // (Sat)
        ParkingRange range = new ParkingRange(null, end);

        assertEquals(Day.mon, range.endDay());
    }
}