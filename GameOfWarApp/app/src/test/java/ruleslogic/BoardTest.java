package ruleslogic;

import com.example.gameofwarapp.PieceID;
import com.example.gameofwarapp.PiecePlacer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.Assert.*;

public class BoardTest {

    public static ArrayList<PiecePlacer> getTerrainForTestBoard() {
        ArrayList<PiecePlacer> listOfPieces = new ArrayList<>();
        // put terrain in the listOfPieces
        int[] mountains = {222, 223, 224, 226, 227, 228, 238, 249, 259, 270, 280, 291, 301, 322,
                339, 340, 341, 343};
        for (int i: mountains) listOfPieces.add(new PiecePlacer(i, PieceID.MOUNTAIN));
        int[] fortresses = {71, 187, 285, 324, 454, 489};
        for (int i: fortresses) listOfPieces.add(new PiecePlacer(i, PieceID.FORTRESS));
        int[] mountainPasses = {225, 342};
        for (int i: mountainPasses) listOfPieces.add(new PiecePlacer(i, PieceID.MOUNTAIN_PASS));
        return listOfPieces;
    }

    public static Board getCommsTestObject1() {
        ArrayList<PiecePlacer> listOfPieces = new ArrayList<>();
        // put terrain in the listOfPieces
        listOfPieces.addAll(getTerrainForTestBoard());
        // add the arsenals
        int[] arsenalsNorth = {185, 334};
        for (int i: arsenalsNorth) listOfPieces.add(new PiecePlacer(i, PieceID.ARSENAL_NORTH));
        int[] arsenalsSouth = {64, 484};
        for (int i: arsenalsSouth) listOfPieces.add(new PiecePlacer(i, PieceID.ARSENAL_SOUTH));
        // put north fighters in
        int[] infantryN = {125, 169, 391, 392, 411, 414};
        for (int i: infantryN) listOfPieces.add(new PiecePlacer(i, PieceID.INFANTRY_NORTH));
        int[] cavalryN = {245, 274, 330, 460};
        for (int i: cavalryN) listOfPieces.add(new PiecePlacer(i, PieceID.CAVALRY_NORTH));
        listOfPieces.add(new PiecePlacer(75, PieceID.ARTILLERY_NORTH));
        listOfPieces.add(new PiecePlacer(40, PieceID.ARTILLERYMOUNTED_NORTH));
        listOfPieces.add(new PiecePlacer(202, PieceID.TRANSMISSION_NORTH));
        listOfPieces.add(new PiecePlacer(494, PieceID.TRANSMISSIONMOUNTED_NORTH));
        // put south fighters in
        int[] infantryS = {30, 32, 54, 71, 76, 82, 92, 467};
        for (int i: infantryS) listOfPieces.add(new PiecePlacer(i, PieceID.INFANTRY_SOUTH));
        int[] cavalryS = {31, 50};
        for (int i: cavalryS) listOfPieces.add(new PiecePlacer(i, PieceID.CAVALRY_SOUTH));
        listOfPieces.add(new PiecePlacer(211, PieceID.ARTILLERY_SOUTH));
        listOfPieces.add(new PiecePlacer(25, PieceID.ARTILLERYMOUNTED_SOUTH));
        listOfPieces.add(new PiecePlacer(130, PieceID.TRANSMISSION_SOUTH));
        listOfPieces.add(new PiecePlacer(466, PieceID.TRANSMISSIONMOUNTED_SOUTH));
        Collections.sort(listOfPieces);
        return new Board(listOfPieces, "north", 0);
    }

