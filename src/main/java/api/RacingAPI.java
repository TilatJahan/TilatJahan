package api;


import org.json.JSONObject;
import javax.ws.rs.HttpMethod;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class RacingAPI {
    private String urlString = "http://ergast.com/api/f1/";
    private HttpURLConnection connection = null;
    private String JsonObj;

    public RacingAPI(String JsonObj) {this.JsonObj = JsonObj; }

    public RacingAPI(HttpURLConnection connection) {
        this.connection = connection;
    }
        public void makeConnection() throws IOException {

            URL url = getURL();
            getResponse(url);

            final InputStream inputStream = connection.getInputStream();
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//Fetch data from the json
            Stream<String> lines = bufferedReader.lines();
            List<String> races = lines.map(line -> new JSONObject(line).getJSONObject("MRData").getJSONObject("RaceTable")
                    .getJSONArray("Races"))
                    .map(jsonArray ->
                    { List<String> objs = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {

                            objs.add(jsonArray.getJSONObject(i).get("season").toString() + ","
                                    + jsonArray.getJSONObject(i).get("raceName") + ","
                                    + jsonArray.getJSONObject(i).getJSONObject("Circuit").getJSONObject("Location").get("country") + ","
                                    + jsonArray.getJSONObject(i).getJSONArray("Results").getJSONObject(0).getJSONObject("Driver").get("givenName") +","
                                    + jsonArray.getJSONObject(i).getJSONArray("Results").getJSONObject(0).getJSONObject("Driver").get("nationality"));

                        }
                        return objs;
                    })

                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
                    writeToFile(races);


        }

       private void writeToFile( List<String> races) throws IOException
       {
           String path = WriterAppF1race.setPath();
           RandomWriterF1race rw = new RandomWriterF1race(path, "Races.txt");
           for (String race : races) {
               rw.WriteData(race);
               rw.WriteData("\n");
           }
       }


    private URL getURL() throws MalformedURLException
    {
        this.urlString += this.JsonObj + ".json";
        urlString += "?&limit=8000";
        System.out.println(urlString);
        return new URL(urlString);
    }

    private void getResponse(URL url) throws IOException
    {
        connection = (HttpURLConnection) url.openConnection();
        int timeOut = 1000;
        connection.setConnectTimeout(timeOut);
        connection.setRequestMethod(HttpMethod.GET);
    }

}