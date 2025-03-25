import gameelements.Item;

class itemFactory {

  public itemClass create(String shapeProduct, int weight, int value, String name, int maxUses,
                          int usesRemaining, String whenUsed, String description,
                          String picture) {
    if (shapeProduct.equalsIgnoreCase("item")) {

    return new itemClass(weight, value, name, maxUses,
            usesRemaining, whenUsed, description, picture);
}
    return null;
  }
}
