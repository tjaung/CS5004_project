package TextUI;

import java.io.InputStreamReader;
import java.util.Scanner;

public class GameCommandReader {
  private String[] data;
  private Readable in;
  private Scanner scanner;

  public GameCommandReader() {
    // STRING BUFFER LENGTH!!!!!
    this.data = new String[2];
    this.in = new InputStreamReader(System.in);
  }

  public GameCommandReader(String[] data, Readable in) {
    this.data = data;
    this.in = in;
  }

  public boolean getOptionFromUser() {
    try {
      Scanner scanner = new Scanner(this.in);
      String option = scanner.nextLine();
      if (option.length() > 1) {
        this.data[0] = String.valueOf(option.charAt(0));
        this.data[1] = option.substring(2);
      } else {
        this.data[0] = option;
      }

      if (this.data[0].equalsIgnoreCase("Q")) {
        return false;
      }
      return true;
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  public String getCommand() {return this.data[0];}

  public String getElement() {
    if (this.data[1].isEmpty()) {
      throw new IllegalArgumentException("You need to include what element you wish to take the action on\n");
    }
    else {
      return this.data[1];
    }
  }

  //public String getStringFromUser() {
    //Scanner scanner2 = new Scanner(this.in);
    //return null;
  //}
}
