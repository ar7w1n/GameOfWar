package com.example.gameofwarapp;

import org.junit.Test;

import static org.junit.Assert.*;

public class BoardStateTest {

    @Test
    public void hasFighterAt_responds_correctly() {
        // finds a fighter that exists
        BoardState bS = StockStates.makePumpHouse();
        assertTrue(bS.hasFighterAt(158));

        // deals correctly with empty space
        assertFalse(bS.hasFighterAt(157));

        // doesn't mistake Arsenal for Fighter
        assertFalse(bS.hasFighterAt(64));
    }

    @Test
    public void hasFighterAt_responds_correctly_using_String_constructor() {
        // finds a fighter that exists
        BoardState bS = new BoardState(" 64 10 71 2 185 3 187 2 222 0 223 0 224 0 225 1 " +
                "226 0 227 0 228 0 238 0 249 0 259 0 270 0 280 0 285 2 291 0 301 0 322 0 324 2 " +
                "334 3 339 0 340 0 341 0 342 1 343 0 454 2 484 10 489 2//97 5 116 7 117 7 118 7 " +
                "138 9 139 7 158 6 160 6 179 6 180 6 200 6 202 6 225 6 245 4 246 8 247 6 266 6 " +
                "342 13 364 13 384 11 385 15 405 13 406 13 426 13 427 13 446 13 447 13 448 16 " +
                "449 14 469 14 470 14 489 13 490 12 491 14//north//0//");

        assertTrue(bS.hasFighterAt(158));

        // deals correctly with empty space
        assertFalse(bS.hasFighterAt(157));

        // doesn't mistake Arsenal for Fighter
        assertFalse(bS.hasFighterAt(64));
    }

    @Test
    public void toString_on_PumpHouse() {
        BoardState bState = StockStates.makePumpHouse();
        assertEquals("64 10 71 2 185 3 187 2 222 0 223 0 224 0 225 1 " +
                "226 0 227 0 228 0 238 0 249 0 259 0 270 0 280 0 285 2 291 0 301 0 322 0 324 2 " +
                "334 3 339 0 340 0 341 0 342 1 343 0 454 2 484 10 489 2//97 5 116 7 117 7 118 7 " +
                "138 9 139 7 158 6 160 6 179 6 180 6 200 6 202 6 225 6 245 4 246 8 247 6 266 6 " +
                "342 13 364 13 384 11 385 15 405 13 406 13 426 13 427 13 446 13 447 13 448 16 " +
                "449 14 469 14 470 14 489 13 490 12 491 14//north//0//", bState.toString() );
    }

}