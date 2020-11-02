package api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.ws.rs.HttpMethod;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class RacingAPI {
    private String urlString = "http://ergast.com/api/f1/";

    private HttpURLConnection connection = null;
    private String JsonObj;

    public RacingAPI(String JsonObj) {
        this.JsonObj = JsonObj;
    }

    public RacingAPI(HttpURLConnection connection) {
        this.connection = connection;
    }

    public void makeConnection()
    {
        try
        {
            URL url = getURL();
            getResponse(url);

            final InputStream inputStream = connection.getInputStream();
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            JSONParser parser = new JSONParser();
            JSONObject response = (JSONObject) parser.parse(bufferedReader.readLine());
            System.out.println("Printing Data");
            JSONObject resp_obj = (JSONObject) response.get("MRData");
            JSONObject raceData = (JSONObject) resp_obj.get("RaceTable");
            JSONArray results = (JSONArray) raceData.get("Races");

            String path = WriterAppF1race.setPath();
            RandomWriterF1race rw = new RandomWriterF1race(path, "Races.txt");
            resultFile(rw,results);
        } catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }

        public static ArrayList<String> subArray(JSONArray results)
        {
            ArrayList<String> country =new ArrayList<>();
            for (JSONObject factObj : (Iterable<JSONObject>) results)
            {
                JSONObject circuit = (JSONObject) factObj.get("Circuit");
                JSONObject location = (JSONObject) circuit.get("Location");
                String country1 = (String) location.get("country");
                country.add(country1);
            }
            return country;
        }


        public static void resultFile(RandomWriterF1race rw, JSONArray results) throws IOException
        {
            int i=0;
            ArrayList<String> countryList;
            countryList = subArray(results);
            for (Object r : results) {
                JSONObject result = (JSONObject) r;
                String toFile = result.get("season") + "," + result.get("raceName") + "," + countryList.get(i) + "\n";
                System.out.println(toFile);
                i=i+1;
                rw.WriteData(toFile);
            }

        }

    private URL getURL() throws MalformedURLException
    {
        this.urlString += this.JsonObj + ".json";
        urlString += "?&limit=1000";
        System.out.println(urlString);
        return new URL(urlString + "/Races/");

    }

    private void getResponse(URL url) throws IOException
    {
        connection = (HttpURLConnection) url.openConnection();
        int timeOut = 1000;
        connection.setConnectTimeout(timeOut);
        connection.setRequestMethod(HttpMethod.GET);
    }

}