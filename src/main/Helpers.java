import java.io.File;
import java.net.URL;

public class Helpers {
  public static File loadFile(URL path) {
    return new File(path.getFile());
  }
}
