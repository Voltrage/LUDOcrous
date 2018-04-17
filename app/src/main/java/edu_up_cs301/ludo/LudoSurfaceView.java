package edu_up_cs301.ludo;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import edu_up_cs301.game.GamePlayer;
import edu_up_cs301.game.util.FlashSurfaceView;

import java.util.*;


/**
 *
 *
 *
 * onDraw method Created by nayyar19 and guillermo19 on 2/3/2018.
 *
 * @author Luke Danowski
 * @author Avery Guillermo
 * @author Ravi Nayyar
 * @author Chris Sebchets
 *
 * @version April 9
 */

public class LudoSurfaceView extends FlashSurfaceView{

    private LudoState state;// = new LudoState();
    private RectF[] boardPositions;
    private RectF[][] startPositions;
    private RectF[][] homeStretchPositions;
    private ArrayList<TileF> occupiedTiles;
    private float cellWH;

    //general
    Paint blackPaintBor;

    //token
    Paint[] tokenPaint;
    Paint redPaintTok;
    Paint greenPaintTok;
    Paint yellowPaintTok;
    Paint bluePaintTok;
    Paint whitePaintTok;

    //board
    Paint[] boardPaint;
    Paint greenPaintBor;
    Paint redPaintBor;
    Paint bluePaintBor;
    Paint yellowPaintBor;
    Paint whitePaintBor;
    Paint whitePaint2Bor;


//    //red
//    private final int[][] path0 = new int[][]{
//            {1, 6}, {2, 6}, {3, 6}, {4, 6}, {5, 6},
//            {6, 5}, {6, 4}, {6, 3}, {6, 2}, {6, 1}, {6, 0},
//            {7, 0},
//            {8, 0}, {8, 1}, {8, 2}, {8, 3}, {8, 4}, {8, 5},
//            {9, 6}, {10, 6}, {11, 6}, {12, 6}, {13, 6}, {14, 6},
//            {14, 7},
//            {14, 8}, {13, 8}, {12, 8}, {11, 8}, {10, 8}, {9, 8},
//            {8, 9}, {8, 10}, {8, 11}, {8, 12}, {8, 13}, {8, 14},
//            {7, 14},
//            {6, 14}, {6, 13}, {6, 12}, {6, 11}, {6, 10}, {6, 9},
//            {5, 8}, {4, 8}, {3, 8}, {2, 8}, {1, 8}, {0, 8},
//            {0, 7}, {1, 7}, {2, 7}, {3, 7}, {4, 7}, {5, 7}, {6, 7}};
//
//    //green
//    private final int[][] path1 = new int[][]{{8, 1}, {8, 2}, {8, 3}, {8, 4}, {8, 5},
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
//    //blue should be yellow
//    private final int[][] path2 = new int[][]{{6, 13}, {6, 12}, {6, 11},
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
//    //yellow should be blue
//    private final int[][] path3 = new int[][]{{13, 8}, {12, 8}, {11, 8}, {10, 8},
//        {9, 8}, {8, 9}, {8, 10}, {8, 11}, {8, 12}, {8, 13},
//        {8, 14}, {7, 14}, {6, 14}, {6, 13}, {6, 12}, {6, 11},
//        {6, 10}, {6, 9}, {5, 8}, {4, 8}, {3, 8}, {2, 8},
//        {1, 8}, {0, 8}, {0, 7}, {0, 6}, {1, 6}, {2, 6},
//        {3, 6}, {4, 6}, {5, 6}, {6, 5}, {6, 4}, {6, 3},
//        {6, 2}, {6, 1}, {6, 0}, {7, 0}, {8, 0},
//        {8, 1}, {8, 2}, {8, 3}, {8, 4}, {8, 5},
//        {9, 6}, {10, 6}, {11, 6}, {12, 6}, {13, 6}, {14, 6},
//        {14, 7}, {13, 7}, {12, 7}, {11, 7}, {10, 7}, {9, 7}, {8, 7}};

    public LudoSurfaceView(Context context) {
        super(context);
                occupiedTiles = new ArrayList<>();
        generalInit();
    }

