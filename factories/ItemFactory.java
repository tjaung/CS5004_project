package factories;

import gameelements.Item;

class ItemFactory {

  public Item create(String shapeProduct, int weight, int value, String name, int maxUses,
                          int usesRemaining, String whenUsed, String description,
                          String picture) {
    if (shapeProduct.equalsIgnoreCase("item")) {

    return new Item(weight, value, name, maxUses,
            usesRemaining, whenUsed, description, picture);
}
    return null;
  }
}