    public static Board getDefenceModifierTestBoard1() {
        ArrayList<PiecePlacer> listOfPieces = new ArrayList<>();
        // put terrain in the listOfPieces
        listOfPieces.addAll(getTerrainForTestBoard());
        // add North pieces
        listOfPieces.add(new PiecePlacer(185, PieceID.ARSENAL_NORTH));
        listOfPieces.add(new PiecePlacer(334, PieceID.ARSENAL_NORTH));
        listOfPieces.add(new PiecePlacer(187, PieceID.ARTILLERYMOUNTED_NORTH));
        listOfPieces.add(new PiecePlacer(225, PieceID.INFANTRY_NORTH));
        listOfPieces.add(new PiecePlacer(285, PieceID.ARTILLERY_NORTH));
        listOfPieces.add(new PiecePlacer(454, PieceID.TRANSMISSION_NORTH));
        // add South pieces
        listOfPieces.add(new PiecePlacer(64, PieceID.ARSENAL_SOUTH));
        listOfPieces.add(new PiecePlacer(484, PieceID.ARSENAL_SOUTH));
        listOfPieces.add(new PiecePlacer(71, PieceID.INFANTRY_SOUTH));
        listOfPieces.add(new PiecePlacer(324, PieceID.CAVALRY_SOUTH));
        listOfPieces.add(new PiecePlacer(342, PieceID.ARTILLERY_SOUTH));
        listOfPieces.add(new PiecePlacer(442, PieceID.TRANSMISSION_SOUTH));
        listOfPieces.add(new PiecePlacer(489, PieceID.TRANSMISSIONMOUNTED_SOUTH));
        Collections.sort(listOfPieces);
        return new Board(listOfPieces, "south", 0);
    }

    public static Board getBattleTestBoard1() {
        ArrayList<PiecePlacer> listOfPieces = new ArrayList<>();
        // put terrain in the listOfPieces
        listOfPieces.addAll(getTerrainForTestBoard());
        // add North pieces
        listOfPieces.add(new PiecePlacer(185, PieceID.ARSENAL_NORTH));
        listOfPieces.add(new PiecePlacer(245, PieceID.ARTILLERY_NORTH));
        listOfPieces.add(new PiecePlacer(203, PieceID.INFANTRY_NORTH));
        listOfPieces.add(new PiecePlacer(183, PieceID.INFANTRY_NORTH));
        // add South pieces
        listOfPieces.add(new PiecePlacer(64, PieceID.ARSENAL_SOUTH));
        int[] infantryS = {201, 202, 221};
        for (int i: infantryS) listOfPieces.add(new PiecePlacer(i, PieceID.INFANTRY_SOUTH));
        listOfPieces.add(new PiecePlacer(243, PieceID.ARTILLERY_SOUTH));
        listOfPieces.add(new PiecePlacer(67, PieceID.TRANSMISSION_SOUTH));
        Collections.sort(listOfPieces);
        return new Board(listOfPieces, "south", 0);
    }

    public static Board getBattleTestBoard2() {
        ArrayList<PiecePlacer> listOfPieces = new ArrayList<>();
        // put terrain in the listOfPieces
        listOfPieces.addAll(getTerrainForTestBoard());
        // add North pieces
        listOfPieces.add(new PiecePlacer(185, PieceID.ARSENAL_NORTH));
        listOfPieces.add(new PiecePlacer(178, PieceID.ARTILLERY_NORTH));
        listOfPieces.add(new PiecePlacer(177, PieceID.ARTILLERYMOUNTED_NORTH));
        listOfPieces.add(new PiecePlacer(174, PieceID.CAVALRY_NORTH));
        // add South pieces
        listOfPieces.add(new PiecePlacer(64, PieceID.ARSENAL_SOUTH));
        listOfPieces.add(new PiecePlacer(130, PieceID.INFANTRY_SOUTH));
        listOfPieces.add(new PiecePlacer(108, PieceID.ARTILLERY_SOUTH));
        listOfPieces.add(new PiecePlacer(86, PieceID.ARTILLERYMOUNTED_SOUTH));
        Collections.sort(listOfPieces);
        return new Board(listOfPieces, "south", 0);
    }

