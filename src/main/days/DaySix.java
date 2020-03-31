import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class DaySix {

  public void lights() {
    int[][] lights = new int[1000][1000];
    File file = Helpers.loadFile(Days.class.getResource("resources/day-six.txt"));
    int totalBrightness = 0;

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      while (reader.ready()) {
        String command = reader.readLine();
        String numbers = command.replaceAll("[^0-9]+", " ");
        int[] instructions = Arrays.stream(numbers.trim().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
        for (int i = instructions[0]; i <= instructions[2]; i++) {
          for (int j = instructions[1]; j <= instructions[3]; j++) {
            if (command.startsWith("turn on")) {
              lights[i][j] += 1;
            } else if (command.startsWith("turn off")) {
              if (lights[i][j] > 0) {
                lights[i][j] -= 1;
              }
            } else if (command.startsWith("toggle")) {
              lights[i][j] += 2;
            }
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    for (int[] light : lights) {
      for (int brightness : light) {
        totalBrightness += brightness;
      }
    }

    System.out.println(totalBrightness);
  }
}
