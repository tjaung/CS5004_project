import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

import gameelements.Fixture;
import gameelements.IRoomElement;
import gameelements.Item;

import gameelements.Monster;
import gameelements.Puzzle;
import gameelements.Room;
import model.Parser;

/**
 * Test cases for the json parser. Verifying that game state is properly managed, and
 * all game actions are properly validated.
 */
public class ParserTest {
  private final String path1 = "../FinalProject/src/resources/dummy.json";
  private final String path2 = "../FinalProject/src/resources/align_quest_game_elements.json";

  /**
   * Setup for each parse test.
   *
   * @throws Exception if error throw this
   */
  @BeforeEach
  void setUp() throws Exception {
    String path1 = "../FinalProject/src/resources/dummy.json";
    String path2 = "../FinalProject/src/resources/align_quest_game_elements.json";

    JSONObject json1 = Parser.parseJsonString(Parser.readJsonFile(path1));
    JSONObject json2 = Parser.parseJsonString(Parser.readJsonFile(path2));
  }

  /**
   * Test that parser creates strings of json files.
   */
  @Test
  public void testFileToString() throws Exception {
    String gameFile = Parser.readJsonFile(path1);
    Assert.assertNotNull(gameFile);
    Assert.assertTrue(gameFile instanceof String);

    String gameFile2 = Parser.readJsonFile(path2);
    Assert.assertNotNull(gameFile2);
    Assert.assertTrue(gameFile2 instanceof String);

    // test for exceptions
    try {
      String failTest = Parser.readJsonFile("./resources/notfound.json");
      Assert.fail("Error reading json file");
    } catch (Exception iae) {
      //assertEquals("Position occupied", iae.getMessage());
      Assert.assertTrue(!iae.getMessage().isEmpty());
    }
  }

  @Test
  public void testStringToJsonObject  () throws Exception {
    String gameFile = Parser.readJsonFile(path1);
    JSONObject json = Parser.parseJsonString(gameFile);
    Assert.assertNotNull(json);

    String gameFile2 = Parser.readJsonFile(path2);
    JSONObject json2 = Parser.parseJsonString(gameFile2);
    Assert.assertNotNull(json2);

    // test for exceptions
    try {
      String failString = "this is not a json string";
      String failTest = Parser.readJsonFile(failString);
      Assert.fail("Error parsing json string");
    } catch (Exception iae) {
      //assertEquals("Position occupied", iae.getMessage());
      Assert.assertTrue(!iae.getMessage().isEmpty());
    }

    try {
      String failString = "100000";
      String failTest = Parser.readJsonFile(failString);
      Assert.fail("Error parsing json string");
    } catch (Exception iae) {
      //assertEquals("Position occupied", iae.getMessage());
      Assert.assertTrue(!iae.getMessage().isEmpty());
    }
  }

  /**
   * Test parser on Items.
   *
   * @throws Exception any errors
   */
  @Test
  public void testItemParseHasAllElements() throws Exception {
    JSONObject json1 = Parser.parseJsonString(Parser.readJsonFile(path1));
    JSONArray items1 = json1.getJSONArray("items");
    Assert.assertTrue(items1.length() > 0);
    List<IRoomElement> parsedList1 = Parser.parseItems(items1);
    List<Item> itemList1 = new ArrayList<>();
    for (IRoomElement iRoomElement : parsedList1) {
      Item item = (Item) iRoomElement;
      itemList1.add(item);
    }

    Assert.assertTrue(itemList1.getFirst().getName().equals("Ticket"));
    Assert.assertEquals(1, itemList1.getFirst().getWeight());
    Assert.assertEquals(1, itemList1.getFirst().getMaxUses());
    Assert.assertEquals(1, itemList1.getFirst().getUsesRemaining());
    Assert.assertEquals(5, itemList1.getFirst().getValue());
    Assert.assertEquals("You insert the ticket. 'Swish! Beep!'",
            itemList1.getFirst().getWhenUsed());
    Assert.assertEquals("A complimentary museum ticket. It says ADMIT ONE, pwd = Align.",
            itemList1.getFirst().getDescription());
    Assert.assertNull(itemList1.getFirst().getPicture());

    // test exceptions
    try { // null array passed in
      JSONArray failArray = null;
      List<IRoomElement> failTest = Parser.parseItems(failArray);
      Assert.fail("itemsArray is null");
    } catch (Exception iae) {
      //assertEquals("Position occupied", iae.getMessage());
      Assert.assertTrue(!iae.getMessage().isEmpty());
    }
    // empty array passed in
    JSONArray failArray = new JSONArray();
    List<IRoomElement> failTest = Parser.parseItems(failArray);
    Assert.assertTrue(failTest.size() == 0);
  }

