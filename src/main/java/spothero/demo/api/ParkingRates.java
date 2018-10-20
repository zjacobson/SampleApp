package spothero.demo.api;

import lombok.Data;

import java.util.List;

@Data
class ParkingRates {
    static ParkingRates testData() {
        return new ParkingRates();
    }

    List<Rate> rates;

    public boolean hasRates() {
        return false;
    }

    static class Rate {
        String days;
        String times;
        String price;
    }
}
