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
  public String inputPopUp(String desc);

  /**
   * This method returns item in the inventory.
   */
  public IRoomElement openListPopUp (List<IRoomElement> items);

  /**
   * This method tells you a description of the item.
   */
  public void openDescPopUp(IRoomElement input);

  /**
   * This method confirms if you want to add an item.
   */
  public void confirmPopUp(String message);

}
