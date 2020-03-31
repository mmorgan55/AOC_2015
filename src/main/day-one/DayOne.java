import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class DayOne {

  public void whichFloor() throws FileNotFoundException {
    URL path = DayOne.class.getResource("day-one.txt");
    File file = new File(path.getFile());
    int floor = 0;

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      char[] chars = new char[(int) file.length()];
      reader.read(chars);
      for (int i = 0; i < chars.length; i++) {
        if (chars[i] == '(') {
          floor++;
        } else {
          floor--;
          if (floor == -1) {
            System.out.println(i + 1);
            break;
          }
        }
      }
      System.out.println(floor);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}

