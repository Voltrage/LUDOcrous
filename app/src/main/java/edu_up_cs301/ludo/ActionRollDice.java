package edu_up_cs301.ludo;

import edu_up_cs301.game.GamePlayer;
import edu_up_cs301.game.actionMsg.GameAction;

/**
 * Created by Luke on 2/28/2018.
 */

public class ActionRollDice extends GameAction {



    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public ActionRollDice(GamePlayer player) {
        super(player);
    }
}
