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
  @Override
  public String inputPopUp(String desc) {
    return JOptionPane.showInputDialog(desc);
  }

  /**
   * This method returns item in the inventory.
   */
  @Override
  public IRoomElement openListPopUp(List<IRoomElement> items) {
    if (items.size() == 0) {
      this.confirmPopUp("There is nothing to see here.");
    }
    List<String> options = new ArrayList<String>();
    for (IRoomElement item: items){
      options.add(item.getName());
    }
    Object[] inputOptions = options.toArray();
    Object value = JOptionPane.showInputDialog(null,"",
            "",JOptionPane.QUESTION_MESSAGE,null,inputOptions,inputOptions[0]);
    int index = options.indexOf(value);
    return items.get(index);
  }

  /**
   * This method tells you a description of the item.
   */
  @Override
  public void openDescPopUp(IRoomElement input) {
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
  @Override
  public void confirmPopUp(String message) {
    JOptionPane.showConfirmDialog(null,
            message=message,
            "Confirm",
            JOptionPane.DEFAULT_OPTION);
  }
}
