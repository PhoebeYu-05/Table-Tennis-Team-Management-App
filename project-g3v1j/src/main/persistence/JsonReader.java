package persistence;

import model.Player;
import model.Team;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Reference: JsonSerializationDemo persistence/JsonReader (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)

// Represents a reader that reads team from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads team from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Team read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkRoom(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses team from JSON object and returns it
    private Team parseWorkRoom(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Team t = new Team(name);
        addPlayers(t, jsonObject);
        return t;
    }

    // MODIFIES: t
    // EFFECTS: parses players from JSON object and adds them to team
    private void addPlayers(Team t, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("team");
        for (Object json : jsonArray) {
            JSONObject nextPlayer = (JSONObject) json;
            addPlayer(t, nextPlayer);
        }
    }   

    // MODIFIES: t
    // EFFECTS: parses player from JSON object and adds it to team
    private void addPlayer(Team t, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Player player = new Player(name);
        t.addNewPlayer(player);
    }
}
