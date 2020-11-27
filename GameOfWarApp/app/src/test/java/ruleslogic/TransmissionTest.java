package ruleslogic;

import org.junit.Test;

import static org.junit.Assert.*;

public class TransmissionTest {

    @Test
    public void transmissionGettersTest() {
        Board b = BoardTest.getCommsTestObject1();
//        Using these pieces:
//        Piece   inComms     loc
//        T       true        130
//        T       false       202
        Fighter f = b.southArmy.getFighterAt(130);
        assertEquals(1, f.getMovement());
        assertEquals(2, f.getWeaponsRange());
        assertEquals(0, f.getAttack());
        assertEquals(1, f.getDefence());
        f = b.northArmy.getFighterAt(202);
        assertEquals(1, f.getMovement());
        assertEquals(2, f.getWeaponsRange());
        assertEquals(0, f.getAttack());
        assertEquals(1, f.getDefence());
    }

    @Test
    public void transmission_defence_not_modified_by_terrain() {
        Board b = BoardTest.getDefenceModifierTestBoard1();
        Fighter f = b.northArmy.getFighterAt(454);
        assertEquals(1, f.getDefence());
        f = b.southArmy.getFighterAt(442);
        assertEquals(1, f.getDefence());
    }

}