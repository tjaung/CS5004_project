package factories;

import java.util.List;

import gameelements.Inventory;
import gameelements.Item;

public class InventoryFactory{


public static Inventory create (int maxWeight, int currWeight, String photoPath){
  if(maxWeight < 0 || currWeight < 0){
    throw new IllegalArgumentException("Weights are always positive");
  }
  return new Inventory(maxWeight,currWeight,photoPath);
}





}