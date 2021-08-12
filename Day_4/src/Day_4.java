import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Day_4 {

  public ArrayList<HashMap<String, String>> getData(File file) throws IOException {

    ArrayList<String> data = new ArrayList<>();
    ArrayList<String> passportSplit = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new FileReader("Day_4.txt"));
    String line = "";
    while ((line = reader.readLine()) != null) {
      data.add(line);
    }
    reader.close();


    for (int i = 0; i < data.size(); i++) {
      String[] splitData = data.get(i).split(" ");
      for (int j = 0; j < splitData.length; j++) {
        passportSplit.add(splitData[j]);
      }
    }


    ArrayList<HashMap<String, String>> passportInfo = new ArrayList<>();
    int index = 0;
    int count = 0;

    while (index < passportSplit.size()) {
      HashMap<String, String> passportEntry = new HashMap<>();
      while (index < passportSplit.size() && !(passportSplit.get(index).equals(""))) {
        String[] keyValue = passportSplit.get(index).split(":");
        passportEntry.put(keyValue[0], keyValue[1]);
        index++;
      }
      passportInfo.add(count, passportEntry);
//      System.out.println(passportInfo.get(count));
      count++;
      index++;
    }
  return passportInfo;
  }

  public int checkPassports1(ArrayList<HashMap<String, String>> passportInfo) {
    int count = 0;

    for (int i = 0; i < passportInfo.size(); i++) {
      HashMap<String, String> entry = passportInfo.get(i);
      if (entry.containsKey("byr") && entry.containsKey("eyr") && entry.containsKey("pid")
      && entry.containsKey("hgt") && entry.containsKey("iyr") && entry.containsKey("ecl")
      && entry.containsKey("hcl")) {
        count++;
      }
    }
    return count;
  }

  public int checkPassports2(ArrayList<HashMap<String, String>> passportInfo) {
    int count = 0;

    for (int i = 0; i < passportInfo.size(); i++) {
      HashMap<String, String> entry = passportInfo.get(i);
      if (entry.containsKey("byr") && entry.containsKey("eyr") && entry.containsKey("pid")
          && entry.containsKey("hgt") && entry.containsKey("iyr") && entry.containsKey("ecl")
          && entry.containsKey("hcl")) {
        if (checkByr(entry.get("byr")) && checkIyr(entry.get("iyr")) && checkEyr(entry.get("eyr"))
        && checkHgt(entry.get("hgt")) && checkHcl(entry.get("hcl")) && checkEcl(entry.get("ecl"))
        && checkPid(entry.get("pid"))) {
          System.out.println(entry);
          count++;
        }
      }
    }
    return count;
  }

  private boolean checkPid(String pid) {
    return pid.length() == 9 && pid.matches("[0-9]+");
  }

  private boolean checkEcl(String ecl) {
    return ecl.equals("amb") || ecl.equals("blu") || ecl.equals("gry") || ecl.equals("brn")
        || ecl.equals("grn") || ecl.equals("hzl") || ecl.equals("oth");
  }

  private boolean checkHcl(String hcl) {
    int n = hcl.length();
    if (hcl.charAt(0) == '#' && n == 7) {
      String hclCode = hcl.substring(1, n - 1);
      return hclCode.matches("[0-9A-F]+");
    } else {
      return false;
    }
  }

  private boolean checkHgt(String hgt) {

    int n = hgt.length();
    if (hgt.charAt(n - 1) == 'm' && hgt.charAt(n - 2) == 'c') {
      String newHgt = hgt.substring(0, n - 2);
      return Integer.parseInt(newHgt) >= 150 && Integer.parseInt(newHgt) <= 193;
    } else if (hgt.charAt(n - 1) == 'n' && hgt.charAt(n - 2) == 'i') {
      String newHgt = hgt.substring(0, n - 2);
      return Integer.parseInt(newHgt) >= 59 && Integer.parseInt(newHgt) <= 76;
    } else {
      return false;
    }
  }

  private boolean checkByr(String byr) {
    int intByr = Integer.parseInt(byr);
    return intByr >= 1920 && intByr <= 2002;
  }

  private boolean checkIyr(String iyr) {
    int intIyr = Integer.parseInt(iyr);
    return intIyr >= 2010 && intIyr <= 2020;
  }

  private boolean checkEyr(String eyr) {
    int intEyr = Integer.parseInt(eyr);
    return intEyr >= 2020 && intEyr <= 2030;
  }

  public static void main(String[] args) throws IOException {
    File myFile = new File("Day_4.txt");
    Day_4 day_4 = new Day_4();
    ArrayList<HashMap<String, String>> passportInfo = day_4.getData(myFile);
    int answer = day_4.checkPassports2(passportInfo);
    System.out.println(answer);
  }
}
