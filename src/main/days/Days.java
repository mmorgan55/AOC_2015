import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Days {

  public void whichFloor() {
    int floor = 0;

    File file = loadFile(Days.class.getResource("resources/day-one.txt"));

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

  public void wrappingPaper() {
    File file = loadFile(Days.class.getResource("resources/day-two.txt"));
    int wrappingPaperTotal = 0;
    int ribbonTotal = 0;
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      while (reader.ready()) {
        int smallSide = Integer.MAX_VALUE;
        int[] dims = Arrays.stream(reader.readLine().split("x"))
            .mapToInt(Integer::parseInt)
            .toArray();

        int smallPerimeter = 2 * dims[0] + 2 * dims[1];
        if ((2 * dims[0] + 2 * dims[2]) < smallPerimeter) {
          smallPerimeter = 2 * dims[0] + 2 * dims[2];
        }

        if ((2 * dims[1] + 2 * dims[2]) < smallPerimeter) {
          smallPerimeter = 2 * dims[1] + 2 * dims[2];
        }

        ribbonTotal += smallPerimeter + (dims[0] * dims[1] * dims[2]);

        int lw = (dims[0] * dims[1]);
        if (lw < smallSide) {
          smallSide = lw;
        }

        int wh = (dims[1] * dims[2]);
        if (wh < smallSide) {
          smallSide = wh;
        }

        int lh = (dims[0] * dims[2]);
        if (lh < smallSide) {
          smallSide = lh;
        }

        wrappingPaperTotal += (2 * lw + 2 * wh + 2 * lh + smallSide);
      }
      System.out.println(wrappingPaperTotal);
      System.out.println(ribbonTotal);

    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public void visitedHouses() {
    File file = loadFile(Days.class.getResource("resources/day-three.txt"));
    Set<Point> housesVisited = new HashSet<>();
    int sx = 0;
    int sy = 0;
    int rx = 0;
    int ry = 0;

    housesVisited.add(new Point(sx, sy));

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      char[] directions = new char[(int) file.length()];
      reader.read(directions);

      for (int i = 0; i < directions.length; i++) {

        int whoMoves = i % 2;
        switch (whoMoves) {
          case 0:
            if (directions[i] == '^') {
              sy++;
            } else if (directions[i] == 'v') {
              sy--;
            } else if (directions[i] == '>') {
              sx++;
            } else if (directions[i] == '<') {
              sx--;
            }
            housesVisited.add(new Point(sx, sy));
            break;
          case 1:
            if (directions[i] == '^') {
              ry++;
            } else if (directions[i] == 'v') {
              ry--;
            } else if (directions[i] == '>') {
              rx++;
            } else if (directions[i] == '<') {
              rx--;
            }
            housesVisited.add(new Point(rx, ry));
        }
      }
      System.out.println(housesVisited.size());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void findHash() {
    StringBuilder sb = new StringBuilder("bgvyzdsv");
    int i = 1;
    while (true) {
      String hash = getMD5(sb.toString() + i);
      if (hash.startsWith("000000")) {
        System.out.println(hash);
        System.out.println(i);
        break;
      }
      i++;
    }
  }

  private File loadFile(URL path) {
    return new File(path.getFile());
  }

  private String getMD5(String input) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] messageDigest = md.digest(input.getBytes());
      StringBuilder hash = new StringBuilder(new BigInteger(1, messageDigest).toString(16));
      while (hash.length() < 32) {
        hash.insert(0, "0");
      }
      return hash.toString();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return null;
  }
}

