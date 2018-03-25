package edu_up_cs301.ludo;

import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
 * TODO: everybody needs access to pieces, but only a copy of them,
 */

public class HumanPlayer extends GameHumanPlayer implements View.OnClickListener, View.OnTouchListener {

    // to satisfy Serializable interface
    private static final long serialVersionUID = 3548793282648392873L;


    LudoSurfaceView surfaceView;

    private EditText testString;
    private Button runTest;

    // most recent state, an appropriately filled view of the game as given to us from LudoLocalGame
    private LudoState state;

    // the android activity that we are running
    private GameMainActivity myActivity;


    private int[][] path;


    /**
     * constructor
     *
     * @param name of player
     */
    public HumanPlayer(String name) {
        super(name);
//        generalInit(color);
    }

    private void generalInit(String color) {
        switch (color) {
            case "red":
                path = new int[][]{{1, 6}, {2, 6},
                        {3, 6}, {4, 6}, {5, 6}, {6, 5}, {6, 4}, {6, 3},
                        {6, 2}, {6, 1}, {6, 0}, {7, 0}, {8, 0},
                        {8, 1}, {8, 2}, {8, 3}, {8, 4}, {8, 5},
                        {9, 6}, {10, 6}, {11, 6}, {12, 6}, {13, 6}, {14, 6},
                        {14, 7}, {14, 8}, {13, 8}, {12, 8}, {11, 8}, {10, 8},
                        {9, 8}, {8, 9}, {8, 10}, {8, 11}, {8, 12}, {8, 13},
                        {8, 14}, {7, 14}, {6, 14}, {6, 13}, {6, 12}, {6, 11},
                        {6, 10}, {6, 9}, {5, 8}, {4, 8}, {3, 8}, {2, 8},
                        {1, 8}, {0, 8}, {0, 7}, {1, 7}, {2, 7}, {3, 7}, {4, 7}, {5, 7}, {6, 7}};
                break;
            case "green":
                path = new int[][]{{8, 1}, {8, 2}, {8, 3}, {8, 4}, {8, 5},
                        {9, 6}, {10, 6}, {11, 6}, {12, 6}, {13, 6}, {14, 6},
                        {14, 7}, {14, 8}, {13, 8}, {12, 8}, {11, 8}, {10, 8},
                        {9, 8}, {8, 9}, {8, 10}, {8, 11}, {8, 12}, {8, 13},
                        {8, 14}, {7, 14}, {6, 14}, {6, 13}, {6, 12}, {6, 11},
                        {6, 10}, {6, 9}, {5, 8}, {4, 8}, {3, 8}, {2, 8},
                        {1, 8}, {0, 8}, {0, 7}, {0, 6}, {1, 6}, {2, 6},
                        {3, 6}, {4, 6}, {5, 6}, {6, 5}, {6, 4}, {6, 3},
                        {6, 2}, {6, 1}, {6, 0}, {7, 0}, {7, 1}, {7, 2},
                        {7, 3}, {7, 4}, {7, 5}, {7, 6}};
                break;
            case "blue":
                path = new int[][]{{6, 13}, {6, 12}, {6, 11},
                        {6, 10}, {6, 9}, {5, 8}, {4, 8}, {3, 8}, {2, 8},
                        {1, 8}, {0, 8}, {0, 7}, {0, 6}, {1, 6}, {2, 6},
                        {3, 6}, {4, 6}, {5, 6}, {6, 5}, {6, 4}, {6, 3},
                        {6, 2}, {6, 1}, {6, 0}, {7, 0}, {8, 0},
                        {8, 1}, {8, 2}, {8, 3}, {8, 4}, {8, 5},
                        {9, 6}, {10, 6}, {11, 6}, {12, 6}, {13, 6}, {14, 6},
                        {14, 7}, {14, 8}, {13, 8}, {12, 8}, {11, 8}, {10, 8},
                        {9, 8}, {8, 9}, {8, 10}, {8, 11}, {8, 12}, {8, 13},
                        {8, 14}, {7, 14}, {7, 13}, {7, 12}, {7, 11}, {7, 10}, {7, 9}, {7, 8}};
                break;
            case "yellow":
                path = new int[][]{{13, 8}, {12, 8}, {11, 8}, {10, 8},
                        {9, 8}, {8, 9}, {8, 10}, {8, 11}, {8, 12}, {8, 13},
                        {8, 14}, {7, 14}, {6, 14}, {6, 13}, {6, 12}, {6, 11},
                        {6, 10}, {6, 9}, {5, 8}, {4, 8}, {3, 8}, {2, 8},
                        {1, 8}, {0, 8}, {0, 7}, {0, 6}, {1, 6}, {2, 6},
                        {3, 6}, {4, 6}, {5, 6}, {6, 5}, {6, 4}, {6, 3},
                        {6, 2}, {6, 1}, {6, 0}, {7, 0}, {8, 0},
                        {8, 1}, {8, 2}, {8, 3}, {8, 4}, {8, 5},
                        {9, 6}, {10, 6}, {11, 6}, {12, 6}, {13, 6}, {14, 6},
                        {14, 7}, {13, 7}, {12, 7}, {11, 7}, {10, 7}, {9, 7}, {8, 7}};
                break;
        }
    }