    public static Board getBattleTestBoard3(boolean largeForces) {
        ArrayList<PiecePlacer> listOfPieces = new ArrayList<>();
        // put terrain in the listOfPieces
        listOfPieces.addAll(getTerrainForTestBoard());
        // add North pieces
        listOfPieces.add(new PiecePlacer(185, PieceID.ARSENAL_NORTH));
        ArrayList<Integer> infantryN = new ArrayList<>();
        infantryN.add(265);
        infantryN.add(266);
        if (largeForces) {
            int[] larger = {267, 268, 269};
            for (int i: larger) infantryN.add(i);
        }
        for (int i: infantryN) listOfPieces.add(new PiecePlacer(i, PieceID.INFANTRY_NORTH));
        // add South pieces
        listOfPieces.add(new PiecePlacer(484, PieceID.ARSENAL_SOUTH));
        int[] baseInfantry = {264, 284, 304};
        ArrayList<Integer> infantryS = new ArrayList<>();
        for (int i: baseInfantry) infantryS.add(i);
        if (largeForces) {
            int[] larger = {324, 344, 364};
            for (int i: larger) infantryN.add(i);
        }
        for (int i: infantryS) listOfPieces.add(new PiecePlacer(i, PieceID.INFANTRY_SOUTH));
        Collections.sort(listOfPieces);
        return new Board(listOfPieces, "north", 0);
    }

    public static Board getBattleTestBoard4() {
        ArrayList<PiecePlacer> listOfPieces = new ArrayList<>();
        // put terrain in the listOfPieces
        listOfPieces.addAll(getTerrainForTestBoard());
        // add North pieces
        listOfPieces.add(new PiecePlacer(185, PieceID.ARSENAL_NORTH));
        listOfPieces.add(new PiecePlacer(266, PieceID.CAVALRY_NORTH));
        listOfPieces.add(new PiecePlacer(265, PieceID.INFANTRY_NORTH));
        listOfPieces.add(new PiecePlacer(308, PieceID.INFANTRY_NORTH));
        // add South pieces
        listOfPieces.add(new PiecePlacer(484, PieceID.ARSENAL_SOUTH));
        listOfPieces.add(new PiecePlacer(264, PieceID.INFANTRY_SOUTH));
        listOfPieces.add(new PiecePlacer(262, PieceID.INFANTRY_SOUTH));
        Collections.sort(listOfPieces);
        return new Board(listOfPieces, "north", 0);
    }

    public static Board getBattleTestBoard5(int i) {
        ArrayList<PiecePlacer> listOfPieces = new ArrayList<>();
        // put terrain in the listOfPieces
        listOfPieces.addAll(getTerrainForTestBoard());
        // add North pieces
        listOfPieces.add(new PiecePlacer(334, PieceID.ARSENAL_NORTH));
        listOfPieces.add(new PiecePlacer(494, PieceID.CAVALRY_NORTH));
        // add South pieces
        listOfPieces.add(new PiecePlacer(484, PieceID.ARSENAL_SOUTH));
        int putCavalryAt = 493;
        while (i > 0) {
            listOfPieces.add(new PiecePlacer(putCavalryAt, PieceID.CAVALRY_SOUTH));
            putCavalryAt -= 1;
            i -= 1;
        }
        Collections.sort(listOfPieces);
        return new Board(listOfPieces, "south", 0);
    }


    @Test
    public void isOnBoard_rejects_on_letter_grid_row() {
        assertFalse(Board.isOnBoard(15));
    }

    @Test
    public void isOnBoard_rejects_below_lowest_valid() {
        assertFalse(Board.isOnBoard(21));
        assertFalse(Board.isOnBoard(0));
    }

    @Test
    public void isOnBoard_accepts_lowest_valid() {
        assertTrue(Board.isOnBoard(22));
    }

    @Test
    public void isOnBoard_accepts_highest_valid() {
        assertTrue(Board.isOnBoard(545));
    }

    @Test
    public void isOnBoard_rejects_above_highest_valid() {
        assertFalse(Board.isOnBoard(546));
        assertFalse(Board.isOnBoard(550));
    }

