package enginedriver;

//import org.json.simple.JSONObject;

import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
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
