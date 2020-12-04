package f1racingfx.view;

import f1racingfx.models.F1DomainPath;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static f1racingfx.models.RaceCollections.getAllRaces;

public class BarGraphDriversData implements DataInterface {

    public  Scene scene2;
    private List<F1DomainPath> race;
    final   Stage stage2;
    final   CategoryAxis xAxis;
    final   NumberAxis yAxis;
    final   BarChart<String,Number> bc2;

    public BarGraphDriversData() {
        stage2 = new Stage();
        race = new ArrayList<>();
        xAxis = new CategoryAxis();
        yAxis = new NumberAxis();
        bc2 = new BarChart<>(xAxis, yAxis);
        barGraph();
    }

    public BarGraphDriversData barGraph()
    {
        chartLabelsDesign();
        XYChart.Series<String,Number>  series1 = new XYChart.Series<>();
        series1.setName("Drivers Data");
        race = getAllRaces();
        List<String> drivers = race.stream().map(F1DomainPath::getGivenName).distinct().collect(Collectors.toList());
        for (String driver : drivers) {
            series1.getData().add(new XYChart.Data<>(driver, race.stream().filter(j -> j.getGivenName().equals(driver)).count()));

        }

        createScene(series1);
        return null;
    }

    private  void createScene(XYChart.Series<String,Number>  series1) {
        scene2  = new Scene(bc2,1200,600);
        bc2.getData().addAll(series1);
        stage2.setScene(scene2);
        stage2.show();
    }

    private  void chartLabelsDesign() {
        bc2.setTitle("Drivers Summary");
        bc2.setStyle("-fx-font: 20 georgia;");
        xAxis.setLabel("Drivers");
        xAxis.setStyle("-fx-font: 20 georgia;");
        yAxis.setLabel("Number of times Won");
        yAxis.setStyle("-fx-font: 20 georgia;");

    }
}
