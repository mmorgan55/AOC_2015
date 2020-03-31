import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class DayFour {

  public void findNiceStrings() {
    File file = Helpers.loadFile(Days.class.getResource("resources/day-four.txt"));
    int niceStrings = 0;

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      while (reader.ready()) {
        String input = reader.readLine();
        if (checkPairs(input)) {
          if (letterInBetween(input)) {
            niceStrings++;
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println(niceStrings);
  }

  private boolean checkNaughtyString(String input) {
    for (int i = 0; i < input.length() - 1; i++) {
      String sub = input.substring(i, i + 2);
      if (sub.equals("ab") || sub.equals("cd") || sub.equals("pq") || sub.equals("xy")) {
        return false;
      }
    }
    return true;
  }

  private boolean checkPairs(String input) {
    Set<String> pairSet = new HashSet<>();
    for (int i = 0; i < input.length() - 1; i++) {
      String sub = input.substring(i, i + 2);
      if (pairSet.contains(sub)) {
        return true;
      }
      if (i + 2 < input.length()) {
        if (sub.equals(input.substring(i + 1, i + 3))) {
          pairSet.add(sub);
          i++;
        } else {
          pairSet.add(sub);
        }
      } else {
        pairSet.add(sub);
      }
    }
    return false;
  }

  private boolean letterInBetween(String input) {
    for (int i = 0; i < input.length() - 2; i++) {
      if (input.charAt(i) == input.charAt(i + 2)) {
        return true;
      }
    }
    return false;
  }

  private boolean checkVowels(String input) {
    int vowels = 0;
    for (int i = 0; i < input.length(); i++) {
      if (input.charAt(i) == 'a' || input.charAt(i) == 'e' || input.charAt(i) == 'i'
          || input.charAt(i) == 'o' || input.charAt(i) == 'u') {
        vowels++;
      }
      if (vowels == 3) {
        return true;
      }
    }
    return false;
  }

  private boolean checkDoubleLetter(String input) {
    for (int i = 0; i < input.length() - 1; i++) {
      if (input.charAt(i) == input.charAt(i + 1)) {
        return true;
      }
    }
    return false;
  }
}
