package edu_up_cs301.ludo;

import android.graphics.Point;

/**
 * Created by Luke on 1/31/2018.
 *  Edited by Avery Guillermo on 3/27/2019
 *
 *
 *
 */

public class Token {
//
//
//                  [] [] []
//                  [] [] []
//                  [] [] []
//                  [] [] []
//                  [] [] []
//                  [] [] []
//[] [] [] [] [] []    []    [] [] [] [] [] []
//[] [] [] [] [] [] []    [] [] [] [] [] [] []
//[] [] [] [] [] []    []    [] [] [] [] [] []
//                  [] [] []
//                  [] [] []
//                  [] [] []
//                  [] [] []
//                  [] [] []
//                  [] [] []
//                                                          home
//                                                [-1, 7] [ 0, 7] [ 1, 7]
//                                                [-1, 6] [ 0, 6] [ 1, 6] start
//                                           safe [-1, 5] [ 0, 5] [ 1, 5]
//                                                [-1, 4] [ 0, 4] [ 1, 4]
//                                                [-1, 3] [ 0, 3] [ 1, 3]
//                                                [-1, 2] [ 0, 2] [ 1, 2]
//[-7, 1] [-6, 1] [-5, 1] [-4, 1] [-3, 1] [-2, 1]         [ 0, 1]         [ 2, 1] [ 3, 1] [ 4, 1] [ 5, 1] [ 6, 1] [ 7, 1]
//[-7, 0] [-6, 0] [-5, 0] [-4, 0] [-3, 0] [-2, 0] [-1, 0]         [ 1, 0] [ 2, 0] [ 3, 0] [ 4, 0] [ 5, 0] [ 6, 0] [ 7, 0]
//[-7,-1] [-6,-1] [-5,-1] [-4,-1] [-3,-1] [-2,-1]         [ 0,-1]         [ 2,-1] [ 3,-1] [ 4,-1] [ 5,-1] [ 6,-1] [ 7,-1]
//                                                [-1,-2] [ 0,-2] [ 1,-2]
//                                                [-1,-3] [ 0,-3] [ 1,-3]
//                                                [-1,-4] [ 0,-4] [ 1,-4]
//                                                [-1,-5] [ 0,-5] [ 1,-5]
//                                                [-1,-6] [ 0,-6] [ 1,-6]
//                                                [-1,-7] [ 0,-7] [ 1,-7]
//
// home            start
//[ 0, 7] [ 1, 7] [ 1, 6] [ 1, 5] [ 1, 4] [ 1, 3] [ 1, 2]
//        [ 2, 1] [ 3, 1] [ 4, 1] [ 5, 1] [ 6, 1] [ 7, 1]
//[ 7, 0] [ 7,-1] [ 6,-1] [ 5,-1] [ 4,-1] [ 3,-1] [ 2,-1]
//        [ 1,-2] [ 1,-3] [ 1,-4] [ 1,-5] [ 1,-6] [ 1,-7]
//[ 0,-7] [-1,-7] [-1,-6] [-1,-5] [-1,-4] [-1,-3] [-1,-2]
//        [-2,-1] [-3,-1] [-4,-1] [-5,-1] [-6,-1] [-7,-1]
//[-7, 0] [-7, 1] [-6, 1] [-5, 1] [-4, 1] [-3, 1] [-2, 1]
//        [-1, 2] [-1, 3] [-1, 4] [-1, 5] [-1, 6] [-1, 7]
//
//
//    int index = 2;
//    Point[] leg1 = new Point[] {new Point( 0, 7), new Point( 1, 7), new Point( 1, 6), new Point( 1, 5), new Point( 1, 4), new Point( 1, 3), new Point( 1, 2)};
//    int[][] leg = {{ 0, 7}, { 1, 7}, { 1, 6}, { 1, 5}, { 1, 4}, { 1, 3}, { 1, 2}};
//    //    [ 0, 7] [ 1, 7] [ 1, 6] [ 1, 5] [ 1, 4] [ 1, 3] [ 1, 2]
//    int row[] = new int[]{ 7, 7, 6, 5, 4, 3, 2};
//    int col[] = new int[]{ 0, 1, 1, 1, 1, 1, 1};
//
//    int legOfJourney = 0;
//    //traverse forward,
//    if( row == 0){
//        legOfJourney++;
//    }
//    if(index == 6){
//        switch(legOfJourney){
//            case 1:
//                for(int n: leg){
//                    int temp = x;
//                    x = y;
//                    y = temp;
//                }
//                break;
//        }
////traverse backward,
//
//instance variables
private int numSpacesMoved;
    private int owner;
    private int tokenState; //is home = 0, is en route = 1, is in home stretch = 2;
    private boolean isMovable;
    private boolean isHome;
    private boolean reachedHomeBase;


