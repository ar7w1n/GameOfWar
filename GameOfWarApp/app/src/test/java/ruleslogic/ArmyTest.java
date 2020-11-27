package ruleslogic;

import com.example.gameofwarapp.PieceID;
import com.example.gameofwarapp.PiecePlacer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

public class ArmyTest {

    @Test
    public void hasFighterAt_expects_true() {
        Board b = BoardTest.getCommsTestObject1();
        assertTrue(b.northArmy.hasFighterAt(40));
        assertTrue(b.northArmy.hasFighterAt(245));
        assertTrue(b.northArmy.hasFighterAt(494));
        assertTrue(b.southArmy.hasFighterAt(25));
        assertTrue(b.southArmy.hasFighterAt(211));
        assertTrue(b.southArmy.hasFighterAt(467));
    }

    @Test
    public void hasFighterAt_expects_false() {
        Board b = BoardTest.getCommsTestObject1();
        // empty location, terrain, fighter of *other* army, arsenal
        assertFalse(b.northArmy.hasFighterAt(108));
        assertFalse(b.northArmy.hasFighterAt(285));
        assertFalse(b.northArmy.hasFighterAt(31));
        assertFalse(b.northArmy.hasFighterAt(185));
        assertFalse(b.southArmy.hasFighterAt(400));
        assertFalse(b.southArmy.hasFighterAt(489));
        assertFalse(b.southArmy.hasFighterAt(245));
        assertFalse(b.southArmy.hasFighterAt(484));
    }

    @Test
    public void hasArsenalAt_expects_true() {
        Board b = BoardTest.getCommsTestObject1();
        assertTrue(b.northArmy.hasArsenalAt(185));
        assertTrue(b.northArmy.hasArsenalAt(334));
        assertTrue(b.southArmy.hasArsenalAt(64));
        assertTrue(b.southArmy.hasArsenalAt(484));
    }

    @Test
    public void hasArsenalAt_expects_false() {
        Board b = BoardTest.getCommsTestObject1();
        // empty location, terrain, fighter of own army
        assertFalse(b.northArmy.hasArsenalAt(195));
        assertFalse(b.northArmy.hasArsenalAt(225));
        assertFalse(b.northArmy.hasArsenalAt(40));
        assertFalse(b.southArmy.hasArsenalAt(535));
        assertFalse(b.southArmy.hasArsenalAt(342));
        assertFalse(b.southArmy.hasArsenalAt(211));
    }

    @Test
    public void getFighterAtTest() {
        Board b = BoardTest.getCommsTestObject1();
        // first south fighter
        assertEquals(new ArtilleryMounted(25, b), b.southArmy.getFighterAt(25));
        // last north fighter
        assertEquals(new TransmissionMounted(494, b), b.northArmy.getFighterAt(494));
        // middle
        assertEquals(new Cavalry(245, b), b.northArmy.getFighterAt(245));
    }

