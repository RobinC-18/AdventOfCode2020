import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Day_10 {

  public List<Integer> extractData(File file) {
    Scanner scanner = null;
    try {
      scanner = new Scanner(file);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    List<Integer> data = new ArrayList<>();
    while (scanner.hasNextInt()) {
      data.add(scanner.nextInt());
    }
    return data;
  }

  public int solvePart1(List<Integer> data) {
    Collections.sort(data);
    int oneJolt = 0;
    int threeJolt = 0;
    data.add(0, 0);
    data.add(data.size(), 183);
    for (int i = 0; i < data.size() - 1; i++) {
      int diff = data.get(i + 1) - data.get(i);
      if (diff == 1) {
        oneJolt++;
      }
      if (diff == 3) {
        threeJolt++;
      }
    }
    return threeJolt * oneJolt;
  }

  public int solvePart2(List<Integer> data) {
    Collections.sort(data);
    System.out.println(data);
    List<Integer> differences = new ArrayList<>();
    for (int i = 0; i < data.size() - 1; i++) {
      int diff = data.get(i + 1) - data.get(i);
      differences.add(diff);
    }
    System.out.println(differences);
    int a = 0;
    int b = 1;
    int c = 0;
    for (int i = 0; i < differences.size() - 1; i++) {
      if (differences.get(i) == 1 && differences.get(i + 1) == 1 && differences.get(i + 2) == 1 &&
          differences.get(i + 3) == 1) {
        a++;
      } else if (differences.get(i) == 3 && differences.get(i + 1) == 1 &&
          differences.get(i + 2) == 1 && differences.get(i + 3) == 1 &&
          differences.get(i + 4) == 3) {
        b++;
      } else if (differences.get(i) == 3 && differences.get(i + 1) == 1 &&
          differences.get(i + 2) == 1 && differences.get(i + 3) == 3) {
        c++;
      }

    }
    double ans = Math.pow(7, a) * Math.pow(4, b) * Math.pow(2, c);
    long intAns = (long) ans;
    System.out.println(intAns);
    return 1;
  }

  public static void main(String[] args) {
    File myFile = new File("Day_10.txt");
    Day_10 day_10 = new Day_10();
    List<Integer> data = day_10.extractData(myFile);
    System.out.println(day_10.solvePart1(data));
    day_10.solvePart2(data);

  }
}
