package ruleslogic;

import org.junit.Test;

import static org.junit.Assert.*;

public class TransmissionMountedTest {

    @Test
    public void transmissionMountedGettersTest() {
        Board b = BoardTest.getCommsTestObject1();
//        Using these pieces:
//        Piece   inComms     loc
//        Tm      true        494
//        Tm      false       466
        Fighter f = b.northArmy.getFighterAt(494);
        assertEquals(2, f.getMovement());
        assertEquals(2, f.getWeaponsRange());
        assertEquals(0, f.getAttack());
        assertEquals(1, f.getDefence());
        f = b.southArmy.getFighterAt(466);
        assertEquals(2, f.getMovement());
        assertEquals(2, f.getWeaponsRange());
        assertEquals(0, f.getAttack());
        assertEquals(1, f.getDefence());
    }

    @Test
    public void transmissionMounted_defence_not_modified_by_terrain() {
        Board b = BoardTest.getDefenceModifierTestBoard1();
        Fighter f = b.southArmy.getFighterAt(489);
        assertEquals(1, f.getDefence());
    }
}