    @Test
    public void setLocInCommsTest_and_Fighter_inComms_Test() {
        Board b = BoardTest.getCommsTestObject1();
//        To avoid having to compose huge arrays by hand to test setLocInComms(), testing it is
//        combined with checking that inComms sets correctly in all Fighters, using all combinations
//        of communication transmission: far (from Arsenals and Transmission pieces) and adjacent (from
//        all other Fighters); and all eight straight-line directions.
//        Some pieces on CommsTestObject1 are there to enable other combinations so aren't themselves
//        tested.
//        The format for the description before each test is as follows:
//        loc/piece   expected    case

//        North:
//        40:Am       false       doesn't loop horizontal
        assertFalse(b.northArmy.getFighterAt(40).inComms);
//        75:A        true        transmit far down-left
        assertTrue(b.northArmy.getFighterAt(75).inComms);
//        125:I       true        transmit far up-left
        assertTrue(b.northArmy.getFighterAt(125).inComms);
//        169:I       ** no need to test inComms, just to enable another position
//        202:T       ** no need to test inComms, just to enable another position
//        245:C       true        MountainPass doesn't block
        assertTrue(b.northArmy.getFighterAt(245).inComms);
//        274:C       false       comms doesn't loop diagonal
        assertFalse(b.northArmy.getFighterAt(274).inComms);
//        330:C       true        transmit far down
        assertTrue(b.northArmy.getFighterAt(330).inComms);
//        391:I       true        transmit near down
        assertTrue(b.northArmy.getFighterAt(391).inComms);
//        392:I       true        transmit near down-left
        assertTrue(b.northArmy.getFighterAt(392).inComms);
//        411:I       true        transmit near down-right
        assertTrue(b.northArmy.getFighterAt(411).inComms);
//        414:I       true        transmit far down-right
        assertTrue(b.northArmy.getFighterAt(414).inComms);
//        460:C       true        transmit far right
        assertTrue(b.northArmy.getFighterAt(460).inComms);
//        494:Tm      true        Fortress doesn't block
        assertTrue(b.northArmy.getFighterAt(494).inComms);
//
//        South:
//        25:Am       true        transmit far left
        assertTrue(b.southArmy.getFighterAt(25).inComms);
//        30:I        true        transmit near up-left
        assertTrue(b.southArmy.getFighterAt(30).inComms);
//        31:C        true        transmit near up
        assertTrue(b.southArmy.getFighterAt(31).inComms);
//        32:I        ** no need to test inComms, just to enable another position
//        50:C        true        transmit near left
        assertTrue(b.southArmy.getFighterAt(50).inComms);
//        54:I        true        transmit near up-right
        assertTrue(b.southArmy.getFighterAt(54).inComms);
//        71:I        true        transmit far up
        assertTrue(b.southArmy.getFighterAt(71).inComms);
//        76:I        ** no need to test inComms, just to enable another position
//        82:I        false       doesn't loop vertical, near doesn't do far
        assertFalse(b.southArmy.getFighterAt(82).inComms);
//        92:I        true        transmit near right
        assertTrue(b.southArmy.getFighterAt(92).inComms);
//        130:T       true        transmit far up-right
        assertTrue(b.southArmy.getFighterAt(130).inComms);
//        211:A       false       blocked by pieces inComms and not
        assertFalse(b.southArmy.getFighterAt(211).inComms);
//        466:Tm      false       Mountain blocks
        assertFalse(b.southArmy.getFighterAt(466).inComms);
//        467:I       false       Tm doesn't transmit when out of comms
        assertFalse(b.southArmy.getFighterAt(467).inComms);
    }

    @Test
    public void setLocInComms_extra_edge_case() {
        ArrayList<PiecePlacer> listOfPieces = new ArrayList<>();
        // put terrain in the listOfPieces
        listOfPieces.addAll(BoardTest.getTerrainForTestBoard());
        // add North pieces
        listOfPieces.add(new PiecePlacer(185, PieceID.ARSENAL_NORTH));
        listOfPieces.add(new PiecePlacer(334, PieceID.ARSENAL_NORTH));
        listOfPieces.add(new PiecePlacer(269, PieceID.INFANTRY_NORTH));
        listOfPieces.add(new PiecePlacer(290, PieceID.TRANSMISSION_NORTH));
        // add South pieces
        listOfPieces.add(new PiecePlacer(64, PieceID.ARSENAL_SOUTH));
        listOfPieces.add(new PiecePlacer(484, PieceID.ARSENAL_SOUTH));
        listOfPieces.add(new PiecePlacer(268, PieceID.INFANTRY_SOUTH));
        listOfPieces.add(new PiecePlacer(289, PieceID.INFANTRY_SOUTH));
        listOfPieces.add(new PiecePlacer(247, PieceID.TRANSMISSION_SOUTH));
        listOfPieces.add(new PiecePlacer(244, PieceID.TRANSMISSIONMOUNTED_SOUTH));
        Collections.sort(listOfPieces);
        Board b = new Board(listOfPieces, "north", 269);
        assertTrue(b.northArmy.getFighterAt(269).inComms);
    }

