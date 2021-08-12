import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day_12 {

  public List<String> extractData(File file) {
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

  public int part1(List<String> data) {
    int east = 0;
    int north = 0;
    int direction = 90;
    for (String currentLine : data) {
      direction = (((direction % 360) + 360) % 360);
      char instruction = currentLine.charAt(0);
      int value = Integer.parseInt(currentLine.substring(1));
      if (instruction == 'N') {
        north += value;
      } else if (instruction == 'S') {
        north -= value;
      } else if (instruction == 'E') {
        east += value;
      } else if (instruction == 'W') {
        east -= value;
      } else if (instruction == 'R') {
        direction += value;
      } else if (instruction == 'L') {
        direction -= value;
      } else if (instruction == 'F') {
        if (direction == 0) {
          north += value;
        } else if (direction == 90) {
          east += value;
        } else if (direction == 180) {
          north -= value;
        } else if (direction == 270) {
          east -= value;
        }
      }
    }
    north = Math.abs(north);
    east = Math.abs(east);
    return north + east;
  }

  public int part2(List<String> data) {
    int north = 0;
    int east = 0;
    int waypointSouth = 0;
    int waypointWest = 0;
    int waypointEast = 10;
    int waypointNorth = 1;
    int direction = 90;

    for (String currentLine : data) {
      direction = (((direction % 360) + 360) % 360);
      char instruction = currentLine.charAt(0);
      int value = Integer.parseInt(currentLine.substring(1));
      if (instruction == 'F') {
        north += waypointNorth * value;
        north -= waypointSouth * value;
        east += waypointEast * value;
        east -= waypointWest * value;
      } else if (instruction == 'N') {
        if (waypointNorth == 0) {
          waypointSouth -= value;
        } else {
          waypointNorth += value;
        }
      } else if (instruction == 'E') {
        if (waypointEast == 0) {
          waypointWest -= value;
        } else {
          waypointEast += value;
        }
      } else if (instruction == 'S') {
        if (waypointSouth == 0) {
          waypointNorth -= value;
        } else {
          waypointSouth += value;
        }
      } else if (instruction == 'W') {
        if (waypointWest == 0) {
          waypointEast -= value;
        } else {
          waypointWest += value;
        }
      } else if (instruction == 'L') {
        int tempNorth = waypointNorth;
        int tempEast = waypointEast;
        int tempSouth = waypointSouth;
        int tempWest = waypointWest;
        if (value == 90) {
          waypointNorth = tempEast;
          waypointWest = tempNorth;
          waypointSouth = tempWest;
          waypointEast = tempSouth;
        } else if (value == 180) {
          waypointNorth = tempSouth;
          waypointEast = tempWest;
          waypointWest = tempEast;
          waypointSouth = tempNorth;
        } else if (value == 270) {
          waypointSouth = tempEast;
          waypointWest = tempSouth;
          waypointNorth = tempWest;
          waypointEast = tempNorth;
        }
      } else if (instruction == 'R') {
        int tempNorth = waypointNorth;
        int tempEast = waypointEast;
        int tempSouth = waypointSouth;
        int tempWest = waypointWest;
        if (value == 90) {
          waypointSouth = tempEast;
          waypointWest = tempSouth;
          waypointNorth = tempWest;
          waypointEast = tempNorth;
        } else if (value == 180) {
          waypointNorth = tempSouth;
          waypointEast = tempWest;
          waypointWest = tempEast;
          waypointSouth = tempNorth;
        } else if (value == 270) {
          waypointNorth = tempEast;
          waypointWest = tempNorth;
          waypointSouth = tempWest;
          waypointEast = tempSouth;
        }
      }
      System.out.println(waypointNorth + " " + waypointEast + " " + waypointSouth + " " + waypointWest);
    }
    north = Math.abs(north);
    east = Math.abs(east);
    return north + east;
  }

  public static void main(String[] args) {

    File myFile = new File("Day_12.txt");
    File testFile = new File("test.txt");
    Day_12 day_12 = new Day_12();
    List<String> data = day_12.extractData(myFile);
    List<String> testData = day_12.extractData(testFile);
    int ans = day_12.part1(data);
    int testAns = day_12.part1(testData);
    int part2Ans = day_12.part2(data);
    System.out.println(part2Ans);


  }
}
