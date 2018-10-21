package spothero.demo.model;

import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public
class ParkingRates {
    List<Rate> rates;


    public static ParkingRates testData() {
        return  new GsonBuilder().create().fromJson(ParkingRates.testData, ParkingRates.class);
    }


    public List<Rate> ratesForDay(Day startDay) {
        return rates.stream().filter(rate -> rate.days.contains(startDay.name())).collect(Collectors.toList());
    }

    private static String testData = "{" +
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
