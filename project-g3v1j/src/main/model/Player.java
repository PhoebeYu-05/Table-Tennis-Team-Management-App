package model;

import org.json.JSONObject;
import persistence.Writable;
import org.json.JSONObject;
import persistence.Writable;

// Represents basic information of a player
public class Player implements Writable {

    private String name; // player name

    // REQUIRES: playerNamer have non-zero length
    // EFFECTS: name is set to playerName
    public Player(String playerName) {
        this.name = playerName;
    }


    public String getName() {
        return name;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        return json;
    }
}