    @Test
    public void isOnBoard_rejects_number_grid_column() {
        assertFalse(Board.isOnBoard(42));
        assertFalse(Board.isOnBoard(525));
        assertFalse(Board.isOnBoard(210));
    }

    @Test
    public void isOnBoard_accepts_in_middle() {
        assertTrue(Board.isOnBoard(31));
        assertTrue(Board.isOnBoard(71));
        assertTrue(Board.isOnBoard(230));
        assertTrue(Board.isOnBoard(358));
        assertTrue(Board.isOnBoard(540));
    }

    @Test
    public void getCandidateLocations_works_short() {
        Board b = getCommsTestObject1();
        HashSet<Integer> a = new HashSet<>();
        int[] correct = {22, 23, 24, 43, 44, 45, 64, 65, 66};
        for (int i: correct) a.add(i);
        assertEquals(a, b.getCandidateLocations(44,1, false, b.northArmy));
    }

    @Test
    public void getCandidateLocations_handles_board_edge() {
        Board b = getCommsTestObject1();
        HashSet<Integer> a = new HashSet<>();
        int[] correct = {22, 23, 24, 43, 44, 45, 46, 64, 65, 66, 86, 88};
        for (int i: correct) a.add(i);
        assertEquals(a, b.getCandidateLocations(44,2, false, b.northArmy));
    }

    @Test
    public void getCandidateLocations_handles_mountains() {
        Board b = getCommsTestObject1();
        HashSet<Integer> a = new HashSet<>();
        int[] correct = {233, 237, 255, 258, 277, 279, 299, 300, 317, 318,
                319, 320, 321, 342, 363, 384, 405};
        for (int i: correct) a.add(i);
        assertEquals(a, b.getCandidateLocations(321,4, false, b.northArmy));
    }

    @Test
    public void getCandidateLocations_handles_opponentBlock_with_fighters() {
        Board b = getCommsTestObject1();
        HashSet<Integer> a = new HashSet<>();
        int[] correct = {33, 34, 35, 53, 55, 73, 77};
        for (int i: correct) a.add(i);
        assertEquals(a, b.getCandidateLocations(33,2, true, b.northArmy));
    }

    @Test
    public void getCandidateLocations_handles_opponentBlock_with_arsenal() {
        Board b = getCommsTestObject1();
        HashSet<Integer> a = new HashSet<>();
        int[] correct = {61, 82, 103, 124, 145, 166, 187, 208, 229, 250, 251, 271, 272, 292, 293, 313};
        for (int i: correct) a.add(i);
        assertEquals(a, b.getCandidateLocations(271,25, true, b.southArmy));
    }

    @Test
    public void getCandidateLocations_spans_full_length_of_board() {
        Board b = getCommsTestObject1();
        HashSet<Integer> a = new HashSet<>();
        int[] correct = {40, 61, 82, 103, 124, 145, 166, 187, 208, 229, 250, 251, 271, 272, 292,
                293, 313, 334, 355, 376, 397, 418, 439, 460, 481, 502, 523, 544};
        for (int i: correct) a.add(i);
        assertEquals(a, b.getCandidateLocations(271,25, false, b.southArmy));
    }

    @Test
    public void hasMountainAt_rejects_square_with_no_terrain() {
        Board b = getCommsTestObject1();
        assertFalse(b.hasMountainAt(202));
        assertFalse(b.hasMountainAt(421));
        assertFalse(b.hasMountainAt(229));
        assertFalse(b.hasMountainAt(362));
    }

    @Test
    public void hasMountainAt_rejects_other_terrain_types() {
        Board b = getCommsTestObject1();
        // Fortress
        assertFalse(b.hasMountainAt(71));
        assertFalse(b.hasMountainAt(285));
        // MountainPass
        assertFalse(b.hasMountainAt(225));
        assertFalse(b.hasMountainAt(342));
        // Arsenal
        assertFalse(b.hasMountainAt(64));
        assertFalse(b.hasMountainAt(334));
    }

