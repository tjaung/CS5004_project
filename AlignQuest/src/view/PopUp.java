package view;

import gameelements.IRoomElement;
import gameelements.Item;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class PopUp implements PopUpInterface{


  /**
   * This method returns the input.
   *
   * @param desc
   */
  public static String inputPopUp(String desc) {
    return JOptionPane.showInputDialog(desc);
  }

  /**
   * This method returns item in the inventory.
   *
   * @param items list<Room elements> to display
   * @return single room element
   */
  public static IRoomElement openListPopUp(List<IRoomElement> items) {
    if (items.isEmpty()) {
      confirmPopUp("There is nothing to see here.");
    }
    List<String> options = new ArrayList<String>();
    for (IRoomElement item: items){
      options.add(item.getName());
    }
    Object[] inputOptions = options.toArray();
    Object value = JOptionPane.showInputDialog(null,"",
            "",JOptionPane.QUESTION_MESSAGE,null,inputOptions,inputOptions[0]);
    if (value != null) {
      int index = options.indexOf(value);
      return items.get(index);
    }
    return null;
  }

  /**
   * This method tells you a description of the item.
   */
  public static void openDescPopUp(IRoomElement input) {
    String basePath = "../AlignQuest/resources/resources/images/";
    String defaultImg = "generic_item.png";

    String picPath = input.getPicture();
    String imgPath = basePath + picPath;
    // Room element description
    String desc = input.getDescription();
    ImageIcon imgIcon = new ImageIcon(imgPath);
    // if img not found, fall back to default image
    if(imgIcon.getImageLoadStatus() == java.awt.MediaTracker.ERRORED) {
      imgIcon = new ImageIcon(basePath + defaultImg);
    }

    // show popup
    JOptionPane.showConfirmDialog(
            null,
            desc,
            "OK",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            imgIcon);
  }

  /**
   * This method confirms if you want to add an item.
   */
  public static void confirmPopUp(String message) {
    JOptionPane.showConfirmDialog(null,
            message=message,
            "Confirm",
            JOptionPane.DEFAULT_OPTION);
  }

  public static void quitPopUp(String name, int score, String byeMessage) {
    String basePath = "../AlignQuest/resources/resources/images/";
    String defaultImg = "nighty_night.png";
    ImageIcon imgIcon = new ImageIcon(basePath + defaultImg);
    if (byeMessage != null) {
      JOptionPane.showConfirmDialog(
              null,
              byeMessage + "\nThanks for playing!\n"
                      + "Your score is " + score,
              "Game Over!",
              JOptionPane.DEFAULT_OPTION,
              JOptionPane.QUESTION_MESSAGE,
              imgIcon);
    }
    else {
      JOptionPane.showConfirmDialog(
              null,
              "Status for " + name + "\nThanks for playing!\n"
                      + "Your score is " + score,
              "Game Over!",
              JOptionPane.DEFAULT_OPTION,
              JOptionPane.QUESTION_MESSAGE,
              imgIcon);
    }
  }

  public static String openSaveList(List<String> saves) {
    if (saves.isEmpty()) {
      confirmPopUp("There is nothing to see here.");
    }
    List<String> options = new ArrayList<String>();
    for (String file: saves){
      options.add(file);
    }
    Object[] inputOptions = options.toArray();
    Object value = JOptionPane.showInputDialog(null,"",
            "",JOptionPane.QUESTION_MESSAGE,null,inputOptions,inputOptions[0]);
    int index = options.indexOf(value);
    try {
      return saves.get(index);
    }
    catch (Exception error) {
      return "";
    }
  }
}
