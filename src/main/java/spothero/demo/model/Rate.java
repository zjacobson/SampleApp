package spothero.demo.model;

import lombok.*;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@NoArgsConstructor
@AllArgsConstructor
@Data
@XmlRootElement(name="rate")
@XmlAccessorType(XmlAccessType.FIELD)
public class Rate {
    public static final Rate Unavailable = new Rate("unavailable", "0000-2400", "0");

    @Setter(AccessLevel.PACKAGE) String days;
    @Setter(AccessLevel.PACKAGE) String times;
    @Setter(AccessLevel.PACKAGE) String price;

    public LocalTime getStartTime() {
        return LocalTime.parse(times.substring(0, times.indexOf("-")), DateTimeFormat.forPattern("HHmm"));
    }

    public LocalTime getEndTime() {
        return LocalTime.parse(times.substring(times.indexOf("-")+1), DateTimeFormat.forPattern("HHmm"));
    }
}