    @Test
    public void hasMountainAt_accepts_actual_mountains() {
        Board b = getCommsTestObject1();
        assertTrue(b.hasMountainAt(222));
        assertTrue(b.hasMountainAt(249));
        assertTrue(b.hasMountainAt(301));
        assertTrue(b.hasMountainAt(341));
    }

    @Test
    public void hasFortressAt_rejects_location_with_no_terrain() {
        Board b = getCommsTestObject1();
        assertFalse(b.hasFortressAt(175));
    }

    @Test
    public void hasFortressAt_rejects_other_terrain_types() {
        Board b = getCommsTestObject1();
        assertFalse(b.hasFortressAt(238));
        assertFalse(b.hasFortressAt(342));
    }

    @Test
    public void hasFortressAt_accepts_actual_fortresses() {
        Board b = getCommsTestObject1();
        assertTrue(b.hasFortressAt(71));
        assertTrue(b.hasFortressAt(187));
        assertTrue(b.hasFortressAt(489));
        assertTrue(b.hasFortressAt(454));
    }

    @Test
    public void hasTerrainAt_accepts_correctly() {
        Board b = getCommsTestObject1();
        // a Fortress
        assertTrue(b.hasTerrainAt(454));
        // a Mountain
        assertTrue(b.hasTerrainAt(291));
        // a MountainPass
        assertTrue(b.hasTerrainAt(342));
    }

    @Test
    public void hasTerrainAt_rejects_correctly() {
        Board b = getCommsTestObject1();
        // an empty square
        assertFalse(b.hasTerrainAt(276));
        // a location with a Fighter
        assertFalse(b.hasTerrainAt(245));
        // an Arsenal (since included in the armies)
        assertFalse(b.hasTerrainAt(64));
    }

    @Test
    public void getTerrainAt_returns_correct_objects() {
        Board b = getCommsTestObject1();
        // works with ...Fortress
        assertEquals(b.getTerrainAt(324), new Fortress(324));
        // ...Mountain
        assertEquals(b.getTerrainAt(223), new Mountain(223));
        // ...MountainPass
        assertEquals(b.getTerrainAt(342), new MountainPass(342));
    }

    @Test
    public void resolveAttack_no_defender_and_defender_out_of_comms() {
        // scenarios 1 and 6 in TestBoardStates //
        ArrayList<PiecePlacer> listOfPieces = new ArrayList<>();
        // put terrain in the listOfPieces
        listOfPieces.addAll(getTerrainForTestBoard());
        // add the arsenals
        int[] arsenalsNorth = {185, 334};
        for (int i: arsenalsNorth) listOfPieces.add(new PiecePlacer(i, PieceID.ARSENAL_NORTH));
        int[] arsenalsSouth = {64, 484};
        for (int i: arsenalsSouth) listOfPieces.add(new PiecePlacer(i, PieceID.ARSENAL_SOUTH));
        // add North pieces
        listOfPieces.add(new PiecePlacer(38, PieceID.ARTILLERY_NORTH));
        // add South pieces
        listOfPieces.add(new PiecePlacer(35, PieceID.INFANTRY_SOUTH));
        Collections.sort(listOfPieces);
        Board b = new Board(listOfPieces, "north", 0);
        // test the attack at wrong location
        assertEquals("", b.resolveAttack(b.northArmy, b.southArmy, 36));
        // test the successful attack
        assertTrue(b.southArmy.hasFighterAt(35));
        String msgReturn = b.resolveAttack(b.northArmy, b.southArmy, 35);
        assertEquals("", msgReturn);
        assertEquals(0, b.locationMustMove);
        assertFalse(b.southArmy.hasFighterAt(35));
    }

