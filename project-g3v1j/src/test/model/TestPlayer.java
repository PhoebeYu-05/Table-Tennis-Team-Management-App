package model;

import static org.junit.Assert.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPlayer {
    private Player testPlayer;
    
    @BeforeEach
    void runBefore() {
        testPlayer = new Player("Kate");
    }

    @Test
    void testConstructor() {
        assertEquals("Kate", testPlayer.getName());
    }
}
