package f1racingfx.view;

import f1racingfx.models.F1DomainPath;

import static f1racingfx.models.RaceCollections.getAllRaces;
import static f1racingfx.models.RaceCollections.getSeasonRace;
import f1racingfx.models.SeasonYear;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

import java.util.List;
import java.util.Map;

public class ApplicationDisplays {
    private ComboBox<SeasonYear> categories;
    private ComboBox<F1DomainPath> raceData;
    final Text textBox,textBox1;
    private  final Map<SeasonYear, List<F1DomainPath>> f1Map;
    private  final ObservableList<SeasonYear> seasonRange;
    private String displayText;



    public ApplicationDisplays() {
        f1Map = getSeasonRace();
        seasonRange = observableArrayList(f1Map.keySet());
        textBox = new Text();
        textBox1 = new Text();
        setUpCategories();
        setUpRaceData();
    }

    private static class SeasonStringConverter extends StringConverter<F1DomainPath> {
        private final String separator1 = ", Season:  ";


        @Override
        public String toString(F1DomainPath season) {
            if (season == null)
                return null;
            else
                return  " Race Name: " + season.getRacename() + separator1 + season.getSeason();
        }

        @Override
        public F1DomainPath fromString(String string) {
            int year = Integer.parseInt(string.split(separator1)[2]);

            for (F1DomainPath race1: getAllRaces()) {
                if(race1.getSeason() == year)
                 return race1;
            }
            return null;
        }
    }


    private  void setUpCategories(){
        categories = new ComboBox<>();
        categories.getItems().addAll(sortSeasons());
        categories.setPromptText("----Select Season Year----");
        categories.setStyle("-fx-text-fill: burlywood;" + "-fx-font-color:blue");
        categories.setStyle("-fx-font-family: Georgia;" + "-fx-font-size: 15");
        categories.valueProperty().addListener((observable, oldValue, newValue) -> {
            textBox.setVisible(false);
            textBox1.setVisible(true);
            raceData.getItems().clear();
            raceData.getItems().addAll(f1Map.get(newValue));
            raceData.setStyle("-fx-font-family: Georgia;" + "-fx-font-size: 15");
            raceData.setVisible(true);
        });
    }


    private  ObservableList<SeasonYear> sortSeasons()
    {
        return seasonRange.sorted((o1, o2) -> {
            if (o1.getMin() < o2.getMin())
                return -1;
            else if (o1.getMin() > o2.getMin())
                return 1;
            else
                return 0;
        });
    }

    private  void  setUpRaceData()
    {
        raceData = new ComboBox<>();
        raceData.setPromptText("----Select Data----");
        raceData.setConverter(new SeasonStringConverter());
        raceData.setVisible(false);
        createSeasonSelectionListener();
        createYearSelectionListener();
        manageRaceDataComboUpdate();

    }

    private void manageRaceDataComboUpdate()
    {
        raceData.setButtonCell(new ListCell<>(){
    @Override
    protected void updateItem(F1DomainPath race, boolean empty) {
        super.updateItem(race, empty);

        if (empty || race == null)
            setText("------Select Data-------");

        else {
            SeasonStringConverter converter = new SeasonStringConverter();
            setText(converter.toString(race));
        }
    }
        });
    }

    private  void createSeasonSelectionListener()
    {
        raceData.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null)
            {
                String displayText = "Season: "+ newValue.getSeason() + "\n"
                    + "RaceName: " + newValue.getRacename() +"\n"
                        + "Country: " + newValue.getCountry()+"\n"
                        + "Driver Won: " + newValue.getGivenName() +"\n"
                        + "Driver Nationality: " + newValue.getNationality();
                textBox.setText(displayText);
                textBox.setStyle("-fx-font: 15 georgia;");
                textBox.setVisible(true);
            }
        });
    }

    public int getCountYears(String seasonYear){

        switch (seasonYear) {
            case "ANTIENT_50_70":
                return this.f1Map.get(SeasonYear.ANTIENT_50_70).size();
            case "YEAR_71_90":
                return this.f1Map.get(SeasonYear.YEAR_71_90).size();
            case "YEAR_91_2000":
                return this.f1Map.get(SeasonYear.YEAR_91_2000).size();
            case "YEAR_2000_PRESENT":
                return this.f1Map.get(SeasonYear.YEAR_2000_PRESENT).size();
            default:
                return 0;
        }
    }

    private  void createYearSelectionListener()
    {
        categories.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null) {
                if (categories.getSelectionModel().getSelectedItem().equals(SeasonYear.ANTIENT_50_70))
                     displayText = "Number of Races held between the Years 1950 to 1970 =  "  + getCountYears("ANTIENT_50_70");
                else if (categories.getSelectionModel().getSelectedItem().equals(SeasonYear.YEAR_71_90))
                     displayText = "Number of Races held between the Years 1970 to 1990 =  " + getCountYears("YEAR_71_90");
                 else if (categories.getSelectionModel().getSelectedItem().equals(SeasonYear.YEAR_91_2000))
                     displayText = "Number of Races held between the Years 1991 to 2000 =  " + getCountYears("YEAR_91_2000");
                 else
                     displayText = "Number of Races held between the Years 2000 to 2018 =  "  + getCountYears("YEAR_2000_PRESENT");
            }
                textBox1.setText(displayText);
                textBox1.setStyle("-fx-font: 15 georgia; -fx-base: #b6e7c8;");
                textBox1.setVisible(true);

        });
}

    public ComboBox<SeasonYear> getCategories() {
        return categories;
    }

    public ComboBox<F1DomainPath> getRaceData() {
        return raceData;
    }

    public Text getTextBox() {
        return textBox;
    }

    public Text getTextBox1(){
        return textBox1;

    }

}
