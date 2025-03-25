public class InventoryFactory{


public static Inventory create (int maxWeight,int currWeight, List<Item> items, String photoPath){
  if(maxWeight < 0 || currWeight < 0){
    throw new IllegalArgumentException("Weights are always positive");
  }
  return new Inventory(maxWeight,currWeight,items,photoPath);
}





}