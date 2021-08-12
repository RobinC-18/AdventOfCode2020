import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day_11 {

  public List<String> dataExtract(File file) {
    Scanner scanner = null;
    try {
      scanner = new Scanner(file);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    List<String> data = new ArrayList<>();
    while (scanner.hasNextLine()) {
      data.add(scanner.nextLine());
    }
    return data;
  }

  public List<String> part1(List<String> data) {
    for (int i = 1; i < 1000; i++) {
      List<Seat> elligibleSeatsToSit = new ArrayList<>();
      List<Seat> inelligibleSeats = new ArrayList<>();
      int rowLength = data.get(0).length();
      for (int row = 0; row < data.size(); row++) {
        for (int col = 0; col < rowLength; col++) {
          if (data.get(row).charAt(col) == 'L') {
            if (isElligibleToSit(row, col, data)) {
              elligibleSeatsToSit.add(new Seat(row, col));
            }
          } else if (data.get(row).charAt(col) == '#') {
            if (shouldStand(row, col, data)) {
              inelligibleSeats.add(new Seat(row, col));
            }
          }
        }
      }
      for (Seat seat : elligibleSeatsToSit) {
        String currentRow = data.get(seat.getRow());
        int currentCol = seat.getCol();
        String newRow =
            currentRow.substring(0, currentCol) + '#' + currentRow.substring(currentCol + 1);
        data.set(seat.getRow(), newRow);
      }

      for (Seat seat : inelligibleSeats) {
        String currentRow = data.get(seat.getRow());
        int currentCol = seat.getCol();
        String newRow =
            currentRow.substring(0, currentCol) + 'L' + currentRow.substring(currentCol + 1);
        data.set(seat.getRow(), newRow);
      }

      for (String row : data) {
        System.out.println(row);
      }
      System.out.println("\n");

      if (elligibleSeatsToSit.isEmpty() && inelligibleSeats.isEmpty()) {
        return data;
      }
    }
    return data;
  }

  public boolean shouldStand(int row, int col, List<String> data) {
    String currentRow = data.get(row);
    int numberOfRows = data.size();
    int count = 0;
    for (int k = 0; k < 3; k++) {
      if (row - 1 >= 0 && col - 1 + k >= 0 && col - 1 + k < currentRow.length()) {
        if (data.get(row - 1).charAt(col - 1 + k) == '#') {
          count++;
        }
      }
      if (row + 1 < numberOfRows && col - 1 + k >= 0 && col - 1 + k < currentRow.length()) {
        if (data.get(row + 1).charAt(col - 1 + k) == '#') {
          count++;
        }
      }
    }
    if (col - 1 >= 0) {
      if (data.get(row).charAt(col - 1) == '#') {
        count++;
      }
    }
    if (col + 1 < currentRow.length()) {
      if (data.get(row).charAt(col + 1) == '#') {
        count++;
      }
    }
    return count >= 4;
  }

  public boolean isElligibleToSit(int row, int col, List<String> data) {
    String currentRow = data.get(row);
    int numberOfRows = data.size();
    int count = 0;
    for (int k = 0; k < 3; k++) {
      if (row - 1 >= 0 && col - 1 + k >= 0 && col - 1 + k < currentRow.length()) {
        if (data.get(row - 1).charAt(col - 1 + k) == '#') {
          count++;
        }
      }
      if (row + 1 < numberOfRows && col - 1 + k >= 0 && col - 1 + k < currentRow.length()) {
        if (data.get(row + 1).charAt(col - 1 + k) == '#') {
          count++;
        }
      }
    }
    if (col - 1 >= 0) {
      if (data.get(row).charAt(col - 1) == '#') {
        count++;
      }
    }
    if (col + 1 < currentRow.length()) {
      if (data.get(row).charAt(col + 1) == '#') {
        count++;
      }
    }
    return count < 1;
  }

  public int countOccupied(List<String> data) {
    int count = 0;
    for (int row = 0; row < data.size(); row++) {
      for (int col = 0; col < data.get(0).length(); col++) {
        if (data.get(row).charAt(col) == '#') {
          count++;
        }
      }
    }
    return count;
  }

  public static void main(String[] args) {
    File myFile = new File("Day_11.txt");
    File testFile = new File("test.txt");
    Day_11 day_11 = new Day_11();
    List<String> data = day_11.dataExtract(myFile);
    List<String> testData = day_11.dataExtract(testFile);
    List<String> finalSeats = day_11.part1(data);
    System.out.println(day_11.countOccupied(finalSeats));


  }
}
