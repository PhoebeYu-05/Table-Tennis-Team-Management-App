package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Represents a team, including its name and a list of players
public class Team implements Writable {
    private String name;
    private List<Player> team;

    // Constructor
    public Team(String name) {
        this.name = name;
        this.team = new ArrayList<>();
    }

    // EFFECTS: Get team name
    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: adds a player to the list of players
    public void addNewPlayer(Player player) {
        // Player player = new Player(name);
        this.team.add(player);
    }

    // REQUIRES: index >= 0
    // EFFECTS: Get a player by index
    public Player getPlayer(int index) {
        if (index < team.size()) {
            return team.get(index);
        }
        return null;
    }

    // EFFECTS: Get total number of players in team
    public int getNum() {
        return team.size();
    }

    // EFFECTS: Get all players
    public List<Player> getAllPlayers() {
        return team;
    }

    public boolean isEmpty() {
        return this.team.isEmpty();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("team", teamToJson());
        return json;
    }

        // EFFECTS: returns players in this team as a JSON array
    private JSONArray teamToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Player p : team) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }


}
