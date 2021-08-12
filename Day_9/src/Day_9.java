import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Day_9 {

  public int[] getData(File file) throws IOException {

    BufferedReader reader = new BufferedReader(new FileReader(file));
    int lines = 0;
    while(reader.readLine() != null) {
      lines++;
    }
    reader.close();

    Scanner scanner = new Scanner(file);
    int[] data = new int[lines];
    int count = 0;
    while (scanner.hasNextInt()) {
      data[count] = scanner.nextInt();
      count++;
    }
    return data;
  }

  public int check(int[] data) {
    for (int x = 25; x < data.length; x++) {
      List<Integer> preamble = findPreamble(data, x);
      int currentInt = data[x];
      if (!preamble.contains(currentInt)) {
        return currentInt;
      }
    }
    return 1;
  }

  public List<Integer> findPreamble(int[] data, int index) {
    List<Integer> preamble = new ArrayList<>();
    int start = index - 25;
    for (int i = start; i < index; i++) {
      for (int j = i; j < index; j++) {
        if (i != j) {
          preamble.add(data[i] + data[j]);
        }
      }
    }
    return preamble;
  }

  public int part2(int[] data, int result) {
    for (int length = 2; length < data.length; length++) {
      for (int pos = 0; pos < data.length - length; pos++) {
        List<Integer> numbers = new ArrayList<>();
        int ans = 0;
        for (int k = pos; k < pos + length; k++) {
          ans += data[k];
          numbers.add(data[k]);
        }
        if (ans == result) {
          System.out.println(numbers);
          return findSumOfMaxAndMin(numbers);
        }
      }
    }
    return 0;
  }

  public int findSumOfMaxAndMin(List<Integer> numbers) {
    int min = 1678295400;
    int max = 0;

    for(int i = 0; i < numbers.size(); i++) {
      if (numbers.get(i) > max) {
        max = numbers.get(i);
      }
      if (numbers.get(i) < min) {
        min = numbers.get(i);
      }
    }
    return max + min;
  }
  public static void main(String[] args) throws IOException {

    File myFile = new File("Day_9.txt");
    Day_9 day_9 = new Day_9();
    int[] data = day_9.getData(myFile);
    int result = day_9.check(data);
    System.out.println(day_9.part2(data, result));



  }
}
