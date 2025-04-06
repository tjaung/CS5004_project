package view;


import gameelements.IRoomElement;
import gameelements.Item;
import java.util.List;

/**
 * This is the interface for the pop up class.
 */
public interface PopUpInterface {

  /**
   * This method returns the input.
   */
  public static String inputPopUp(String desc) {
    return null;
  }

  /**
   * This method returns item in the inventory.
   */
  public static IRoomElement openListPopUp(List<IRoomElement> items) {
    return null;
  }

  /**
   * This method tells you a description of the item.
   */
  public static void openDescPopUp(IRoomElement input) {

  }

  /**
   * This method confirms if you want to add an item.
   */
  public static void confirmPopUp(String message) {

  }

}
