package spothero.demo.api;

import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import spothero.demo.model.Day;
import spothero.demo.model.ParkingRange;

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

    String compute() {

        Optional<ParkingRates.Rate> rate = ratesForDay(range.startDay()).stream().filter(this::rangeInRate).findFirst();
        if (rate.isPresent()) {
            return rate.get().price;
        }
        return "0";
    }

    boolean rangeInRate(ParkingRates.Rate rate) {
        String times = rate.times;
        //start and end times need to be fully within the times string
        LocalTime rangeStart = range.startTime();
        LocalTime rangeEnd = range.endTime();

        LocalTime rateStart = getStartTime(times);
        LocalTime rateEnd = getEndTime(times);

        return rangeStart.isAfter(rateStart) && rangeEnd.isBefore(rateEnd);
    }


    List<ParkingRates.Rate> ratesForDay(Day day) {
        return rates.rates.stream().filter(rate -> rate.days.contains(day.name())).collect(Collectors.toList());
    }

    LocalTime getStartTime(String timeRange) {
        return LocalTime.parse(timeRange.substring(0, timeRange.indexOf("-")), DateTimeFormat.forPattern("HHmm"));
    }

    LocalTime getEndTime(String timeRange) {
        return LocalTime.parse(timeRange.substring(timeRange.indexOf("-")+1), DateTimeFormat.forPattern("HHmm"));
    }
}
