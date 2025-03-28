package TextUI;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import gameelements.IRoomElement;
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
    this.view.print(this.model.getRoomModel().getCurrentRoom().getRoomName().concat(".\n\n"));
    TimeUnit.SECONDS.sleep(1);
    this.view.showOptions();
    this.view.print("\n".concat(this.model.getRoomModel().getCurrentRoom().getRoomDescription()).concat("\n"));

    while (this.in.getOptionFromUser()) {
      // check for monsters or puzzle effects
      switch (this.in.getCommand().toUpperCase()) {
        // move any direction into one case
        case "N":
          try {
            this.model.move("N");
            this.view.enterRoom(this.model.getRoomModel().getCurrentRoom().getRoomName(), this.model.getRoomModel().getCurrentRoom().getRoomDescription());
          }
          catch (IllegalArgumentException e) {
            this.view.print(e.getMessage());
          }
          break;
        case "S":
          try {
            this.model.move("S");
            this.view.enterRoom(this.model.getRoomModel().getCurrentRoom().getRoomName(), this.model.getRoomModel().getCurrentRoom().getRoomDescription());
          }
          catch (IllegalArgumentException e) {
            this.view.print(e.getMessage());
          }
          break;
        case "E":
          try {
            this.model.move("E");
            this.view.enterRoom(this.model.getRoomModel().getCurrentRoom().getRoomName(), this.model.getRoomModel().getCurrentRoom().getRoomDescription());
          }
          catch (IllegalArgumentException e) {
            this.view.print(e.getMessage());
          }
          break;
        case "W":
          try {
            this.model.move("W");
            this.view.enterRoom(this.model.getRoomModel().getCurrentRoom().getRoomName(), this.model.getRoomModel().getCurrentRoom().getRoomDescription());
          }
          catch (IllegalArgumentException e) {
            this.view.print(e.getMessage());
          }
          break;
        case "I":
          break;
        case "L":
          this.view.print(this.model.getRoomModel().getCurrentRoom().getRoomDescription().concat(".\n"));
          this.view.showItems();
          try {
            for (IRoomElement i : this.model.getRoomModel().getCurrentRoom().getElements()) {
              this.view.print(i.getDescription().concat("\n"));
            }
          }
          catch (Exception e) {
            this.view.print("You don't see any items\n");
            continue;
          }
          break;
        case "U":
          break;
        case "T":
          break;
        case "D":
          break;
        case "X":
          try {
            String elementName = this.in.getElement();
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

//else if (this.in.getCommand().equalsIgnoreCase("Q")) {
