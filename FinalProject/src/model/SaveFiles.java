package model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SaveFiles {

  public static void saveToJSON(
          String player,
          List<String> roomList) {
    try (FileWriter writer = new FileWriter("./resources/save.json"))
    {
      writer.write(player);
      writer.write("\nrooms: " + roomList + "\n");
      System.out.println("Data written to the file successfully.");
    }

    // Exception Thrown
      	catch (IOException e) {
      System.out.println("An error occurred while writing"
              + " to the file: " + e.getMessage());
    }

  }
}
