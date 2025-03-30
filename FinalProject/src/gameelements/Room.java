package gameelements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.ReverseParser;

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
    private List<IRoomElement> monster;
    private List<IRoomElement> puzzle;
    private List<IRoomElement> items;
    private List<IRoomElement> fixtures;
    private String picture;
    private List<IRoomElement> elements;
    private boolean hasEffect;
    private String effectDescription;
    private boolean clear;
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
            List<IRoomElement> monster,
            List<IRoomElement> puzzle,
            List<IRoomElement> items,
            List<IRoomElement> fixtures,
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

        this.elements = Stream.of(monster, puzzle, items,fixtures)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        if (this.getMonster() == null && this.getPuzzle() == null) {
            this.clear = true;
        }
        else {
            this.clear = false;
        }
    }

    public String getName() {
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
    public String getDescription() {
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

    public void setN(int n) {
        this.N = n;
    }

    public void setS(int s) {
        this.S = s;
    }

    public void setE(int e) {
        this.E = e;
    }

    public void setW(int w) {
        this.W = w;
    }

    public String getPicture() {
        return picture;
    }

    public Monster getMonster() {

        if (monster.size() == 0) {
            return null;
        }
        return (Monster) monster.get(0); // assuming only 1 monster per room
    }

    public Puzzle getPuzzle() {
        if (puzzle.size() == 0) {
            return null;
        }
        return (Puzzle) puzzle.get(0); // assuming only 1 puzzle per room
    }

    public List<IRoomElement> getItems() {
        return items;
    }

    public String printItems() {
        if(items.size() == 0) {
            return "";
        }
        StringBuilder out = new StringBuilder();
        for (IRoomElement i : items) {
            out.append(i.getName()).append(" ");
        }
        return out.toString();
    }

    public List<IRoomElement> getFixtures() {
        if(fixtures.size() == 0) {
            return null;
        }
        return fixtures;
    }

    public String printFixtures() {
        if(fixtures.size() == 0) {
            return "";
        }
        StringBuilder out = new StringBuilder();
        for (IRoomElement i : fixtures) {
            out.append(i.getName()).append(" ");
        }
        return out.toString();
    }

    public IRoomElement getElement(String elementName) {
        if(elementName.isEmpty()) {
            return null;
        }
        for (IRoomElement i : elements) {
            if (i.getName().equalsIgnoreCase(elementName)) {
                return i;
            }
        }
        throw new IllegalArgumentException(elementName.concat(" does not exist.\n"));
    }

    public Item getItem(String itemName) {
        if(itemName.isEmpty()) {
            return null;
        }
        for (IRoomElement i : this.items) {
            if (i.getName().equalsIgnoreCase(itemName)) {
                return (Item) i;
            }
        }
        throw new IllegalArgumentException("There is no ".concat(itemName).concat(" here.\n"));
    }

    @Override
    public String toString() {
        if (this.getPuzzle() != null) {
            if (this.getPuzzle().isActive()) {
                return this.getPuzzle().getEffects().concat("\n");
            }
            else {
                return this.getDescription();
            }
        }
        else if (this.getMonster() != null) {
            if (this.getMonster().isActive()) {
                return this.getMonster().getEffects().concat("\n");
            }
            else {
                return this.getDescription();
            }
        }
        else {
            return this.getDescription();
        }

    }

    public List<IRoomElement> getElements() {
        return elements;
    }

    public void removeItem(Item item) {
        this.items.remove(item);
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public boolean hasItem(Item item) {
        for (IRoomElement i : this.items) {
            if (i.getName().equalsIgnoreCase(item.getName())) {
                return true;
            }
        }
        return false;
    }

    public boolean isClear() {
        return this.clear;
    }

    public void setClear(boolean bool) {
        this.clear = bool;
    }

}