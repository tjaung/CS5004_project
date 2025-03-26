package gameelements;

public class Monster {
    // The gameelements.Monster class represents a monster.

    // Instance variables 
    private final String name;        // This is the name of the monster
    private boolean active;           // Is the monster active
    private boolean affectsTarget;    // Does this impact the target
    private boolean affectsPlayer;    // Does this impact the player
    private Item solution;            // How to calm it down
    private int value;                // Score you get for killing it
    private String description;       // Describe the monster
    private String effects;           // What it does 
    private int damage;               // The damage dealt by the monster
    private String target;            // Target
    private boolean canAttack;        // Whether the monster can attack
    private String attack;            // The type of attack
    private String picture;           // The file path to the image
    private int health;               // Health of the monster (now not nullable)

    // Constructor to initialize object with specified values
    public Monster(String name, boolean active, boolean affectsTarget, boolean affectsPlayer, Item solution, 
                   int value, String description, String effects, int damage, String target,
                   boolean canAttack, String attack, String picture, int health ) {
        this.name = name;
        this.active = active;
        this.affectsTarget = affectsTarget;
        this.affectsPlayer = affectsPlayer; // Fixed typo here
        this.solution = solution;
        this.value = value;
        this.description = description;
        this.effects = effects;
        this.damage = damage;
        this.target = target;
        this.canAttack = canAttack;
        this.attack = attack;
        this.picture = picture;
        this.health = health;   
    }

    // Getter for damage
    public int getDamage() {
        return damage;
    }

    // Setter for damage
    public void setDamage(int damage) {
        this.damage = damage;
    }

    // Getter for health
    public int getHealth() {
        return health;
    }

    // Setter for health
    public void setHealth(int health) {
        this.health = health;
    }

    // Method to set the health after taking damage
    public void setHealthAfterDamage(int attackDamage) {
        this.health -= attackDamage;
    }

    // Getter for attack capability
    public boolean canAttack() {
        return canAttack;
    }

    // Setter for attack capability
    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }

    // Getter for attack type
    public String getTypeOfAttack() {
        return attack;
    }

    // Setter for attack type
    public void setTypeOfAttack(String attack) {
        this.attack = attack;
    }

    // Getter for picture
    public String getPicture() {
        return picture;
    }

    // Setter for picture
    public void setPicture(String picture) {
        this.picture = picture;
    }

    // Getter for active status
    public boolean isActive() {
        return active;
    }

    // Setter for active status
    public void setActive(boolean active) {
        this.active = active;
    }

    // Getter for affects target status
    public boolean isAffectsTarget() {
        return affectsTarget;
    }

    // Setter for affects target status
    public void setAffectsTarget(boolean affectsTarget) {
        this.affectsTarget = affectsTarget;
    }

    // Getter for affects player status
    public boolean isAffectsPlayer() {
        return affectsPlayer;
    }

    // Setter for affects player status
    public void setAffectsPlayer(boolean affectsPlayer) {
        this.affectsPlayer = affectsPlayer;
    }

    // Getter for solution
    public Item getSolution() {
        return solution;
    }

    // Setter for solution
    public void setSolution(Item solution) {
        this.solution = solution;
    }

    // Getter for description
    public String getDescription() {
        return description;
    }

    // Setter for description
    public void setDescription(String description) {
        this.description = description;
    }

    // Getter for effects
    public String getEffects() {
        return effects;
    }

    // Setter for effects
    public void setEffects(String effects) {
        this.effects = effects;
    }

    // Getter for target
    public String getTarget() {
        return target;
    }

    // Setter for target
    public void setTarget(String target) {
        this.target = target;
    }

    // Getter for name
    public String getName() {
        return name;
    }
}