    public LudoSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
                occupiedTiles = new ArrayList<>();
        generalInit();
    }

    public void setState(LudoState state) {
        this.state = state;

        occupiedTiles.clear();
        generateOccupiedArray();

    }


    /**
     * \
     * generalInit
     * <p>
     * Initialization stuff used by all ctors
     */
    private void generalInit() {
        setWillNotDraw(false);

//        occupiedTiles.clear();
//        generateOccupiedArray();


        cellWH = this.getWidth()/15f;

        int[][] path = new int[][]{
                //start
                {0, 6}, {1, 6}, {2, 6}, {3, 6}, {4, 6}, {5, 6},
                {6, 5}, {6, 4}, {6, 3}, {6, 2}, {6, 1}, {6, 0},
                {7, 0},
                {8, 0}, {8, 1}, {8, 2}, {8, 3}, {8, 4}, {8, 5},
                {9, 6}, {10, 6}, {11, 6}, {12, 6}, {13, 6}, {14, 6},
                {14, 7},
                {14, 8}, {13, 8}, {12, 8}, {11, 8}, {10, 8}, {9, 8},
                {8, 9}, {8, 10}, {8, 11}, {8, 12}, {8, 13}, {8, 14},
                {7, 14},
                {6, 14}, {6, 13}, {6, 12}, {6, 11}, {6, 10}, {6, 9},
                {5, 8}, {4, 8}, {3, 8}, {2, 8}, {1, 8}, {0, 8},
                {0, 7}, //would enter home stretch
        };

        int[][][] homeStrech = new int[][][]{
                { {1, 7}, {2, 7}, {3, 7}, {4, 7}, {5, 7}, {6, 7} }, //red 0
                { {7, 1}, {7, 2}, {7, 3}, {7, 4}, {7, 5}, {7, 6} }, //green 1
                { {13, 7}, {12, 7}, {11, 7}, {10, 7}, {9, 7}, {8, 7}}, //yellow 2
                { {7, 13}, {7, 12}, {7, 11}, {7, 10}, {7, 9}, {7, 8} } //blue 3
        };


        float[][][] startPos = new float[][][]{
                { {1f, 2.5f}, {2.5f, 1f}, {4f, 2.5f}, {2.5f, 4f} },
                { {11.5f, 1f}, {13f, 2.5f}, {11.5f, 4f}, {10f, 2.5f} },
                { {13f, 11.5f}, {11.5f, 13f}, {10f, 11.5f}, {11.5f, 10f} },
                { {2.5f, 13f}, {1f, 11.5f}, {2.5f, 10f}, {4f, 11.5f} }
        };

        //define normal routes
        boardPositions = new RectF[52];
        for(int n = 0; n<52 ; n++) {
            boardPositions[n] = new RectF(
                    path[n][0]*(cellWH),
                    path[n][1]*(cellWH),
                    path[n][0]*(cellWH) + cellWH,
                    path[n][1]*(cellWH) + cellWH );
            //some space
            boardPositions[n].inset(cellWH*.01f, cellWH*.01f);
        }

        //define home stretch
        homeStretchPositions = new RectF[4][6];
        for(int n1 = 0; n1<4 ; n1++){
            for(int n2 = 0; n2<6 ; n2++){
                homeStretchPositions[n1][n2] = new RectF(
                        homeStrech[n1][n2][0]*(cellWH),
                        homeStrech[n1][n2][1]*(cellWH),
                        homeStrech[n1][n2][0]*(cellWH) + cellWH,
                        homeStrech[n1][n2][1]*(cellWH) + cellWH );
                //some space
                homeStretchPositions[n1][n2].inset(cellWH*.01f,cellWH*.01f);
            }
        }

        //define home base
        startPositions = new RectF[4][4];
        for(int n1 = 0; n1<4 ; n1++){
            for(int n2 = 0; n2<4 ; n2++){
                startPositions[n1][n2] = new RectF(
                        startPos[n1][n2][0]*(cellWH),
                        startPos[n1][n2][1]*(cellWH),
                        startPos[n1][n2][0]*(cellWH) + cellWH,
                        startPos[n1][n2][1]*(cellWH) + cellWH );
                //some space
                startPositions[n1][n2].inset(cellWH*.01f,cellWH*.01f);
            }
        }


        redPaintTok = new Paint();
        redPaintTok.setColor(Color.rgb(255,0,0));
        greenPaintTok = new Paint();
        greenPaintTok.setColor(Color.rgb(80, 255, 95));
        bluePaintTok = new Paint();
        bluePaintTok.setColor(Color.rgb(75,150,255));
        yellowPaintTok = new Paint();
        yellowPaintTok.setColor(Color.rgb(230, 190, 70));
        whitePaintTok = new Paint();
        whitePaintTok.setColor(Color.rgb(255,255,255));

        tokenPaint = new Paint[] {redPaintTok, greenPaintTok, yellowPaintTok, bluePaintTok};


        greenPaintBor = new Paint();
        redPaintBor = new Paint();
        bluePaintBor = new Paint();
        yellowPaintBor = new Paint();
        whitePaintBor = new Paint();
        blackPaintBor = new Paint();
        whitePaint2Bor = new Paint();
        redPaintBor.setColor(Color.rgb(193, 23, 23));
        greenPaintBor.setColor(Color.rgb(50, 220, 50));
        bluePaintBor.setColor(Color.rgb(10, 10, 230));
        yellowPaintBor.setColor(Color.rgb(242, 228, 38));
        whitePaintBor.setColor(Color.rgb(255, 255, 255));
        whitePaint2Bor.setColor(Color.rgb(255, 255, 255));
        blackPaintBor.setColor(Color.rgb(0, 0, 0));

        boardPaint = new Paint[] {redPaintBor, greenPaintBor, yellowPaintBor, bluePaintBor};


    }//generalInit




    /**
     * override the onDraw() method to draw cool stuff for students to see
     *
     * @param canvas - draw on this
     */
    @Override
    public void onDraw(Canvas canvas) {

        if(state==null){
            return;
        }

        if(cellWH<=0) {
            //TODO: synchronize so generalInit will only need to be called once, in constructor after width & height are defined
            generalInit();
            occupiedTiles.clear();
            generateOccupiedArray();
        }
        canvas.drawRect(this.getLeft(), this.getTop(), this.getRight(), this.getBottom(), blackPaintBor);



        drawHomeBase(canvas);


        //draw board path
        for(int i = 0; i<52 ; i++){

            //draw tile color, typically white
            canvas.drawRect(boardPositions[i],
                    (i == 1 || i == 48) ? redPaintBor :
                            (i == 9 || i == 14) ? greenPaintBor :
                                    (i == 22 || i == 27) ? yellowPaintBor :
                                            (i == 35 || i == 40) ? bluePaintBor : whitePaintBor);
            //differentiate non-starting safe tiles
            switch(i) {
                case 9:
                case 22:
                case 35:
                case 48:
                    canvas.drawCircle(boardPositions[i].centerX(), boardPositions[i].centerY(), cellWH*.4f, blackPaintBor);
                    canvas.drawCircle(boardPositions[i].centerX(), boardPositions[i].centerY(), cellWH*.38f, whitePaintBor);
                    break;
            }

        }

        for( int p = 0; p < 4; p++){
            for(int i = 0; i<6; i++) {

                canvas.drawRect(homeStretchPositions[p][i], boardPaint[p]);

            }
        }

//        //Red Large Tile Section Creation
//        canvas.drawRect(0, 0, (cellWH * 6), (cellWH * 6), redPaint);
//        Point d11 = new Point(boxInt * 3, 0);
//        Point d12 = new Point(boxInt * 6, boxInt * 3);
//        Point d13 = new Point((boxInt * 3), (boxInt * 6));
//        Point d14 = new Point((boxInt * 0), (boxInt * 3)); //Bottom Left
//        //Drawing the red diamond
//        Path redDiamond = new Path();
//        redDiamond.moveTo(d11.x, d11.y);
//        redDiamond.lineTo(d12.x, d12.y);
//        redDiamond.lineTo(d13.x, d13.y);
//        redDiamond.lineTo(d14.x, d14.y);
//        redDiamond.close();
//        canvas.drawPath(redDiamond, whitePaint);
//        drawStartTiles(cellWH, canvas, redPaint, 0, 0);
//
//        //Green Large Tile Section Creation
//        canvas.drawRect((cellWH * 9), 0.0f, (cellWH * 15), (cellWH * 6), greenPaint);
//        Point d21 = new Point(boxInt * 12, 0);
//        Point d22 = new Point(boxInt * 15, boxInt * 3);
//        Point d23 = new Point((boxInt * 12), (boxInt * 6));
//        Point d24 = new Point((boxInt * 9), (boxInt * 3)); //Bottom Left
//        //Drawing the green diamond
//        Path greenDiamond = new Path();
//        greenDiamond.moveTo(d21.x, d21.y);
//        greenDiamond.lineTo(d22.x, d22.y);
//        greenDiamond.lineTo(d23.x, d23.y);
//        greenDiamond.lineTo(d24.x, d24.y);
//        greenDiamond.close();
//        canvas.drawPath(greenDiamond, whitePaint);
//        drawStartTiles(cellWH, canvas, greenPaint, boxInt * 9, 0);
//
//        //Blue Large Tile Section Creation
//        canvas.drawRect((cellWH * 0), cellWH * 9, (cellWH * 6), (cellWH * 15), bluePaint);
//        Point d31 = new Point(boxInt * 3, boxInt * 9);
//        Point d32 = new Point(boxInt * 6, boxInt * 12);
//        Point d33 = new Point((boxInt * 3), (boxInt * 15));
//        Point d34 = new Point((boxInt * 0), (boxInt * 12)); //Bottom Left
//        //Drawing the blue diamond
//        Path blueDiamond = new Path();
//        blueDiamond.moveTo(d31.x, d31.y);
//        blueDiamond.lineTo(d32.x, d32.y);
//        blueDiamond.lineTo(d33.x, d33.y);
//        blueDiamond.lineTo(d34.x, d34.y);
//        blueDiamond.close();
//        canvas.drawPath(blueDiamond, whitePaint);
//        drawStartTiles(cellWH, canvas, bluePaint, 0, boxInt * 9);
//
//        //Yellow Large Tile Section Creation
//        canvas.drawRect((cellWH * 9), (cellWH * 9), (cellWH * 15), (cellWH * 15), yellowPaint);
//        Point d41 = new Point(boxInt * 12, boxInt * 9);
//        Point d42 = new Point(boxInt * 15, boxInt * 12);
//        Point d43 = new Point((boxInt * 12), (boxInt * 15));
//        Point d44 = new Point((boxInt * 9), (boxInt * 12)); //Bottom Left
//        //Drawing the yellow diamond
//        Path yellowDiamond = new Path();
//        yellowDiamond.moveTo(d41.x, d41.y);
//        yellowDiamond.lineTo(d42.x, d42.y);
//        yellowDiamond.lineTo(d43.x, d43.y);
//        yellowDiamond.lineTo(d44.x, d44.y);
//        yellowDiamond.close();
//        canvas.drawPath(yellowDiamond, whitePaint);
//        drawStartTiles(cellWH, canvas, yellowPaint, boxInt * 9, boxInt * 9);
//
//        //Drawing Center Square
//        canvas.drawRect((cellWH * 6), (cellWH * 6), (cellWH * 9), (cellWH * 9), whitePaint);
//        Point p1 = new Point((boxInt * 6), (boxInt * 6)); //Top Left
//        Point p2 = new Point((canvas.getWidth() / 2), (canvas.getWidth() / 2)); //Dead Center
//        Point p3 = new Point((boxInt * 9), (boxInt * 6)); //Top Right
//        Point p4 = new Point((boxInt * 6), (boxInt * 9)); //Bottom Left
//        Point p5 = new Point((boxInt * 9), (boxInt * 9)); //Bottom Right
//        //Drawing the green Center Triangle
//        Path tri1 = new Path();
//        tri1.moveTo(p1.x, p1.y);
//        tri1.lineTo(p2.x, p2.y);
//        tri1.lineTo(p3.x, p3.y);
//        tri1.close();
//        canvas.drawPath(tri1, greenPaint);
//
//        //Drawing the red Center Triangle
//        Path tri2 = new Path();
//        tri2.moveTo(p1.x, p1.y);
//        tri2.lineTo(p2.x, p2.y);
//        tri2.lineTo(p4.x, p4.y);
//        tri2.close();
//        canvas.drawPath(tri2, redPaint);
//
//        //Drawing the blue Center Triangle
//        Path tri3 = new Path();
//        tri3.moveTo(p4.x, p4.y);
//        tri3.lineTo(p2.x, p2.y);
//        tri3.lineTo(p5.x, p5.y);
//        tri3.close();
//        canvas.drawPath(tri3, bluePaint);
//
//        //Drawing the yellow Center Triangle
//        Path tri4 = new Path();
//        tri4.moveTo(p3.x, p3.y);
//        tri4.lineTo(p2.x, p2.y);
//        tri4.lineTo(p5.x, p5.y);
//        tri4.close();
//        canvas.drawPath(tri4, yellowPaint);
//
//        //draw the safe space tiles
//        drawStar((boxInt * 2), (boxInt * 8), canvas, cellWH);
//        drawStar((boxInt * 8), (boxInt * 12), canvas, cellWH);
//        drawStar((boxInt * 6), (boxInt * 2), canvas, cellWH);
//        drawStar((boxInt * 12), (boxInt * 6), canvas, cellWH);
//
        //draw all the pieces



        drawPieces(canvas);

        drawDice(canvas);
        }




    public void drawStar(int xPos,  int yPos, Canvas canvas){
        Paint whitePaint = new Paint();
        whitePaint.setColor(Color.rgb(255,255,255));

        int xShift1 = (int) (0.1122449*cellWH);
        int xShift2 = (int) (0.5*cellWH);
        int xShift3 = (int) (0.88877551*cellWH);
        int yShift1 = (int) (0.295918*cellWH);
        int yShift2 = (int) (0.8877551*cellWH);
        int yShift3 = (int) (0.673469*cellWH);
        int yShift4 = (int) (0.08163*cellWH);

        Point p1 = new Point(xPos+xShift1,yPos+yShift1); //Bottom Left of Lower Star
        Point p2 = new Point(xPos+xShift3,yPos+yShift1); //Bottom Right of Lower Star
        Point p3 = new Point(xPos+xShift2,yPos+yShift2); //Top of Star

        Point p4 = new Point(xPos+xShift1,yPos+yShift3); //Left point of Upper Star
        Point p5 = new Point(xPos+xShift3,yPos+yShift3); //Right point of Upper Star
        Point p6 = new Point(xPos+xShift2,yPos+yShift4); //Bottom of Star


        Path bottomStar = new Path();
        bottomStar.moveTo(p1.x,p1.y);
        bottomStar.lineTo(p2.x,p2.y);
        bottomStar.lineTo(p3.x,p3.y);
        bottomStar.lineTo(p1.x,p1.y);
        bottomStar.close();
        canvas.drawPath(bottomStar,whitePaint);

        Path topStar = new Path();
        topStar.moveTo(p4.x,p4.y);
        topStar.lineTo(p5.x,p5.y);
        topStar.lineTo(p6.x,p6.y);
        topStar.lineTo(p4.x,p4.y);
        topStar.close();
        canvas.drawPath(topStar,whitePaint);

    }


    public void drawDice(Canvas canvas){
        if(state!=null) {
            Paint greyPaint = new Paint();
            greyPaint.setColor(Color.rgb(100, 100, 100));
            Paint redPaint = new Paint();
            redPaint.setColor(Color.rgb(255, 0, 0));
            Paint greenPaint = new Paint();
            greenPaint.setColor(Color.rgb(0, 255, 0));
            Paint bluePaint = new Paint();
            bluePaint.setColor(Color.rgb(0, 0, 255));
            Paint yellowPaint = new Paint();
            yellowPaint.setColor(Color.rgb(255, 255, 0));
            Paint blackPaint = new Paint();
            blackPaint.setColor(Color.rgb(0, 0, 0));

             switch (state.getWhoseMove()) {
                    case 0:
                        canvas.drawRect((float) (cellWH * 2.3), (float) (cellWH * 2.3), (float) (cellWH * 3.7), (float) (cellWH * 3.7), redPaint);
                        drawDots(0, 0, cellWH, state.getDiceVal(), canvas, blackPaint);
                        break;
                    case 1:
                        canvas.drawRect(((float) ((cellWH * 11.3))), (float) (cellWH * 2.3), ((float) ((cellWH * 12.7))), (float) (cellWH * 3.7), greenPaint);
                        drawDots(cellWH * 9, 0, cellWH, state.getDiceVal(), canvas, blackPaint);
                        break;
                    case 2:
                        canvas.drawRect(((float) ((cellWH * 11.3))), ((float) ((cellWH * 11.3))), ((float) ((cellWH * 12.7))), ((float) ((cellWH * 12.7))), yellowPaint);
                        drawDots(cellWH * 9, cellWH * 9, cellWH, state.getDiceVal(), canvas, blackPaint);
                        break;
                    case 3:
                        canvas.drawRect(((float) ((cellWH * 2.3))), ((float) ((cellWH * 11.3))), ((float) (cellWH * 3.7)), ((float) ((cellWH * 12.7))), bluePaint);
                        drawDots(0, cellWH * 9, cellWH, state.getDiceVal(), canvas, blackPaint);
                        break;

            }
        }
    }


    public void drawDots(float xPos, float yPos, float shift ,int diceVal,Canvas canvas, Paint color){
        switch (diceVal){
            case 1:
                canvas.drawCircle(xPos+ shift*3f,yPos+ shift*3f,18f,color);
                break;
            case 2:
                canvas.drawCircle(xPos + (shift*2.7f), yPos + (shift*2.7f),18f,color);
                canvas.drawCircle(xPos + (shift*3.3f), yPos + (shift*3.3f),18f,color);
                break;
            case 3:
                canvas.drawCircle(xPos + (shift*2.7f), yPos + (shift*2.7f),18f,color);
                canvas.drawCircle(xPos + (shift*3.3f), yPos + (shift*3.3f),18f,color);
                canvas.drawCircle(xPos + (shift*3f), yPos +  (shift*3f),18f,color);
                break;
            case 4:
                canvas.drawCircle(xPos + (shift*2.7f), yPos + (shift*2.7f),15f,color);
                canvas.drawCircle(xPos + (shift*2.7f), yPos + (shift*3.3f),15f,color);
                canvas.drawCircle(xPos + (shift*3.3f), yPos + (shift*2.7f),15f,color);
                canvas.drawCircle(xPos + (shift*3.3f), yPos + (shift*3.3f),15f,color);
                break;
            case 5:
                canvas.drawCircle(xPos + (shift*2.6f), yPos + (shift*2.6f),15f,color);
                canvas.drawCircle(xPos + (shift*2.6f), yPos + (shift*3.4f),15f,color);
                canvas.drawCircle(xPos + (shift*3.4f), yPos + (shift*2.6f),15f,color);
                canvas.drawCircle(xPos + (shift*3.4f), yPos + (shift*3.4f),15f,color);
                canvas.drawCircle(xPos + (shift*3f), yPos + (shift*3f),15f,color);
                break;
            case 6:
                canvas.drawCircle(xPos + (shift*2.6f), yPos + (shift*2.6f),15f,color);
                canvas.drawCircle(xPos + (shift*2.6f), yPos + (shift*3.4f),15f,color);
                canvas.drawCircle(xPos + (shift*3.4f), yPos + (shift*2.6f),15f,color);
                canvas.drawCircle(xPos + (shift*3.4f), yPos + (shift*3.4f),15f,color);
                canvas.drawCircle(xPos + (shift*2.6f), yPos + (shift*3f),15f,color);
                canvas.drawCircle(xPos + (shift*3.4f), yPos + (shift*3f),15f,color);
                break;
            default:
                break;
        }
    }


