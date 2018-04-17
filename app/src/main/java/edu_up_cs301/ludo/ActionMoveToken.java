package edu_up_cs301.ludo;

import edu_up_cs301.game.GamePlayer;
import edu_up_cs301.game.actionMsg.GameAction;

/**
 * Created by Luke on 2/28/2018.
 *
 * @author Luke Danowski
 * @author Avery Guillermo
 *
 */

public class ActionMoveToken extends GameAction {

    private int index;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     * @param index index of piece in the array in LudoState
     */
    public ActionMoveToken(GamePlayer player, int index){
        super(player);
        this.index = index;
    }


    /**
     * getter method for index of piece
     * @return the index of piece in the array in LudoState
     */
    public int getIndex() {
        return this.index;
    }
}
