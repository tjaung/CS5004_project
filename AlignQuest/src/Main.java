import java.io.BufferedReader;
import java.io.StringReader;

import enginedriver.GameEngineApp;

public class Main {
  public static void main(String [] args) throws Exception {
    // smoke tests - first send synthetic data via a string
    String s = "Sir Mix-A-Lot\nT NOTEBOOK\nN\nT HAIR CLIPPERS\nT KEY\nD NOTEBOOK\nQuit";
    GameEngineApp gameEngineApp = new GameEngineApp("../AlignQuest/align_quest_game_elements.json");
    gameEngineApp.start();


    // Next, comment the above and uncomment this to do some ad-hoc testing by hand via System.in
     // GameEngineApp gameEngineApp = new GameEngineApp("../FinalProject/src/resources/align_quest_game_elements.json", new InputStreamReader(System.in), System.out);
     // gameEngineApp.start();
  }
}
