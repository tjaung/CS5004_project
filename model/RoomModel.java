package model;

import java.util.ArrayList;
import java.util.List;

import gameelements.Room;

/**
 * This class is the game's database
 */
public class RoomModel {
  List<Room> roomList;
  Room currentRoom;

  public RoomModel(Readable source) {
    this.roomList = Parser.parseRooms();
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
