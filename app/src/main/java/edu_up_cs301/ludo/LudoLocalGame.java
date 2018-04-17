package edu_up_cs301.ludo;

import android.util.Log;
import edu_up_cs301.game.GamePlayer;
import edu_up_cs301.game.LocalGame;
import edu_up_cs301.game.actionMsg.GameAction;

import static java.lang.Thread.sleep;

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

    /**
     * Handles what to do in response to the GameAction sent
     *
     * RollDice  -> no moves -> next player
     *            -> 1 move -> do the move (handles next player)
     *
     * MoveToken -> do the move (handles next player)
     *
     * EnableToken -> do the move
     *
     * @param action
     * 			The move that the player has sent to the game
     * @return
     */

    @Override
    protected boolean makeMove(GameAction action) {
//
//        int playerID;
//        if (canMove(playerID = getPlayerIdx(action.getPlayer()))) { //if able
//
//
//            if (action instanceof ActionMoveToken) {
//
//                //move forward, consider and react to landing on another piece
//                return state.advanceToken(playerID, ((ActionMoveToken) action).getSpacesTraveled());
//
//            } else if (action instanceof ActionRollDice) {
//
//                state.newRoll();
//
//                //only next players turn if isRollable boolean is false
//                if (!state.getIsRollable()) {
//                    state.tryNextPlayerActive();
//                } else {
//                    return true;
//                }
//
//            } else if (action instanceof ActionEnableToken) {
//
//                //toggle boolean to false
//                state.pieces[((ActionEnableToken) action).getIndex()].setIsHome(false);
//
//            }
//
//        }
//
//        //do nothing since the move was not valid!
//        return false;

        int playerID ;
        //if its the person's turn and they are trying to make a move
        if (canMove(playerID = getPlayerIdx(action.getPlayer()))) {

            if (action instanceof ActionRollDice && state.getIsRollable()) {


                //main function is to roll the dice, then deal with how many moves are possible differently
                switch(state.newRoll()){
                    case 0:
                        Log.i("Playable", " nothing available with a");
                        state.changePlayerTurn();
                        break;
                    case 1: //only one move available,
                        //find the only one that isMovable
                        for (int i = playerID; i < 16; i += 4) {
                            if (state.pieces[i].getIsMovable()) {
                                //secondary method function, handle move it only one move is possible
                                action = new ActionMoveToken(action.getPlayer(), i);
//                                    state.advanceToken(playerID,i);
                                break;
                            }
                        }
                    default:
                }

                Log.i("Dice", " rolled " + state.getDiceVal());

//                //update SurfaceView
//                if(action.getPlayer() instanceof HumanPlayer){
//                    ((HumanPlayer) action.getPlayer()).getTopView().invalidate();
//                    try {
//                        sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }

//
//                    //if the player did not roll a six but can move a single piece
//                    if (state.getDiceVal() != 6 && state.getNumMovableTokens(playerID) == 1) {
//                        state.setIsRollable(false);
//                        int index = state.getTokenIndexOfFirstPieceOutOfStart(playerID);
//                        state.advanceToken(playerID, index);
//                        state.tryNextPlayerActive();
//                        return true;
//                    }
//                    //if the player did not roll a six but can move multiple pieces
//                    if (state.getDiceVal() != 6 && state.getNumMovableTokens(playerID) > 1) {
//                        state.setIsRollable(false);
//                        return true;
//                    }
//
//
//                } else {
//                    //no moves available
//                    state.tryNextPlayerActive();
//                    return true;
//                }
            }


            //moves piece selected
            if (  action instanceof ActionMoveToken  && state.getNumMovableTokens(playerID) > 0) {
                //move forward, consider and react to landing on another piece
              return state.advanceToken(playerID, ((ActionMoveToken) action).getIndex());
            }

            //if made it this far, nothing has caused it to return false, so it probably rolled the dice and was fine
            return true;

        }
        //was true
        return false; // do nothing since the move was not valid!

    }


}
