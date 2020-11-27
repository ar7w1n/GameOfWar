package ruleslogic;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArtilleryTest {

    @Test
    public void artilleryGettersTest() {
        Board b = BoardTest.getCommsTestObject1();
//        Using these pieces:
//        Piece   inComms     loc
//        A       true        75
//        A       false       211
        Fighter f = b.northArmy.getFighterAt(75);
        assertEquals(1, f.getMovement());
        assertEquals(3, f.getWeaponsRange());
        assertEquals(5, f.getAttack());
        assertEquals(8, f.getDefence());
        f = b.southArmy.getFighterAt(211);
        assertEquals(0, f.getMovement());
        assertEquals(3, f.getWeaponsRange());
        assertEquals(0, f.getAttack());
        assertEquals(0, f.getDefence());
    }

    @Test
    public void artillery_defence_modified_by_terrain() {
        Board b = BoardTest.getDefenceModifierTestBoard1();
        Fighter f = b.northArmy.getFighterAt(285);
        assertEquals(12, f.getDefence());
        f = b.southArmy.getFighterAt(342);
        assertEquals(10, f.getDefence());
    }
}