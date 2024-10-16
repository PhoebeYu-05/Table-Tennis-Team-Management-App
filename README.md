# Application for Table Tennis Team Management 

## Introduction
This application is designed to help table tennis team coaches manage their team's information, including player information player name, team name, and show list of players. Personally, I am a big fan of table tennis. I love both playing table tennis and watching matches, therefore I am very thrilled to create an application that helps coaches manage their teams more effectively.


## User stories

- As a user, I want to be able to add a new player to the team.
- As a user, I want to be able to remove a player from the team.
- As a user, I want to be able to view the list of players in my team.
- As a user, I want to be able to input basic information for a player.

- As a user, I want to be able to save my list of players to file (if I choose so).
- As a user, I want to be able to load my list of players from file (if I choose so).


## Instructions for Grader

- You can generate the first required action related to the user story "Add Multiple Players to a Team" by typing the player's name in the box and clicking "Add". At the same time, the view list function is also implemented.

- You can generate the second required action related to the user story "Remove Players from a Team" by selecting the player and clicking "Remove".

- You can locate my visual component by looking at the background image (loading issue)

- You can save the state of my application by clicking the "Save" button.

- You can reload the state of my application by clicking the "Load" button.


### Phase 4: Task 2 Sample
#### Logged events:

Wed Aug 07 10:47:45 PDT 2024
Team **My Team** created.

Wed Aug 07 10:47:48 PDT 2024
**Player added: Jessica**

Wed Aug 07 10:56:52 PDT 2024
**Player removed: Jessica**

### Phase 4: Task 3
UML_Design_Diagram.pdf is included in .vscode file

 #### Reflection on Refactoring
If I had more time, I would apply the Single Responsibility Principle more rigorously in the `PlayerListApp` class. Currently, this class contains several inner classes: `RemoveListener`, `AddListener`, `SaveListener`, and `LoadListener`, each handling different responsibilities. By refactoring these into separate classes, the code would become easier to maintain. This separation would also simplify testing, as each class could be tested independently. Additionally, it would improve code readability and make debugging more straightforward.
