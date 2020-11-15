package db;


import java.util.Properties;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.InputStream;

public class UtilProp {

   static Properties prop = new Properties();

   public static void loadProperty(ServletContext servletContext) throws Exception {
      String filePath = "/WEB-INF/config.properties";
      InputStream stream = servletContext.getResourceAsStream(filePath);
      
      prop.load(stream);
      if (prop.isEmpty()) {
    	  System.out.println("dummy");
      }
   }

   public static String getProp(String key) {
      return prop.getProperty(key).trim();
   }
}
