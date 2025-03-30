# CS5004 Project

# User Instructions
  We are assuming the tester knows where to place a json file for their testing and that their own test functions know where this is. If not, a sample Main class has been provided in /FinalProject/src/. This sample Main class will look for a json file in /FinalProject/src/resources/. Note that this is also where save files will be created and loaded from. The required .jar files can be found in the Library package at /FinalProject/src/Library. Diagrams are included in the Diagrams folder at /FinalProject/Diagrams. It may be helpful to zoom in on the diagrams within the files.

### Overview:
  Our team has revamped our high level design since last week to adhere to the MVC paradigm. Control flow is handled by the 'GameController' class in the textUI package. This is the controller in the MVC paradigm and it uses the 'GameView' class to implement the view of the MVC paradigm. Input is handled by 'GameCommandReader.' The 'GameModel' class in the model package represents the model for the MVC paradigm. It has a 'Player' and 'RoomModel' which collectively make up the database of the model. Game elements such as Room, Monster, Puzzle, Item, etc remain similar to the previous design and are contained in the gameelements package for organization purposes. Parsing JSON data and writing to JSON files is handled by the 'Parser' and 'ReverseParser' classes within the model package.
  
  The overall flow of the program in simple terms involves the controller taking input from the user, making a request from the model, and then telling the view the appropriate message to display. The model handles requests by using its built in logic to determine if they are allowed by the rules and/or state of the database. If they are, the appropriate changes to the database are made. If not, an exception is raised that tells the controller what went wrong so that it can tell the view what to display. Once told to 'go' the controller will continue this cycle until the player presses (q) to quit, all rooms are cleared, or they run out of health. An appropriate message will be displayed before the game ends. 

  


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

