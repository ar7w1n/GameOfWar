package com.example.gameofwarapp;

import java.util.ArrayList;
import java.util.Collections;

public class StockStates {

    public static BoardState makePumpHouse() {
        // make Terrain ArrayList
        ArrayList<PiecePlacer> terrain = BoardState.getTerrain();
        terrain.add(new PiecePlacer(185, PieceID.ARSENAL_NORTH));
        terrain.add(new PiecePlacer(334, PieceID.ARSENAL_NORTH));
        terrain.add(new PiecePlacer(64, PieceID.ARSENAL_SOUTH));
        terrain.add(new PiecePlacer(484, PieceID.ARSENAL_SOUTH));
        Collections.sort(terrain);
        // make Armies ArrayList for Pump House
        ArrayList<PiecePlacer> fighters = new ArrayList<>();
        // north army
        fighters.add(new PiecePlacer(245, PieceID.TRANSMISSION_NORTH));
        fighters.add(new PiecePlacer(97, PieceID.TRANSMISSIONMOUNTED_NORTH));
        fighters.add(new PiecePlacer(246, PieceID.ARTILLERY_NORTH));
        fighters.add(new PiecePlacer(138, PieceID.ARTILLERYMOUNTED_NORTH));
        int[] cavalrynorth = {116, 117, 118, 139};
        for (int i : cavalrynorth) { fighters.add(new PiecePlacer(i, PieceID.CAVALRY_NORTH)); }
        int[] infantrynorth = {158, 160, 179, 180, 200, 202, 225, 247, 266};
        for (int i : infantrynorth) { fighters.add(new PiecePlacer(i, PieceID.INFANTRY_NORTH)); }
        // south army
        fighters.add(new PiecePlacer(384, PieceID.TRANSMISSION_SOUTH));
        fighters.add(new PiecePlacer(490, PieceID.TRANSMISSIONMOUNTED_SOUTH));
        fighters.add(new PiecePlacer(385, PieceID.ARTILLERY_SOUTH));
        fighters.add(new PiecePlacer(448, PieceID.ARTILLERYMOUNTED_SOUTH));
        int[] cavalrysouth = {449, 469, 470, 491};
        for (int i : cavalrysouth) { fighters.add(new PiecePlacer(i, PieceID.CAVALRY_SOUTH)); }
        int[] infantrysouth = {342, 364, 405, 406, 426, 427, 446, 447, 489};
        for (int i : infantrysouth) { fighters.add(new PiecePlacer(i, PieceID.INFANTRY_SOUTH)); }
        Collections.sort(fighters);
        String turn = "north";
        return new BoardState(terrain, fighters, turn);
    }

    public static BoardState makeRioDeJaneiro() {
        // make Terrain ArrayList
        ArrayList<PiecePlacer> terrain = BoardState.getTerrain();
        terrain.add(new PiecePlacer(185, PieceID.ARSENAL_NORTH));
        terrain.add(new PiecePlacer(334, PieceID.ARSENAL_NORTH));
        terrain.add(new PiecePlacer(64, PieceID.ARSENAL_SOUTH));
        terrain.add(new PiecePlacer(484, PieceID.ARSENAL_SOUTH));
        Collections.sort(terrain);
        // make Armies ArrayList for Pump House
        ArrayList<PiecePlacer> fighters = new ArrayList<>();
        // north army
        fighters.add(new PiecePlacer(455, PieceID.TRANSMISSION_NORTH));
        fighters.add(new PiecePlacer(183, PieceID.TRANSMISSIONMOUNTED_NORTH));
        fighters.add(new PiecePlacer(454, PieceID.ARTILLERY_NORTH));
        fighters.add(new PiecePlacer(140, PieceID.ARTILLERYMOUNTED_NORTH));
        int[] cavalrynorth = {161, 162, 475, 476};
        for (int i : cavalrynorth) { fighters.add(new PiecePlacer(i, PieceID.CAVALRY_NORTH)); }
        int[] infantrynorth = {182, 204, 225, 285, 286, 306, 307, 433, 434};
        for (int i : infantrynorth) { fighters.add(new PiecePlacer(i, PieceID.INFANTRY_NORTH)); }
        // south army
        fighters.add(new PiecePlacer(384, PieceID.TRANSMISSION_SOUTH));
        fighters.add(new PiecePlacer(69, PieceID.TRANSMISSIONMOUNTED_SOUTH));
        fighters.add(new PiecePlacer(113, PieceID.ARTILLERY_SOUTH));
        fighters.add(new PiecePlacer(492, PieceID.ARTILLERYMOUNTED_SOUTH));
        int[] cavalrysouth = {70, 91, 512, 513};
        for (int i : cavalrysouth) { fighters.add(new PiecePlacer(i, PieceID.CAVALRY_SOUTH)); }
        int[] infantrysouth = {71, 92, 303, 324, 342, 344, 450, 470, 471};
        for (int i : infantrysouth) { fighters.add(new PiecePlacer(i, PieceID.INFANTRY_SOUTH)); }
        Collections.sort(fighters);
        String turn = "north";
        return new BoardState(terrain, fighters, turn);
    }

