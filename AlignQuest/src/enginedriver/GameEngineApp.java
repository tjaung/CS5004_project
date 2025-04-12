package enginedriver;

//import org.json.simple.JSONObject;

import controller.GameController;
import controller.VisualController;
import model.GameModel;
import view.GameView;
import view.TextView;

public class GameEngineApp {
  private GameModel model;
  private VisualController controller;

  public GameEngineApp(String gameFileName) throws Exception {
    GameModel model = new GameModel(gameFileName);
    GameView view = new GameView("Align Quest");
    this.controller = new VisualController(model, view);

  }

  public void start() throws Exception {
    // Should create a new GameModel and new Controller
    // Then tell controller to go
    this.controller.go();
  }

  public static void main(String [] args) throws Exception {
    // smoke tests - first send synthetic data via a string
    String s = "Sir Mix-A-Lot\nT NOTEBOOK\nN\nT HAIR CLIPPERS\nT KEY\nD NOTEBOOK\nQuit";
    GameEngineApp gameEngineApp = new GameEngineApp("../AlignQuest/align_quest_game_elements.json");
    gameEngineApp.start();
  }
}
