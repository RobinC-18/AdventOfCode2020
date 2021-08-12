import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

public class Day_3 {

  public String[] getData(File file) throws IOException {

    BufferedReader reader = new BufferedReader(new FileReader("Day_3.txt"));
    int lines = 0;
    while (reader.readLine() != null) {
      lines++;
    }
    reader.close();

    String[] data = new String[lines];

    Scanner scanner = new Scanner(file);
    int count = 0;
    while (scanner.hasNextLine()) {
      data[count] = scanner.nextLine();
      count++;
    }
    return data;
  }

  public long countTrees(String[] data, int right, int down) {
    int n = data.length;
    int rowLength = data[0].length();

    long trees = 0;


    int col = 0;
    for (int i = 0; i < n; i += down) {
      int colMod = col % rowLength;
      if (data[i].charAt(colMod) == '#') {
        String newData = data[i].substring(0, colMod) + 'X' + data[i].substring(colMod + 1);
        System.out.println(newData);
        trees++;
      }
      col += right;
    }
    return trees;
  }

  public static void main(String[] args) throws IOException {
    File myFile = new File("Day_3.txt");
    Day_3 day_3 = new Day_3();
    String[] data = day_3.getData(myFile);
    long a = day_3.countTrees(data, 3, 1);
    long b = day_3.countTrees(data, 1, 1);
    long c = day_3.countTrees(data, 5, 1);
    long d = day_3.countTrees(data, 7, 1);
    long e = day_3.countTrees(data, 1, 2);
    System.out.println(e);
    long answer = a * b * c * d * e;
    System.out.println(answer);


  }
}
