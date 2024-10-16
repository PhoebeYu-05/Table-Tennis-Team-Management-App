package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.Player;

public class JsonTest {
    protected void checkPlayer(String name, Player player) {
        assertEquals(name, player.getName());
    }
}
