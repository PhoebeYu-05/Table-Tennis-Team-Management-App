package persistence;

import model.Player;
import model.Team;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Team t = new Team("My team");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyTeam() {
        try {
            Team t = new Team("My team");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTeam.json");
            writer.open();
            writer.write(t);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTeam.json");
            t = reader.read();
            assertEquals("My team", t.getName());
            assertEquals(0, t.getNum());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Team t = new Team("My team");
            Player p = new Player("Jack");
            Player p2 = new Player("Angela");
            t.addNewPlayer(p);
            t.addNewPlayer(p2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralTeam.json");
            writer.open();
            writer.write(t);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralTeam.json");
            t = reader.read();
            assertEquals("My team", t.getName());
            List<Player> team = t.getAllPlayers();
            assertEquals(2, team.size());

            checkPlayer("Jack", team.get(0));
            checkPlayer("Angela", team.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
