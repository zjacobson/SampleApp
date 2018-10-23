package spothero.demo.model;

public enum Day {
    mon,tues,wed,thur,fri,sat,sun;

    public static Day from(int jodaConst) {
        switch (jodaConst) {
            case 1: return mon;
            case 2: return tues;
            case 3: return wed;
            case 4: return thur;
            case 5: return fri;
            case 6: return sat;
            case 7: return sun;
            default:
                throw new IllegalArgumentException("unexpected joda-time day of week: " + jodaConst);
        }
    }
}
