package gameelements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        // if monster effect or puzzle effect
        // this.hasEffect = effect
        // this.effectdescription = effect desc
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

    public String getPicture() {
        return picture;
    }

    public IRoomElement getMonster() {

        if (monster.size() == 0) {
            return null;
        }
        return monster.get(0); // assuming only 1 monster per room
    }

    public IRoomElement getPuzzle() {
        if (puzzle.size() == 0) {
            return null;
        }
        return puzzle.get(0); // assuming only 1 puzzle per room
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
        IRoomElement element = null;
        for (IRoomElement i : elements) {
            if (i.getName().equalsIgnoreCase(elementName)) {
                element = i;
                return element;
            }
        }
        throw new IllegalArgumentException(elementName.concat(" does not exist.\n"));
    }

    @Override
    public String toString() {

        String m = getMonster() == null ? "none" : getMonster().getName();
        String p = getPuzzle() == null ? "none" : getPuzzle().getName();
//        String pSolution = !Objects.equals(p, "none") ? " " + puzzle.getFirst().getClass().getName() : "";
        String i = printItems() == "" ? "none" : printItems();
        String f = printFixtures() == "" ? "none" : printFixtures();

        return "Room: " + roomName + "\n"
                + "Description: " + description + "\n"
                + "N: " + N + "\n"
                + "S: " + S + "\n"
                + "E: " + E + "\n"
                + "W: " + W + "\n"
                + "Monster: " + m + "\n"
                + "Puzzle: " + p  + "\n"
                + "Items: " + i + "\n"
                + "Fixtures: " + f + "\n"
                + "Picture: " + picture + "\n";
    }

    //COLLECTS IROOMELEMENTS
    // SHOULDN't EACH TYPE BE A LIST IN ROOM??
//    public void addElements() {
//        this.elements = new ArrayList<IRoomElement>();
//        if (this.getFixtures() != null) {
//            this.elements.add(this.getFixtures());
//        }
//        if (this.getMonster() != null) {
//            this.elements.add(this.getMonster());
//        }
//        if (this.getPuzzle() != null) {
//            this.elements.add(this.getPuzzle());
//        }
//        if (this.getItems() != null) {
//            this.elements.add(this.getItems());
//        }
//    }

    public List<IRoomElement> getElements() {
        return elements;
    }
}