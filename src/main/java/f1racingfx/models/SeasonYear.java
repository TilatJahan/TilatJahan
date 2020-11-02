package f1racingfx.models;

public enum SeasonYear {
    ANTIENT_50_70(1950, 1970),
    YEAR_71_90(1971, 1990),
    YEAR_91_2000(1991,2000),
    YEAR_2000_PRESENT(2001, 2020);

    private final int min;
    private  final int max;

    SeasonYear(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}


