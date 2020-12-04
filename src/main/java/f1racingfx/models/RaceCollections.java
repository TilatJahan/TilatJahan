package f1racingfx.models;

import java.util.*;
import java.util.stream.IntStream;

public class RaceCollections
{
private  static List<F1DomainPath> race;

    private RaceCollections() { }

    public  static  List<F1DomainPath> getAllRaces()  {
        return getRace();
    }

    private static  List<F1DomainPath> getRace(){
    String fileData = ReadRaceData.readData();
    race = new ArrayList<>();
    int length = fileData.split("\n").length;
    IntStream.range(0, length).forEach(i -> race.add(insertData(i, fileData)));
    return race;
}


private static F1DomainPath insertData(int a, String fileData)
{
    String season1 = fileData.split("\n")[a].split(",")[0].trim();
    int season = Integer.parseInt(season1);
    String raceName = fileData.split("\n")[a].split(",")[1].trim();
    String country = fileData.split("\n")[a].split(",")[2].trim();
    String givenName = fileData.split("\n")[a].split(",")[3].trim();
    String nationality = fileData.split("\n")[a].split(",")[4].trim();
    return new F1DomainPath( raceName, season, country,givenName,nationality);

}

public  static Map<SeasonYear, List<F1DomainPath>> getSeasonRace() {
    Map<SeasonYear, List<F1DomainPath>> Season = new HashMap<>();
    getRace().forEach(r -> {getRaceToYearCategory(Season, r);});
    return Season;
}

private static void getRaceToYearCategory(Map<SeasonYear, List<F1DomainPath>> season, F1DomainPath r) {
    Arrays.asList(SeasonYear.values()).forEach(yearCategory -> {
        if(r.getSeason()>=yearCategory.getMin()&& r.getSeason()<=yearCategory.getMax())
            addToRaceMap(yearCategory, r, season);
    });
}

    private static void addToRaceMap(SeasonYear key, F1DomainPath race, Map<SeasonYear, List<F1DomainPath>> map){
        if(!map.containsKey(key))
            map.put(key,new ArrayList<>((Collections.singletonList(race))));
            else
                map.get(key).add(race);

        }
    }


