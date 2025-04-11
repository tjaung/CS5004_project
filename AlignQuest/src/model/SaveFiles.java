package model;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Saves json data to specified path.
 */
public class SaveFiles {

  /**
   * Takes in a json string of game data and writes to a json file.
   *
   * @param jsonStr string of game data
   */
  public static String saveToJSON(String jsonStr, String name) {
    String path = "../AlignQuest/saves/" + name + ".json";
    try (FileWriter writer = new FileWriter(path))
    {
      writer.write(jsonStr);
      return "Save data written to file successfully.";
    } catch (IOException e) {
      return "An error occurred while saving"
              + " data to file: " + e.getMessage();
    }
  }
}
