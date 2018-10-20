package spothero.demo.api;

import com.google.gson.GsonBuilder;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingRatesTest {
    
    @Test public void parseFromJson() {
        ParkingRates parkingRates = new GsonBuilder().create().fromJson(testData, ParkingRates.class);
        assertEquals(5, parkingRates.rates.size());
        assertEquals("wed", parkingRates.rates.get(2).days);
        assertEquals("0100-0700", parkingRates.rates.get(4).times);
        assertEquals("1500", parkingRates.rates.get(0).price);
    }

    String testData = "{" +
            "    \"rates\": [" +
            "        {" +
            "            \"days\": \"mon,tues,thurs\"," +
            "            \"times\": \"0900-2100\"," +
            "            \"price\": 1500" +
            "        }," +
            "        {" +
            "            \"days\": \"fri,sat,sun\"," +
            "            \"times\": \"0900-2100\"," +
            "            \"price\": 2000" +
            "        }," +
            "        {" +
            "            \"days\": \"wed\"," +
            "            \"times\": \"0600-1800\"," +
            "            \"price\": 1750" +
            "        }," +
            "        {" +
            "            \"days\": \"mon,wed,sat\"," +
            "            \"times\": \"0100-0500\"," +
            "            \"price\": 1000" +
            "        }," +
            "        {" +
            "            \"days\": \"sun,tues\"," +
            "            \"times\": \"0100-0700\"," +
            "            \"price\": 925" +
            "        }" +
            "    ]" +
            "}";
}