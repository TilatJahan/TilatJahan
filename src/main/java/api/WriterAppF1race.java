package api;

import java.io.File;
import java.net.URISyntaxException;

public class WriterAppF1race {

   public  static String setPath() {
       String path = "";
       try {
           path = getPath();

       } catch (URISyntaxException e) {
           System.out.println(e.toString());
       }
       return path;
   }

    public static String getPath() throws URISyntaxException {
       String path = ClassLoader.getSystemClassLoader().getResource("").toURI().getPath();

       path = path.substring(0, path.indexOf("classes"));
       path += "resources" + File.separator + "main"+ File.separator;
        System.out.println(path);
       return path;
    }
}
