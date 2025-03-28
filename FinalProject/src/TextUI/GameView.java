package TextUI;

import java.io.IOException;

import gameelements.Room;

public class GameView {
  private Appendable out;

  public GameView() {
    this.out = System.out;
  }

  public GameView(Appendable out) {
    this.out = out;
  }

  public void showIntro() throws IOException {
    this.out.append("You start your adventure in the ");
  }
  public void showOptions() throws IOException {
    this.out.append("To move, enter: (N)orth, (S)outh, (E)ast or (W)est.\n" +
            "Other actions: (I)nventory, (L)ook around the location, (U)se an item\n" +
            "(T)ake an item, (D)rop an item, or e(X)amine something. \n" +
            "(A)nswer a question or provide a text solution. \n" +
            "To end the game, enter (Q)uit to quit and exit.\n");
  }

  public void print(String s) throws IOException {
    this.out.append(s);
  }

  public void printInventory(String s) throws IOException {
    String output = "Items in your inventory: ";
    this.out.append(s);
  }

  public void showOptionError() throws IOException {
    this.out.append("Incorrect input please try again. Use (O) to view options\n");
  }

  public void showItems() throws IOException {
    this.out.append("Items in this room: ");
  }

  public void enterRoom(String name, String description) throws IOException {
    this.out.append("You enter the ");
    this.out.append(name.concat("\n"));
    this.out.append(description.concat("\n"));
  }
}