  /**
   * Test parser on Monsters.
   *
   * @throws Exception any errors
   */
  @Test
  public void testMonsterParseHasAllElements() throws Exception {
    // set up by getting monsters and item lists
    JSONObject json2 = Parser.parseJsonString(Parser.readJsonFile(path2));
    JSONArray monsters = json2.getJSONArray("monsters");
    JSONArray items = json2.getJSONArray("items");
    List<IRoomElement> itemList = Parser.parseItems(items);
    Assert.assertTrue(monsters.length() > 0);
    List<IRoomElement> parsedList1 = Parser.parseMonster(monsters, itemList);
    List<Monster> monsterList = new ArrayList<>();
    for (IRoomElement iRoomElement : parsedList1) {
      Monster mon = (Monster) iRoomElement;
      monsterList.add(mon);
    }

    Assert.assertTrue(monsterList.getFirst().getName().equals("Rabbit"));
    Assert.assertTrue(monsterList.getFirst().isActive());
    Assert.assertTrue(monsterList.getFirst().isAffectsPlayer());
    Assert.assertTrue(monsterList.getFirst().isAffectsTarget());
    Assert.assertTrue(monsterList.getFirst().getSolution() instanceof Item);
    Assert.assertEquals(300,
            monsterList.getFirst().getValue());
    Assert.assertEquals("Awww. A furry rabbit twitching its nose and eating a carrot. Makes you want to pet him",
            monsterList.getFirst().getDescription());
    Assert.assertEquals("A monster Rabbit moves towards you! He's blocking the way north. \nI think you might be dinner!",
                    monsterList.getFirst().getEffects());
    Assert.assertEquals(-15, monsterList.getFirst().getDamage());
    Assert.assertEquals("7:Dining Room", monsterList.getFirst().getTarget());
    Assert.assertTrue(monsterList.getFirst().canAttack());
    Assert.assertEquals("licks you with a giant tongue!", monsterList.getFirst().getTypeOfAttack());
    Assert.assertEquals("monster-rabbit.png", monsterList.getFirst().getPicture());


    // test exceptions
    try { // null array passed in
      List<IRoomElement> failArray = null;
      List<IRoomElement> failTest = Parser.parseMonster(monsters, failArray);
      Assert.fail("One or more arguments is null");
    } catch (Exception iae) {
      //assertEquals("Position occupied", iae.getMessage());
      Assert.assertTrue(!iae.getMessage().isEmpty());
    }
    // empty array passed in
    JSONArray failArray = new JSONArray();
    List<IRoomElement> itemArray = new ArrayList<>();
    List<IRoomElement> failTest = Parser.parseMonster(failArray, itemArray);
    Assert.assertTrue(failTest.size() == 0);
  }

  /**
   * Test parser on Puzzle.
   *
   * @throws Exception any errors
   */
  @Test
  public void testPuzzleParseHasAllElements() throws Exception {
    // set up by getting monsters and item lists
    JSONObject json2 = Parser.parseJsonString(Parser.readJsonFile(path2));
    JSONArray puzzles = json2.getJSONArray("puzzles");

    JSONArray items = json2.getJSONArray("items");
    List<IRoomElement> itemList = Parser.parseItems(items);
    Assert.assertTrue(puzzles.length() > 0);

    List<IRoomElement> parsedList1 = Parser.parsePuzzle(puzzles, itemList);
    List<Puzzle> puzzleList = new ArrayList<>();
    for (IRoomElement iRoomElement : parsedList1) {
      Puzzle puz = (Puzzle) iRoomElement;
      puzzleList.add(puz);
    }

    Assert.assertTrue(puzzleList.getFirst().getName().equals("DARKNESS"));
    Assert.assertTrue(puzzleList.getFirst().isActive());
    Assert.assertTrue(puzzleList.getFirst().affects_player());
    Assert.assertTrue(puzzleList.getFirst().affects_target());
    Assert.assertTrue(puzzleList.getFirst().getSolution() instanceof Item);
    Assert.assertEquals(150,
            puzzleList.getFirst().getValue());
    Assert.assertEquals("Darkness! You cannot see!",
            puzzleList.getFirst().getDescription());
    Assert.assertEquals("It's dark! You cannot see anything! Maybe we should go back?",
            puzzleList.getFirst().getEffects());
    Assert.assertEquals("6:Kitchen", puzzleList.getFirst().getTarget());
    Assert.assertEquals("darkness.png", puzzleList.getFirst().getPicture());


    // test exceptions
    try { // null array passed in
      List<IRoomElement> failArray = null;
      List<IRoomElement> failTest = Parser.parseMonster(puzzles, failArray);
      Assert.fail("One or more arguments is null");
    } catch (Exception iae) {
      //assertEquals("Position occupied", iae.getMessage());
      Assert.assertTrue(!iae.getMessage().isEmpty());
    }
    // empty array passed in
    JSONArray failArray = new JSONArray();
    List<IRoomElement> itemArray = new ArrayList<>();
    List<IRoomElement> failTest = Parser.parseMonster(failArray, itemArray);
    Assert.assertTrue(failTest.size() == 0);
  }