    @Test
    public void moveFighter_basic_tests() {
        // each move-test has 3 components:
        // 1. check the moveFighter() method returns correctly.
        // 2. check there is a fighter in the correct location. (source/destination depends on
        //    whether the move is expected to succeed or not)
        // 3. check there isn't a fighter in the wrong location.

        // failed to move (not in comms)
        Board b = BoardTest.getCommsTestObject1();
        assertEquals(0, b.northArmy.moveFighter(40, 41));
        assertTrue(b.northArmy.hasFighterAt(40));
        assertFalse(b.northArmy.hasFighterAt(41));

        // move up 1 (comms not relevant)
        assertEquals(1, b.northArmy.moveFighter(494, 495));
        assertTrue(b.northArmy.hasFighterAt(495));
        assertFalse(b.northArmy.hasFighterAt(494));

        // move down 1 (comms not relevant)
        b = BoardTest.getCommsTestObject1();
        assertEquals(1, b.northArmy.moveFighter(494, 493));
        assertTrue(b.northArmy.hasFighterAt(493));
        assertFalse(b.northArmy.hasFighterAt(494));

        // tests removed while conditions in moveFighter are removed
//        // move 2 (should fail)
//        b = BoardTest.getCommsTestObject1();
//        assertEquals(0, b.northArmy.moveFighter(494, 496));
//        assertTrue(b.northArmy.hasFighterAt(494));
//        assertFalse(b.northArmy.hasFighterAt(496));
//
//        // tries to move to location occupied by same army
//        assertEquals(0, b.northArmy.moveFighter(76, 54));
//        assertTrue(b.southArmy.hasFighterAt(76));
//        assertTrue(b.southArmy.hasFighterAt(54));
//
//        // tries to move to location occupied by other army
//        assertEquals(0, b.northArmy.moveFighter(75, 76));
//        assertTrue(b.northArmy.hasFighterAt(75));
//        assertFalse(b.northArmy.hasFighterAt(76));

        // move up-left 1 after successful comms check
        assertEquals(1, b.northArmy.moveFighter(414, 394));
        assertTrue(b.northArmy.hasFighterAt(394));
        assertFalse(b.northArmy.hasFighterAt(414));

        // move down-right 1 after successful comms check
        assertEquals(1, b.northArmy.moveFighter(411, 431));
        assertTrue(b.northArmy.hasFighterAt(431));
        assertFalse(b.northArmy.hasFighterAt(411));
    }

    @Test
    public void defend_test_weapons_dont_cross_mountains() {
        Board b = BoardTest.getBattleTestBoard1();
        assertEquals(12, b.northArmy.defend(203));
    }

    @Test
    public void defend_test_artillery_range_works_correctly() {
        Board b = BoardTest.getBattleTestBoard2();
        assertEquals(13, b.northArmy.defend(174));
    }

    @Test
    public void defend_test_infantry_inrange_works_correctly() {
        Board b = BoardTest.getBattleTestBoard3(false);
        assertEquals(18, b.southArmy.defend(264));
    }

    @Test
    public void defend_test_infantry_out_of_range_works_correctly() {
        Board b = BoardTest.getBattleTestBoard3(true);
        assertEquals(18, b.southArmy.defend(264));
    }

    @Test
    public void defend_test_out_of_comms_fighter_doesnt_support() {
        Board b = BoardTest.getBattleTestBoard4();
        assertEquals(6, b.southArmy.defend(264));
    }

    @Test
    public void attack_test_weapons_dont_cross_mountains() {
        Board b = BoardTest.getBattleTestBoard1();
        assertEquals(8, b.southArmy.attack(203));
    }

    @Test
    public void attack_test_artillery_range_works_correctly() {
        Board b = BoardTest.getBattleTestBoard2();
        assertEquals(9, b.southArmy.attack(174));
    }

    @Test
    public void attack_test_infantry_inrange_works_correctly() {
        Board b = BoardTest.getBattleTestBoard3(false);
        assertEquals(8, b.northArmy.attack(264));
    }

    @Test
    public void attack_test_infantry_out_of_range_works_correctly() {
        Board b = BoardTest.getBattleTestBoard3(true);
        assertEquals(8, b.northArmy.attack(264));
    }

