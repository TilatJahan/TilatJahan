package f1racingfx.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import java.io.IOException;

public class RadioButtonSelection {
    private BarGraphDriversData br;
    private BarGraphYearly bd;
    private PieDisplayCountryData pd;


    public RadioButtonSelection(){
    }

    public void setToggleGroup(ToggleGroup group)
    {
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                if (group.getSelectedToggle() != null) {
                    System.out.println(group.getSelectedToggle().getUserData().toString());
                    try {
                        if (group.getSelectedToggle().getUserData() == "Pie Chart")
                            pd = new PieDisplayCountryData();


                      else if ((group.getSelectedToggle().getUserData() == "Bar Graph"))
                          bd = new BarGraphYearly();

                      else if ((group.getSelectedToggle().getUserData() == "Chart Line"))
                          br = new BarGraphDriversData();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        });

    }
}

