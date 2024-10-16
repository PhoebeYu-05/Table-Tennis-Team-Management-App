package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class TestTeam {

    private Team testTeam;
    private Player player;

    @BeforeEach
    public void runBefore() {
        testTeam = new Team("team 1");
        player = new Player("Catherine");
    }

    @Test
    public void testConstructor() {
        assertEquals("team 1", testTeam.getName());
        //assertTrue(testTeam.getAllPlayers().isEmpty());
        assertTrue(testTeam.getAllPlayers().isEmpty());
    }

    @Test
    public void testAddPlayer() {
        testTeam.addNewPlayer(player);
        assertEquals(player, testTeam.getPlayer(0));
        assertEquals(1, testTeam.getNum());
        List<Player> listOfPlayer = new ArrayList<>();
        listOfPlayer.add(player);
        assertEquals(listOfPlayer, testTeam.getAllPlayers());
    }

    @Test
    public void testEmpty() { 
        assertTrue(testTeam.isEmpty());
        testTeam.addNewPlayer(player);
        assertFalse(testTeam.isEmpty());
    }

    @Test
    public void testGetPlayer() {
        Player p2 = new Player("Jim");
        testTeam.addNewPlayer(player);
        assertEquals(player, testTeam.getPlayer(0));
        assertNull(testTeam.getPlayer(1));
        testTeam.addNewPlayer(p2);
        assertEquals(p2, testTeam.getPlayer(1));
    }
}
