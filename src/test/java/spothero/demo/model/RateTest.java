package spothero.demo.model;

import org.joda.time.LocalTime;
import org.junit.Test;

import static org.junit.Assert.*;

public class RateTest {
    @Test
    public void testGetStartTime() {
        LocalTime time = new Rate("mon,tue", "0200-1300", "1245").getStartTime();
        assertEquals(2, time.getHourOfDay());
    }

    @Test
    public void testGetEndTime() {
        LocalTime time = new Rate("mon,tue", "0200-1300", "1245").getStartTime();
        assertEquals(13, time.getHourOfDay());
    }

}