    /**
     * Sets this player as the one attached to the GUI. Saves the
     * activity, links listeners to IO to invoke specific methods.
     */
    @Override
    public void setAsGui(GameMainActivity activity) {
        //remember the activity
        myActivity = activity;

//        activity.setContentView(R.layout.test);
        activity.setContentView(R.layout.ludo_game_view);

        //find references
        surfaceView = (LudoSurfaceView) activity.findViewById(R.id.board_canvas);
//        runTest     = (Button) activity.findViewById(R.id.test_button);
//        testString  = (EditText) activity.findViewById(R.id.TestString);

        //attach listener
//        runTest.setOnClickListener(this);
        surfaceView.setOnTouchListener(this);



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
        }
        else if (!(info instanceof LudoState))
            // if we do not have a TTTState, ignore
            return;
        else {
//            surfaceView.setState((LudoState)info);
            state = (LudoState)info;
            surfaceView.invalidate();
            Log.i("human player", "receiving");
        }

        if(info instanceof NotYourTurnInfo){
            this.flash(Color.RED, 50);
        }


    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float cell_row = event.getX();
        float cell_col = event.getY();

        //TODO: turn into valid row and col values for board, also if within SurfaceView
        int row = 0;
        int col = 0;

        int index = 0;

        //create instance of gameAction
        GameAction action = null;

        switch(v.getId()){
            case R.id.board_canvas: // if surfaceView is pressed, tell the game
                state.newRoll();
                action = new ActionMoveToken(this, index);
                game.sendAction(action);
                return true;
        }



        return false;
    }


    @Override
    public void onClick(View v) {

        //create instance of gameAction associated with button pressed
        GameAction action = null;


        if(v.getId() ==  runTest.getId()){

            LudoState firstInstance = new LudoState();

            LudoState secondInstance = new LudoState(firstInstance);


            testString.setText("");

            while (! firstInstance.newRoll()){
                testString.setText(testString.getText() + "Player " + firstInstance.getWhoseMove() + " rolled a " + firstInstance.getDiceVal() + "\n");
                firstInstance.tryNextPlayerActive();
            }
            firstInstance.advanceToken(firstInstance.getWhoseMove(), 0);

            if(! firstInstance.pieces[firstInstance.getWhoseMove()].getIsHome()) {
                String str = "Player " + firstInstance.getWhoseMove() + " rolled a " + firstInstance.getDiceVal() + " and piece 0 is now in play. ";
                firstInstance.tryNextPlayerActive();
                firstInstance.newRoll();
                firstInstance.advanceToken(firstInstance.getWhoseMove(), 0);
                str += "\nPlayer " + firstInstance.getWhoseMove() + " rolled a " + firstInstance.getDiceVal() + " and piece 0 is now at " + firstInstance.getDiceVal()+ ".";
                testString.setText(testString.getText() + str);
            }

            LudoState thirdInstance = new LudoState();

            LudoState fourthInstance = new LudoState(thirdInstance);

            testString.setText(testString.getText() + "\n\nFirstInstance\n" + firstInstance.toString() + "\n\nSecondInstance\n" + secondInstance.toString() + "\n\nFourthInstance\n" + fourthInstance.toString());

        }

    }

}
