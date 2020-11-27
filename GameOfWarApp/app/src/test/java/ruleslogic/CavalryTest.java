package ruleslogic;

import org.junit.Test;

import static org.junit.Assert.*;

public class CavalryTest {

    @Test
    public void cavalryGettersTest() {
        Board b = BoardTest.getCommsTestObject1();
//        Using these pieces:
//        Piece   inComms     loc
//        C       true        245
//        C       false       274
        Fighter f = b.northArmy.getFighterAt(245);
        assertEquals(2, f.getMovement());
        assertEquals(2, f.getWeaponsRange());
        assertEquals(4, f.getAttack());
        assertEquals(5, f.getDefence());
        f = b.northArmy.getFighterAt(74);
        assertEquals(0, f.getMovement());
        assertEquals(2, f.getWeaponsRange());
        assertEquals(0, f.getAttack());
        assertEquals(0, f.getDefence());
    }

    @Test
    public void cavalry_defence_not_modified_by_terrain() {
        Board b = BoardTest.getDefenceModifierTestBoard1();
        Fighter f = b.southArmy.getFighterAt(324);
        assertEquals(5, f.getDefence());
    }
}