package f1racingfx.models;


import java.util.Objects;

public class F1DomainPath {

    final String  racename;
    final  int season;
    final String country;
    final String givenName;
    final String nationality;

    public F1DomainPath(String racename, int season, String country,String givenName,String nationality) {
        this.racename = racename;
        this.season = season;
        this.country = country;
        this.givenName = givenName;
        this.nationality = nationality;
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

    public  String getGivenName() { return givenName;}

    public String getNationality(){ return nationality;}

    @Override
    public String toString() {
        return
                "Season =  " + season +'\n'+
                "Racename = " + racename + '\n' +
                "Country = " + country+ '\n' +
                "Driver Won = " + givenName +'\n'+
                "Driver Nationality = " + nationality +'\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        F1DomainPath f1DomainPath = (F1DomainPath) o;
        return season == f1DomainPath.season &&
                racename.equals(f1DomainPath.racename) &&
                country.equals(f1DomainPath.country) &&
                givenName.equals(f1DomainPath.givenName)&&
                nationality.equals(f1DomainPath.givenName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(racename, season, country, givenName,nationality);
    }
}
