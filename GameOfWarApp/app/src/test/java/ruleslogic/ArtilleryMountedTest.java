package ruleslogic;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArtilleryMountedTest {

    @Test
    public void artilleryMountedGettersTest() {
        Board b = BoardTest.getCommsTestObject1();
//        Using these pieces:
//        Piece   inComms     loc
//        Am      true        25
//        Am      false       40
        Fighter f = b.southArmy.getFighterAt(25);
        assertEquals(2, f.getMovement());
        assertEquals(3, f.getWeaponsRange());
        assertEquals(5, f.getAttack());
        assertEquals(8, f.getDefence());
        f = b.northArmy.getFighterAt(40);
        assertEquals(0, f.getMovement());
        assertEquals(3, f.getWeaponsRange());
        assertEquals(0, f.getAttack());
        assertEquals(0, f.getDefence());
    }

    @Test
    public void artilleryMounted_defence_modified_by_terrain() {
        Board b = BoardTest.getDefenceModifierTestBoard1();
        Fighter f = b.northArmy.getFighterAt(187);
        assertEquals(12, f.getDefence());
    }
}