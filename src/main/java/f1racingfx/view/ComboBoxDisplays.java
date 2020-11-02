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

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ComboBoxDisplays {
    private ComboBox<SeasonYear> categories;
    private ComboBox<F1DomainPath> raceData;
    private Text textBox;
    private  final Map<SeasonYear, List<F1DomainPath>> f1Map;
    private  final ObservableList<SeasonYear> seasonRange;

    public ComboBoxDisplays() throws IOException {
        f1Map = getSeasonRace();
        seasonRange = observableArrayList(f1Map.keySet());
        textBox = new Text();
        setUpCategories();
        setUpRaceData();
    }

    private static class SeasonStringConverter extends StringConverter<F1DomainPath> {
        private final String separator1 = ", Season:  ";
        private final String separator2 = "Country:  ";

        @Override
        public String toString(F1DomainPath season) {
            if (season == null)
                return null;
            else
                return separator2 + season.getCountry() + " Race: " + season.getRacename() + separator1 + season.getSeason();
        }

        @Override
        public F1DomainPath fromString(String string) {
            int year = Integer.parseInt(string.split(separator1)[2]);

           try {
               for (F1DomainPath race1: getAllRaces()) {
                   if(race1.getSeason() == year)
                    return race1;
               }
           } catch (IOException e) {
               e.printStackTrace();
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
                        + "Country: " + newValue.getCountry();
                textBox.setText(displayText);
                textBox.setStyle("-fx-font: 15 georgia;");
                textBox.setVisible(true);
            }
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
}
