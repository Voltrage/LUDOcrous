package edu_up_cs301.ludo;

import android.graphics.Point;

/**
 * Created by Luke on 1/31/2018.
 */

public class Token {
/*

                  [] [] []
                  [] [] []
                  [] [] []
                  [] [] []
                  [] [] []
                  [] [] []
[] [] [] [] [] []    []    [] [] [] [] [] []
[] [] [] [] [] [] []    [] [] [] [] [] [] []
[] [] [] [] [] []    []    [] [] [] [] [] []
                  [] [] []
                  [] [] []
                  [] [] []
                  [] [] []
                  [] [] []
                  [] [] []
                                                          home
                                                [-1, 7] [ 0, 7] [ 1, 7]
                                                [-1, 6] [ 0, 6] [ 1, 6] start
                                           safe [-1, 5] [ 0, 5] [ 1, 5]
                                                [-1, 4] [ 0, 4] [ 1, 4]
                                                [-1, 3] [ 0, 3] [ 1, 3]
                                                [-1, 2] [ 0, 2] [ 1, 2]
[-7, 1] [-6, 1] [-5, 1] [-4, 1] [-3, 1] [-2, 1]         [ 0, 1]         [ 2, 1] [ 3, 1] [ 4, 1] [ 5, 1] [ 6, 1] [ 7, 1]
[-7, 0] [-6, 0] [-5, 0] [-4, 0] [-3, 0] [-2, 0] [-1, 0]         [ 1, 0] [ 2, 0] [ 3, 0] [ 4, 0] [ 5, 0] [ 6, 0] [ 7, 0]
[-7,-1] [-6,-1] [-5,-1] [-4,-1] [-3,-1] [-2,-1]         [ 0,-1]         [ 2,-1] [ 3,-1] [ 4,-1] [ 5,-1] [ 6,-1] [ 7,-1]
                                                [-1,-2] [ 0,-2] [ 1,-2]
                                                [-1,-3] [ 0,-3] [ 1,-3]
                                                [-1,-4] [ 0,-4] [ 1,-4]
                                                [-1,-5] [ 0,-5] [ 1,-5]
                                                [-1,-6] [ 0,-6] [ 1,-6]
                                                [-1,-7] [ 0,-7] [ 1,-7]

 home            start
[ 0, 7] [ 1, 7] [ 1, 6] [ 1, 5] [ 1, 4] [ 1, 3] [ 1, 2]
        [ 2, 1] [ 3, 1] [ 4, 1] [ 5, 1] [ 6, 1] [ 7, 1]
[ 7, 0] [ 7,-1] [ 6,-1] [ 5,-1] [ 4,-1] [ 3,-1] [ 2,-1]
        [ 1,-2] [ 1,-3] [ 1,-4] [ 1,-5] [ 1,-6] [ 1,-7]
[ 0,-7] [-1,-7] [-1,-6] [-1,-5] [-1,-4] [-1,-3] [-1,-2]
        [-2,-1] [-3,-1] [-4,-1] [-5,-1] [-6,-1] [-7,-1]
[-7, 0] [-7, 1] [-6, 1] [-5, 1] [-4, 1] [-3, 1] [-2, 1]
        [-1, 2] [-1, 3] [-1, 4] [-1, 5] [-1, 6] [-1, 7]


    int index = 2;
    Point[] leg1 = new Point[] {new Point( 0, 7), new Point( 1, 7), new Point( 1, 6), new Point( 1, 5), new Point( 1, 4), new Point( 1, 3), new Point( 1, 2)};
    int[][] leg = {{ 0, 7}, { 1, 7}, { 1, 6}, { 1, 5}, { 1, 4}, { 1, 3}, { 1, 2}};
    //    [ 0, 7] [ 1, 7] [ 1, 6] [ 1, 5] [ 1, 4] [ 1, 3] [ 1, 2]
    int row[] = new int[]{ 7, 7, 6, 5, 4, 3, 2};
    int col[] = new int[]{ 0, 1, 1, 1, 1, 1, 1};

    int legOfJourney = 0;
    //traverse forward,
    if( row == 0){
        legOfJourney++;
    }
    if(index == 6){
        switch(legOfJourney){
            case 1:
                for(int n: leg){
                    int temp = x;
                    x = y;
                    y = temp;
                }
                break;
        }
//traverse backward,
*/

    //instance variables
    private int numSpacesMoved;
    private int owner;
    private int row;
    private int col;
    private boolean isMovable;
    private boolean isHome;



    /**
     * constructor
     */
    public Token(int ownsMe){
        numSpacesMoved = 0;
        isMovable=false;
        isHome = true;
        owner = ownsMe;
    }

    /**
     * copy constructor
     * @param original
     */
    public Token(Token original){
        numSpacesMoved = original.numSpacesMoved;
        isMovable = original.isMovable;
        isHome = original.isHome;
        owner = original.owner;
        row = original.row;
        col = original.col;
    }

    public boolean shiftToken (int shiftAmount){
        if(isMovable) {
            numSpacesMoved += shiftAmount;
            return true;
        }
        else
            return false;
    }

    public int getNumSpacesMoved() {
        return numSpacesMoved;
    }

    public void incNumSpacesMoved(int numDiceVal) {
        this.numSpacesMoved = numDiceVal;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean getIsMovable() {
        return isMovable;
    }

    public void setIsMovable(boolean movable) {
        isMovable = movable;
    }

    public boolean getIsHome() {
        return isHome;
    }

    public void setIsHome(boolean home) {
        if(home){
            numSpacesMoved = 0;
        }
        isHome = home;
    }

    public int getOwner() {
        return owner;
    }

}
