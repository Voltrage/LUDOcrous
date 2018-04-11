package edu_up_cs301.ludo;

import java.util.Random;

import android.util.Log;
import edu_up_cs301.game.infoMsg.GameState;

/**
 * This contains the official state for the Ludo Game.
 * <p>
 * Created by Luke on 2/26/2018
 *
 * based on GameFramework by Steven R. Vegdahl
 * CounterGame
 * TickTackToe2
 * PigGame - implemented by us in lab
 *
 * @author Luke Danowski
 */

public class LudoState extends GameState {

    // to satisfy Serializable interface
    private static final long serialVersionUID = 1433248382648392873L;
    //instance variables
    private Random dice;
    private int diceVal;
    private boolean isRollable;
    Token[] pieces;
    private int numPlayers;
    private int playerID_active;
    private int[] playerScore;
    private boolean isRollUsable;

    /**
     * constructor, initializing from parameter
     * <p>
     * the value to which the counter's value should be initialized
     */
    public LudoState() {
        playerID_active = 0;
        numPlayers = 4;
        dice = new Random();
        diceVal = 1;
        isRollable = true;
        pieces = new Token[]{
                new Token(0),
                new Token(1),
                new Token(2),
                new Token(3),
                new Token(0),
                new Token(1),
                new Token(2),
                new Token(3),
                new Token(0),
                new Token(1),
                new Token(2),
                new Token(3),
                new Token(0),
                new Token(1),
                new Token(2),
                new Token(3)};

        //scores are 0;
        playerScore = new int[]{0, 0, 0, 0};
    }


    /**
     * copy constructor; makes a copy of the original object
     *
     * @param original the object from which the copy should be made
     */
    public LudoState(LudoState original) {
        this.numPlayers = original.numPlayers;
        this.playerID_active = original.playerID_active;
        this.dice = original.dice;
        this.diceVal = original.diceVal;
        this.isRollable = original.isRollable;
        this.isRollUsable = original.isRollUsable;

        //set the piece array
        this.pieces = new Token[original.pieces.length];
        for( int i=0; i<original.pieces.length; i++) {
            pieces[i] = new Token(original.pieces[i]);
        }

        //set the scores
        this.playerScore = new int[original.playerScore.length];
        for(int i=0; i<original.playerScore.length; i++){
            this.playerScore[i] = original.playerScore[i];
        }

    }

    /**
     * Tells whose move it is.
     *
     * @return the index (0 or 1) of the player whose move it is.
     */
    public int getWhoseMove() {
        return playerID_active;
    }


    /**
     * getter method for score
     * @return score array
     */
    public int getPlayerScore(int index){
        return playerScore[index];
    }

    /**
     * getter method for numPlayers
     * @return number of active players
     */
    public int getNumPlayers(){
        return numPlayers;
    }

    /**
     * generates a new random value between 1 and 6 for the active player,
     * if roll a 6, they get to roll again so keep isRollable true
     * only called if it was that players turn.
     *
     * @return number of moves possible
     */
    public int newRoll() {
        if (isRollable && !isRollUsable){

            //handle the case of rerolls on a 6 with a switch
            switch (diceVal = dice.nextInt(6) + 1) {
                case 6:
                    isRollable = false; //so it stays true;
                default:
                    isRollable = !isRollable;
            }
        }
        int result = updateAndCountMovesAvailable();
        //if at least one move possible, then true
        isRollUsable = result>0 ? true : false;

        return result;
    }

    /**
     * Called after a dice roll. We go through the active player's pieces and checks for pieces
     * which can move by the rules of the game, each piece has a boolean which is changed to
     * reflect the outcome of the check.
     * @return number of moves possible
     */
    private int updateAndCountMovesAvailable() {

        //checks if any moves available;
        int count = 0;

        //set movable boolean
        for (int i = playerID_active; i < 16; i += 4) {

            switch (pieces[i].getTokenState()) {
                case 0: //in home base
                    if (diceVal == 6) {
                        pieces[i].setIsMovable(true);
                        count++;
                    } else {
                        pieces[i].setIsMovable(false);
                    }
                    break;
                case 1: //en route
                    pieces[i].setIsMovable(true);
                    count++;
                    break;
                case 2: //in home stretch
                    if (pieces[i].getNumSpacesMoved() + diceVal < 58) {
                        //in home stretch and total will be less than or equal to 57
                        pieces[i].setIsMovable(true);
                        count++;
                    }
                default:
                    pieces[i].setIsMovable(false);
            }
        }
        return count;
    }

