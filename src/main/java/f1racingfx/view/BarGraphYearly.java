package f1racingfx.view;

import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;


public class BarGraphYearly {
    final  Stage stage2;
    final  CategoryAxis xAxis;
    final  NumberAxis yAxis;
    public Scene scene2;
    final  ApplicationDisplays fetchB;
    final BarChart<String,Number> bc;
    final XYChart.Series<String,Number> series1;

    public BarGraphYearly() {
        stage2 = new Stage();
        xAxis = new CategoryAxis();
        yAxis = new NumberAxis();
        fetchB = new ApplicationDisplays();
        bc = new BarChart<>(xAxis, yAxis);
        series1 = new XYChart.Series<>();
        barGraph();
    }

    public void barGraph() {
        chartLabelDesign1();
        series1.setName("Season Data");
        series1.getData().add(new XYChart.Data<>("ANTIENT_50_70", fetchB.getCountYears("ANTIENT_50_70")));
        series1.getData().add(new XYChart.Data<>("YEAR_71_90", fetchB.getCountYears("YEAR_71_90")));
        series1.getData().add(new XYChart.Data<>("YEAR_91_2000", fetchB.getCountYears("YEAR_91_2000")));
        series1.getData().add(new XYChart.Data<>("YEAR_2000_PRESENT", fetchB.getCountYears("YEAR_2000_PRESENT")));
        chartScene2(series1);
    }

    private void chartLabelDesign1() {
        bc.setTitle("Season Summary");
        bc.setStyle("-fx-font: 20 georgia;");
        xAxis.setLabel("Season Years");
        xAxis.setStyle("-fx-font: 20 georgia;");
        yAxis.setLabel("Number of Races");
        yAxis.setStyle("-fx-font: 20 georgia;");
    }

    private void chartScene2(XYChart.Series<String,Number> series1) {
        scene2  = new Scene(bc,400,600);
        bc.getData().addAll(series1);
        stage2.setScene(scene2);
        stage2.show();
    }
}
