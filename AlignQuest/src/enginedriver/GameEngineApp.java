package enginedriver;

//import org.json.simple.JSONObject;

import org.w3c.dom.Text;

import controller.GameController;
import controller.IController;
import controller.VisualController;
import model.GameModel;
import view.GraphicsView;
import view.TextView;

public class GameEngineApp {
  private GameModel model;
  private IController controller;

  public GameEngineApp(String gameFileName, Boolean graphicsMode) throws Exception {
    if (graphicsMode == true) {
      GameModel model = new GameModel(gameFileName);
      GraphicsView view = new GraphicsView("Align Quest");
      this.controller = new VisualController(model, view);
    }
    else {
      GameModel model = new GameModel(gameFileName);
      TextView view = new TextView(System.out);
      this.controller = new GameController(model, view);
    }
  }

  public void start() throws Exception {
    // Should create a new GameModel and new Controller
    // Then tell controller to go
    this.controller.go();
  }

  public static void main(String [] args) throws Exception {
    // smoke tests - first send synthetic data via a string
    GameEngineApp gameEngineApp;
    if (args.length == 0) {
      gameEngineApp = new GameEngineApp("../AlignQuest/align_quest_game_elements.json", true);
    }
    else {
      String fileName = args[0];
      // graphics mode
      if (args[1] == "graphics") {
        gameEngineApp = new GameEngineApp("../AlignQuest/".concat(fileName), true);
      }
      // text mode
      else if (args[1] == "text") {
        gameEngineApp = new GameEngineApp("../AlignQuest/".concat(fileName), false);
      }
      // batch mode
      else {
        gameEngineApp = new GameEngineApp("../AlignQuest/".concat(fileName), false);
      }
      String s = "Sir Mix-A-Lot\nT NOTEBOOK\nN\nT HAIR CLIPPERS\nT KEY\nD NOTEBOOK\nQuit";
      //GameEngineApp gameEngineApp = new GameEngineApp("../AlignQuest/align_quest_game_elements.json", false);
    }
    gameEngineApp.start();
  }
}