    /**
     * getter method for dice value
     *
     * @return value of dice
     */
    public int getDiceVal() {
        return diceVal;
    }

    /**
     * getter method for roll boolean
     * @return true if allowed to make roll
     */
    public boolean getIsRollable() {
        return isRollable;
    }


    /**
     * increments player score
     */
    public void incPlayerScore() {
        playerScore[playerID_active]++;
    }

    /**
     * Fixed By Avery Guillermo!
     *
     * returns the number of pieces which are in play
     //     * @param playerID
     * @return
     */
    public int getNumMovableTokens(int playerID) {
        int count = 0;
        for (int i = playerID; i < 16; i+=4) {
            if (pieces[i].getIsMovable()){
                count++;
            }
        }
        return count;
    }

    /**
     * Increments the current player if they have no more rolls
     * @return true if incremented to next player
     */
    public boolean tryNextPlayerActive() {

        if(isRollable) {
            return false;
        } //no more rolls
        else {
            changePlayerTurn();
            return true;
        }
    }

    public void changePlayerTurn(){
        if ((++playerID_active) >= numPlayers) {
            playerID_active = 0;
        }
        this.isRollable = true;
    }

    public void setIsRollable(boolean bol){
        this.isRollable = bol;
    }


    /** @author Luke
     *
     * @param playerID
     * @param index of piece in its array
     * @return was move made
     */
    public boolean advanceToken(int playerID, int index) {

        //only act on movable pieces. Always updated after each dice roll.
        if(pieces[index].getIsMovable() && isRollUsable) {

            //used to implement player turn rules
            boolean change=true;

            switch(pieces[index].getTokenState()) {

                case 0: //in home base and movable
                    //only movable if we just rolled a 6, so don't need to check
                    pieces[index].enableToken();
                    //don't change turn
                    change=false;
                    break;
                case 1: //en route and movable
                    //advance the piece
                    pieces[index].incNumSpacesMoved(diceVal);

                    //warning, if for animation we only increment one tile at a time, then this will
                    // eliminate all tokens encountered along the path.

                    //check for overlap in non-safe tiles
                    switch (pieces[index].getAdjustedNumSpacesMoved()) {
                        //safe tiles
                        case 1:
                        case 9:
                        case 14:
                        case 22:
                        case 27:
                        case 35:
                        case 40:
                        case 48:
                            break;  //do nothing
                        default:    //else
                            //check to see if we overlap and need to kick another piece back home
                            //iterate through all active pieces
                            for (int i = 0; i < 16; i++) {
                                if (    pieces[i].getOwner() != playerID_active &&  //not one of my own
                                        pieces[i].getTokenState() == 1 &&                   //is in play
                                        pieces[i].getAdjustedNumSpacesMoved() == pieces[index].getAdjustedNumSpacesMoved() //is at same spot
                                        ){
                                    //changes boolean and numSpacesMoved back to 0
                                    pieces[i].disableToken();
                                    //if sent a piece home, don't change turn
                                    change=false;
                                }
                            }
                    }
                    break;
                case 2: //in home stretch
                    //advance the piece
                    pieces[index].incNumSpacesMoved(diceVal);
                    //check to see if it made it to the end, if so give the player a point
                    if(pieces[index].getNumSpacesMoved()==57){
                        incPlayerScore();
                        Log.i("Player Scored a Point",""+playerScore[playerID]);
                        //if scored, don't change
                        change=false;
                    }
                    break;
            }


            //record that we used the dice value, any further attempts are invalid for this roll
            isRollUsable = false;

            //deals with rule of when to change player turn
            if(change) {
                changePlayerTurn();
            }

            //successfully made a move
            return true;
        }

        //invalid input
        return false;

    }



    //implemented by Avery!
    //used for the computer player!
    //TODO: Optimize this so that it gets the furthest piece out of start!
    public int getTokenIndexOfFirstPieceOutOfStart(int playerID) {
        for (int i = playerID; i < 16; i += 4) {//traverse through the only the pieces the player owns
            if (pieces[i].getTokenState() == 1) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString()
    {

        String output = "";
        for (int i = 0; i < 4; i++) {
            output+= "\nPlayer " + pieces[i].getOwner() + ": ";
            for (int j = i; j < 16; j += 4) {
                output+= "\nPiece " + (j-i)/4 + " at " + pieces[j].getNumSpacesMoved() +
                        " and " + (pieces[j].getTokenState()==0? "is home." : "is not home.");
            }

        }

        return output;
    }

    public boolean getIsRollUsable() {
        return isRollUsable;
    }

}
