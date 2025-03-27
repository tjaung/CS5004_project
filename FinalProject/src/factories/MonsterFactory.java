package factories;

import gameelements.Item;
import gameelements.Monster;

public class MonsterFactory {

 public static Monster create(String name, boolean active, boolean affectsTarget, boolean affectsPlayer, Item solution,
                              int value, String description, String effects, int damage, String target,
                              boolean canAttack, String attack, String picture, int health){
   if(health < 0){
     throw new IllegalArgumentException("Health is always positive");
   }
   return new Monster(name, active, affectsTarget, affectsPlayer, solution, value, description, effects, damage, target, canAttack, attack, picture, health);
 }


}
