package edu_up_cs301.ludo;

import edu_up_cs301.game.GamePlayer;
import edu_up_cs301.game.actionMsg.GameAction;

/**
 * Created by Luke on 3/4/2018.
 *
 *
 */

public class ActionRemoveFromBase extends GameAction {

    int index;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public ActionRemoveFromBase(GamePlayer player, int index) {
        super(player);
        this.index = index;
    }

    public int getIndex(){ return index; }


}
