package ui;

import model.*;
import java.util.Scanner;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

// Team management application
public class TeamApp {
    private static final String JSON_STORE = "./data/team.json";
    private Team team;
    private int currentPlayerIndex;
    private Scanner scanner;
    private boolean isProgramRunning;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: creates an instance of the Team console ui application
    public TeamApp() throws FileNotFoundException {
        init();

        team = new Team("My team");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        printDivider();
        System.out.println("Welcome to the Table Tennis Team Management app");
        printDivider();

        while (this.isProgramRunning) {
            handleMenu();
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes the application with the starting values
    public void init() {
        this.team = new Team("My team");
        this.scanner = new Scanner(System.in);
        this.isProgramRunning = true;
    }

    // EFFECTS: displays and processes inputs for the main menu
    public void handleMenu() {
        displayMenu();
        String input = this.scanner.nextLine();
        processMenuCommands(input);
    }

    // EFFECTS: displays a list of commands that can be used in the main menu
    public void displayMenu() {
        System.out.println("Please select an action:\n");
        System.out.println("a: Add a new player");
        System.out.println("b: View all players");
        System.out.println("c: Save team to file");
        System.out.println("d: Load team from file");
        System.out.println("q: Quit the app");
        printDivider();
    }

    // EFFECTS: processes the user's input in the main menu
    public void processMenuCommands(String input) {
        printDivider();
        switch (input) {
            case "a":
                addNewPlayer();
                break;
            case "b":
                displayGivenPlayers(team);
                break;
            case "c":
                saveTeam();
                break;
            case "d":
                loadTeam();
                break;
            case "q":
                quitApp();
                break;
            default:
                System.out.println("Invalid option inputted. Please try again.");
        }
        printDivider();
    }

    // EFFECTS: adds a player to the list of players
    public void addNewPlayer() {
        System.out.println("Please enter the player's name:");
        String name = this.scanner.nextLine();
        Player player = new Player(name);
        team.addNewPlayer(player);
        System.out.println("\nNew player successfully created!");
    }

    // EFFECTS: displays the given list of players
    public void displayGivenPlayers(Team team) {
        if (team.isEmpty()) {
            System.out.println("Error: No players in team. Try adding a player first!");
        }

        displayViewMenu();
        String input = "";
        while (!input.equals("q")) {
            Player currentPlayer = team.getPlayer(this.currentPlayerIndex);
            displayPlayer(currentPlayer);
            input = this.scanner.nextLine();
            handleViewCommands(input, team);
        }
        this.currentPlayerIndex = 0;
    }

    // EFFECTS: displays a view of the menu
    public void displayViewMenu() {
        System.out.println("Enter 'n' to move to the next player.");
        System.out.println("Enter 'p' to move to the previous player.");
        System.out.println("Enter 'q' to return to the menu.");
    }

    // EFFECTS: displays the given player
    public void displayPlayer(Player player) {
        printDivider();
        System.out.println("Player #" + (this.currentPlayerIndex++));
        System.out.println(player.getName());
    }

    // EFFECTS: processes the user's input in the menu
    public void handleViewCommands(String input, Team listOfPlayers) {
        System.out.print("\n");

        Player currentPlayer = team.getPlayer(this.currentPlayerIndex);
        switch (input) {
            case "n":
                getNextPlayer(team);
                break;
            case "p":
                getPreviousPlayer();
                break;
            case "q":
                System.out.println("Returning to the menu...");
                break;
            default:
                System.out.println("Invalid option inputted. Please try again.");
        }
    }

    // MODIFIES: this
    // EFFECTS: if there is another player to display, increments the current player
    // index
    public void getNextPlayer(Team team) {
        if (this.currentPlayerIndex >= team.getNum() - 1) {
            System.out.println("Error: No more new players to display!");
        } else {
            this.currentPlayerIndex++;
        }
    }

    // MODIFIES: this
    // EFFECTS: if there is a previous player to display, decrements the current
    // player index
    public void getPreviousPlayer() {
        if (this.currentPlayerIndex <= 0) {
            System.out.println("Error: no more previous players to display!");
        } else {
            this.currentPlayerIndex--;
        }
    }

    // EFFECTS: saves the team to file
    private void saveTeam() {
        try {
            jsonWriter.open();
            jsonWriter.write(team);
            jsonWriter.close();
            System.out.println("Saved " + team.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads team from file
    private void loadTeam() {
        try {
            team = jsonReader.read();
            System.out.println("Loaded " + team.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: prints a closing message and marks the program as not running
    public void quitApp() {
        System.out.println("Goodbye!");
        this.isProgramRunning = false;
    }

    public void printDivider() {
        System.out.println("--------------------------------------");
    }
}
