package edu_up_cs301.ludo;

import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import edu_up_cs301.game.GameHumanPlayer;
import edu_up_cs301.game.GameMainActivity;
import edu_up_cs301.game.R;
import edu_up_cs301.game.actionMsg.GameAction;
import edu_up_cs301.game.infoMsg.GameInfo;
import edu_up_cs301.game.infoMsg.IllegalMoveInfo;
import edu_up_cs301.game.infoMsg.NotYourTurnInfo;

/**
 * contains gui
 * Created by Luke on 2/26/2018.
 *
 * @author Luke Danowski
 * @author Avery Guillermo--> Edited on March 27, 2018
 *
 * TODO: everybody needs access to pieces, but only a copy of them,
 */

public class HumanPlayer extends GameHumanPlayer implements View.OnClickListener, View.OnTouchListener {

    // to satisfy Serializable interface
    private static final long serialVersionUID = 3548793282648392873L;

    private LudoSurfaceView surfaceView;
    private Button rollDiceButton;

    // most recent state, an appropriately filled view of the game as given to us from LudoLocalGame

    private LudoState state;

    // the android activity that we are running
    private GameMainActivity myActivity;

//
//    private final int [][] Rpath = new int[][]{{1, 6}, {2, 6},
//        {3, 6}, {4, 6}, {5, 6}, {6, 5}, {6, 4}, {6, 3},
//        {6, 2}, {6, 1}, {6, 0}, {7, 0}, {8, 0},
//        {8, 1}, {8, 2}, {8, 3}, {8, 4}, {8, 5},
//        {9, 6}, {10, 6}, {11, 6}, {12, 6}, {13, 6}, {14, 6},
//        {14, 7}, {14, 8}, {13, 8}, {12, 8}, {11, 8}, {10, 8},
//        {9, 8}, {8, 9}, {8, 10}, {8, 11}, {8, 12}, {8, 13},
//        {8, 14}, {7, 14}, {6, 14}, {6, 13}, {6, 12}, {6, 11},
//        {6, 10}, {6, 9}, {5, 8}, {4, 8}, {3, 8}, {2, 8},
//        {1, 8}, {0, 8}, {0, 7}, {1, 7}, {2, 7}, {3, 7}, {4, 7}, {5, 7}, {6, 7}};
//
//    private final int[][] Gpath = new int[][]{{8, 1}, {8, 2}, {8, 3}, {8, 4}, {8, 5},
//        {9, 6}, {10, 6}, {11, 6}, {12, 6}, {13, 6}, {14, 6},
//        {14, 7}, {14, 8}, {13, 8}, {12, 8}, {11, 8}, {10, 8},
//        {9, 8}, {8, 9}, {8, 10}, {8, 11}, {8, 12}, {8, 13},
//        {8, 14}, {7, 14}, {6, 14}, {6, 13}, {6, 12}, {6, 11},
//        {6, 10}, {6, 9}, {5, 8}, {4, 8}, {3, 8}, {2, 8},
//        {1, 8}, {0, 8}, {0, 7}, {0, 6}, {1, 6}, {2, 6},
//        {3, 6}, {4, 6}, {5, 6}, {6, 5}, {6, 4}, {6, 3},
//        {6, 2}, {6, 1}, {6, 0}, {7, 0}, {7, 1}, {7, 2},
//        {7, 3}, {7, 4}, {7, 5}, {7, 6}};
//
//  private final int[][] Ypath = new int[][]{{13, 8}, {12, 8}, {11, 8}, {10, 8},
//        {9, 8}, {8, 9}, {8, 10}, {8, 11}, {8, 12}, {8, 13},
//        {8, 14}, {7, 14}, {6, 14}, {6, 13}, {6, 12}, {6, 11},
//        {6, 10}, {6, 9}, {5, 8}, {4, 8}, {3, 8}, {2, 8},
//        {1, 8}, {0, 8}, {0, 7}, {0, 6}, {1, 6}, {2, 6},
//        {3, 6}, {4, 6}, {5, 6}, {6, 5}, {6, 4}, {6, 3},
//        {6, 2}, {6, 1}, {6, 0}, {7, 0}, {8, 0},
//        {8, 1}, {8, 2}, {8, 3}, {8, 4}, {8, 5},
//        {9, 6}, {10, 6}, {11, 6}, {12, 6}, {13, 6}, {14, 6},
//        {14, 7}, {13, 7}, {12, 7}, {11, 7}, {10, 7}, {9, 7}, {8, 7}};
//
//    private final int[][] Bpath = new int[][]{{6, 13}, {6, 12}, {6, 11},
//        {6, 10}, {6, 9}, {5, 8}, {4, 8}, {3, 8}, {2, 8},
//        {1, 8}, {0, 8}, {0, 7}, {0, 6}, {1, 6}, {2, 6},
//        {3, 6}, {4, 6}, {5, 6}, {6, 5}, {6, 4}, {6, 3},
//        {6, 2}, {6, 1}, {6, 0}, {7, 0}, {8, 0},
//        {8, 1}, {8, 2}, {8, 3}, {8, 4}, {8, 5},
//        {9, 6}, {10, 6}, {11, 6}, {12, 6}, {13, 6}, {14, 6},
//        {14, 7}, {14, 8}, {13, 8}, {12, 8}, {11, 8}, {10, 8},
//        {9, 8}, {8, 9}, {8, 10}, {8, 11}, {8, 12}, {8, 13},
//        {8, 14}, {7, 14}, {7, 13}, {7, 12}, {7, 11}, {7, 10}, {7, 9}, {7, 8}};
//


