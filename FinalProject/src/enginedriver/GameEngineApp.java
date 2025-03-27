package enginedriver;

import java.io.IOException;

import model.GameModel;

public class GameEngineApp {

  public GameEngineApp(String gameFileName, Readable source, Appendable output) throws Exception {
    GameModel model = new GameModel(gameFileName);

  }

  public void start() {
    // Should create a new GameModel and new Controller
    // Then tell controller to go
  }
}