    public static BoardState make1800Marengo() {
        // make Terrain ArrayList
        ArrayList<PiecePlacer> terrain = BoardState.getTerrain();
        terrain.add(new PiecePlacer(185, PieceID.ARSENAL_NORTH));
        terrain.add(new PiecePlacer(334, PieceID.ARSENAL_NORTH));
        terrain.add(new PiecePlacer(64, PieceID.ARSENAL_SOUTH));
        terrain.add(new PiecePlacer(484, PieceID.ARSENAL_SOUTH));
        Collections.sort(terrain);
        // make Armies ArrayList for Pump House
        ArrayList<PiecePlacer> fighters = new ArrayList<>();
        // north army
        fighters.add(new PiecePlacer(141, PieceID.TRANSMISSION_NORTH));
        fighters.add(new PiecePlacer(414, PieceID.TRANSMISSIONMOUNTED_NORTH));
        fighters.add(new PiecePlacer(118, PieceID.ARTILLERY_NORTH));
        fighters.add(new PiecePlacer(454, PieceID.ARTILLERYMOUNTED_NORTH));
        int[] cavalrynorth = {76, 97, 496, 517};
        for (int i : cavalrynorth) { fighters.add(new PiecePlacer(i, PieceID.CAVALRY_NORTH)); }
        int[] infantrynorth = {96, 117, 138, 139, 225, 432, 453, 474, 475};
        for (int i : infantrynorth) { fighters.add(new PiecePlacer(i, PieceID.INFANTRY_NORTH)); }
        // south army
        fighters.add(new PiecePlacer(384, PieceID.TRANSMISSION_SOUTH));
        fighters.add(new PiecePlacer(152, PieceID.TRANSMISSIONMOUNTED_SOUTH));
        fighters.add(new PiecePlacer(70, PieceID.ARTILLERY_SOUTH));
        fighters.add(new PiecePlacer(211, PieceID.ARTILLERYMOUNTED_SOUTH));
        int[] cavalrysouth = {190, 191, 212, 232};
        for (int i : cavalrysouth) { fighters.add(new PiecePlacer(i, PieceID.CAVALRY_SOUTH)); }
        int[] infantrysouth = {71, 92, 174, 213, 233, 234, 324, 364, 489};
        for (int i : infantrysouth) { fighters.add(new PiecePlacer(i, PieceID.INFANTRY_SOUTH)); }
        Collections.sort(fighters);
        String turn = "north";
        return new BoardState(terrain, fighters, turn);
    }

    public static BoardState make1804Austerlitz() {
        // make Terrain ArrayList
        ArrayList<PiecePlacer> terrain = BoardState.getTerrain();
        terrain.add(new PiecePlacer(185, PieceID.ARSENAL_NORTH));
        terrain.add(new PiecePlacer(334, PieceID.ARSENAL_NORTH));
        terrain.add(new PiecePlacer(64, PieceID.ARSENAL_SOUTH));
        terrain.add(new PiecePlacer(484, PieceID.ARSENAL_SOUTH));
        Collections.sort(terrain);
        // make Armies ArrayList for Pump House
        ArrayList<PiecePlacer> fighters = new ArrayList<>();
        // north army
        fighters.add(new PiecePlacer(454, PieceID.TRANSMISSION_NORTH));
        fighters.add(new PiecePlacer(183, PieceID.TRANSMISSIONMOUNTED_NORTH));
        fighters.add(new PiecePlacer(181, PieceID.ARTILLERY_NORTH));
        fighters.add(new PiecePlacer(516, PieceID.ARTILLERYMOUNTED_NORTH));
        int[] cavalrynorth = {141, 494, 495, 515};
        for (int i : cavalrynorth) { fighters.add(new PiecePlacer(i, PieceID.CAVALRY_NORTH)); }
        int[] infantrynorth = {139, 161, 285, 306, 452, 453, 473, 474, 475};
        for (int i : infantrynorth) { fighters.add(new PiecePlacer(i, PieceID.INFANTRY_NORTH)); }
        // south army
        fighters.add(new PiecePlacer(69, PieceID.TRANSMISSION_SOUTH));
        fighters.add(new PiecePlacer(174, PieceID.TRANSMISSIONMOUNTED_SOUTH));
        fighters.add(new PiecePlacer(324, PieceID.ARTILLERY_SOUTH));
        fighters.add(new PiecePlacer(194, PieceID.ARTILLERYMOUNTED_SOUTH));
        int[] cavalrysouth = {91, 235, 236, 256};
        for (int i : cavalrysouth) { fighters.add(new PiecePlacer(i, PieceID.CAVALRY_SOUTH)); }
        int[] infantrysouth = {71, 92, 237, 257, 258, 281, 344, 489, 511};
        for (int i : infantrysouth) { fighters.add(new PiecePlacer(i, PieceID.INFANTRY_SOUTH)); }
        Collections.sort(fighters);
        String turn = "north";
        return new BoardState(terrain, fighters, turn);
    }

