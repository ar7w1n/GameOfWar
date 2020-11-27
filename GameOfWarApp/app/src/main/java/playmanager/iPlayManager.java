package playmanager;

import com.example.gameofwarapp.BoardState;

import java.util.ArrayList;

/**
 * Specifies the API for the back end's mediator/interface object.
 */
public interface iPlayManager {
    /**
     * For eventual use with a GameStates module, not yet implemented
     * @return a list of the states which can be loaded to play
     */
    ArrayList<String> getAvailableLayouts();

    /**
     * For eventual use with a GameStates module, not yet implemented.
     * Should create a new state within the GameStates module and return its ID
     * @return a gameID
     */
    String makeNewGame();

    /**
     * Checks whether move data is valid for a given BoardState
     * @param startState the BoardState on which the move proposes to operate
     * @param moveCoords the locations of pieces to move, where to move to, and optional
     *                  attack location
     * @return either "valid" if the move is, or an error message describing the problem
     */
    String validateMove(BoardState startState, String[] moveCoords);

    /**
     * Returns a new BoardState based on a starting state and the given move data.
     * @param startState the BoardState before the move is carried out
     * @param moveCoords the locations of pieces to move, where to move to, and optional
     *                  attack location
     * @return the BoardState which results from carrying out the move on the given start state
     */
    BoardState makeMove(BoardState startState, String[] moveCoords);

}
