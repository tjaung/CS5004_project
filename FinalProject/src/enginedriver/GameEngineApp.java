package enginedriver;

//import org.json.simple.JSONObject;

import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

import TextUI.GameController;
import model.GameModel;

public class GameEngineApp {
  private GameModel model;
  private GameController controller;

  public GameEngineApp(String gameFileName, Readable source, Appendable output) throws Exception {
    GameModel model = new GameModel(gameFileName);
    this.controller = new GameController(model);

  }

  public void start() throws IOException, InterruptedException {
    // Should create a new GameModel and new Controller
    // Then tell controller to go
    this.controller.go();

  }
}
