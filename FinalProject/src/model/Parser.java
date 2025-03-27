package model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import gameelements.Fixture;
import gameelements.Item;
import gameelements.Room;

public class Parser {


  public static JSONObject readJSON(String gameFileName) {
    System.out.println(gameFileName);
//    String filePath = "/resources/dummy.json"; // Replace with the actual path
    try {
      String content = new String(Files.readAllBytes(Paths.get(gameFileName)));
      JSONObject jsonObject = new JSONObject(content);

      // Access data from the JSON object
//      System.out.println("Name: " + jsonObject.getString("name"));
//      System.out.println("Age: " + jsonObject.getInt("age"));

      return jsonObject;

    } catch (IOException e) {
      System.err.println("Error reading file: " + e.getMessage());
    } catch (JSONException e) {
      System.err.println("Error parsing JSON: " + e.getMessage());
    }
    return null;
  }

  public static String readJsonFile(String filePath) throws Exception {
    return Files.readString(Paths.get(filePath));
  }

  public static JSONObject parseJsonString(String jsonData) throws Exception {
    return new JSONObject(jsonData);
  }

  public static List<Item> parseItems(JSONObject jsonObject){
    List<Item> itemList = new ArrayList<>();
    JSONArray itemsArray = jsonObject.getJSONArray("item");
    for(int i=0; i < itemsArray.length(); i++){
      JSONObject itemJson = itemsArray.getJSONObject(i);
      String name = itemJson.getString("name");
      int weight = itemJson.getInt("weight");
      int maxUses = itemJson.getInt("max_uses");
      int usesRemaining = itemJson.getInt("uses_remaining");
      int value = itemJson.getInt("value");
      String whenUsed = itemJson.getString("when_used");
      String description = itemJson.getString("description");
      String picture = itemJson.getString("picture");
      Item item = new Item(weight,value,name,maxUses,usesRemaining,whenUsed,description,picture);
      itemList.add(item);
    }
    return itemList;
  }

  public static List<gameelements.Monster> parseMonster(JSONObject jsonObject) {
    List<gameelements.Monster> monsterList = new ArrayList<>();
    JSONArray monsterArray = jsonObject.getJSONArray("monster");
    for (int i = 0; i < monsterArray.length(); i++) {
      JSONObject monsterJson = monsterArray.getJSONObject(i);
      String name = monsterJson.getString("name");
      boolean active = monsterJson.getBoolean("active");
      boolean affectsTarget = monsterJson.getBoolean("affectsTarget");
      boolean affectsPlayer = monsterJson.getBoolean("affectsPlayer");
      Item solution = null;
      if (monsterJson.has("solution")) {
        JSONObject solutionJson = monsterJson.getJSONObject("solution");
        solution = (Item) parseItems(solutionJson);
      }
      int value = monsterJson.getInt("value");
      String description = monsterJson.optString("description");
      String effects = monsterJson.optString("effects");
      int damage = monsterJson.getInt("damage");
      String target = monsterJson.optString("target");
      boolean canAttack = monsterJson.getBoolean("canAttack");
      String attack = monsterJson.getString("attack");
      String picture = monsterJson.getString("picture");
      int health = monsterJson.getInt("health");
      gameelements.Monster monster = new gameelements.Monster(name, active, affectsTarget, affectsPlayer, solution, value, description,
          effects, damage, target, canAttack, attack, picture, health);
      monsterList.add(monster);
    }
    return monsterList;
  }
  public static List<gameelements.Puzzle> parsePuzzle(JSONObject jsonObject){
    List<gameelements.Puzzle> puzzleList = new ArrayList<>();
    JSONArray puzzleArray = jsonObject.getJSONArray("puzzles");
    for(int i=0; i < puzzleArray.length(); i++){
      JSONObject puzzleJson = puzzleArray.getJSONObject(i);
       String name = puzzleJson.getString("name");
       boolean active = puzzleJson.getBoolean("active");
       boolean affects_targets = puzzleJson.getBoolean("affects_target");
      boolean affects_player = puzzleJson.getBoolean("affects_player");
      Item solution = null;
      if (puzzleJson.has("solution")) {
        JSONObject solutionJson = puzzleJson.getJSONObject("solution");
        solution = (Item) parseItems(solutionJson);
      }
      int value = puzzleJson.getInt("value");
      String description = puzzleJson.getString("description");
      String effects = puzzleJson.getString("effects");
      String target = puzzleJson.getString("target");
      String picture = puzzleJson.getString("picture");
      gameelements.Puzzle puzzle = new gameelements.Puzzle(
          name,
          affects_targets,
          active,
          solution,
          value,
          effects,
          description,
          target,
          picture,
          affects_player);
      puzzleList.add(puzzle);
    }
    return puzzleList;
  }
  public static List<gameelements.Fixture> parseFixture(JSONObject jsonObject){
    List<gameelements.Fixture> fixturesList = new ArrayList<>();
    JSONArray fixtureArray = jsonObject.getJSONArray("fixtures");
    for(int i=0; i < fixtureArray.length(); i++){
      JSONObject fixtureJson = fixtureArray.getJSONObject(i);
      String name = fixtureJson.getString("name");
      int weight = fixtureJson.getInt("weight");
      String description = fixtureJson.getString("description");
      String picture = fixtureJson.getString("picture");
      boolean state = fixtureJson.getBoolean("states");
      gameelements.Puzzle puzzle = null;
      if (fixtureJson.has("puzzle")) {
        JSONObject puzzleJson =fixtureJson.getJSONObject("puzzle");
        puzzle = (gameelements.Puzzle) parsePuzzle(puzzleJson);
      }
      gameelements.Fixture fixture = new gameelements.Fixture(name,weight,description,picture,state,puzzle);
      fixturesList.add(fixture);
    }
    return fixturesList;
  }

  public static List<Room> parseRooms(JSONObject jsonObject){
    List<Room> roomList = new ArrayList<>();
    JSONArray roomArray = jsonObject.getJSONArray("rooms");
    for(int i=0; i < roomArray.length(); i++){
      JSONObject roomJson = roomArray.getJSONObject(i);
      String name = roomJson.getString("room_name");
      String description = roomJson.getString("description");
      int roomNumber = roomJson.getInt("room_number");
      int N = roomJson.getInt("N");
      int S = roomJson.getInt("S");
      int E = roomJson.getInt("E");
      int W = roomJson.getInt("W");
      gameelements.Puzzle puzzle = null;
      if (roomJson.has("puzzles")) {
        JSONObject puzzleJson =roomJson.getJSONObject("puzzles");
        puzzle = (gameelements.Puzzle) parsePuzzle(puzzleJson);
      }
      gameelements.Monster monster = null;
      if(roomJson.has("monster")){
        JSONObject monsterJson =roomJson.getJSONObject("monster");
        monster= (gameelements.Monster) parseMonster(monsterJson);
      }
      Item solution = null;
      if (roomJson.has("solution")) {
        JSONObject solutionJson = roomJson.getJSONObject("solution");
        solution = (Item) parseItems(solutionJson);
      }
      Fixture fixture = null;
      if (roomJson.has("fixtures")) {
        JSONObject fixtureJson = roomJson.getJSONObject("fixtures");
         fixture = (Fixture) parseFixture(fixtureJson);
      }
      String picture = roomJson.getString("picture");
      Room room = new Room(name,roomNumber,description,N,S,E,W,monster,puzzle,solution,fixture,picture);
      roomList.add(room);
    }
    return roomList;
  }



}




