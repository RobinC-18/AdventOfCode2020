import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Arrays;
import java.util.Scanner;

public class Day_1a {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("Day_1.txt"));
    int lines = 0;
    while (reader.readLine() != null) lines++;
    reader.close();

    int[] numbers = new int[lines];
    int count = 0;
    try {
      File myObj = new File("Day_1.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextInt()) {
        numbers[count] = myReader.nextInt();
//        System.out.println(numbers[count]);
        count++;
      }
    } catch(FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    int a = 0;
    int b = 0;

    int[] newNumbers = new int[lines];

    for (int i = 0; i < lines; i++) {
      newNumbers[i] = 2020 - numbers[i];
//      System.out.println(newNumbers[i]);
    }

    int answer = 0;
    for (int i = 0; i < lines; i++) {
      for (int j = 0; j < lines; j++) {
        if (newNumbers[i] == numbers[j]) {
          answer = numbers[j] * (2020 - numbers[j]);
          System.out.println(answer);
        }
      }
    }


    for (int i = 0; i < lines; i++) {
      for (int j = 0; j < lines; j++) {
        for (int k = 0; k < lines; k++) {
          if(numbers[i] + numbers[j] + numbers[k] == 2020) {
            System.out.println(numbers[i] * numbers[j] * numbers[k]);
          }
        }
      }
    }


  }
}
