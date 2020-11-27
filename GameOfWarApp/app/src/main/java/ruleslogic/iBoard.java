package ruleslogic;

import java.util.ArrayList;

/**
 * Interface defining the API for objects which will represent the state of play and carry out
 * checks against the rules of the game.
 */
public interface iBoard {
    /**
     * Check whether a move is valid given its current state
     * @param locations the array of locations that describe the move
     * @return "valid" or a message saying why not
     */
    String validateMove(int[] locations);

    /**
     * Return a string describing a new board state after the given move is carried out
     * @param locations the array of locations that describe the move
     * @return a String representation of the Board after the move is made
     */
    String processMove(int[] locations);

    /**
     * Get a list of the Terrain pieces on the board.
     * @return an ArrayList of all Terrain objects on the board
     */
    ArrayList<Terrain> getTerrains();

    /**
     * Get the object representing the North army.
     * @return an Army object which contains all of North's Fighters.
     */
    Board.Army getNorthArmy();

    /**
     * Get the object representing the South army.
     * @return an Army object which contains all of South's Fighters.
     */
    Board.Army getSouthArmy();

    /**
     * Get the String which indicates who's turn it is.
     * @return either "north" or "south"
     */
    String getTurn();

    /**
     * Get the integer representation of the location which must move first, or 0.
     * @return 0 or 21 < i < 546.
     */
    int getLocationMustMove();
}
