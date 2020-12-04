package f1racingfx.models;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ReadRaceData {

    public ReadRaceData() { }

    public static String readData()
        {
            Path path = Path.of("build", "resources", "main", "Races.txt").toAbsolutePath();
            StringBuilder data = new StringBuilder();
            try (Stream<String> stream = Files.lines(Paths.get(String.valueOf(path)), StandardCharsets.UTF_8)) {
                stream.forEach(s -> data.append(s).append("\n"));
            } catch (IOException e) {
                e.printStackTrace();

            }

            return data.toString();
        }
}
