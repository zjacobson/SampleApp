package spothero.demo.model;

import lombok.Data;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalTime;
import org.joda.time.format.ISODateTimeFormat;

@Data
public class ParkingRange {
    private final String start;
    private final String end;

    public Day startDay() {
        return Day.from(toDateTime(getStart()).getDayOfWeek());
    }

    public Day endDay() {
        return Day.from(toDateTime(getEnd()).getDayOfWeek());
    }

    public LocalTime startTime() {
        return toDateTime(getStart()).toLocalTime();
    }

    public LocalTime endTime() {
        return toDateTime(getEnd()).toLocalTime();
    }

    private DateTime toDateTime(String iso8601) {
        return DateTime.parse(iso8601, ISODateTimeFormat.dateTimeNoMillis()).toDateTime(DateTimeZone.UTC);
    }
}
