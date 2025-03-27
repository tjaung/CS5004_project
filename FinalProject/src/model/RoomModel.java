package model;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import gameelements.Room;

/**
 * This class is the game's database
 */
public class RoomModel {
  List<Room> roomList;
  Room currentRoom;

  public RoomModel(String gameFileName) throws Exception{
//    String initialJSON = Parser.readJsonFile(gameFileName);
    JSONObject JSON = Parser.readJSON(gameFileName);
    this.roomList = Parser.parseRooms(JSON);
  }

  public Room getCurrentRoom() {
    return this.currentRoom;
  }

  public List<Room> getRoomList() {
    return this.roomList;
  }

  public void add(Room room) {
    // if room in roomList
    this.roomList.add(room);
  }

  public void remove(Room room) {
    // if room in roomList
    this.roomList.remove(room);
  }

}
