package spothero.demo.api;

import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
class ParkingRates {
    static ParkingRates testData() {
        return  new GsonBuilder().create().fromJson(ParkingRates.testData, ParkingRates.class);
    }


    List<Rate> rates;

    boolean hasRates() {
        return rates != null && !rates.isEmpty();
    }

    @NoArgsConstructor
    @AllArgsConstructor
    static class Rate {

        String days;
        String times;
        String price;
    }



    static String testData = "{" +
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
