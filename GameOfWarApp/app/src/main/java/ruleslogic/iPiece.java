package ruleslogic;

/**
 * Interface to propagate location information to all objects on a Board.  The 20 x 25 Board is given an unreferenced edge row plus A to T, and an unreferenced edge column plus 1 to 25, making it 21 x 26 in data representation holding values 0 to 545.  The actual squares on the board, between A1 and T25, are given by the integer `n`, where `22 <= n <= 545`, and `n mod 21 != 0`.  The unreferenced column (0 through 20) means that a default location of 0 (or a0 in move collection) can be used instead of having to treat null values.  The unreference row (corresponding to `n mod 21 = 0`) means that wrapping around the board (i.e. jumping from the top of one row on the board to the bottom of the next row, or vice versa) is easily made impossible as any value in that row (n mod 21 = 0) is an invalid position.
 */
public interface iPiece {
    /**
     * Method which will return an integer representation of a location on the board for a given
     * piece.
     *
     * The 20 x 25 Board is given an unreferenced edge row plus A to T, and an unreferenced edge
     * column plus 1 to 25, making it 21 x 26 in data representation holding values 0 to 545.  The
     * actual squares on the board, between A1 and T25, are given by the integer `n`, where
     * `22 <= n <= 545`, and `n mod 21 != 0`.  The unreferenced column (0 through 20) means that
     * a default location of 0 (or a0 in move collection) can be used instead of having to treat
     * null values.  The unreference row (corresponding to `n mod 21 = 0`) means that wrapping
     * around the board (i.e. jumping from the top of one row on the board to the bottom of the
     * next row, or vice versa) is easily made impossible as any value in that row (n mod 21 = 0)
     * is an invalid position.
     */
    int getLocation();
}
