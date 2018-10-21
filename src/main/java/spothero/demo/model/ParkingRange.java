package spothero.demo.model;

import lombok.Data;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

@Data
public class ParkingRange {
    private final String start;
    private final String end;

    public Day startDay() {

        int dayOfWeek = toDateTime(start).getDayOfWeek();
        return Day.from(dayOfWeek);
    }

    public Day endDay() {
        return Day.mon;
    }

    DateTime toDateTime(String iso8601) {
        return DateTime.parse(iso8601, ISODateTimeFormat.dateTimeNoMillis());
    }
}
