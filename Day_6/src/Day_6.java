import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Day_6 {

  public String[] getData(File file) throws IOException {

    BufferedReader reader = new BufferedReader(new FileReader("Day_6.txt"));
    int lines = 0;
    while (reader.readLine() != null) {
      lines++;
    }
    reader.close();

    Scanner scanner = new Scanner(file);

    String[] data = new String[lines];
    int count = 0;
    while (scanner.hasNextLine()) {
      data[count] = scanner.nextLine();
      count++;
    }

    String toAdd = "";
    String[] parsedData = new String[900];
    int group = 0;

    for (int i = 0; i < lines; i++) {
      if (!data[i].equals("")) {
        toAdd += data[i] + " ";
        if (i == data.length-1) {
          parsedData[group] = toAdd;
          System.out.println(toAdd);
        }
      } else {
        parsedData[group] = toAdd;
        System.out.println(toAdd);
        group++;
        toAdd = "";
      }
    }

    return parsedData;
  }

  public int getCountUnique(String[] data) {
    int count = 0;
    for (int i = 0; i < data.length; i++) {
      if (data[i] != null) {
        String countChar = data[i].replace(" ", "");
        count += countChar.chars().distinct().count();
      }
    }
    return count;
  }
  public int getCountAll(String[] data) {
    int count = 0;

    for (int i = 0; i < data.length; i++) {
      if (data[i] != null) {
        String[] split = data[i].split(" ");
        String letters = split[0];
        for (int j = 0; j < split.length; j++) {
          String sameLetters = "";
          for (int k = 0; k < letters.length(); k++) {
            if (split[j].indexOf(letters.charAt(k)) >= 0) {
              sameLetters += letters.charAt(k);
            }
          }
          letters = sameLetters;
        }
        count += letters.length();
      }
    }
    return count;
  }

  public static void main(String[] args) throws IOException {

    File myFile = new File("Day_6.txt");
    Day_6 day_6 = new Day_6();
    String[] data = day_6.getData(myFile);
    int count = day_6.getCountUnique(data);
    int count2 = day_6.getCountAll(data);
    System.out.println(count2);



  }
}
