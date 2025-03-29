import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

import enginedriver.GameEngineApp;

public class Main {
  public static void main(String [] args) throws Exception {
    // smoke tests - first send synthetic data via a string
    String s = "Sir Mix-A-Lot\nT NOTEBOOK\nN\nT HAIR CLIPPERS\nT KEY\nD NOTEBOOK\nQuit";
    BufferedReader stringReader = new BufferedReader(new StringReader(s));
//    GameEngineApp gameEngineApp = new GameEngineApp("../FinalProject/src/resources/dummy.json", stringReader, System.out);
//    gameEngineApp.start();


    // Next, comment the above and uncomment this to do some ad-hoc testing by hand via System.in
     GameEngineApp gameEngineApp = new GameEngineApp("../FinalProject/src/resources/align_quest_game_elements.json", new InputStreamReader(System.in), System.out);
     gameEngineApp.start();
  }
}
