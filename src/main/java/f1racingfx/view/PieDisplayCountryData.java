package f1racingfx.view;
import f1racingfx.models.F1DomainPath;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.Group;
import static f1racingfx.models.RaceCollections.getAllRaces;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class PieDisplayCountryData {

    private List<F1DomainPath> race;
    private  List<String> Countries;
    private  ApplicationDisplays fetch;
    public  Scene scene2;
    final Stage stage2;

    public PieDisplayCountryData() throws IOException {
        race = new ArrayList<>();
        stage2 = new Stage();
        scene2 = new Scene(new Group());
        race = getAllRaces();
        pieChart();
    }

    public void pieChart()  {
            Countries = race.stream().map(F1DomainPath::getCountry).distinct().collect(Collectors.toList());
            fetch = new ApplicationDisplays();
            PieChart.Data[] data = new PieChart.Data[Countries.size()];
            for (int i = 0; i < Countries.size(); i++) {
                String country = Countries.get(i);
                data[i] = new PieChart.Data(country, race.stream().filter(j -> j.getCountry().equals(country)).count());
            }
            showPie(data);
    }

    private void showPie(PieChart.Data[] data) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(data);
        final PieChart chart = new PieChart(pieChartData);
        pieDesign(chart);
        ((Group) scene2.getRoot()).getChildren().add(chart);
        stage2.setScene(scene2);
        stage2.show();
    }

    private void pieDesign(PieChart chart) {
        stage2.setTitle("Pie Chart");
        stage2.setWidth(700);
        stage2.setHeight(700);
        chart.setTitle("Distribution of Races in Countries");
        chart.setStyle("-fx-font: 10 georgia");
        chart.setLabelLineLength(10);
        chart.setMinSize(700,700);
        chart.setLegendSide(Side.LEFT);
    }
}




