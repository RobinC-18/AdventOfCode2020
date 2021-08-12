import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Day_5 {

  public String[] getData(File file) throws IOException {

    BufferedReader reader = new BufferedReader(new FileReader("Day_5.txt"));
    int lines = 0;
    while (reader.readLine() != null) {
      lines++;
    }
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

  public int findSeat(String[] data) {


    String seatNumber = "";
    int maxSeatNumber = 0;
    int minSeatNumber = 100;
    int[] seatsTaken = new int[900];
    for (int i = 0; i < data.length; i++) {
      seatNumber = data[i];
      int maxRow = 127;
      int minRow = 0;
      for (int j = 0; j < 7; j++) {
        if(seatNumber.charAt(j) == 'F') {
          maxRow -= ((maxRow - minRow + 1) / 2);
        } else {
          minRow += ((maxRow - minRow + 1) / 2);
        }
      }
      int maxCol = 7;
      int minCol = 0;
      for (int j = 7; j < 10; j++) {
        if (seatNumber.charAt(j) == 'L') {
          maxCol -= (maxCol - minCol + 1) / 2;
        } else {
          minCol += (maxCol - minCol + 1) / 2;
        }
      }
      int seatID = maxRow * 8 + maxCol;
      seatsTaken[i] = seatID;
      if (seatID > maxSeatNumber) {
        maxSeatNumber = seatID;
      }
      if (seatID < minSeatNumber) {
        minSeatNumber = seatID;
      }
    }
    Arrays.sort(seatsTaken);

    for (int i = 0; i < seatsTaken.length - 1; i++) {
      if (seatsTaken[i + 1] - seatsTaken[i] > 1) {
        System.out.println(seatsTaken[i] + " " + seatsTaken[i + 1]);
      }
    }

    return 0;
  }

  public static void main(String[] args) throws IOException {

    File myFile = new File("Day_5.txt");
    Day_5 day_5 = new Day_5();
    String[] data = day_5.getData(myFile);
    day_5.findSeat(data);

  }
}