    @Test
    public void attack_test_out_of_comms_fighter_doesnt_support() {
        Board b = BoardTest.getBattleTestBoard4();
        assertEquals(8, b.northArmy.attack(264));
    }

    @Test
    public void  attack_test_basic_cavalry_charges() {
        Board b = BoardTest.getBattleTestBoard5(1);
        assertEquals(7, b.southArmy.attack(494));
        b = BoardTest.getBattleTestBoard5(2);
        assertEquals(14, b.southArmy.attack(494));
        b = BoardTest.getBattleTestBoard5(3);
        assertEquals(21, b.southArmy.attack(494));
        b = BoardTest.getBattleTestBoard5(4);
        assertEquals(28, b.southArmy.attack(494));
    }

    @Test
    public void attack_test_two_separate_charges() {
        ArrayList<PiecePlacer> listOfPieces = new ArrayList<>();
        // put terrain in the listOfPieces
        listOfPieces.addAll(BoardTest.getTerrainForTestBoard());
        // add North pieces
        listOfPieces.add(new PiecePlacer(334, PieceID.ARSENAL_NORTH));
        listOfPieces.add(new PiecePlacer(494, PieceID.CAVALRY_NORTH));
        // add South pieces
        listOfPieces.add(new PiecePlacer(484, PieceID.ARSENAL_SOUTH));
        listOfPieces.add(new PiecePlacer(493, PieceID.CAVALRY_SOUTH));
        listOfPieces.add(new PiecePlacer(492, PieceID.CAVALRY_SOUTH));
        listOfPieces.add(new PiecePlacer(472, PieceID.CAVALRY_SOUTH));
        Collections.sort(listOfPieces);
        Board b = new Board(listOfPieces, "south", 0);
        assertEquals(21, b.southArmy.attack(494));
    }

    @Test
    public void attack_test_four_separate_charges() {
        ArrayList<PiecePlacer> listOfPieces = new ArrayList<>();
        // put terrain in the listOfPieces
        listOfPieces.addAll(BoardTest.getTerrainForTestBoard());
        // add North pieces
        listOfPieces.add(new PiecePlacer(334, PieceID.ARSENAL_NORTH));
        listOfPieces.add(new PiecePlacer(494, PieceID.CAVALRY_NORTH));
        listOfPieces.add(new PiecePlacer(472, PieceID.CAVALRY_NORTH));
        listOfPieces.add(new PiecePlacer(473, PieceID.CAVALRY_NORTH));
        listOfPieces.add(new PiecePlacer(514, PieceID.CAVALRY_NORTH));
        // add South pieces
        listOfPieces.add(new PiecePlacer(484, PieceID.ARSENAL_SOUTH));
        listOfPieces.add(new PiecePlacer(493, PieceID.CAVALRY_SOUTH));
        Collections.sort(listOfPieces);
        Board b = new Board(listOfPieces, "north", 0);
        assertEquals(28, b.northArmy.attack(493));
    }

    @Test
    public void attack_test_charge_plus_interrupted_line() {
        ArrayList<PiecePlacer> listOfPieces = new ArrayList<>();
        // put terrain in the listOfPieces
        listOfPieces.addAll(BoardTest.getTerrainForTestBoard());
        // add North pieces
        listOfPieces.add(new PiecePlacer(334, PieceID.ARSENAL_NORTH));
        listOfPieces.add(new PiecePlacer(494, PieceID.CAVALRY_NORTH));
        // add South pieces
        listOfPieces.add(new PiecePlacer(484, PieceID.ARSENAL_SOUTH));
        listOfPieces.add(new PiecePlacer(493, PieceID.CAVALRY_SOUTH));
        listOfPieces.add(new PiecePlacer(491, PieceID.CAVALRY_SOUTH));
        Collections.sort(listOfPieces);
        Board b = new Board(listOfPieces, "south", 0);
        assertEquals(7, b.southArmy.attack(494));
    }

