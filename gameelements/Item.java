package gameelements;

public abstract class Item  {
  private int weight;
  private int value;
  private String name;
  private int maxUses;
  private int usesRemaining;
  private String whenUsed;
  private String description;
  private String picture;

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

  public int getWeight() {
    return this.weight;
  }

  public int getValue() {
    return this.value;
  }

  public String getName() {
    return this.name;
  }

  public int getMaxUses() {
    return this.maxUses;
  }

  public int getUsesRemaining() {
    return this.usesRemaining;
  }

  public String getWhenUsed() {
    return this.whenUsed;
  }

  public String getDescription() {
    return this.description;
  }

  public String getPicture() {
    return this.picture;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }

  public void setMaxUses(int maxUses) {
    this.maxUses = maxUses;
  }

  public void setUsesRemaining(int usesRemaining) {
    this.usesRemaining = usesRemaining;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public void setWhenUsed(String whenUsed) {
    this.whenUsed = whenUsed;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }
}
