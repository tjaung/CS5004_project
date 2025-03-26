# CS5004 Project

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

