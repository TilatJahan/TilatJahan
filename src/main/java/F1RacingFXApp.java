import f1racingfx.models.F1DomainPath;
import f1racingfx.models.SeasonYear;
import f1racingfx.view.ComboBoxDisplays;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class F1RacingFXApp extends Application {

    private ComboBoxDisplays comboBoxes;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException
    {
        comboBoxes = new ComboBoxDisplays();
        BorderPane pane = new BorderPane();
        setUpBorderPane(pane);

        Scene scene = new Scene(pane, 1000, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("F1 Car Race Data");
        primaryStage.show();
    }

    private  void  setUpBorderPane(BorderPane borderPane)
    {
        HBox hBox = new HBox();
        setUpHBox(hBox);
        borderPane.setTop(hBox);
    }

    private void setUpHBox(HBox hBox) {
        hBox.setSpacing(10);
        ComboBox<SeasonYear> seasonRange = comboBoxes.getCategories();
        ComboBox<F1DomainPath> raceData = comboBoxes.getRaceData();
        Text textBox = comboBoxes.getTextBox();
        hBox.getChildren().addAll(seasonRange,raceData,textBox);
        HBox.setMargin(seasonRange, new Insets(20,5,5,10));
        HBox.setMargin(raceData,new Insets(20,5,5,5));
        HBox.setMargin(textBox,new Insets(20,5,5,5));
    }
}
