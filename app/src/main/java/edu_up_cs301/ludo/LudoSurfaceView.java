package edu_up_cs301.ludo;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import edu_up_cs301.game.GamePlayer;
import edu_up_cs301.game.util.FlashSurfaceView;


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
    private GamePlayer hasMe;
    private RectF[] boardPositions;
    private RectF[][] startPositions;
    private RectF[][] homeStretchPositions;
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
        generalInit();
    }

    public LudoSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        generalInit();
    }

    public void setState(LudoState state) {
        this.state = state;
    }


    /**
     * \
     * generalInit
     * <p>
     * Initialization stuff used by all ctors
     */
    private void generalInit() {
        setWillNotDraw(false);

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
            boardPositions[n].inset(1f,1f);
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
                homeStretchPositions[n1][n2].inset(1f,1f);
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
                startPositions[n1][n2].inset(1f,1f);
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

        if(cellWH<=0) {
            //TODO: synchronize so generalInit will only need to be called once, in constructor after width & height are defined
            generalInit();
        }
        canvas.drawRect(this.getLeft(), this.getTop(), this.getRight(), this.getBottom(), blackPaintBor);
        

        for(int i = 0; i<52 ; i++){
            canvas.drawRect(boardPositions[i],
                    (i == 1 || i == 48) ? redPaintBor :
                            (i == 9 || i == 14) ? greenPaintBor :
                                    (i == 22 || i == 27) ? yellowPaintBor :
                                            (i == 35 || i == 40) ? bluePaintBor : whitePaintBor);
            switch(i) {
//                case 1:
                case 48:
                    canvas.drawCircle(boardPositions[i].centerX(), boardPositions[i].centerY(), cellWH*.4f, whitePaintBor);
                    break;
//                case 14:
                case 9:
                    canvas.drawCircle(boardPositions[i].centerX(), boardPositions[i].centerY(), cellWH*.4f, whitePaintBor);
                    break;
//                case 27:
                case 22:
                    canvas.drawCircle(boardPositions[i].centerX(), boardPositions[i].centerY(), cellWH*.4f, whitePaintBor);
                    break;
//                case 40:
                case 35:
                    canvas.drawCircle(boardPositions[i].centerX(), boardPositions[i].centerY(), cellWH*.4f, whitePaintBor);
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

            drawDice(canvas); //draw the dice
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
                canvas.drawCircle(xPos+ shift*3,yPos+ shift*3,18,color);
                break;
            case 2:
                canvas.drawCircle(xPos + (float)(shift*2.7), yPos + (float)(shift*2.7),18,color);
                canvas.drawCircle(xPos + (float)(shift*3.3), yPos + (float)(shift*3.3),18,color);
                break;
            case 3:
                canvas.drawCircle(xPos + (float)(shift*2.7), yPos + (float)(shift*2.7),18,color);
                canvas.drawCircle(xPos + (float)(shift*3.3), yPos + (float)(shift*3.3),18,color);
                canvas.drawCircle(xPos + (float)(shift*3), yPos + (float)(shift*3),18,color);
                break;
            case 4:
                canvas.drawCircle(xPos + (float)(shift*2.7), yPos + (float)(shift*2.7),15,color);
                canvas.drawCircle(xPos + (float)(shift*2.7), yPos + (float)(shift*3.3),15,color);
                canvas.drawCircle(xPos + (float)(shift*3.3), yPos + (float)(shift*2.7),15,color);
                canvas.drawCircle(xPos + (float)(shift*3.3), yPos + (float)(shift*3.3),15,color);
                break;
            case 5:
                canvas.drawCircle(xPos + (float)(shift*2.6), yPos + (float)(shift*2.6),15,color);
                canvas.drawCircle(xPos + (float)(shift*2.6), yPos + (float)(shift*3.4),15,color);
                canvas.drawCircle(xPos + (float)(shift*3.4), yPos + (float)(shift*2.6),15,color);
                canvas.drawCircle(xPos + (float)(shift*3.4), yPos + (float)(shift*3.4),15,color);
                canvas.drawCircle(xPos + (float)(shift*3), yPos + (float)(shift*3),15,color);
                break;
            case 6:
                canvas.drawCircle(xPos + (float)(shift*2.6), yPos + (float)(shift*2.6),15,color);
                canvas.drawCircle(xPos + (float)(shift*2.6), yPos + (float)(shift*3.4),15,color);
                canvas.drawCircle(xPos + (float)(shift*3.4), yPos + (float)(shift*2.6),15,color);
                canvas.drawCircle(xPos + (float)(shift*3.4), yPos + (float)(shift*3.4),15,color);
                canvas.drawCircle(xPos + (float)(shift*2.6), yPos + (float)(shift*3),15,color);
                canvas.drawCircle(xPos + (float)(shift*3.4), yPos + (float)(shift*3),15,color);
                break;
            default:
                break;
        }
    }


    public void drawStartTiles(Canvas canvas,Paint paint,int xShfit, int yShift){

        int box1X1=(int)(cellWH*2.3),box1Y1=(int)(cellWH*0.7), box1X2=(int)(cellWH*3.7), box1Y2=(int)(cellWH*2.1);
        int box2X1=(int)(cellWH*3.9),box2Y1=(int)(cellWH*2.3), box2X2=(int)(cellWH*5.3), box2Y2=(int)(cellWH*3.7);
        int box3X1=(int)(cellWH*2.3),box3Y1=(int)(cellWH*3.9), box3X2=(int)(cellWH*3.7), box3Y2=(int)(cellWH*5.3);
        int box4X1=(int)(cellWH*0.7),box4Y1=(int)(cellWH*2.3), box4X2=(int)(cellWH*2.1), box4Y2=(int)(cellWH*3.7);
        canvas.drawRect(box1X1+xShfit,box1Y1+yShift,box1X2+xShfit,box1Y2+yShift,paint);
        canvas.drawRect(box2X1+xShfit,box2Y1+yShift,box2X2+xShfit,box2Y2+yShift,paint);
        canvas.drawRect(box3X1+xShfit,box3Y1+yShift,box3X2+xShfit,box3Y2+yShift,paint);
        canvas.drawRect(box4X1+xShfit,box4Y1+yShift,box4X2+xShfit,box4Y2+yShift,paint);

    }

    //created by Luke//TODO: fix when it goes to home stretch
    public void drawPieces(Canvas canvas) {

        if(state!=null) {

            for (int p, r = 0; r < 4; r++) { //through each player
                p = ~(state.getWhoseMove()+r) & 3;
                for (int i = p; i < 16; i += 4) { //through each of their pieces

                    switch (state.pieces[i].getTokenState()) {
                        case 0: //is starting in home base
                            canvas.drawCircle(startPositions[p][i / 4].centerX(), startPositions[p][i / 4].centerY(), cellWH * .37f, blackPaintBor);
                            canvas.drawCircle(startPositions[p][i / 4].centerX(), startPositions[p][i / 4].centerY(), cellWH * .35f, tokenPaint[p]);
                            break;
                        case 1: //is en route
                            int indexInBoard = state.pieces[i].getAdjustedNumSpacesMoved();
                            canvas.drawCircle(boardPositions[indexInBoard].centerX(), boardPositions[indexInBoard].centerY(), cellWH * .37f, blackPaintBor);
                            canvas.drawCircle(boardPositions[indexInBoard].centerX(), boardPositions[indexInBoard].centerY(), cellWH * .35f, tokenPaint[p]);
                            break;
                        case 2: //is in home stretch
                            canvas.drawCircle(homeStretchPositions[p][i / 4].centerX(), homeStretchPositions[p][i / 4].centerY(), cellWH * .37f, blackPaintBor);
                            canvas.drawCircle(homeStretchPositions[p][i / 4].centerX(), homeStretchPositions[p][i / 4].centerY(), cellWH * .35f, tokenPaint[p]);
                            break;
                        default:
                    }
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

    public void setHasMe(GamePlayer hasMe) {
        this.hasMe = hasMe;
    }



}
