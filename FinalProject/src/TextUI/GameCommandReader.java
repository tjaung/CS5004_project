package TextUI;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class GameCommandReader {
  private String[] data;
  private Readable in;


  public GameCommandReader() {
    this.data = new String[1];
    this.in = new InputStreamReader(System.in);
  }

  public GameCommandReader(String[] data, Readable in) {
    this.data = data;
    this.in = in;
  }

  public boolean getDataFromUser() {
    try {
      Scanner scanner = new Scanner(this.in);
      for (int i = 0; i < this.data.length; i++) {
        if (scanner.hasNext()) { // could have done NextLines
          this.data[i] = scanner.next();
          if (data[0].equalsIgnoreCase("Q")) {
            return false;
          }
        }
      }
      return true;
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  public String getCommand() {return this.data[0];}
}
