package f1racingfx.models;

public class NoData extends Exception {
    public NoData() {
        super("API did not return any data for the Races");
    }
}