    @Test
    public void attack_test_charge_plus_noncharging_cavalry_support() {
        ArrayList<PiecePlacer> listOfPieces = new ArrayList<>();
        // put terrain in the listOfPieces
        listOfPieces.addAll(BoardTest.getTerrainForTestBoard());
        // add North pieces
        listOfPieces.add(new PiecePlacer(334, PieceID.ARSENAL_NORTH));
        listOfPieces.add(new PiecePlacer(494, PieceID.CAVALRY_NORTH));
        // add South pieces
        listOfPieces.add(new PiecePlacer(484, PieceID.ARSENAL_SOUTH));
        listOfPieces.add(new PiecePlacer(450, PieceID.CAVALRY_SOUTH));
        listOfPieces.add(new PiecePlacer(472, PieceID.CAVALRY_SOUTH));
        listOfPieces.add(new PiecePlacer(492, PieceID.CAVALRY_SOUTH));
        Collections.sort(listOfPieces);
        Board b = new Board(listOfPieces, "south", 0);
        assertEquals(18, b.southArmy.attack(494));
    }

    @Test
    public void attack_test_cavalry_cant_charge_against_fortress() {
        ArrayList<PiecePlacer> listOfPieces = new ArrayList<>();
        // put terrain in the listOfPieces
        listOfPieces.addAll(BoardTest.getTerrainForTestBoard());
        // add North pieces
        listOfPieces.add(new PiecePlacer(334, PieceID.ARSENAL_NORTH));
        listOfPieces.add(new PiecePlacer(454, PieceID.CAVALRY_NORTH));
        // add South pieces
        listOfPieces.add(new PiecePlacer(484, PieceID.ARSENAL_SOUTH));
        listOfPieces.add(new PiecePlacer(474, PieceID.CAVALRY_SOUTH));
        listOfPieces.add(new PiecePlacer(494, PieceID.CAVALRY_SOUTH));
        Collections.sort(listOfPieces);
        Board b = new Board(listOfPieces, "south", 0);
        assertEquals(8, b.southArmy.attack(454));
    }

    @Test
    public void attack_test_cavalry_cant_charge_from_adjacent_fortress() {
        ArrayList<PiecePlacer> listOfPieces = new ArrayList<>();
        // put terrain in the listOfPieces
        listOfPieces.addAll(BoardTest.getTerrainForTestBoard());
        // add North pieces
        listOfPieces.add(new PiecePlacer(334, PieceID.ARSENAL_NORTH));
        listOfPieces.add(new PiecePlacer(454, PieceID.CAVALRY_NORTH));
        listOfPieces.add(new PiecePlacer(434, PieceID.CAVALRY_NORTH));
        // add South pieces
        listOfPieces.add(new PiecePlacer(484, PieceID.ARSENAL_SOUTH));
        listOfPieces.add(new PiecePlacer(474, PieceID.CAVALRY_SOUTH));
        listOfPieces.add(new PiecePlacer(494, PieceID.CAVALRY_SOUTH));
        Collections.sort(listOfPieces);
        Board b = new Board(listOfPieces, "north", 0);
        assertEquals(8, b.northArmy.attack(474));
    }

    @Test
    public void attack_test_cavalry_cant_join_charge_from_fortress() {
        ArrayList<PiecePlacer> listOfPieces = new ArrayList<>();
        // put terrain in the listOfPieces
        listOfPieces.addAll(BoardTest.getTerrainForTestBoard());
        // add North pieces
        listOfPieces.add(new PiecePlacer(334, PieceID.ARSENAL_NORTH));
        listOfPieces.add(new PiecePlacer(454, PieceID.CAVALRY_NORTH));
        listOfPieces.add(new PiecePlacer(434, PieceID.CAVALRY_NORTH));
        listOfPieces.add(new PiecePlacer(474, PieceID.CAVALRY_NORTH));
        // add South pieces
        listOfPieces.add(new PiecePlacer(484, PieceID.ARSENAL_SOUTH));
        listOfPieces.add(new PiecePlacer(494, PieceID.CAVALRY_SOUTH));
        Collections.sort(listOfPieces);
        Board b = new Board(listOfPieces, "north", 0);
        assertEquals(11, b.northArmy.attack(494));
    }

