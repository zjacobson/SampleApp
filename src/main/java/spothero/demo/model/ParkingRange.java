package spothero.demo.model;

import lombok.Data;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

@Data
public class ParkingRange {
    private final String start;
    private final String end;

    public Day startDay() {
        return Day.from(toDateTime(start).getDayOfWeek());
    }

    Day endDay() {
        return Day.from(toDateTime(end).getDayOfWeek());
    }

    DateTime toDateTime(String iso8601) {
        return DateTime.parse(iso8601, ISODateTimeFormat.dateTimeNoMillis()).toDateTime(DateTimeZone.UTC);
    }

    public LocalTime startTime() {
        return toDateTime(start).toLocalTime();
    }

    public LocalTime endTime() {
        return toDateTime(end).toLocalTime();
    }
}
