import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day_13 {

  public List<Integer> extractData(File file) {

    Scanner scanner = null;
    try {
      scanner = new Scanner(file);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    List<String> firstDataExtract = new ArrayList<>();
    while (scanner.hasNextLine()) {
      firstDataExtract.add(scanner.nextLine());
    }

    List<Integer> secondDataExtract = new ArrayList<>();

    String[] busTimes = firstDataExtract.get(1).split(",");

    secondDataExtract.add(Integer.parseInt(firstDataExtract.get(0)));
    for (String busTime : busTimes) {
      if (!busTime.equals("x")) {
        secondDataExtract.add(Integer.parseInt(busTime));
      }
    }
    System.out.println(secondDataExtract);

    return null;
  }

  public static void main(String[] args) {
    File myFile = new File("Day_13.txt");
    Day_13 day_13 = new Day_13();
    day_13.extractData(myFile);
  }
}
