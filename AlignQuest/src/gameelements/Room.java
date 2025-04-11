package gameelements;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The room class
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
     * Constructor for the class.
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

    /**
     * Getter for name.
     * @return room name
     */
    public String getName() {
        return roomName;
    }

    /**
     * Getter for room number.
     * @return int of room number
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * Getter for the room description.
     * @return desc - string of the room desc.
     */
    public String getDescription() {
        if(this.getPuzzle() != null && this.getPuzzle().isActive()) {
            return this.getPuzzle().getDescription();
        } else {
            return this.description;
        }
    }

    /**
     * Getter for North direction.
     * @return N - int of N room
     */
    public int getN() {
        return N;
    }

    /**
     * Getter for South direction.
     * @return S - int of S room
     */
    public int getS() {
        return S;
    }

    /**
     * Getter for East direction.
     * @return E - int of E room
     */
    public int getE() {
        return E;
    }

    /**
     * Getter for West direction.
     * @return W - int of W room
     */
    public int getW() {
        return W;
    }

    
    /**
     * Sets room number for the North.
     */ 
    public void setN(int n) {
        this.N = n;
    }

    /**
     * Sets room number for the South.
     * @param s
     */
    public void setS(int s) {
        this.S = s;
    }

    
    /**
     * Sets room number for the East.
     *
     */         
    public void setE(int e) {
        this.E = e;
    }

     /**
     * Sets room number for the West.
     *
     */     
    public void setW(int w) {
        this.W = w;
    }

    /**
     * Getter for picture.
     * @return  picture.
     */
    public String getPicture() {
        if(this.getPuzzle() != null
                && this.getPuzzle().isActive()
                && this.getPuzzle().getPicture() != null) {
            return this.getPuzzle().getPicture();
        } else if (this.getMonster() != null
                && this.getMonster().isActive()) {
            return this.getMonster().getPicture();
        } else {
            return this.picture;
        }
    }

    /**
     * Getter for monster.
     * @return
     */
    public Monster getMonster() {

        if (monster.size() == 0) {
            return null;
        }
        return (Monster) monster.get(0); // assuming only 1 monster per room
    }

    /**
     * Getter for puzzle.
     * @return  The room's puzzle. Null if the room has no puzzle.
     */
    public Puzzle getPuzzle() {
        if (puzzle.size() == 0) {
            return null;
        }
        return (Puzzle) puzzle.get(0); // assuming only 1 puzzle per room
    }

    /**
     *
     * @return
     */
    public List<IRoomElement> getItems() {
        return items;
    }

    /**
     *
     * @return
     */
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

    /**
     *
     * @return
     */
    public List<IRoomElement> getFixtures() {
        if(fixtures.size() == 0) {
            return null;
        }
        return fixtures;
    }

    /**
     *
     * @return
     */
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

    /**
     * Gets the input element
     *
     */  
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

    /**
     *
     * @param itemName
     * @return
     */
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

    /**
     *
     * @return
     */
    public String toString() {
        String desc = this.getDescription();
        String items = this.getItems().stream()
                .map(item -> item.getName())
                .collect(Collectors.joining(", "));
        String itemString = items != null ? "\nItems you see in this room: " + items : "";
        // if puzzle in room, only return puzzle effect
        if (this.getPuzzle() != null) {
            if (this.getPuzzle().isActive()) {
                return this.getPuzzle().getEffects().concat("\n");
            }
            else {
                return desc + itemString;
            }
        }
        // if monster in room print both room desc and monster effect
        else if (this.getMonster() != null) {
            if (this.getMonster().isActive()) {
                return this.getMonster().getEffects().concat("\n");
            }
            else {
                return desc + itemString;
            }
        }
        // just print room desc
        else {
            return desc +itemString;
        }

    }

    /**
     *
     * @return
     */
    public List<IRoomElement> getElements() {
        return elements;
    }

    /**
     *
     * @param item
     */
    public void removeItem(Item item) {
        this.items.remove(item);
        this.elements.remove(item);
    }

    /**
     * The room's puzzle. Null if the room has no puzzle.
     * @param item
     */
    public void addItem(Item item) {
        this.items.add(item);
    }

    /**
     *
     * @param item
     * @return
     */
    public boolean hasItem(Item item) {
        for (IRoomElement i : this.items) {
            if (i.getName().equalsIgnoreCase(item.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return
     */
    public boolean isClear() {
        return this.clear;
    }

    /**
     *
     * @param bool
     */
    public void setClear(boolean bool) {
        this.clear = bool;
    }

}
