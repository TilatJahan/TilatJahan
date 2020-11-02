package f1racingfx.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ReadRaceData {

    public ReadRaceData() { }

    public static String readData() throws IOException
        {
            Path path = Path.of("build", "resources", "main", "Races.txt").toAbsolutePath();
            BufferedReader buff = Files.newBufferedReader(path);
            StringBuilder data = new StringBuilder();
         /*   while (buff.ready())
            {
                data.append((char)buff.read());
            }
            return data.toString();
        }*/
            try (Stream<String> stream = Files.lines(Paths.get(String.valueOf(path)), StandardCharsets.UTF_8)) {
                stream.forEach(s -> data.append(s).append("\n"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            return data.toString();
        }
}
