package playmanager;

import com.example.gameofwarapp.BoardState;
import com.example.gameofwarapp.PiecePlacer;

import java.util.ArrayList;

import ruleslogic.Board;
import ruleslogic.iBoard;

public class PlayManager implements iPlayManager {
    /**
     * Binds the PlayManager object to a specific entry in GameStates, for purposes of retrieving
     * the state to start from, and for writing new states.
     */
    private final String gameID;

    /**
     * A constructor to use until GameStates is implemented.
     */
    public PlayManager () {
        gameID = "0";
    }

    /**
     * Constructor to use once GameStates is implemented to ensure continuity while playing.
     * @param s is the gameID
     */
    public PlayManager (String s) {
        gameID = s;
    }

    /**
     * @inheritDoc
     */
    public ArrayList<String> getAvailableLayouts() {
        // for eventual use with GameStates
        return new ArrayList<String>();
    }

    /**
     * @inheritDoc
     */
    public String makeNewGame() {
        // for eventual use with GameStates
        return gameID;
    }

    /**
     * @inheritDoc
     */
    public String validateMove(BoardState startState, String[] moveCoords) {
        int[] locations = new int[16];
        // convert the strings into location integers or return error message
        try {
            for (int i = 0; i < moveCoords.length; i++){
                // skip the conversion if the field was left as "a0"
                if (moveCoords[i].equals("a0")) {
                    locations[i] = 0;
                    continue;
                }
                // convert the row letter to an int using ASCII
                String s = moveCoords[i].trim().toLowerCase();
                int row = s.charAt(0) - 96;
                if (row < 1 || row > 20) throw new IndexOutOfBoundsException();
                int col = Integer.parseInt(s.substring(1));
                if (col < 1 || col > 25) throw new IndexOutOfBoundsException();
                // then set the location as a linear int
                locations[i] = (col * 21) + row;
            }
        }
        catch (Exception ex) {
            return "location format error, please enter locations again, as eg. 'd10'";
        }

        // Make a Board, get the Board to do the remaining checks
        ArrayList<PiecePlacer> allPieces = new ArrayList<>();
        for (PiecePlacer pp: startState.terrain) allPieces.add(pp);
        allPieces.addAll(startState.fighters);
        iBoard b = new Board(allPieces, startState.turn, startState.locationMustMove);
        return b.validateMove(locations);
    }

    /**
     * @inheritDoc
     */
    public BoardState makeMove(BoardState startState, String[] moveCoords) {
        // call validateMove
        String validity = this.validateMove(startState, moveCoords);

        // if validateMove() != "valid" return the string it returned with the input boardstate
        if ( !(validity.equals("valid")) ) {
            startState.messageToUser = validity;
            return startState;
        }
        // since it's been validated, turn the string coords into ints
        int[] locations = new int[16];
        for (int i = 0; i < moveCoords.length; i++){
            // skip the conversion if the field was left as "a0"
            if (moveCoords[i].equals("a0")) {
                locations[i] = 0;
                continue;
            }
            // convert the row letter to an int using ASCII
            String s = moveCoords[i].trim().toLowerCase();
            int row = s.charAt(0) - 96;
            if (row < 1 || row > 20) throw new IndexOutOfBoundsException();
            int col = Integer.parseInt(s.substring(1));
            if (col < 1 || col > 25) throw new IndexOutOfBoundsException();
            // then set the location as a linear int
            locations[i] = (col * 21) + row;
        }

        iBoard bM = new Board(startState.toString());

        // some work delegated to processMove() in the Board, to carry out piece movement and
        // attack, returning a new string to convert into a BoardState
        String moveDone = bM.processMove(locations);

        if (moveDone.equals("oops, something went wrong")) {
            startState.messageToUser = moveDone + "\n" + startState.messageToUser;
            return startState;
        }

        BoardState newState = new BoardState(moveDone);
        // change turn
        newState.turn = (newState.turn.equals("north")) ? "south" : "north";

        return newState;
    }

}