    @Test
    public void resolveAttack_AttackSum_lessthanorequal_DefenceSum() {
        ArrayList<PiecePlacer> listOfPieces = new ArrayList<>();
        // put terrain in the listOfPieces
        listOfPieces.addAll(getTerrainForTestBoard());
        // add the arsenals
        int[] arsenalsNorth = {185, 334};
        for (int i: arsenalsNorth) listOfPieces.add(new PiecePlacer(i, PieceID.ARSENAL_NORTH));
        int[] arsenalsSouth = {64, 484};
        for (int i: arsenalsSouth) listOfPieces.add(new PiecePlacer(i, PieceID.ARSENAL_SOUTH));
        // add North pieces
        listOfPieces.add(new PiecePlacer(75, PieceID.CAVALRY_NORTH));
        // add South pieces
        listOfPieces.add(new PiecePlacer(74, PieceID.INFANTRY_SOUTH));
        listOfPieces.add(new PiecePlacer(73, PieceID.TRANSMISSION_SOUTH));
        Collections.sort(listOfPieces);
        Board b = new Board(listOfPieces, "north", 0);
        String msgReturn = b.resolveAttack(b.northArmy, b.southArmy, 74);
        assertEquals("", msgReturn);
        assertEquals(0, b.locationMustMove);
        assertTrue(b.southArmy.hasFighterAt(74));
    }

    @Test
    public void resolveAttack_AttackSum_2_over_DefenceSum() {
        ArrayList<PiecePlacer> listOfPieces = new ArrayList<>();
        // put terrain in the listOfPieces
        listOfPieces.addAll(getTerrainForTestBoard());
        // add the arsenals
        int[] arsenalsNorth = {185, 334};
        for (int i: arsenalsNorth) listOfPieces.add(new PiecePlacer(i, PieceID.ARSENAL_NORTH));
        int[] arsenalsSouth = {64, 484};
        for (int i: arsenalsSouth) listOfPieces.add(new PiecePlacer(i, PieceID.ARSENAL_SOUTH));
        // add North pieces
        listOfPieces.add(new PiecePlacer(474, PieceID.INFANTRY_NORTH));
        // add South pieces
        listOfPieces.add(new PiecePlacer(473, PieceID.INFANTRY_SOUTH));
        listOfPieces.add(new PiecePlacer(494, PieceID.INFANTRY_SOUTH));
        Collections.sort(listOfPieces);
        Board b = new Board(listOfPieces, "south", 0);
        String msgReturn = b.resolveAttack(b.southArmy, b.northArmy, 474);
        assertEquals("", msgReturn);
        assertEquals(0, b.locationMustMove);
        assertFalse(b.northArmy.hasFighterAt(474));
    }

    @Test
    public void resolveAttack_AttackSum_one_greater_than_DefenceSum_and_no_escape() {
        ArrayList<PiecePlacer> listOfPieces = new ArrayList<>();
        // put terrain in the listOfPieces
        listOfPieces.addAll(getTerrainForTestBoard());
        // add the arsenals
        int[] arsenalsNorth = {185, 334};
        for (int i: arsenalsNorth) listOfPieces.add(new PiecePlacer(i, PieceID.ARSENAL_NORTH));
        int[] arsenalsSouth = {64, 484};
        for (int i: arsenalsSouth) listOfPieces.add(new PiecePlacer(i, PieceID.ARSENAL_SOUTH));
        // add North pieces
        int[] infantryN = {362, 363, 364, 365, 384};
        for (int i: infantryN) listOfPieces.add(new PiecePlacer(i, PieceID.INFANTRY_NORTH));
        listOfPieces.add(new PiecePlacer(323, PieceID.TRANSMISSION_NORTH));
        // add South pieces
        listOfPieces.add(new PiecePlacer(321, PieceID.TRANSMISSION_SOUTH));
        listOfPieces.add(new PiecePlacer(174, PieceID.TRANSMISSIONMOUNTED_SOUTH));
        listOfPieces.add(new PiecePlacer(342, PieceID.INFANTRY_SOUTH));
        listOfPieces.add(new PiecePlacer(320, PieceID.INFANTRY_SOUTH));
        Collections.sort(listOfPieces);
        Board b = new Board(listOfPieces, "north", 0);
        assertTrue(b.southArmy.hasFighterAt(342));
        String msgReturn = b.resolveAttack(b.northArmy, b.southArmy, 342);
        assertEquals("", msgReturn);
        assertEquals(0, b.locationMustMove);
        assertFalse(b.southArmy.hasFighterAt(342));
    }

