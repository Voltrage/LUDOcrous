package edu_up_cs301.ludo;

import edu_up_cs301.game.GameComputerPlayer;
import edu_up_cs301.game.infoMsg.GameInfo;
import edu_up_cs301.game.infoMsg.NotYourTurnInfo;

/**
 * Created by Luke on 2/26/2018.
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

        if (info instanceof NotYourTurnInfo) { return; }


            if(info instanceof LudoState){



        }

    }

}
