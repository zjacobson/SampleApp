package spothero.demo.api;

import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import spothero.demo.model.Day;
import spothero.demo.model.ParkingRange;
import spothero.demo.model.ParkingRates;
import spothero.demo.model.Rate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class ParkingComputer {
    private final ParkingRates rates;
    private final ParkingRange range;

    ParkingComputer(ParkingRates rates, ParkingRange range) {
        this.rates = rates;
        this.range = range;
    }

    Rate compute() {

        Optional<Rate> rate = ratesForDay(range.startDay(), range.endDay()).stream().filter(this::rangeInRate).findFirst();
        return rate.orElse(Rate.Unavailable);
    }

    boolean rangeInRate(Rate rate) {
        //start and end times need to be fully within the times string
        LocalTime rangeStart = range.startTime();
        LocalTime rangeEnd = range.endTime();

        LocalTime rateStart = rate.getStartTime();
        LocalTime rateEnd = rate.getEndTime();

        return rangeStart.isAfter(rateStart) && rangeEnd.isBefore(rateEnd);
    }


    List<Rate> ratesForDay(Day startDay, Day endDay) {
        if (startDay != endDay) {
            return Collections.emptyList();

        }
        return rates.ratesForDay(startDay);
    }


}
