package spothero.demo.api;

import spothero.demo.model.ParkingRange;

class ParkingComputer {
    private final ParkingRates rates;
    private final ParkingRange range;

    ParkingComputer(ParkingRates rates, ParkingRange range) {
        this.rates = rates;
        this.range = range;
    }

    String compute() {
        if (rates.hasRates()) {
            return "1";
        }
        return "0";
    }
}
