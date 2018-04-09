package edu_up_cs301.ludo;

import java.util.ArrayList;
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
    private boolean isRollUseable;

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
     * generates a new random roll if player is allowed to play.
     * considers if it is their turn and if roll a 6, they get to roll again
     *
     //     * @param playerID who sent action
     * @return true if dice was able to roll
     */
    public boolean newRoll() {
        if (isRollable && !isRollUseable){

            //handle the case of rerolls on a 6 with a switch
            switch (diceVal = dice.nextInt(6) + 1) {
                case 6:
                    isRollable = false; //so it stays true;
                default:
                    isRollable = !isRollable;
                    isRollUseable = true; //new roll has not been used so set true
            }

            //if no moves exist based on diceVal, mark as used so it is unusable
            if(!updateMovesAvailable()){
                isRollUseable =false;
            }
        }
        //unable to roll
        return false;
    }

    /**
     * Called after a dice roll. We go through the active player's pieces and checks for pieces
     * which can move by the rules of the game, each piece has a boolean which is changed to
     * reflect the outcome of the check;
     * @return true if valid moves exist
     */
    private boolean updateMovesAvailable() {

        //checks if any moves available;
        boolean oneTrue = false;

        //set movable boolean
        for (int i = playerID_active; i < 16; i+=4) {
            if (pieces[i].getIsHome()) {
                if (diceVal == 6) {
                    //is in home base and a 6 has been rolled
                    pieces[i].setIsMovable(true);
                    oneTrue = true;
                } else {
                    pieces[i].setIsMovable(false);
                }
            } else if (pieces[i].getNumSpacesMoved() < 51) {
                //in normal route
                pieces[i].setIsMovable(true);
                oneTrue = true;
            } else if (pieces[i].getNumSpacesMoved() + diceVal < 57) {
                //in home stretch and total will be less than or equal to 56
                pieces[i].setIsMovable(true);
                oneTrue = true;
            } else {
                pieces[i].setIsMovable(false);
            }
        }

        return oneTrue;
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
     * getter method to search for peice by reference to distance traveled
     *
     * @param playerID
     * @param spacesTraveled
     * @return
     */
    public int getTokenIndexByTravelDistance(int playerID, int spacesTraveled) {

        //only iterates through players' 4 Tokens
        for (int i = playerID; i < 16; i += 4) {
            if (pieces[i].getOwner() == playerID && pieces[i].getNumSpacesMoved() == spacesTraveled) {
                return i;
            }
        }
        return -1;
    }


//    /**
//     * helper method to get relevant pieces
//     *
//     * @param playerID
//     * @return only the 4 pieces owned by the player
//     */
//    public Token[] getMyTokens(int playerID) {
//        ArrayList<Token> myPieces = new ArrayList<>();
//
//        //only iterates through players' 4 Tokens
//        for (int i = playerID; i < 16; i += 4) {
//            if (pieces[i].getOwner() == playerID && myPieces.size() < 4) {
//                myPieces.add(pieces[i]);
//            }
//        }
//        return (Token[]) myPieces.toArray();
//    }

//    /**
//     * helper method to get relevant pieces
//     *
//     * @param playerID
//     * @return only the 4 pieces owned by the player
//     */
//    private Token[] getPlayerActiveTokens(int playerID) {
//        ArrayList<Token> myPieces = new ArrayList<>();
//
//        //only iterates through players' 4 Tokens
//        for (int i = playerID; i < 16; i += 4) {
//            if (pieces[i].getOwner() == playerID && myPieces.size() < 4) {
//                myPieces.add(pieces[i]);
//            }
//        }
//        return (Token[]) myPieces.toArray();
//    }


//    public ArrayList<Token> isTokenWithinDiceValueOfMyTokensInPlay(int playerID) {
//
//        for (int i = 0; i < 16; i++) {
//
//        }
//        return null;
//    }

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
            if (pieces[i].getIsHome() == false && pieces[i].getReachedHomeBase() == false){
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
     * @param spacesPieceHasTraveled
     * @return
     */
    public boolean advanceToken(int playerID, int index) {

        //only act on movable pieces. Always updated after each dice roll.
        if(pieces[index].getIsMovable() && isRollUseable) {

            //used to implement player turn rules
            boolean change=true;

            switch(pieces[index].getTokenState()) {

                case 0: //in home base and movable
                    pieces[index].setIsHome(false);
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
                        case 8:
                        case 13:
                        case 21:
                        case 26:
                        case 34:
                        case 39:
                        case 47:
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
                                    pieces[i].setIsHome(true);
                                    //if sent a piece home, don't change turn
                                    change=false;
                                }
                            }
                    }
                    break;
                case 2: //in home stretch
                    pieces[index].incNumSpacesMoved(diceVal);
                    if(pieces[index].getNumSpacesMoved()==57){
                        incPlayerScore();
                        Log.i("Player Scored a Point",""+playerScore[playerID]);
                        //if scored, don't change
                        change=false;
                    }
                    break;
            }


            //record that we used the dice value, any further attempts are invalid for this roll
            isRollUseable = false;

            if(change){
                changePlayerTurn();
            }

            //successfully made a move
            return true;
        }
        else {
            //invalid input
            return false;
        }
    }



    //implemented by Avery!
    public int getTokenIndexOfFirstPieceInStart(int playerID){
        for(int i = playerID; i<16; i+=4){//traverse through the only the pieces the player owns
            if(pieces[i].getTokenState()==0){
                return i;
            }
        }
        return -1;

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
                        " and " + (pieces[j].getIsHome()? "is home." : "is not home.");
            }

        }

        return output;
    }


}
