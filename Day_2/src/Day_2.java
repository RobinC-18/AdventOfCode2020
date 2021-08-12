import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Day_2 {

  public String[] getData(File file) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("Day_2.txt"));
    int lines = 0;
    while (reader.readLine() != null) lines++;
    reader.close();
    Scanner scanner = new Scanner(file);
    String[] data = new String[lines];
    int count = 0;
    while(scanner.hasNextLine()) {
      data[count] = scanner.nextLine();
      count++;
    }
    return data;
  }

  public void version1(String[] data) {
    int n = data.length;

    int amount = 0;

    for (String datum : data) {
      String[] parts = datum.split("-|:|\\s+");
      int min = Integer.parseInt(parts[0]);
      int max = Integer.parseInt(parts[1]);
      char letter = parts[2].charAt(0);
      String password = parts[4];

      int letterCount = 0;
      for (int j = 0; j < password.length(); j++) {
        if (password.charAt(j) == letter) {
          letterCount += 1;
        }
      }
      if (letterCount >= min && letterCount <= max) {
        amount += 1;
      }
    }
    System.out.println(amount);
  }

  public void version2(String[] data) {
    int amount = 0;

    for (String datum : data) {
      String[] parts = datum.split("-|:|\\s");
      int a = Integer.parseInt(parts[0]);
      int b = Integer.parseInt(parts[1]);
      char letter = parts[2].charAt(0);
      String password = parts[4];

      if (password.charAt(a - 1) == letter && password.charAt(b - 1) != letter) {
        amount += 1;
      } else if (password.charAt(a - 1) != letter && password.charAt(b - 1) == letter) {
        amount += 1;
      }
    }
    System.out.println(amount);
  }

  public static void main(String[] args) throws IOException {
    File myFile = new File("Day_2.txt");
    Day_2 day_2 = new Day_2();
    String[] data = day_2.getData(myFile);
    day_2.version1(data);
    day_2.version2(data);






  }

}