    public static BoardState makeDemoForWins() {
        // make Terrain ArrayList
        ArrayList<PiecePlacer> terrain = BoardState.getTerrain();
        terrain.add(new PiecePlacer(185, PieceID.ARSENAL_NORTH));
        terrain.add(new PiecePlacer(64, PieceID.ARSENAL_SOUTH));
        terrain.add(new PiecePlacer(484, PieceID.ARSENAL_SOUTH));

        ArrayList<PiecePlacer> listOfFighters = new ArrayList<>();
        // add North pieces
        listOfFighters.add(new PiecePlacer(164, PieceID.INFANTRY_NORTH));
        // add South pieces
        listOfFighters.add(new PiecePlacer(169, PieceID.TRANSMISSION_SOUTH));
        listOfFighters.add(new PiecePlacer(180, PieceID.CAVALRY_SOUTH));
        listOfFighters.add(new PiecePlacer(181, PieceID.CAVALRY_SOUTH));
        listOfFighters.add(new PiecePlacer(182, PieceID.CAVALRY_SOUTH));
        listOfFighters.add(new PiecePlacer(183, PieceID.CAVALRY_SOUTH));
        listOfFighters.add(new PiecePlacer(184, PieceID.INFANTRY_SOUTH));
        Collections.sort(listOfFighters);
        return new BoardState(terrain, listOfFighters, "south", 0, "");
    }

    public static BoardState makeDemoForRetreat() {
        // make Terrain ArrayList
        ArrayList<PiecePlacer> terrain = BoardState.getTerrain();
        terrain.add(new PiecePlacer(185, PieceID.ARSENAL_NORTH));
        terrain.add(new PiecePlacer(334, PieceID.ARSENAL_NORTH));
        terrain.add(new PiecePlacer(64, PieceID.ARSENAL_SOUTH));
        terrain.add(new PiecePlacer(484, PieceID.ARSENAL_SOUTH));

        ArrayList<PiecePlacer> listOfFighters = new ArrayList<>();
        // add North pieces
        listOfFighters.add(new PiecePlacer(290, PieceID.TRANSMISSION_NORTH));
        listOfFighters.add(new PiecePlacer(269, PieceID.INFANTRY_NORTH));
        // add South pieces
        listOfFighters.add(new PiecePlacer(268, PieceID.INFANTRY_SOUTH));
        listOfFighters.add(new PiecePlacer(289, PieceID.INFANTRY_SOUTH));
        listOfFighters.add(new PiecePlacer(247, PieceID.TRANSMISSION_SOUTH));
        listOfFighters.add(new PiecePlacer(244, PieceID.TRANSMISSIONMOUNTED_SOUTH));
        Collections.sort(listOfFighters);
        return new BoardState(terrain, listOfFighters, "south", 0, "");
    }

    public static BoardState makeDemoForNoRetreat() {
        // make Terrain ArrayList
        ArrayList<PiecePlacer> terrain = BoardState.getTerrain();
        terrain.add(new PiecePlacer(185, PieceID.ARSENAL_NORTH));
        terrain.add(new PiecePlacer(334, PieceID.ARSENAL_NORTH));
        terrain.add(new PiecePlacer(64, PieceID.ARSENAL_SOUTH));
        terrain.add(new PiecePlacer(484, PieceID.ARSENAL_SOUTH));
        ArrayList<PiecePlacer> listOfFighters = new ArrayList<>();
        // add North pieces
        int[] infantryN = {362, 363, 364, 365, 384};
        for (int i: infantryN) listOfFighters.add(new PiecePlacer(i, PieceID.INFANTRY_NORTH));
        listOfFighters.add(new PiecePlacer(323, PieceID.TRANSMISSION_NORTH));
        // add South pieces
        listOfFighters.add(new PiecePlacer(321, PieceID.TRANSMISSION_SOUTH));
        listOfFighters.add(new PiecePlacer(174, PieceID.TRANSMISSIONMOUNTED_SOUTH));
        listOfFighters.add(new PiecePlacer(342, PieceID.INFANTRY_SOUTH));
        listOfFighters.add(new PiecePlacer(320, PieceID.INFANTRY_SOUTH));
        return new BoardState(terrain, listOfFighters, "south", 0, "");
    }
}
