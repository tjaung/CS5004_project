package gameelements;

public class Monster {
    // The gameelements.Monster class represents a monster.

    // Instance variables 
    private final String damage;        // The damage dealt by the monster
    private int health;                 // gameelements.Monster health (now mutable)
    private final String canAttack;     // Whether the monster can attack
    private final String typeOfAttack;  // The type of attack
    private final String picture;          // The file path to the image

    // Constructor to initialize the gameelements.Monster object with specified values
    public Monster(String damage, int initialHealth, String canAttack, String typeOfAttack, String image) {
        this.damage = damage;  
        this.health = initialHealth;  // Assign the initial health value
        this.canAttack = canAttack;     
        this.typeOfAttack = typeOfAttack; 
        this.picture = image;              
    }

    // Method to get the damage 
    public String getDamage() {
        return damage;  
    }

    // Method to get the monster health
    public int getHealth() {
        return health;  // Return current health
    }

    // Method to set the health after taking damage
    public void setHealth(int attack) {
        this.health = this.health - attack; 
    }

    // Method to get whether the monster can attack
    public String getAttack() {
        return canAttack;  
    }

    // Method to get the file path
    public String getPicture() {
        return picture; 
    }

    // Method to get the type of attack
    public String getTypeOfAttack() {
        return typeOfAttack;  
    }
}
