package TextUI;

import java.io.IOException;
import java.util.List;

import gameelements.IRoomElement;
import gameelements.Item;
import model.GameModel;

public class GameController {
  private GameModel model;
  private GameCommandReader in;
  private GameView view;

  public GameController(GameModel model) {
    this.model = model;
  }

  public void go() throws IOException, InterruptedException {
    this.view = new GameView();
    this.in = new GameCommandReader();

    this.view.showIntro();
    this.view.print(this.model.getRoomModel().getCurrentRoom().getName().concat(".\n\n"));
//    TimeUnit.SECONDS.sleep(1);
    this.view.showOptions();
    this.view.print("\n".concat(this.model.getRoomModel().getCurrentRoom().toString()).concat("\n"));

    while (this.in.getOptionFromUser()) {
      // check for monsters or puzzle effects
      String input = this.in.getCommand().toUpperCase();
      switch (input) {
        // move any direction into one case
        case "N":
        case "S":
        case "E":
        case "W":
          try {
            this.model.move(input);
            this.view.enterRoom(this.model.getRoomModel().getCurrentRoom().getName(),
                    this.model.getRoomModel().getCurrentRoom().toString());
          }
          catch (IllegalArgumentException e) {
            this.view.print(e.getMessage());
          }
          break;

        case "I":
          List<Item> items = this.model.getPlayer().getInventory().getItems();
          this.view.printInventory(items);
          break;

        // should this show fixtures as well?
        case "L":
          this.view.print(this.model.getRoomModel().getCurrentRoom().toString().concat(".\n"));
          this.view.showItems();
          try {
            for (IRoomElement i : this.model.getRoomModel().getCurrentRoom().getItems()) {
              this.view.print(i.getName().concat("\n"));
            }
          }
          catch (Exception e) {
            this.view.print("You don't see any items\n");
            continue;
          }
          break;

        case "U":
          try {
            this.model.useItem(this.model.getPlayer().getInventory().getItem(this.in.getElement()));
            this.view.print(this.model.getPlayer().getInventory().getItem(this.in.getElement()).getWhenUsed());
          }
          catch (Exception e) {
            this.view.print(e.getMessage());
          }
          break;

        case "T":
          try {
            this.model.takeItem(this.model.getRoomModel().getCurrentRoom().getItem(this.in.getElement()));
            this.view.print(this.in.getElement().concat(" added to your inventory.\n"));
          }
          catch (Exception e) {
            this.view.print(e.getMessage());
          }
          // grab input index 1
          // index model.getRoomModel().getCurrentRoom().getElement(input);
          break;

        case "D":
          try {
            this.model.dropItem(this.model.getPlayer().getInventory().getItem(this.in.getElement()));
            this.view.print("You dropped ".concat(this.in.getElement().concat(".\n")));
          }
          catch (Exception e) {
            this.view.print(e.getMessage());
          }
          break;

        case "X":
          try {
            String elementName = this.in.getElement();
            String examineString = this.model.getRoomModel().getCurrentRoom().getElement(elementName).getDescription();
            this.view.print(examineString.concat("\n"));
          }
          catch (Exception e) {
            this.view.print(e.getMessage());
          }
          break;

        case "A":
          break;

        case "O":
          this.view.showOptions();
          break;

        case "+":
          //this.view.print("File name:\n");
          //this.in.
          this.view.print("We should implement a save function...\n");
          break;

        case "-":
          this.view.print("We should implement a load save function...\n");
          break;

        default:
          this.view.showOptionError();
          break;
          //TimeUnit.SECONDS.sleep(1);
          //this.view.showOptions();
      }



    }
  }
}

// THE FOLLOWING IS THE CORRECT IMPLEMENTATION ACCORDING TO TAs.
// USE EXCEPTIONS AND CATCH THEM HERE.
// the model throws the exception for invalidity and the controller catches it,
// printing an appropriate message.
//else if (this.in.getCommand().equalsIgnoreCase("A")) {
//this.dataReader.out.append(this.model.answer);
//boolean issolbed = gamemodel.answer(input);
//if issolved priny uou did it

