import api.RacingAPI;

import java.io.IOException;

public class MainApplication {



        public static void main(String[] args) throws IOException {

            RacingAPI raceData = new RacingAPI("races");
            raceData.makeConnection();

        }
    }


