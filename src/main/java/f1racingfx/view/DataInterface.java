package f1racingfx.view;

import f1racingfx.models.NoData;
import java.io.IOException;

public interface DataInterface {
    BarGraphDriversData barGraph() throws NoData, IOException;
}