  /**
   * Test parser on fixtures.
   *
   * @throws Exception any errors
   */
  @Test
  public void testFixturesParseHasAllElements() throws Exception {
    // set up by getting monsters and item lists
    JSONObject json2 = Parser.parseJsonString(Parser.readJsonFile(path2));
    JSONArray fixtures = json2.getJSONArray("fixtures");

    JSONArray items = json2.getJSONArray("items");
    List<IRoomElement> itemList = Parser.parseItems(items);

    JSONArray puzzles = json2.getJSONArray("puzzles");
    List<IRoomElement> puzzleList = Parser.parsePuzzle(puzzles, itemList);
    Assert.assertTrue(fixtures.length() > 0);

    List<IRoomElement> parsedList1 = Parser.parseFixture(fixtures, puzzleList);
    List<Fixture> fixtureList = new ArrayList<>();
    for (IRoomElement iRoomElement : parsedList1) {
      Fixture fix = (Fixture) iRoomElement;
      fixtureList.add(fix);
    }

    Assert.assertTrue(fixtureList.getFirst().getName().equals("Desk"));
    Assert.assertEquals(1000, fixtureList.getFirst().getWeight());
    Assert.assertNull(fixtureList.getFirst().getPuzzle());
    Assert.assertTrue(fixtureList.getFirst().getState());
    Assert.assertEquals("An old wooden desk with a mess of papers. A note says: \"use thumb drive!\"",
            fixtureList.getFirst().getDescription());
    Assert.assertNull(fixtureList.getFirst().getPicture());

    // test exceptions
    try { // null array passed in
      List<IRoomElement> failArray = null;
      List<IRoomElement> failTest = Parser.parseFixture(fixtures, failArray);
      Assert.fail("One or more arguments is null");
    } catch (Exception iae) {
      //assertEquals("Position occupied", iae.getMessage());
      Assert.assertTrue(!iae.getMessage().isEmpty());
    }
    // empty array passed in
    JSONArray failArray = new JSONArray();
    List<IRoomElement> itemArray = new ArrayList<>();
    List<IRoomElement> failTest = Parser.parseFixture(failArray, itemArray);
    Assert.assertTrue(failTest.size() == 0);
  }

  /**
   * Test parser on Rooms.
   *
   * @throws Exception any errors
   */
  @Test
  public void testRoomsParseHasAllElements() throws Exception {
    // set up by getting monsters and item lists
    JSONObject json2 = Parser.parseJsonString(Parser.readJsonFile(path2));
    List<Room> rooms = Parser.parseRooms(json2);
    Assert.assertTrue(rooms.size() > 0);


    Assert.assertTrue(rooms.getFirst().getName().equals("Courtyard"));
    Assert.assertEquals(1, rooms.getFirst().getRoomNumber());
    Assert.assertEquals("A beautiful courtyard with flowers on both sides of the stone walkway. \nThe walkway leads north. A billboard is in the distance.",
            rooms.getFirst().getDescription());
    Assert.assertEquals(2, rooms.getFirst().getN());
    Assert.assertEquals(0, rooms.getFirst().getS());
    Assert.assertEquals(0, rooms.getFirst().getE());
    Assert.assertEquals(0, rooms.getFirst().getW());
    Assert.assertNull(rooms.getFirst().getPuzzle());
    Assert.assertNull(rooms.getFirst().getMonster());
    Assert.assertTrue(rooms.getFirst().getItems().getFirst() instanceof IRoomElement);
    Assert.assertTrue(rooms.getFirst().getFixtures().getFirst() instanceof IRoomElement);
    Assert.assertEquals("courtyard.png", rooms.getFirst().getPicture());

    // test exceptions
    try { // null array passed in
      JSONObject failArray = null;
      List<Room> failTest = Parser.parseRooms(failArray);
      Assert.fail("JSON argument is null");
    } catch (Exception iae) {
      //assertEquals("Position occupied", iae.getMessage());
      Assert.assertTrue(!iae.getMessage().isEmpty());
    }
    // empty object passed in
    try { // null array passed in
      JSONObject failArray = new JSONObject();
      List<Room> failTest = Parser.parseRooms(failArray);
      Assert.fail("JSON argument is empty");
    } catch (Exception iae) {
      //assertEquals("Position occupied", iae.getMessage());
      Assert.assertTrue(!iae.getMessage().isEmpty());
    }
  }

}
