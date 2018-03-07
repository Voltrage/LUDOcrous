package edu_up_cs301.ludo;

import edu_up_cs301.game.GamePlayer;
import edu_up_cs301.game.actionMsg.GameAction;

/**
 * Created by Luke on 2/28/2018.
 */

public class ActionMoveToken extends GameAction {

    private int spacesTraveled;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     * @param numSpacesTraveled number of counter
     */
    public ActionMoveToken(GamePlayer player, int numSpacesTraveled){
        super(player);
        spacesTraveled = numSpacesTraveled;
    }


    /**
     * getter method for spacesTravled
     * @return number of counter
     */
    public int getSpacesTraveled() {
        return spacesTraveled;
    }
}