    /**
     * constructor
     */
    public Token(int ownsMe){
        tokenState = 0;
        numSpacesMoved = 0;
        isMovable=false;
        isHome = true;
        owner = ownsMe;
        this.reachedHomeBase = false;
//
//        switch(ownsMe){
//            case 0:
//                path = new int[][]{{1, 6}, {2, 6},
//                        {3, 6}, {4, 6}, {5, 6}, {6, 5}, {6, 4}, {6, 3},
//                        {6, 2}, {6, 1}, {6, 0}, {7, 0}, {8, 0},
//                        {8, 1}, {8, 2}, {8, 3}, {8, 4}, {8, 5},
//                        {9, 6}, {10, 6}, {11, 6}, {12, 6}, {13, 6}, {14, 6},
//                        {14, 7}, {14, 8}, {13, 8}, {12, 8}, {11, 8}, {10, 8},
//                        {9, 8}, {8, 9}, {8, 10}, {8, 11}, {8, 12}, {8, 13},
//                        {8, 14}, {7, 14}, {6, 14}, {6, 13}, {6, 12}, {6, 11},
//                        {6, 10}, {6, 9}, {5, 8}, {4, 8}, {3, 8}, {2, 8},
//                        {1, 8}, {0, 8}, {0, 7}, {1, 7}, {2, 7}, {3, 7}, {4, 7}, {5, 7}, {6, 7}};
//                break;
//
//            case 1:
//                path = new int[][]{{8, 1}, {8, 2}, {8, 3}, {8, 4}, {8, 5},
//                        {9, 6}, {10, 6}, {11, 6}, {12, 6}, {13, 6}, {14, 6},
//                        {14, 7}, {14, 8}, {13, 8}, {12, 8}, {11, 8}, {10, 8},
//                        {9, 8}, {8, 9}, {8, 10}, {8, 11}, {8, 12}, {8, 13},
//                        {8, 14}, {7, 14}, {6, 14}, {6, 13}, {6, 12}, {6, 11},
//                        {6, 10}, {6, 9}, {5, 8}, {4, 8}, {3, 8}, {2, 8},
//                        {1, 8}, {0, 8}, {0, 7}, {0, 6}, {1, 6}, {2, 6},
//                        {3, 6}, {4, 6}, {5, 6}, {6, 5}, {6, 4}, {6, 3},
//                        {6, 2}, {6, 1}, {6, 0}, {7, 0}, {7, 1}, {7, 2},
//                        {7, 3}, {7, 4}, {7, 5}, {7, 6}};
//                break;
//            case 2:
//                path = new int[][]{{13, 8}, {12, 8}, {11, 8}, {10, 8},
//                        {9, 8}, {8, 9}, {8, 10}, {8, 11}, {8, 12}, {8, 13},
//                        {8, 14}, {7, 14}, {6, 14}, {6, 13}, {6, 12}, {6, 11},
//                        {6, 10}, {6, 9}, {5, 8}, {4, 8}, {3, 8}, {2, 8},
//                        {1, 8}, {0, 8}, {0, 7}, {0, 6}, {1, 6}, {2, 6},
//                        {3, 6}, {4, 6}, {5, 6}, {6, 5}, {6, 4}, {6, 3},
//                        {6, 2}, {6, 1}, {6, 0}, {7, 0}, {8, 0},
//                        {8, 1}, {8, 2}, {8, 3}, {8, 4}, {8, 5},
//                        {9, 6}, {10, 6}, {11, 6}, {12, 6}, {13, 6}, {14, 6},
//                        {14, 7}, {13, 7}, {12, 7}, {11, 7}, {10, 7}, {9, 7}, {8, 7}};
//
//                break;
//            case 3:
//                path = new int[][]{{6, 13}, {6, 12}, {6, 11},
//                        {6, 10}, {6, 9}, {5, 8}, {4, 8}, {3, 8}, {2, 8},
//                        {1, 8}, {0, 8}, {0, 7}, {0, 6}, {1, 6}, {2, 6},
//                        {3, 6}, {4, 6}, {5, 6}, {6, 5}, {6, 4}, {6, 3},
//                        {6, 2}, {6, 1}, {6, 0}, {7, 0}, {8, 0},
//                        {8, 1}, {8, 2}, {8, 3}, {8, 4}, {8, 5},
//                        {9, 6}, {10, 6}, {11, 6}, {12, 6}, {13, 6}, {14, 6},
//                        {14, 7}, {14, 8}, {13, 8}, {12, 8}, {11, 8}, {10, 8},
//                        {9, 8}, {8, 9}, {8, 10}, {8, 11}, {8, 12}, {8, 13},
//                        {8, 14}, {7, 14}, {7, 13}, {7, 12}, {7, 11}, {7, 10}, {7, 9}, {7, 8}};
//
//                break;
    }


    /**
     * copy constructor
     * @param original
     */
    public Token(Token original){
        this.numSpacesMoved = original.numSpacesMoved;
        this.isMovable = original.isMovable;
        this.isHome = original.isHome;
        this.owner = original.owner;
        this.reachedHomeBase = original.reachedHomeBase;
    }


    /**
     * because of the way we index the path, need a shifted, mod 51 counter
     * @return index of position in RectF array in LudoSurfaceView
     */
    public int getAdjustedNumSpacesMoved() {

        int index = this.numSpacesMoved+owner*13+1; //0 starts at 1, 1 starts at 14, 2 starts at 28, 3 starts at 4

        if(index>51) {
            index -= 51;
        }

        return index;
    }

    public int getTokenState(){return this.tokenState;}

    public int getNumSpacesMoved() {
        return numSpacesMoved;
    }

    public void incNumSpacesMoved(int numDiceVal) {
        this.numSpacesMoved += numDiceVal;
        updateState();
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
            this.numSpacesMoved = 0;
        }
        isHome = home;
        updateState();
    }

    public int getOwner() {
        return owner;
    }

    public boolean getReachedHomeBase(){
        return reachedHomeBase;
    }

    private void updateState() {

        if(isHome){
            tokenState = 0;
        }
        else if(numSpacesMoved<52){
            tokenState = 1;
        }
        else{
            tokenState = 2;
        }

    }
}