package f1racingfx.models;


import java.util.Objects;

public class F1DomainPath {

    private String  racename;
    private  int season;
    private String country;

    public F1DomainPath(String racename, int season, String country) {
        this.racename = racename;
        this.season = season;
        this.country = country;
    }

    public String getRacename() {
        return racename;
    }


    public int getSeason() {
        return season;
    }


    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return
                "Season =  " + season +'\n'+
                "Racename = " + racename + '\n' +
                "Country = " + country;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        F1DomainPath f1DomainPath = (F1DomainPath) o;
        return season == f1DomainPath.season &&
                racename.equals(f1DomainPath.racename) &&
                country.equals(f1DomainPath.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(racename, season, country);
    }
}
