package files;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.function.Consumer;

public class FileReader {
  private final String DIRECTORY_PATH = "G:\\JAVA\\SpringBootTask\\MainTask\\src\\main\\BackFiles";
  private final String PATH;

  public FileReader(String filePath){
    this.PATH = DIRECTORY_PATH + "\\" + filePath;
  }


  public void readFile(Consumer<String> consumer) {
    try (BufferedReader reader = new BufferedReader(new java.io.FileReader(this.PATH))){
      String line;

      while ((line = reader.readLine()) != null){
        consumer.accept(line);
      }

    } catch (IOException exception){
      System.out.println(exception);
    }
  }
}
