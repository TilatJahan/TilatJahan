package f1racingfx.view;

import f1racingfx.models.F1DomainPath;
import f1racingfx.models.SeasonYear;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class InitiateApplication {

    final ComboBox<SeasonYear> seasonRange;
    final ComboBox<F1DomainPath> raceData;
    public Stage primary;
    private Scene scene1, scene2;
    private RadioButton rb1, rb2, rb3;
    final Text textBox, textBox1;
    final Button button1, button2;
    final VBox set, set2;
    final Label optionHead,queryLabel;
    private ObservableList<Node> list;
    private ObservableList<javafx.scene.Node> list1;
    final Image image;
    final ImageView imageView;



    private void sceneFirst() {
        list1.addAll(queryLabel, seasonRange, raceData, textBox, textBox1, button1, imageView);
        scene1 = new Scene(set, 800, 500);
        primary.setScene(scene1);
        primary.setTitle("F1 Car Racing Data");
    }


    private void sceneSecond() {
        list.addAll(optionHead, rb1, rb2, rb3, button2);
        scene2 = new Scene(set2, 800, 500, Color.RED);
        primary.setScene(scene1);
        primary.show();
    }

    public InitiateApplication(Stage primaryStage)  throws IOException {
        primary = primaryStage;
        ApplicationDisplays tools = new ApplicationDisplays();
        set = new VBox();
        seasonRange = tools.getCategories();
        raceData = tools.getRaceData();
        textBox = tools.getTextBox();
        textBox1 = tools.getTextBox1();
        set2 = new VBox(5);
        button1 = new Button("Get Charts");
        button2 = new Button("Get Data");
        optionHead = new Label("Data Distribution in Charts");
        queryLabel = new Label("Select the Racing Data from the year 1950 - 2018");
        image = new Image(new FileInputStream("f1-car.jpg"));
        imageView = new ImageView(image);
        firstPane();
        commandButtons();
    }

    private void firstPane() {
        set.setSpacing(10);
        imageView.setX(10);
        imageView.setY(15);
        imageView.setFitHeight(200);
        imageView.setFitWidth(200);
        imageView.setImage(image);
        imageView.setPreserveRatio(true);

        set.setMargin(queryLabel, new Insets(20, 20, 20, 20));
        set.setMargin(seasonRange, new Insets(20, 20, 20, 20));
        set.setMargin(textBox1, new Insets(20, 20, 20, 20));
        set.setMargin(raceData, new Insets(20, 20, 20, 20));
        set.setMargin(textBox, new Insets(20, 20, 20, 20));
        set.setMargin(button1, new Insets(-20, 20,20, 20));
        set.setMargin(imageView, new Insets(-300, -300, -400, 550));
        button1.setStyle("-fx-font: 18 Georgia; -fx-base: #b6e7c9;");
        list1 = set.getChildren();
        sceneFirst();
    }


    private void commandButtons() {
        button1.setOnAction(e -> primary.setScene(scene2));
        button2.setOnAction(e -> primary.setScene(scene1));
        CreateRadioButton();
    }

    private void CreateRadioButton() {

        final ToggleGroup group = new ToggleGroup();
        rb1 = new RadioButton("Distribution of Races conducted in every Country");
        rb2 = new RadioButton("Number of Races Conducted between the mentioned years");
        rb3 = new RadioButton("Drivers Winning data");
        rb1.setToggleGroup(group);
        rb1.setUserData("Pie Chart");
        rb2.setToggleGroup(group);
        rb2.setUserData("Bar Graph");
        rb3.setToggleGroup(group);
        rb3.setUserData("Chart Line");
        pane2Setup();
        setStyle();
        new RadioButtonSelection().setToggleGroup(group);
        sceneSecond();
    }

    private void pane2Setup() {

        set2.setSpacing(10);
        set2.setMargin(optionHead, new Insets(20, 20, 20, 20));
        set2.setMargin(rb1, new Insets(20, 20, 20, 20));
        set2.setMargin(rb2, new Insets(20, 20, 20, 20));
        set2.setMargin(rb3, new Insets(20, 20, 20, 20));
        set2.setMargin(button2, new Insets(20, 20, 20, 20));
        list = set2.getChildren();
    }

    private void setStyle() {
        optionHead.setFont(new Font("Georgia", 30));
        optionHead.setTextFill(Color.web("#0076a3"));
        queryLabel.setFont(new Font("Georgia", 30));
        queryLabel.setTextFill(Color.web("#0076a3"));
        rb1.setFont(Font.font("Georgia", FontWeight.BOLD, FontPosture.REGULAR, 12));
        rb3.setFont(Font.font("Georgia", FontWeight.BOLD, FontPosture.REGULAR, 12));
        rb2.setFont(Font.font("Georgia", FontWeight.BOLD, FontPosture.REGULAR, 12));
        button2.setStyle("-fx-font: 15 Georgia; -fx-base: #b6e7c9;");
    }
}
