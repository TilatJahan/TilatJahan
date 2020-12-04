package  api;

import java.io.*;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;

import java.io.IOException;

public class RandomWriterF1race{

    private final String fullPath;
    private final OutputStream os;


    public RandomWriterF1race(String path, String fileName) throws FileNotFoundException {
        this.fullPath = createPath(path, fileName);
        this.os = createOutputStream();
    }

    private String createPath(String path, String fileName) {
        File dirs = new File(path);
        if (!dirs.exists())
            dirs.mkdirs();
        return path + fileName;
    }

    private OutputStream createOutputStream() throws FileNotFoundException {

            return new BufferedOutputStream(new FileOutputStream(fullPath));

    }

    public void WriteData(String fetched)  {

        try {

            FileWriter myWriter = new FileWriter(fullPath,true);
            if (!fullPath.isEmpty()) {
                myWriter.write(fetched);
            } else {
                myWriter.write(fetched);
            }
            myWriter.flush();
            myWriter.close();

        }

        catch (IOException e) {
            System.out.println("error");
            e.printStackTrace();
        }

    }
}
