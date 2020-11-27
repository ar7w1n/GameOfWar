package ruleslogic;

import org.junit.Test;

import static org.junit.Assert.*;

public class InfantryTest {

    @Test
    public void infantryGettersTest() {
        Board b = BoardTest.getCommsTestObject1();
//        Using these pieces:
//        Piece   inComms     loc
//        I       true        30
//        I       false       82
        Fighter f = b.southArmy.getFighterAt(30);
        assertEquals(1, f.getMovement());
        assertEquals(2, f.getWeaponsRange());
        assertEquals(4, f.getAttack());
        assertEquals(6, f.getDefence());
        f = b.southArmy.getFighterAt(82);
        assertEquals(0, f.getMovement());
        assertEquals(2, f.getWeaponsRange());
        assertEquals(0, f.getAttack());
        assertEquals(0, f.getDefence());
    }

    @Test
    public void infantry_defence_modified_by_terrain() {
        Board b = BoardTest.getDefenceModifierTestBoard1();
        Fighter f = b.northArmy.getFighterAt(225);
        assertEquals(8, f.getDefence());
        f = b.southArmy.getFighterAt(71);
        assertEquals(10, f.getDefence());
    }
}