package gameelements;

/**
 * Concrete item class.
 */
public class Item implements IRoomElement {
  
  // Item attributes
  private int weight;
  private int value;
  private String name;
  private int maxUses;
  private int usesRemaining;
  private String whenUsed;
  private String description;
  private String picture;

  /**
   * Constructs an Item with specified attributes.
   * @param weight The weight of the item.
   * @param value The value of the item.
   * @param name The name of the item.
   * @param maxUses The maximum number of times the item can be used.
   * @param usesRemaining The number of uses remaining for the item.
   * @param whenUsed Describes the condition under which the item is used.
   * @param description A description of the item.
   * @param picture A picture or path to the image of the item.
   */
  public Item(int weight, int value, String name, int maxUses,
              int usesRemaining, String whenUsed, String description,
              String picture) {
    this.weight = weight;
    this.value = value;
    this.name = name;
    this.maxUses = maxUses;
    this.usesRemaining = usesRemaining;
    this.whenUsed = whenUsed;
    this.description = description;
    this.picture = picture;
  }

  /**
   * Gets the weight of the item.
   * @return The weight of the item.
   */
  public int getWeight() {
    return this.weight;
  }

  /**
   * Gets the value of the item.
   * @return The value of the item.
   */
  public int getValue() {
    return this.value;
  }

  /**
   * Gets the name of the item.
   * @return The name of the item.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets the maximum uses of the item.
   * @return The maximum number of uses for the item.
   */
  public int getMaxUses() {
    return this.maxUses;
  }

  /**
   * Gets the number of uses remaining for the item.
   * @return The number of uses remaining.
   */
  public int getUsesRemaining() {
    return this.usesRemaining;
  }

  /**
   * Gets the condition under which the item is used.
   * @return A description of when the item is used.
   */
  public String getWhenUsed() {
    return this.whenUsed;
  }

  /**
   * Gets a description of the item.
   * @return A description of the item.
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Gets the picture or image associated with the item.
   * @return The picture or path to the image of the item.
   */
  public String getPicture() {
    return this.picture;
  }

  /**
   * Sets the name of the item.
   * @param name The new name for the item.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Sets the weight of the item.
   * @param weight The new weight for the item.
   */
  public void setWeight(int weight) {
    this.weight = weight;
  }

  /**
   * Sets the maximum uses of the item.
   * @param maxUses The new maximum number of uses for the item.
   */
  public void setMaxUses(int maxUses) {
    this.maxUses = maxUses;
  }

  /**
   * Sets the remaining uses of the item.
   * @param usesRemaining The new number of uses remaining.
   */
  public void setUsesRemaining(int usesRemaining) {
    this.usesRemaining = usesRemaining;
  }

  /**
   * Sets the value of the item.
   * @param value The new value for the item.
   */
  public void setValue(int value) {
    this.value = value;
  }

  /**
   * Sets the condition under which the item is used.
   * @param whenUsed The new condition for when the item is used.
   */
  public void setWhenUsed(String whenUsed) {
    this.whenUsed = whenUsed;
  }

  /**
   * Sets the description of the item.
   * @param description The new description for the item.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Sets the picture or image associated with the item.
   * @param picture The new picture or path to the image of the item.
   */
  public void setPicture(String picture) {
    this.picture = picture;
  }
}