    @Test
    public void resolveAttack_AttackSum_one_greater_than_DefenceSum_and_has_escape() {
        ArrayList<PiecePlacer> listOfPieces = new ArrayList<>();
        // put terrain in the listOfPieces
        listOfPieces.addAll(getTerrainForTestBoard());
        // add the arsenals
        int[] arsenalsNorth = {185, 334};
        for (int i: arsenalsNorth) listOfPieces.add(new PiecePlacer(i, PieceID.ARSENAL_NORTH));
        int[] arsenalsSouth = {64, 484};
        for (int i: arsenalsSouth) listOfPieces.add(new PiecePlacer(i, PieceID.ARSENAL_SOUTH));
        // add North pieces
        listOfPieces.add(new PiecePlacer(290, PieceID.TRANSMISSION_NORTH));
        listOfPieces.add(new PiecePlacer(269, PieceID.INFANTRY_NORTH));
        // add South pieces
        listOfPieces.add(new PiecePlacer(268, PieceID.INFANTRY_SOUTH));
        listOfPieces.add(new PiecePlacer(289, PieceID.INFANTRY_SOUTH));
        listOfPieces.add(new PiecePlacer(247, PieceID.TRANSMISSION_SOUTH));
        listOfPieces.add(new PiecePlacer(244, PieceID.TRANSMISSIONMOUNTED_SOUTH));
        Collections.sort(listOfPieces);
        Board b = new Board(listOfPieces, "south", 0);
        assertTrue(b.northArmy.hasFighterAt(269));
        String msgReturn = b.resolveAttack(b.southArmy, b.northArmy, 269);
        assertEquals("Retreat! Piece at q12 must move first.", msgReturn);
        assertEquals(269, b.locationMustMove);
        assertTrue(b.northArmy.hasFighterAt(269));
    }

