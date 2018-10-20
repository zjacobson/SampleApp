package spothero.demo.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import spothero.demo.model.ParkingRange;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ParkingComputerTest {

    @Mock private ParkingRates rates;
    @Mock private ParkingRange range;
    ParkingComputer computer;

    @Before
    public void setup() {
        computer = new ParkingComputer(rates, range);
    }


    @Test
    public void testBasic() {
        assertEquals("0", computer.compute());
    }

    @Test
    public void testNoRatesFoundForRange() {
        when(rates.hasRates()).thenReturn(true);
        assertEquals("1", computer.compute());
    }

}