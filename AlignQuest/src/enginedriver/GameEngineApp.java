package enginedriver;

//import org.json.simple.JSONObject;

import controller.VisualController;
import model.GameModel;
import view.GraphicsView;

public class GameEngineApp {
  private GameModel model;
  private VisualController controller;

  public GameEngineApp(String gameFileName, Boolean graphicsMode) throws Exception {
    GameModel model = new GameModel(gameFileName);
    GraphicsView view = new GraphicsView("Align Quest");
    this.controller = new VisualController(model, view);

  }

  public void start() throws Exception {
    // Should create a new GameModel and new Controller
    // Then tell controller to go
    this.controller.go();
  }

  public static void main(String [] args) throws Exception {
    // smoke tests - first send synthetic data via a string
    String fileName = args[0];
    GameEngineApp gameEngineApp;
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
    gameEngineApp.start();
  }
}
