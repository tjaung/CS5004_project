package gameelements;

import java.util.ArrayList;
import java.util.List;

import model.ReverseParser;

/**
 * Class for an Inventory concrete Object.
 */
public class Inventory {
    // Instance variables to store the inventory's attributes
    private final int maxWeight;  // The maximum weight
    private int currWeight;       // The current weight of the items
    private String picture;        // The path to an image
    private final List<Item> items;  // A list to hold Item objects

    /**
     * Constructor for Inventory.
     * @param maxWeight - int of the inventory's max weight from 0 to inf.
     * @param currWeight - int of the inventory's curr weight.
     * @param picture - a string path to the corresponding asset.
     */
    public Inventory(int maxWeight, int currWeight, String picture) {
        this.maxWeight = maxWeight;     
        this.currWeight = currWeight;    
        this.items = new ArrayList<>();  
        this.picture = picture;
    }

    /**
     * Getter for current weight.
     * @return current weight.
     */
    public int getCurrWeight() {
        return currWeight;  
    }


    /**
     * Getter for maxWeight.
     * @return  maxWeight.
     */
    public int getMaxWeight() {
        return maxWeight;
    }

    /**
     * Getter for items.
     *
     * @return the list of items in the Inventory.
     */
    public List<Item> getItems() {
        return items; 
    }

    /**
     * Adds an item to the Inventory.
     * @param item  the item to be added.
     */
    public void addItem(Item item) {
        if (this.currWeight + (item).getWeight() > this.maxWeight) {
            throw new IllegalArgumentException("You don't have room in your inventory.\n");
        }
        else {
            this.items.add(item);
        }
    }

    /**
     * Drops an item from the Inventory.
     * @param item  the item to be dropped.
     */
    public void dropItem(Item item) {
        if (this.getItems().contains(item)) {
            this.items.remove(item);
        }
        else {
            throw new IllegalArgumentException("You don't have that item.\n");
        }
    }

    /**
     * Checks to see if an item is in the Inventory.
     * @param item  an item that may or may not be in the Inventory.
     * @return  boolean true if item is in Inventory.
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
     * Getter for an Item in the Inventory by name.
     * @param itemName  the item's name.
     * @return  the item object itself.
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
        throw new IllegalArgumentException("You don't have a ".concat(itemName).concat(" in your inventory.\n"));
    }

    /**
     * Getter for picture.
     * @return  picture.
     */
    public String getPicture() {
        return picture;
    }

    /**
     * TO DO. SHOULD NOT BE IN THIS CLASS.
     * @return
     */
    public String parseInventoryToJSON() {
        return ReverseParser.readInventory(this);
    }
}
