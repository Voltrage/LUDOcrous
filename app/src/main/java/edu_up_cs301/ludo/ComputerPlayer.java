package edu_up_cs301.ludo;

import android.util.Log;
import edu_up_cs301.game.GameComputerPlayer;
import edu_up_cs301.game.actionMsg.GameAction;
import edu_up_cs301.game.infoMsg.GameInfo;

/**
 * Created by Luke on 2/26/2018.
 *
 * @author Avery Guillermo
 * created and implemented by Avery Guillermo
 * This is the super dumb AI
 *
 * made it a bit smarter - luke
 *
 */

public class ComputerPlayer extends GameComputerPlayer {

    // to satisfy Serializable interface
    private static final long serialVersionUID = 578920931238746L;

    //instance variables

    /**
     * constructor
     *
     * @param name
     */
    public ComputerPlayer(String name){
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        // if it's not a LudoState message, ignore it; otherwise
        // cast it
        if (!(info instanceof LudoState)) return;
        LudoState myState = (LudoState)info;


        // if it's not our move, ignore it
        if (myState.getWhoseMove() != this.playerNum) {
            return;
        }
        else {

            // sleep to slow down the game
            sleep(400);

            //if we can use diceVal, make a move
            if(myState.getIsRollUsable()){

                //if makeMove method didn't take care of action already, then we have to choose
                    int index = -1;
                    //get index of my piece that has traveled the farthest, prioritize those out of home base
                    for (int i = playerNum; i < 16; i += 4) {
                        if (myState.pieces[i].getIsMovable()) {
                            index = index < 0 ? i :
                                    myState.pieces[i].getNumSpacesMoved() + (myState.pieces[i].getTokenState()!=0? 1 : 0)
                                    >
                                    myState.pieces[index].getNumSpacesMoved() + (myState.pieces[index].getTokenState()!=0? 1 : 0)?
                                            i : index;
                        }
                    }

//                    //needs to bring out of start
//                    if (myState.pieces[index].getTokenState() == 0) {
//                        game.sendAction(new ActionEnableToken(this, index));
//                        return;
//                    }
                    //moves piece already out

                    game.sendAction(new ActionMoveToken(this, index));

            }
            else if(myState.getIsRollable()) {
                Log.i("Computer Player: " + this.playerNum, "Rolling the dice");
                game.sendAction(new ActionRollDice(this));
            }

        }
    }

}
