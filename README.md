# CS5004 Project

# User Instructions
  The primary .jar file can be found in the AlignQuest folder, which is where the sample align quest game elements json is. Any alternate game json file can be placed loosely in the AlignQuest folder like that to be played. Save games are still stored in the AlignQuest/saves/ folder and any images should be in AlignQuest/resources/.

### Overview:

  Our team made minimal changes to the design paradigm since last week. An interface was created for controllers to adhere to, but the model mostly continues to function as it did, and the only major change is the addition of the graphical view. The game engine driver now contains the main method that allows for access from the command line or within an IDE. The graphical view was of course implemented in swing and made use of four primary panels in the main frame to create a layout similar to the example. Pop ups using the JOptionPane are used for various purposes such as messages to the player, the end game screen, and asking the player for input. A create button class enables any other class in the view package to easily create a button to add into a panel. The GraphicsView class inherits from the JFrame class, allowing it to use its methods and function as the main frame of the game. Popups are managed by this view class, which passes any input back to the controller if necessary.

### Collaborators:

- Rohan
- Daniel
- Max
- Tim

### Git Work Flow:
 1. branch main will be our final draft.
 2. branch dev will be our rough draft that we push new features to.
 3. When we plan and implement a new feature, we will create a new branch that branches off of dev for that feature
    ```
    > git checkout dev # if you are not already on dev branch
    > git pull origin dev # pull newest changes to avoid merge conflicts. Make sure to do this.
    > git checkout -b "featureName" # replace featureName with a name of your new feature i.e. addItemClass
    ```
    This should create and put you on a new branch.
  4. Once we are finished with our feature, we will push our changes to the branch:
     ```
     > git add -A # this adds all changes, so if you need to just add specific changes don't use this
     > git commit -m "commit description" # short description of what you did
     > git push origin "featureName" # featureName is the name of the branch where you made changes
     ```

  5. We will convene to do code reviews and PR reviews together.

### Naming Conventions:
 - Class names will be simple: items are of class Item, rooms are of class Room, monsters are of class Monster, etc.
 - Attributes that hold classes will be named as the class but camelcase i.e.
  ```
  class Room() {
    ...
   private Monster monster;
   private Item item;
   private Fixture fixture;
    ...
}
```
 - An factory names will be the name of the class it creates and "Factory" i.e. PuzzleFactory for puzzle
 - Try to use camelCase for naming variables and functions.

