package gameelements;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for gameelements.Room.
 */
public class Room {
    private String roomName;
    private int roomNumber;
    private String description;
    private int N;
    private int S;
    private int E;
    private int W;
    private IRoomElement monster = null;
    private IRoomElement puzzle = null;
    private IRoomElement items = null;
    private IRoomElement fixtures = null;
    private String picture;
    private List<IRoomElement> elements;

    /**
     * Constructor for gameelements.Room. Takes in a lot of parameters to construct it.
     */
    public Room(
            String roomName,
            int roomNumber,
            String description,
            int N,
            int S,
            int E,
            int W,
            IRoomElement monster,
            IRoomElement puzzle,
            IRoomElement items,
            IRoomElement fixtures,
            String picture) {

        // Check parameters
        //this.checkParameters(roomName, roomNumber, description, N, S, E, W, puzzle, items, fixtures, picture)
        this.roomName = roomName;
        this.description = description;
        this.N = N;
        this.S = S;
        this.E = E;
        this.W = W;
        this.monster = monster;
        this.puzzle = puzzle;
        this.items = items;
        this.fixtures = fixtures;
        this.picture = picture;
        this.roomNumber = roomNumber;
    }

    public String getRoomName() {
        return roomName;
    }

    /**
     * Getter for room number.
     *
     * @return int of room number
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * Getter for the room description.
     * 
     * @return desc - string of the room desc.
     */
    public String getRoomDescription() {
        return description;
    }    

    /**
     * Getter for North direction.
     * 
     * @return N - int of N room
     */
    public int getN() {
        return N;
    }

    /**
     * Getter for South direction.
     * 
     * @return S - int of S room
     */
    public int getS() {
        return S;
    }

    /**
     * Getter for East direction.
     * 
     * @return E - int of E room
     */
    public int getE() {
        return E;
    }

    /**
     * Getter for West direction.
     * 
     * @return W - int of W room
     */
    public int getW() {
        return W;
    }

    public IRoomElement getMonster() {
        return monster;
    }

    public IRoomElement getPuzzle() {
        return puzzle;
    }

    public IRoomElement getItems() {
        return items;
    }

    public IRoomElement getFixtures() {
        return fixtures;
    }

    @Override
    public String toString() {
        String m = monster == null ? "none" : monster.getName();
        String p = puzzle == null ? "none" : puzzle.getName();
        String i = items == null ? "none" : items.getName();
        String f = fixtures == null ? "none" : fixtures.getName();

      return "Room: " + roomName + "\n"
              + "Description: " + description + "\n"
              + "N: " + N + "\n"
              + "S: " + S + "\n"
              + "E: " + E + "\n"
              + "W: " + W + "\n"
              + "Monster: " + m + "\n"
              + "Puzzle: " + p + "\n"
              + "Items: " + i + "\n"
              + "Fixtures: " + f;
    }

    //COLLECTS IROOMELEMENTS
    // SHOULDN't EACH TYPE BE A LIST IN ROOM??
    public void addElements() {
        this.elements = new ArrayList<IRoomElement>();
        if (this.getFixtures() != null) {
            this.elements.add(this.getFixtures());
        }
        if (this.getMonster() != null) {
            this.elements.add(this.getMonster());
        }
        if (this.getPuzzle() != null) {
            this.elements.add(this.getPuzzle());
        }
        if (this.getItems() != null) {
            this.elements.add(this.getItems());
        }
    }

    public List<IRoomElement> getElements() {
        return elements;
    }
}