public void drawHomeBase(Canvas canvas){

    RectF base = new RectF(0, 0, 6 * cellWH, 6 * cellWH);
    base.inset(1f,1f);

    RectF gate1 = new RectF(cellWH, 4f*cellWH, 2f*cellWH, base.bottom);
    RectF gate2 = new RectF(gate1);
    gate2.inset(0.03f*cellWH,0);

    boolean drawGate = false;

    float center = cellWH*7.5f;

    for (int p = 0; p < 4; p++){ //through each player

        drawGate = state.getDiceVal()==6 && state.getIsRollUsable() && p == state.getWhoseMove();

        canvas.drawRect(base, boardPaint[p]);
        if(drawGate){
            canvas.drawRect(gate1, blackPaintBor);
        }
        canvas.drawCircle(base.centerX(), base.centerY(), 2.4f*cellWH, blackPaintBor);
        canvas.drawCircle(base.centerX(), base.centerY(), 2.37f*cellWH, whitePaintBor);
        if(drawGate){
            canvas.drawRect(gate2, whitePaintBor);
        }

//        for (int i = 0; i < 4; i ++) { //through each of their pieces
//            canvas.drawRect(startPositions[0][i], whitePaintBor);
//        }
        canvas.rotate( 90f, center, center);

    }
}

    //created by Luke//TODO: fix when it goes to home stretch
    public void drawPieces(Canvas canvas) {

        if(state!=null) {
//
//
//            for(int i = 0; i<57 ; i++){
//                int numColors=0;
//                int numPieces = 0;
//                for(int t = 0; t<16; t++){
//                    if(state.pieces[t].getAdjustedNumSpacesMoved()==i){
//
//                    }
//                }
//
//
//
//
//            }
//
//
//
//
//
//
//
//
//
//
//
//
////
////            float scale = 2f/3f;
////
//            ArrayList<RectF>[] drawOnThese = new ArrayList[4]; //typically 4p x 4loc
//            ArrayList<Integer>[][] indexOfOverlap = new ArrayList[4][4];
//
//            ArrayList<RectF> draw0 = new ArrayList<>();
//            ArrayList<RectF> draw1 = new ArrayList<>();
//            ArrayList<RectF> draw2 = new ArrayList<>();
//            ArrayList<RectF> draw3 = new ArrayList<>();
//
//            ArrayList<Integer> overlapInt = new ArrayList<>();
//
//            List<List<Integer>> outer = new ArrayList<List<Integer>>(4);
//            ArrayList<Integer> inner = new ArrayList<Integer>();
//
//            inner.add(100);
//            inner.add(200);
//            outer.add(inner); // add first list
//            inner = new ArrayList<Integer>(inner); // create a new inner list that has the same content as
//            // the original inner list
//            outer.add(inner); // add second list
//
//            outer.get(0).add(300); // changes only the first inner list
//
//
//
//
//            /*
//             * https://stackoverflow.com/questions/23460367/comparing-elements-of-the-same-array-in-java
//             */
//            for(int p = 0; p<4 ; p++) { //for each player
//
//
//                for(int i = 0; i<4 ; i++) { //for each player
//                    for (int m, k = i + 1; k < 16; k++) { //
//                        if ((m = state.pieces[i].getAdjustedNumSpacesMoved()) == state.pieces[k].getAdjustedNumSpacesMoved()) {
//                            switch(state.pieces[i].getTokenState()){
//                                case 0:
//                                    drawOnThese.add(startPositions[p][i / 4]);
//                                    break;
//                                case 1:
//                                    drawOnThese.add(boardPositions[m]);
//                                    break;
//                                case 2:
//                                case 3:
//                                    drawOnThese.add(homeStretchPositions[p][m-51]);
//                                    break;
//                            }
//                            inner.add(i);
//                        }
//                    }
//                    outer.add(i, inner);
//                }
//
//            }
////            Set<Integer> over = new LinkedHashSet<>(overlapInt);
//
//
//            switch (state.pieces[i].getTokenState()) {
//                case 0: //is starting in home base
//                    canvas.drawCircle(startPositions[p][i / 4].centerX(), startPositions[p][i / 4].centerY(), cellWH * .38f, blackPaintBor);
//                    canvas.drawCircle(startPositions[p][i / 4].centerX(), startPositions[p][i / 4].centerY(), cellWH * .35f, tokenPaint[p]);
//                    break;
//                case 1: //is en route
//                    int indexInBoard = state.pieces[i].getAdjustedNumSpacesMoved();
//                    canvas.drawCircle(boardPositions[indexInBoard].centerX(), boardPositions[indexInBoard].centerY(), cellWH * .38f, blackPaintBor);
//                    canvas.drawCircle(boardPositions[indexInBoard].centerX(), boardPositions[indexInBoard].centerY(), cellWH * .35f, tokenPaint[p]);
//                    break;
//                case 2: //is in home stretch
//                case 3: //is in last index of home stretch
//                    int indexInHomeStretch = state.pieces[i].getNumSpacesMoved() - 51;
//                    canvas.drawCircle(homeStretchPositions[p][indexInHomeStretch].centerX(), homeStretchPositions[p][indexInHomeStretch].centerY(), cellWH * .38f, blackPaintBor);
//                    canvas.drawCircle(homeStretchPositions[p][indexInHomeStretch].centerX(), homeStretchPositions[p][indexInHomeStretch].centerY(), cellWH * .35f, tokenPaint[p]);
//                    break;
//            }


            //draw the array we created when we were passed the most recent state
            for(TileF k : occupiedTiles){


                int total = k.sum();

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < k.onMe[i]; j++) {

                        canvas.drawCircle(k.centerX(), k.centerY(), cellWH * .38f, blackPaintBor);
                        canvas.drawCircle(k.centerX(), k.centerY(), cellWH * .35f, tokenPaint[i]);

                    }
                }
            }


        }
    }



