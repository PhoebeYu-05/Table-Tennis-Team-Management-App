package persistence;

import model.Player;
import model.Team;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Team t = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyTeam() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTeam.json");
        try {
            Team t = reader.read();
            assertEquals("My team", t.getName());
            assertEquals(0, t.getNum());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralTeam() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralTeam.json");
        try {
            Team t = reader.read();
            assertEquals("My team", t.getName());
            List<Player> team = t.getAllPlayers();
            assertEquals(2, team.size());
            checkPlayer("Jack", team.get(0));
            checkPlayer("Emily", team.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
