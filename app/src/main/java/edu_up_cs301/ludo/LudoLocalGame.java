package edu_up_cs301.ludo;

import edu_up_cs301.game.GamePlayer;
import edu_up_cs301.game.LocalGame;
import edu_up_cs301.game.actionMsg.GameAction;

/**
 * Created by Luke on 2/28/2018.
 *
 * A class that represents the state of our game available to the player.
 * Defines and enforces the game rules; handles interactions with players.
 *
 * @author Luke Danowski
 * @author Steven R. Vegdahl
 * @author Andrew M. Nuxoll
 * @version July 2013
 */

public class LudoLocalGame extends LocalGame {

    // the game's state
    private LudoState state;

    public LudoLocalGame() {
        state = new LudoState();
    }


    /**
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {

        //this will need to be edited to only send player-relevant data.
        //right now it makes a complete copy
        LudoState copy = new LudoState(state);
        p.sendInfo(copy);
    }

    /**
     * can this player make a move
     *
     * @param playerIdx the player's player-number (ID)
     * @return true if matches
     */
    @Override
    protected boolean canMove(int playerIdx) {
        return playerIdx == state.getWhoseMove();
    }

    /**
     * Check if the game is over
     *
     * @return a message that tells who has won the game, or null if the
     * game is not over
     */
    @Override
    protected String checkIfGameOver() {
        for(int i = 0 ; i < state.getNumPlayers() ; i++) {
            if(state.getPlayerScore(i) == 4){
                return "Congrats!" + playerNames[i] + " wins!!!";
            }
        }
        return null;
    }

    @Override
    protected boolean makeMove(GameAction action) {

        int playerID;
        if (canMove(playerID = getPlayerIdx(action.getPlayer()))) { //if able


            if (action instanceof ActionMoveToken) {

                //move forward, consider and react to landing on another piece
                return state.advanceToken(playerID, ((ActionMoveToken) action).getSpacesTraveled());

            } else if (action instanceof ActionRollDice) {

                state.newRoll();

                //only next players turn if isRollable boolean is false
                if (!state.getIsRollable()) {
                    state.tryNextPlayerActive();
                } else {
                    return true;
                }

            } else if (action instanceof ActionRemoveFromBase) {

                //toggle boolean to false
                state.pieces[((ActionRemoveFromBase) action).getIndex()].setIsHome(false);

            }

        }

        return false;

    }
}
