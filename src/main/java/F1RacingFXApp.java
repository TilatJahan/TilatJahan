import api.RacingAPI;
import f1racingfx.view.InitiateApplication;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class F1RacingFXApp extends Application {
    InitiateApplication IniApp;
    public Stage errorStage;
    private Scene sceneError;
    private VBox errorBox;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            RacingAPI raceData = new RacingAPI("results/1");
            raceData.makeConnection();
            IniApp = new InitiateApplication(primaryStage);
        } catch (IOException e) {
            sceneException(primaryStage);
        }
    }
        private void sceneException(Stage stage)
        {
            errorStage = stage;
            setErrorScene();
            errorStage.setScene(sceneError);
            errorStage.setTitle("Caught An Exception");
            errorStage.show();
        }

        private void setErrorScene()
        {
            Label errorMessage = new Label("Data not received from the API");
            errorBox = new VBox(errorMessage);
            sceneError = new Scene(errorBox,400,100);
            errorBox.setPadding(new Insets(30,30,30,30));
            errorBox.setStyle("-fx-font: 20 georgia;");
        }
    }

