package TextUI;

import java.io.IOException;
import java.util.List;

import gameelements.Item;
import gameelements.Room;

/**
 * beginning of a class.
 */
public class GameView {
  private Appendable out;

  /**
   * default constructor.
   */
  public GameView() {
    this.out = System.out;
  }

  /**
   * constructor with parameter out.
   * @param out out.
   */
  public GameView(Appendable out) {
    this.out = out;
  }

  /**
   * show introduction.
   * @throws IOException
   */
  public void showIntro() throws IOException {
    this.out.append("You start your adventure in the ");
  }

  /**
   * show the menu.
   * @throws IOException
   */
  public void showOptions() throws IOException {
    this.out.append("To move, enter: (N)orth, (S)outh, (E)ast or (W)est.\n" +
            "Other actions: (I)nventory, (L)ook around the location, (U)se an item\n" +
            "(T)ake an item, (D)rop an item, or e(X)amine something. \n" +
            "(A)nswer a question or provide a text solution. \n" +
            "To end the game, enter (Q)uit to quit and exit.\n");
  }

  /**
   * print result
   * @param s string
   * @throws IOException
   */
  public void print(String s) throws IOException {
    this.out.append(s);
  }

  /**
   * show inventory.
    * @param items
   * @throws IOException
   */
  public void printInventory(List<Item> items) throws IOException {
    String s = "Items in your inventory: ";
    if (items.size() == 0) {
      s += "none";
    } else {
      for(Item item : items) {
        s += item.getName();
      }
    }
    this.out.append(s);
  }

  /**
   * show error.
   * @throws IOException
   */
  public void showOptionError() throws IOException {
    this.out.append("Incorrect input please try again. Use (O) to view options\n");
  }

  /**
   * show item.
   * @throws IOException
   */
  public void showItems() throws IOException {
    this.out.append("Items in this room: ");
  }

  public void enterRoom(String name, String toString) throws IOException {
    this.out.append("You enter the ");
    this.out.append(name.concat("\n"));
    this.out.append(toString.concat("\n"));
  }
}