    /**
     * constructor
     *
     * @param name of player
     */
    public HumanPlayer(String name) {
        super(name);
    }


    /**
     * Sets this player as the one attached to the GUI. Saves the
     * activity, links listeners to IO to invoke specific methods.
     */
    @Override
    public void setAsGui(GameMainActivity activity) {
        //remember the activity
        myActivity = activity;

        activity.setContentView(R.layout.ludo_game_view);

        //find references
        surfaceView = (LudoSurfaceView) activity.findViewById(R.id.board_canvas);
        rollDiceButton = (Button) activity.findViewById(R.id.button_Roll);
        //set the listeners
        surfaceView.setOnTouchListener(this);
        rollDiceButton.setOnClickListener(this);

    }

    @Override
    public View getTopView() {
        return myActivity.findViewById(R.id.top_gui_layout);
    }


    /**
     * Callback method, called when player gets a message
     *
     * @param info
     * 		the message
     */
    @Override
    public void receiveInfo(GameInfo info) {

        if (surfaceView == null) return;

        if (info instanceof IllegalMoveInfo || info instanceof NotYourTurnInfo) {
            // if the move was out of turn or otherwise illegal, flash the screen
            surfaceView.flash(Color.RED, 50);
            Log.i("human player", "illegal or not your turn");
        }
        else if (!(info instanceof LudoState))
            // if we do not have a LudoState, ignore
            return;
        else {
            state = (LudoState)info;
            surfaceView.setState((LudoState)info);
            surfaceView.invalidate();
            Log.i("human player", "receiving");

            if(state.getWhoseMove()==playerNum){
                rollDiceButton.setAlpha(1f);
                rollDiceButton.setClickable(true);
            } else {
                rollDiceButton.setAlpha(0.4f);
                rollDiceButton.setClickable(false);
            }


        }

    }

    /**
     * Method that reacts according to where the board was touched
     *
     * @author Luke
     * @author Avery
     *
     * @param v
     * @param event
     * @return
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //make sure that the thing that was touched was the surface view
        if(v.getId() == surfaceView.getId()) {

            //for each of players piece
            int p = playerNum;
            GameAction action;

            for (int i = p; i < 16; i += 4) {
                switch (state.pieces[i].getTokenState()) {
                    case 0: //is starting in home base
                        if (surfaceView.getStartPositions(p, i / 4).contains(event.getX(), event.getY())) {
                            action = new ActionEnableToken(this, i);
                            game.sendAction(action);
                            return true;
                        }
                        break;
                    case 1: //is en route
                        int indexTok = state.pieces[i].getAdjustedNumSpacesMoved();
                        if (surfaceView.getBoardPositions(indexTok).contains(event.getX(), event.getY())) {
                            action = new ActionMoveToken(this, i);
                            game.sendAction(action);
                            return true;
                        }
                        break;
                    case 2: //is in home stretch
                        if (surfaceView.getHomeStretchPositions(p, state.pieces[i].getNumSpacesMoved()-51).contains(event.getX(), event.getY())) {
                            action = new ActionMoveToken(this, i);
                            game.sendAction(action);
                            return true;
                        }
                        break;
                    default:
                }
            }

            //invalid touch

        }
        //nothing valid
        return false;
    }


    @Override
    public void onClick(View v) {

        //create instance of gameAction associated with button pressed
        GameAction action;

        if(v.getId() == rollDiceButton.getId()) {
            action = new ActionRollDice(this);
            Log.i("Onclick", "Human Player Rolling Dice");
            game.sendAction(action);
        }
    }

}