    @Test
    public void attack_test_cavalry_cant_charge_against_mountainpass() {
        ArrayList<PiecePlacer> listOfPieces = new ArrayList<>();
        // put terrain in the listOfPieces
        listOfPieces.addAll(BoardTest.getTerrainForTestBoard());
        // add North pieces
        listOfPieces.add(new PiecePlacer(334, PieceID.ARSENAL_NORTH));
        listOfPieces.add(new PiecePlacer(225, PieceID.CAVALRY_NORTH));
        listOfPieces.add(new PiecePlacer(246, PieceID.INFANTRY_NORTH));
        // add South pieces
        listOfPieces.add(new PiecePlacer(484, PieceID.ARSENAL_SOUTH));
        listOfPieces.add(new PiecePlacer(245, PieceID.CAVALRY_SOUTH));
        listOfPieces.add(new PiecePlacer(265, PieceID.CAVALRY_SOUTH));
        listOfPieces.add(new PiecePlacer(244, PieceID.INFANTRY_SOUTH));
        Collections.sort(listOfPieces);
        Board b = new Board(listOfPieces, "south", 0);
        assertEquals(8, b.southArmy.attack(225));
    }

    @Test
    public void attack_test_cavalry_can_charge_from_mountainpass() {
        ArrayList<PiecePlacer> listOfPieces = new ArrayList<>();
        // put terrain in the listOfPieces
        listOfPieces.addAll(BoardTest.getTerrainForTestBoard());
        // add North pieces
        listOfPieces.add(new PiecePlacer(334, PieceID.ARSENAL_NORTH));
        listOfPieces.add(new PiecePlacer(225, PieceID.CAVALRY_NORTH));
        listOfPieces.add(new PiecePlacer(246, PieceID.INFANTRY_NORTH));
        // add South pieces
        listOfPieces.add(new PiecePlacer(484, PieceID.ARSENAL_SOUTH));
        listOfPieces.add(new PiecePlacer(245, PieceID.CAVALRY_SOUTH));
        listOfPieces.add(new PiecePlacer(265, PieceID.CAVALRY_SOUTH));
        listOfPieces.add(new PiecePlacer(244, PieceID.INFANTRY_SOUTH));
        Collections.sort(listOfPieces);
        Board b = new Board(listOfPieces, "north", 0);
        assertEquals(11, b.northArmy.attack(245));
    }

    @Test
    public void attack_test_mixed_attackers_including_charge() {
        ArrayList<PiecePlacer> listOfPieces = new ArrayList<>();
        // put terrain in the listOfPieces
        listOfPieces.addAll(BoardTest.getTerrainForTestBoard());
        // add North pieces
        listOfPieces.add(new PiecePlacer(334, PieceID.ARSENAL_NORTH));
        listOfPieces.add(new PiecePlacer(494, PieceID.INFANTRY_NORTH));
        // add South pieces
        listOfPieces.add(new PiecePlacer(484, PieceID.ARSENAL_SOUTH));
        listOfPieces.add(new PiecePlacer(493, PieceID.CAVALRY_SOUTH));
        listOfPieces.add(new PiecePlacer(492, PieceID.CAVALRY_SOUTH));
        listOfPieces.add(new PiecePlacer(491, PieceID.CAVALRY_SOUTH));
        listOfPieces.add(new PiecePlacer(472, PieceID.INFANTRY_SOUTH));
        listOfPieces.add(new PiecePlacer(450, PieceID.CAVALRY_SOUTH));
        listOfPieces.add(new PiecePlacer(428, PieceID.ARTILLERYMOUNTED_SOUTH));
        Collections.sort(listOfPieces);
        Board b = new Board(listOfPieces, "south", 0);
        assertEquals(34, b.southArmy.attack(494));
    }

    @Test
    public void locationToString_test1() {
        assertEquals("m21", Board.locationToString(454));
    }

    @Test
    public void locationToString_test2() {
        assertEquals("a4", Board.locationToString(85));
    }
}