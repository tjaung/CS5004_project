package model;

import org.json.JSONObject;

import java.util.List;

import gameelements.Room;

/**
 * This class is the game's database
 */
public class RoomModel {
  List<Room> roomList;
  Room currentRoom;

  public RoomModel(String gameFileName) throws Exception{
    // parse local json file to create world
    String initialJSON = Parser.readJsonFile(gameFileName);
    JSONObject JSON = Parser.parseJsonString(initialJSON);
    this.roomList = Parser.parseRooms(JSON);
    // initialize current room as room 1
    this.currentRoom = this.roomList.stream()
            .filter(room -> room.getRoomNumber() == 1)
            .findFirst()
            .get();
  }

  public Room getCurrentRoom() {
    return this.currentRoom;
  }

  public void setCurrentRoom(Room currentRoom) {
    this.currentRoom = currentRoom;
  }

  public List<Room> getRoomList() {
    return this.roomList;
  }

  public void addRoom(Room room) {
    // if room in roomList
    this.roomList.add(room);
  }

  public void removeRoom(Room room) {
    // if room in roomList
    this.roomList.remove(room);
  }


  public Room queryRoom(int roomNumber) {
    return this.roomList.stream()
            .filter(room -> room.getRoomNumber() == roomNumber)
            .findFirst()
            .get();
  }

  public String parseRoomsToJSON() {
    return ReverseParser.readRoom(this.roomList);
  }

  public boolean allClear() {
    for (Room room : this.roomList) {
      if (room.isClear() == false) {
        return false;
      }
    }
    return true;
  }

}
