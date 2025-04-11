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
}
