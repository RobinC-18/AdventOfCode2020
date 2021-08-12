import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Day_8 {

  public String[] getData(File file) throws IOException {

    BufferedReader reader = new BufferedReader(new FileReader(file));
    int lines = 0;
    while(reader.readLine() != null) {
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
    return data;
  }

  public boolean accumulation(String[] data) {

    ArrayList<Integer> nodesVisited = new ArrayList<>();
    int accume = 0;
    int currentNode = 0;
    boolean check = false;
    Set<Integer> uniqueNodesVisited = new HashSet<Integer>(nodesVisited);


    while (nodesVisited.size() == uniqueNodesVisited.size()) {
      if (currentNode == 612) {
        System.out.println(accume);
        check = true;
        break;
      }
      nodesVisited.add(currentNode);
      uniqueNodesVisited = new HashSet<Integer>(nodesVisited);
      if (data[currentNode].charAt(0) == 'j') {
        int n = data[currentNode].length();
        int jump = Integer.parseInt(data[currentNode].substring(5, n));
        if (data[currentNode].charAt(4) == '+') {
          currentNode += jump;
        } else {
          currentNode -= jump;
        }
      } else if (data[currentNode].charAt(0) == 'n') {
        currentNode += 1;
      } else {
        int n = data[currentNode].length();
        int addAccume = Integer.parseInt(data[currentNode].substring(5, n));
        if (data[currentNode].charAt(4) == '+') {
          accume += addAccume;
        } else {
          accume -= addAccume;
        }
        currentNode += 1;
      }
    }
    return check;
  }

  public void newCheck(String[] data) {

    for (int i = 0; i < data.length; i++) {
      if (data[i].charAt(0) == 'j' || data[i].charAt(0) == 'n') {
        String[] newData = new String[data.length];
        for (int j = 0; j < data.length; j++) {
          if (j != i) {
            newData[j] = data[j];
          } else {
            if (data[i].charAt(0) == 'j') {
              String nodeData = "nop " + data[i].substring(4);
              newData[j] = nodeData;
            } else {
              String nodeData = "jmp " + data[i].substring(4);
              newData[j] = nodeData;
            }
          }
        }
        accumulation(newData);
      }
    }
  }

  public static void main(String[] args) throws IOException {

    File myFile = new File("Day_8.txt");
    Day_8 day_8 = new Day_8();
    String[] data = day_8.getData(myFile);
//    day_8.accumulation(data);
    day_8.newCheck(data);

  }
}
