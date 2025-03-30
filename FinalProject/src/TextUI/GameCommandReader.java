package TextUI;

import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * The game command reader.
 */
public class GameCommandReader {
  private String[] data;
  private Readable in;
  private Scanner scanner;

  /**
   * command reader constructor. With two string length.
   */
  public GameCommandReader() {
    // STRING BUFFER LENGTH!!!!!
    this.data = new String[2];
    this.in = new InputStreamReader(System.in);
  }

  /**
   * read user input command.
   * @param data data
   * @param in in
   */
  public GameCommandReader(String[] data, Readable in) {
    this.data = data;
    this.in = in;
  }

  /**
   * get input.
   */
  public void getOptionFromUser() {
    try {
      Scanner scanner = new Scanner(this.in);
      String option = scanner.nextLine();
      if (option.length() > 1) {
        this.data[0] = String.valueOf(option.charAt(0));
        this.data[1] = option.substring(2);
      } else {
        this.data[0] = option;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * get first index as the command.
   * @return command
   */
  public String getCommand() {return this.data[0];}

  /**
   * get the second element.
   * @return
   */
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
