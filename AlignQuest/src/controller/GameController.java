package controller;

import java.io.IOException;
import java.util.List;

import gameelements.IRoomElement;
import gameelements.Item;
import model.GameModel;
import view.TextView;

public class GameController {
  private GameModel model;
  private GameCommandReader in;
  private TextView view;
  private boolean quit;

  public GameController(GameModel model, TextView view) {
    this.model = model;
    this.quit = false;
  }

  public void go() throws IOException {
    this.view = new TextView();
    this.in = new GameCommandReader();

    this.view.showIntro();
    this.view.print(this.model.getRoomModel().getCurrentRoom().getName().concat(".\n\n"));
    this.view.showOptions();
    this.view.print("\n".concat(this.model.getRoomModel().getCurrentRoom().toString()).concat("\n"));

    while (this.quit == false) {
      this.in.getOptionFromUser();
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
          } catch (IllegalArgumentException e) {
            this.view.print(e.getMessage());
          }
          break;

        case "I":
          List<Item> items = this.model.getPlayer().getInventory().getItems();
          this.view.printInventory(items);
          this.view.print("\n");
          break;

        case "L":
          this.view.print(this.model.getRoomModel().getCurrentRoom().toString().concat(".\n"));
          this.view.showItems();
          try {
            for (IRoomElement i : this.model.getRoomModel().getCurrentRoom().getItems()) {
              this.view.print(i.getName().concat("\n"));
            }
          } catch (Exception e) {
            this.view.print("You don't see any items\n");
          }
          break;

        case "U":
          try {
            this.model.useItem(this.model.getPlayer().getInventory().getItem(this.in.getElement()));
            this.view.print(this.model.getPlayer().getInventory().getItem(this.in.getElement()).getWhenUsed());
          } catch (Exception e) {
            this.view.print(e.getMessage());
          }
          break;

        case "T":
          try {
            this.model.takeItem(this.model.getRoomModel().getCurrentRoom().getItem(this.in.getElement()));
            this.view.print(this.in.getElement().concat(" added to your inventory.\n"));
          } catch (Exception e) {
            this.view.print(e.getMessage());
          }
          break;

        case "D":
          try {
            this.model.dropItem(this.model.getPlayer().getInventory().getItem(this.in.getElement()));
            this.view.print("You dropped ".concat(this.in.getElement().concat(".\n")));
          } catch (Exception e) {
            this.view.print(e.getMessage());
          }
          break;

        case "X":
          try {
            String elementName = this.in.getElement();
            String examineString = this.model.getRoomModel().getCurrentRoom().getElement(elementName).getDescription();
            this.view.print(examineString.concat("\n"));
          } catch (Exception e) {
            this.view.print(e.getMessage());
          }
          break;

        case "A":
          try {
            this.model.answerRiddle(this.in.getElement());
            this.view.print("Success!\n");
          }
          catch (Exception e) {
            this.view.print(e.getMessage());
          }
          break;

        case "O":
          this.view.showOptions();
          break;

        case "Q":
          this.quit = true;
          break;

        case "+":
          String response = this.model.saveGame("save");
          this.view.print(response + "\n");
          break;

        case "-":
          // pull data from resources if available
          String res = this.model.loadGame("save");
          // if found, run through parser
          // load world with new data
          this.view.print(res + "\n");
          break;

        default:
          this.view.showOptionError();
          break;
      }

      if (quit == false) {
        try {
          this.model.takeDamage();
          this.view.print(this.model.getRoomModel().getCurrentRoom().getMonster().getName().concat(
                  " ").concat(
                  this.model.getRoomModel().getCurrentRoom().getMonster().getAttack()));
          this.view.print("\n");
          this.view.print("Player takes ".concat(String.valueOf(
                  this.model.getRoomModel().getCurrentRoom().getMonster().getDamage()).concat(
                  " damage!\n")));
          this.view.print(this.model.getPlayer().toString());
        } catch (Exception e) {
          this.view.print(e.getMessage());
        }
      }

      if (this.quit == false) {
        this.quit = this.model.gameOver();
      }
    }

    this.view.print(this.model.endGame());



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
