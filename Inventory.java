import java.util.ArrayList;
import java.util.List;

public class Inventory {
    // Instance variables to store the inventory's attributes
    private final String maxWeight;  // The maximum weight
    private String currWeight;       // The current weight of the items 
    private final List<Item> items;  // A list to hold Item objects
    private String photoPath;        // The path to an image 

    // Constructor to initialize the Inventory object with specified values
    public Inventory(String maxWeight, String currWeight, String path) {
        this.maxWeight = maxWeight;     
        this.currWeight = currWeight;    
        this.items = new ArrayList<>();  
        this.photoPath = path;           
    }

    // Get the current weight
    public String getCurrWeight() {
        return currWeight;  
    }

    // Get the maximum weight
    public String getMaxWeight() {
        return maxWeight;
    }

    // Get method to retrieve the items
    public List<Item> getItems() {
        return items; 
    }

    // Get the photo path
    public String getPhotoPath() {
        return photoPath;
    }
}
