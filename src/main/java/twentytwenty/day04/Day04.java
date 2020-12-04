package twentytwenty.day04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day04 {

  class Passport {
    String birthYear;
    String issueYear;
    String expirationYear;
    String height;
    String hairColour;
    String eyeColour;
    String passportId;
    String countryId;

    public boolean isValid() {
      return birthYear != null
          && issueYear != null
          && expirationYear != null
          && height != null
          && hairColour != null
          && eyeColour != null
          && passportId != null;
    }

    public boolean isValidTwo() {
      try {
        var byeValid = 2002 >= Integer.parseInt(birthYear) && Integer.parseInt(birthYear) >= 1920;
        var iyrValid = 2020 >= Integer.parseInt(issueYear) && Integer.parseInt(issueYear) >= 2010;
        var eyrValid = 2030 >= Integer.parseInt(expirationYear) && Integer.parseInt(expirationYear) >= 2020;
        var hgtValid = false;
        if (height.matches("^[0-9]+cm$")) {
          var num = Integer.parseInt(height.split("c")[0]);
          hgtValid = num >= 150 && num <= 193;
        } else if (height.matches("^[0-9]+in$")) {
          var num = Integer.parseInt(height.split("i")[0]);
          hgtValid = num >= 59 && num <= 76;
        }
        var hclValid = hairColour.matches("^#[0-9a-f]{6}$");
        var eclValid = eyeColour.equals("amb")
            || eyeColour.equals("blu")
            || eyeColour.equals("brn")
            || eyeColour.equals("gry")
            || eyeColour.equals("grn")
            || eyeColour.equals("hzl")
            || eyeColour.equals("oth");
        var pidValid = passportId.matches("^[0-9]{9}$");
        return byeValid && iyrValid && eyrValid && hgtValid && hclValid && eclValid && pidValid;
      } catch (Exception e) {
        // some sort of parse exception due to invalid data;
        return false;
      }
    }
  }

  private List<Passport> buildPassports(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var passports = new ArrayList<Passport>();
    while (scanner.hasNextLine()) {
      var line = scanner.nextLine();
      Passport passport = new Passport();
      while(!line.isEmpty()) {
        for (var pair : line.split(" ")) {
          var key = pair.split(":")[0];
          var val = pair.split(":")[1];
          switch (key) {
            case "byr":
              passport.birthYear = val;
              break;
            case "iyr":
              passport.issueYear = val;
              break;
            case "eyr":
              passport.expirationYear = val;
              break;
            case "hgt":
              passport.height = val;
              break;
            case "hcl":
              passport.hairColour = val;
              break;
            case "ecl":
              passport.eyeColour = val;
              break;
            case "pid":
              passport.passportId = val;
              break;
            case "cid":
              passport.countryId = val;
              break;
          }
        }
        if (scanner.hasNextLine()) line = scanner.nextLine();
        else break;
      }
      passports.add(passport);
    }
    return passports;
  }

  public long solvePartOne(File file) throws FileNotFoundException {
    var passports = buildPassports(file);
    return passports.stream().filter(Passport::isValid).count();
  }

  public long solvePartTwo(File file) throws FileNotFoundException {
    var passports = buildPassports(file);
    return passports.stream().filter(Passport::isValidTwo).count();
  }
}
