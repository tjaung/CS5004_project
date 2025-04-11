package gameelements;

/**
 * The Monster class 
 */
public class Monster implements IRoomElement {
    
    // Instance variables
    private final String name;        // Name of the monster
    private boolean active;           // Is the monster active?
    private boolean affectsTarget;    // Does this monster affect the target?
    private boolean affectsPlayer;    // Does this monster affect the player?
    private IRoomElement solution;    // How to calm it down
    private int value;                // Points given for killing the monster
    private String description;       // Description of the monster
    private String effects;           // What the monster does
    private int damage;               // Damage dealt by the monster
    private String target;            // Target of the monster's attacks
    private boolean canAttack;        // Can the monster attack?
    private String attack;            // Type of attack used by the monster
    private String picture;           // File path to the image of the monster
    private int health;               // Health of the monster

    /**
     * Constructs a new Monster object.
     * @param name The name of the monster.
     * @param active Indicates if the monster is active.
     * @param affectsTarget Indicates if the monster affects the target.
     * @param affectsPlayer Indicates if the monster affects the player.
     * @param solution The solution to calm the monster.
     * @param value The score for defeating the monster.
     * @param description A description of the monster.
     * @param effects The effects of the monster on the player or the environment.
     * @param damage The damage the monster deals.
     * @param target The target of the monster's attacks.
     * @param canAttack Whether the monster can attack.
     * @param attack The type of attack the monster uses.
     * @param picture The file path to the monster's image.
     * @param health The health of the monster.
     */
    public Monster(String name, boolean active, boolean affectsTarget, boolean affectsPlayer, IRoomElement solution,
                   int value, String description, String effects, int damage, String target,
                   boolean canAttack, String attack, String picture, int health ) {
        this.name = name;
        this.active = active;
        this.affectsTarget = affectsTarget;
        this.affectsPlayer = affectsPlayer;
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

    /**
     * Gets the damage dealt by the monster.
     * @return The damage value of the monster.
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Sets the damage dealt by the monster.
     * @param damage The new damage value for the monster.
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Gets the health of the monster.
     * @return The health of the monster.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets the health of the monster.
     * @param health The new health value for the monster.
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Updates the monster's health after it takes damage.
     * @param attackDamage The amount of damage to reduce the health by.
     */
    public void setHealthAfterDamage(int attackDamage) {
        this.health -= attackDamage;
    }

    /**
     * Determines if the monster can attack.
     * @return True if the monster can attack, otherwise false.
     */
    public boolean canAttack() {
        return canAttack;
    }

    /**
     * Sets whether the monster can attack.
     * @param canAttack The new attack capability of the monster.
     */
    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }

    /**
     * Gets the type of attack used by the monster.
     * @return The attack type of the monster.
     */
    public String getAttack() {
        return attack;
    }

    /**
     * Sets the type of attack the monster uses.
     * @param attack The new attack type for the monster.
     */
    public void setAttack(String attack) {
        this.attack = attack;
    }

    /**
     * Gets the value (score) awarded for killing the monster.
     * @return The value (score) for killing the monster.
     */
    public int getValue() {
        return value;
    }

    /**
     * Gets the file path to the picture or image of the monster.
     * @return The file path of the monster's picture.
     */
    public String getPicture() {
        return picture;
    }

    /**
     * Sets the file path to the picture or image of the monster.
     * @param picture The new file path for the monster's picture.
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * Gets the active status of the monster.
     * @return True if the monster is active, otherwise false.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the active status of the monster.
     * @param active The new active status for the monster.
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Gets whether the monster affects the target.
     * @return True if the monster affects the target, otherwise false.
     */
    public boolean affectsTarget() {
        return affectsTarget;
    }

    /**
     * Sets whether the monster affects the target.
     * @param affectsTarget The new value for affectsTarget.
     */
    public void setAffectsTarget(boolean affectsTarget) {
        this.affectsTarget = affectsTarget;
    }

    /**
     * Gets whether the monster affects the player.
     * @return True if the monster affects the player, otherwise false.
     */
    public boolean affectsPlayer() {
        return affectsPlayer;
    }

    /**
     * Sets whether the monster affects the player.
     * @param affectsPlayer The new value for affectsPlayer.
     */
    public void setAffectsPlayer(boolean affectsPlayer) {
        this.affectsPlayer = affectsPlayer;
    }

    /**
     * Gets the solution to calm the monster.
     * @return The solution for calming the monster.
     */
    public IRoomElement getSolution() {
        return solution;
    }

    /**
     * Sets the solution for calming the monster.
     * @param solution The new solution for calming the monster.
     */
    public void setSolution(Item solution) {
        this.solution = solution;
    }

    /**
     * Gets the description of the monster.
     * @return The description of the monster.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the monster.
     * @param description The new description for the monster.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the effects of the monster on the environment or player.
     * @return The effects of the monster.
     */
    public String getEffects() {
        return effects;
    }

    /**
     * Sets the effects of the monster.
     * @param effects The new effects for the monster.
     */
    public void setEffects(String effects) {
        this.effects = effects;
    }

    /**
     * Gets the target of the monster's attacks.
     * @return The target of the monster's attacks.
     */
    public String getTarget() {
        return target;
    }

    /**
     * Sets the target of the monster's attacks.
     * @param target The new target for the monster's attacks.
     */
    public void setTarget(String target) {
        this.target = target;
    }

    /**
     * Gets the name of the monster.
     * @return The name of the monster.
     */
    public String getName() {
        return name;
    }
}