private void generateOccupiedArray(){

        for (int p, r = 0; r < 4; r++) { //through each player
            p = ~(state.getWhoseMove() + r) & 3; //reverse order so we draw current player last
            for (int i = p; i < 16; i += 4) { //through each of their pieces

                boolean added = false;

                switch (state.pieces[i].getTokenState()) {
                    case 0: //is starting in home base
                        occupiedTiles.add(new TileF(startPositions[p][i / 4], state.pieces[i].getOwner()));
                        break;
                    case 1: //is en route
                        int indexInBoard = state.pieces[i].getAdjustedNumSpacesMoved();
                        //if location has already been recorded, add it to that tile
                        for (TileF k : occupiedTiles){
                            if(k.contains(boardPositions[indexInBoard])){
                                k.add(state.pieces[i].getOwner());
                                added = true;
                                break;
                            }
                        }
                        if( ! added)
                            occupiedTiles.add(new TileF(boardPositions[indexInBoard], state.pieces[i].getOwner()));
                        break;
                    case 2: //is in home stretch
                    case 3: //is in last index of home stretch
                        int indexInHomeStretch = state.pieces[i].getNumSpacesMoved() - 51;
                        //if location has already been recorded, add it to that tile
                        for (TileF k : occupiedTiles){
                            if(k.contains(homeStretchPositions[p][indexInHomeStretch])){
                                k.add(state.pieces[i].getOwner());
                                added = true;
                                break;
                            }
                        }
                        if( ! added)
                            occupiedTiles.add(new TileF(new RectF(homeStretchPositions[p][indexInHomeStretch]), state.pieces[i].getOwner()));
                        break;
                }
            }
        }


    }




    /**
     * getters
     * @return
     */
    public RectF getBoardPositions(int index) {
        return boardPositions[index];
    }

    /**
     * getter for start positions
     * @param player 0-3 for index
     * @param piece 0-3 for index
     * @return
     */
    public RectF getStartPositions(int player, int piece) {
        return startPositions[player][piece];
    }

    public RectF getHomeStretchPositions(int player, int piece) {
        return homeStretchPositions[player][piece];
    }


}
