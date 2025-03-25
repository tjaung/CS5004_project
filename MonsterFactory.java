public class MonsterFactory {

 public static Monster create(String damage,int health, String canAttack, String typeOfAttck, String picture){
   if(health < 0){
     throw new IllegalArgumentException("Health is always positive");
   }
   return new Monster(damage,health,canAttack,typeOfAttck,picture);
 }


}