    @Test
    public void test_debug_failed_attack() {
        ArrayList<PiecePlacer> listOfPieces = new ArrayList<>();
        // put terrain in the listOfPieces
        listOfPieces.addAll(getTerrainForTestBoard());
        // add the arsenals
        int[] arsenalsNorth = {185, 334};
        for (int i: arsenalsNorth) listOfPieces.add(new PiecePlacer(i, PieceID.ARSENAL_NORTH));
        int[] arsenalsSouth = {64, 484};
        for (int i: arsenalsSouth) listOfPieces.add(new PiecePlacer(i, PieceID.ARSENAL_SOUTH));
        // add North pieces
        listOfPieces.add(new PiecePlacer(290, PieceID.TRANSMISSION_NORTH));
        listOfPieces.add(new PiecePlacer(269, PieceID.INFANTRY_NORTH));
        // add South pieces
        listOfPieces.add(new PiecePlacer(268, PieceID.INFANTRY_SOUTH));
        listOfPieces.add(new PiecePlacer(289, PieceID.INFANTRY_SOUTH));
        listOfPieces.add(new PiecePlacer(247, PieceID.TRANSMISSION_SOUTH));
        listOfPieces.add(new PiecePlacer(244, PieceID.TRANSMISSIONMOUNTED_SOUTH));
        Collections.sort(listOfPieces);
        Board b = new Board(listOfPieces, "south", 0);
        // south attacks
        int[] moveLocations0 = {0, 0, 0,0,0,0,0,0,0,0,0,0,0,0,0, 269};
        String s0 = b.processMove(moveLocations0);
        assertEquals(269, b.locationMustMove);
        b.turn = "north";
        // north retreats
        int[] moveLocations1 = {269, 248, 0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        String s1 = b.processMove(moveLocations1);
        assertEquals("71 2 187 2 222 0 223 0 224 0 225 1 226 0 227 0 228 0 238 0 249 0" +
                " 259 0 270 0 280 0 285 2 291 0 301 0 322 0 324 2 339 0 340 0 341 0 342 1 343 0 " +
                "454 2 489 2 185 3 334 3 64 10 484 10//248 6 290 4 244 12 247 11 268 13 289 " +
                "13//north//0//", s1);
        b.turn = "south";
        // south attempts divide and conquer
        int[] moveLocations2 = {268, 269, 0,0,0,0,0,0,0,0,0,0,0,0,0, 248};
        String s2 = b.processMove(moveLocations2);
        assertEquals("71 2 187 2 222 0 223 0 224 0 225 1 226 0 227 0 228 0 238 0 249 0 " +
                "259 0 270 0 280 0 285 2 291 0 301 0 322 0 324 2 339 0 340 0 341 0 342 1 343 0 " +
                "454 2 489 2 185 3 334 3 64 10 484 10//290 4 244 12 247 11 269 13 289 13//" +
                "south//0//", s2);
    }

    @Test
    public void test_string_constructor_behaves() {
        // build on the Pump House setup
        Board b = new Board(" 64 10 71 2 185 3 187 2 222 0 223 0 224 0 225 1 226 0 227 0 228 0 238" +
                " 0 249 0 259 0 270 0 280 0 285 2 291 0 301 0 322 0 324 2 334 3 339 0 340 0 341" +
                " 0 342 1 343 0 454 2 484 10 489 2//97 5 116 7 117 7 118 7 138 9 139 7 158 6 160" +
                " 6 179 6 180 6 200 6 202 6 225 6 245 4 246 8 247 6 266 6 342 13 364 13 384 11" +
                " 385 15 405 13 406 13 426 13 427 13 446 13 447 13 448 16 449 14 469 14 470 14" +
                " 489 13 490 12 491 14//north//0//");

        assertTrue(b.northArmy.hasFighterAt(160));

        int[] movelocations = {116, 94, 93,
                158, 157, 0,
                266, 267 ,0,
                0,0,0,0,0,0,0};
        assertEquals("valid", b.validateMove(movelocations));
    }

    @Test
    public void test_toString_on_PumpHouse() {
        // build on the Pump House setup
        Board b = new Board("64 10 71 2 185 3 187 2 222 0 223 0 224 0 225 1 226 0 227 0 228 0 238" +
                " 0 249 0 259 0 270 0 280 0 285 2 291 0 301 0 322 0 324 2 334 3 339 0 340 0 341" +
                " 0 342 1 343 0 454 2 484 10 489 2//97 5 116 7 117 7 118 7 138 9 139 7 158 6 160" +
                " 6 179 6 180 6 200 6 202 6 225 6 245 4 246 8 247 6 266 6 342 13 364 13 384 11" +
                " 385 15 405 13 406 13 426 13 427 13 446 13 447 13 448 16 449 14 469 14 470 14" +
                " 489 13 490 12 491 14//north//0//");

        assertEquals("71 2 187 2 222 0 223 0 224 0 225 1 226 0 227 0 228 0 238 0 249 0 " +
                "259 0 270 0 280 0 285 2 291 0 301 0 322 0 324 2 339 0 340 0 341 0 342 1 343 0 " +
                "454 2 489 2 185 3 334 3 64 10 484 10//97 5 116 7 117 7 118 7 138 9 139 7 158 6 " +
                "160 6 179 6 180 6 200 6 202 6 225 6 245 4 246 8 247 6 266 6 342 13 364 13 384 " +
                "11 385 15 405 13 406 13 426 13 427 13 446 13 447 13 448 16 449 14 469 14 470 14 " +
                "489 13 490 12 491 14//north//0//", b.toString() );
    }


}