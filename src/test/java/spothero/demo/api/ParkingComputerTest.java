package spothero.demo.api;

import org.junit.Test;
import org.mockito.Mock;
import spothero.demo.model.ParkingRange;

import static org.junit.Assert.*;

public class ParkingComputerTest {

    @Mock private ParkingRates rates;
    @Mock private ParkingRange range;

    @Test
    public void testBasic() {
        ParkingComputer computer = new ParkingComputer(rates, range);

        assertEquals("0", computer.compute());
    }

}