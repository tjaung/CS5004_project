package gameelements;

import java.util.ArrayList;
import java.util.List;

import model.ReverseParser;

public class Inventory {
    // Instance variables to store the inventory's attributes
    private final int maxWeight;  // The maximum weight
    private int currWeight;       // The current weight of the items 
    private final List<Item> items;  // A list to hold Item objects
    private String picture;        // The path to an image 

    // Constructor to initialize the gameelements.Inventory object with specified values
    public Inventory(int maxWeight, int currWeight, String path) {
        this.maxWeight = maxWeight;     
        this.currWeight = currWeight;    
        this.items = new ArrayList<>();  
        this.picture = path;           
    }

    // Get the current weight
    public int getCurrWeight() {
        return currWeight;  
    }

    // Get the maximum weight
    public int getMaxWeight() {
        return maxWeight;
    }

    // Get method to retrieve the items
    public List<Item> getItems() {
        return items; 
    }

    // Get the photo path
    public String getPicture() {
        return picture;
    }

    public String parseInventoryToJSON() {
        return ReverseParser.readInventory(this);
    }
}
