package gameelements;

import java.util.ArrayList;
import java.util.List;

import model.ReverseParser;

/**
 * Class for a Inventory concrete Object.
 */
public class Inventory {
    // Instance variables to store the inventory's attributes
    private final int maxWeight;  // The maximum weight
    private int currWeight;       // The current weight of the items 
    private final List<Item> items;  // A list to hold Item objects
    private String picture;        // The path to an image 

    /**
   * Constructor for Inventory. 
   *
   * @param maxWeight - int of the inventory's max weight from 0 to inf.
   * @param currWeight - int of the inventory's curr weight. 
   * @param picture - a string path to the corresponding asset.
   */
    public Inventory(int maxWeight, int currWeight, String path) {
        this.maxWeight = maxWeight;     
        this.currWeight = currWeight;    
        this.items = new ArrayList<>();  
        this.picture = path;           
    }

    /**
   * Getter for current weight.
   *
   * @return current weight.
   */
    public int getCurrWeight() {
        return currWeight;  
    }


     /**
   * Getter for max weight.
   *
   * @return max weight.
   */   
    public int getMaxWeight() {
        return maxWeight;
    }

    /**
   * Getter for items.
   *
   * @return items in a list.
   */   
    public List<Item> getItems() {
        return items; 
    }

    /**
   * addItem() adds a item to the inventory 
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
   * drops a item to the inventory 
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
   * Checks if item is in a list.
   *
   * @return boolean.
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
   * gets an Item from a room.
   *
   * @return Item.
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
   * returns the picture path.
   */      
    public String getPicture() {
        return picture;
    }

    /**
   * parses json
   */     
    public String parseInventoryToJSON() {
        return ReverseParser.readInventory(this);
    }
}
