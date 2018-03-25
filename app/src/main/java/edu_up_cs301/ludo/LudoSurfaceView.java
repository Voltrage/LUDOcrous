package edu_up_cs301.ludo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.SurfaceView;

import edu_up_cs301.game.util.FlashSurfaceView;


/**
 * onDraw method Created by nayyar19 and guillermo19 on 2/3/2018.
 *
 * @author Luke
 */

public class LudoSurfaceView extends FlashSurfaceView {

    public LudoSurfaceView(Context context) {
        super(context);
        generalInit();
    }

    public LudoSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        generalInit();
    }

//    public LudoSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        generalInit();
//    }

    /**
     * \
     * generalInit
     * <p>
     * Initialization stuff used by all ctors
     */
    private void generalInit() {
        setWillNotDraw(false);
    }


    /**
     * override the onDraw() method to draw cool stuff for students to see
     *
     * @param canvas - draw on this
     */
    @Override
    public void onDraw(Canvas canvas) {
        //Draw a green filled square
        //Creating Color Objects
        Paint greenPaint = new Paint();
        Paint redPaint = new Paint();
        Paint bluePaint = new Paint();
        Paint yellowPaint = new Paint();
        Paint whitePaint = new Paint();
        Paint blackPaint = new Paint();
        Paint whitePaint2 = new Paint();
        //Creating the colors
        redPaint.setColor(Color.rgb(193, 23, 23));
        greenPaint.setColor(Color.rgb(50, 220, 50));
        bluePaint.setColor(Color.rgb(10, 10, 230));
        yellowPaint.setColor(Color.rgb(242, 228, 38));
        whitePaint.setColor(Color.rgb(255,255,255));
        whitePaint2.setColor(Color.rgb(255,255,255));
        blackPaint.setColor(Color.rgb(0,0,0));


        //Drawing all HomeStrech and Opening Tiles
        float i,j;
        for(i=95; i<570;i=i+95){ j=665;   canvas.drawRect(i, j, (float) (i+95), (float) (j+95), redPaint);} //Red Homestrech
            canvas.drawRect(95, 570,190f,665, redPaint); //Red Open Tile
        for(i=855; i<1330;i=i+95){ j=665; canvas.drawRect(i, j, (float) (i+95), (float) (j+95), yellowPaint);} //Yellow Homestrech
            canvas.drawRect(1235f, 760f, 1330f,855f, yellowPaint); //Yellow Open Tile
        for(j=95; j<570;j=j+95){ i=667;   canvas.drawRect(i, j, (float) (i+95), (float) (j+95), greenPaint);} //Green Homestrech
            canvas.drawRect(760f, 95f, 855f, 190f, greenPaint); //Green Open Tile
        for(j=855; j<1330;j=j+95){ i=667; canvas.drawRect(i, j, (float) (i+95), (float) (j+95), bluePaint);} //Blue Homestrech
            canvas.drawRect(570f, 1235f,665f,1330, bluePaint); //Blue Open Tile

        //Drawing ALL Valid and Invalid small tile squares
        for(i=0; i<(1426-95); i = i+95){
            for(j=0; j<(1426); j = j+95){
                whitePaint2.setStyle(Paint.Style.STROKE);
                whitePaint2.setStrokeWidth(3);
                canvas.drawRect(i, j, (float) (i+95), (float) (i+95), whitePaint2);
                //canvas.restore();
            }
        }
        /*Outlining in a Black square
        blackPaint.setStyle(Paint.Style.STROKE);
         canvas.save();
        canvas.rotate(45,285,60);
        canvas.drawRect(285f, 60f, 628f, 394f, blackPaint);
        canvas.restore();
        */

        //Red Large Tile Section Creation
        canvas.drawRect(0.0f, 0.0f, 570.0f, 570.0f, redPaint);
        canvas.save();
        canvas.rotate(45,285,0);
        canvas.drawRect(285f, 0f, 688f, 403f, whitePaint);
        canvas.restore();
        canvas.drawRect(220f, 65f, 350f, 195f, redPaint); //Top
        canvas.drawRect(220f, 375f, 350f, 505f, redPaint); //Bottom
        canvas.drawRect(370f, 215, 500f, 345f, redPaint);    //Right
        canvas.drawRect(65f,215f,195f,345f, redPaint);    //Left

        //Green Large Tile Section Creation
        canvas.drawRect(855.0f, 0.0f, 1426.0f, 570.0f, greenPaint);
        canvas.save();
        canvas.rotate(45,1140f,0);
        canvas.drawRect(1140f, 0f, 1543, 403f, whitePaint);
        canvas.restore();
        canvas.drawRect(1075f, 65f, 1205f, 195f, greenPaint); //Top
        canvas.drawRect(1075f, 375f, 1205f, 505f, greenPaint); //Bottom
        canvas.drawRect(1225f, 215, 1355f, 345f, greenPaint);    //Right
        canvas.drawRect(920f,215f,1050f,345f, greenPaint);    //Left

        //Blue Large Tile Section Creation
        canvas.drawRect(0.0f, 855.0f, 570.0f, 1426.0f, bluePaint);
        canvas.save();
        canvas.rotate(45,285,855);
        canvas.drawRect(285f, 855f, 688f, 1258f, whitePaint);
        canvas.restore();
        canvas.drawRect(220f, 920f, 350f, 1050f, bluePaint); //Top
        canvas.drawRect(220f, 1230f, 350f, 1360f, bluePaint); //Bottom
        canvas.drawRect(370f, 1070, 500f, 1200f, bluePaint);    //Right
        canvas.drawRect(65f,1070,195f,1200f, bluePaint);    //Left

        //Yellow Large Tile Section Creation
        canvas.drawRect(855.0f, 855.0f, 1426.0f, 1426.0f, yellowPaint);
        canvas.save();
        canvas.rotate(45,1140f,855);
        canvas.drawRect(1140f, 855f, 1543, 1258f, whitePaint);
        canvas.restore();
        canvas.drawRect(1075f, 920f, 1205f, 1050f, yellowPaint); //Top
        canvas.drawRect(1075f, 1230f, 1205f, 1360f, yellowPaint); //Bottom
        canvas.drawRect(1225f, 1070f, 1355f, 1200f, yellowPaint);    //Right
        canvas.drawRect(920f,1070f,1050f,1200f, yellowPaint);    //Left

        //Drawing Center Square
        canvas.drawRect(570, 570, 855,855, whitePaint);

        Point p1 = new Point(570,570); //Top Left
        Point p2 = new Point(713,713); //Dead Center
        Point p3 = new Point(855,570); //Top Right
        Point p4 = new Point(570,855); //Bottom Left
        Point p5 = new Point(855,855); //Bottom Right

        Path tri1 = new Path();
        tri1.moveTo(p1.x,p1.y);
        tri1.lineTo(p2.x,p2.y);
        tri1.lineTo(p3.x,p3.y);
        tri1.close();
        canvas.drawPath(tri1,greenPaint);

        Path tri2 = new Path();
        tri2.moveTo(p1.x,p1.y);
        tri2.lineTo(p2.x,p2.y);
        tri2.lineTo(p4.x,p4.y);
        tri2.close();
        canvas.drawPath(tri2,redPaint);

        Path tri3 = new Path();
        tri3.moveTo(p4.x,p4.y);
        tri3.lineTo(p2.x,p2.y);
        tri3.lineTo(p5.x,p5.y);
        tri3.close();
        canvas.drawPath(tri3,bluePaint);

        Path tri4 = new Path();
        tri4.moveTo(p3.x,p3.y);
        tri4.lineTo(p2.x,p2.y);
        tri4.lineTo(p5.x,p5.y);
        tri4.close();
        canvas.drawPath(tri4,yellowPaint);


        drawStar(190,760,canvas);
        drawStar(760,1140,canvas);
        drawStar(570,190,canvas);
        drawStar(1140,570,canvas);

        drawDice(0,0,canvas);


        Paint greenPiece = new Paint();
        Paint redPiece = new Paint();
        Paint bluePiece = new Paint();
        Paint yellowPiece = new Paint();
        redPiece.setColor(Color.rgb(255,0,0));
        greenPiece.setColor(Color.rgb(80, 255, 95));
        bluePiece.setColor(Color.rgb(75,150,255));
        yellowPiece.setColor(Color.rgb(230, 190, 70));

        canvas.drawCircle(285,130,30,whitePaint);
        canvas.drawCircle(285,130,25,redPiece);

        //R1
        canvas.drawCircle(285,130,30,whitePaint);
        canvas.drawCircle(285,130,25,redPiece);
        //R2
        canvas.drawCircle(132,280,30,whitePaint);
        canvas.drawCircle(132,280,25,redPiece);
        //R3
        canvas.drawCircle(617,712,30,whitePaint);
        canvas.drawCircle(617,712,25,redPiece);
        //R4
        canvas.drawCircle(1092,617,30,whitePaint);
        canvas.drawCircle(1092,617,25,redPiece);

        //B1
        canvas.drawCircle(285,985,30,whitePaint);
        canvas.drawCircle(285,985,25,bluePiece);
        //B2
        canvas.drawCircle(712,1187,30,whitePaint);
        canvas.drawCircle(712,1187,25,bluePiece);
        //B3
        canvas.drawCircle(805,45,30,whitePaint);
        canvas.drawCircle(805,45,25,bluePiece);
        //B4
        canvas.drawCircle(237,807,30,whitePaint);
        canvas.drawCircle(237,807,25,bluePiece);

        //G1
        canvas.drawCircle(1140,130,30,whitePaint);
        canvas.drawCircle(1140,130,25,greenPiece);
        //G2
        canvas.drawCircle(990,280,30,whitePaint);
        canvas.drawCircle(990,280,25,greenPiece);
        //G3
        canvas.drawCircle(1288,280,30,whitePaint);
        canvas.drawCircle(1288,280,25,greenPiece);
        //G4
        canvas.drawCircle(617,1092,30,whitePaint);
        canvas.drawCircle(617,1092,25,greenPiece);

        //Y1
        canvas.drawCircle(1140,980,30,whitePaint);
        canvas.drawCircle(1140,980,25,yellowPiece);
        //Y2
        canvas.drawCircle(990,1135,30,whitePaint);
        canvas.drawCircle(990,1135,25,yellowPiece);
        //Y3
        canvas.drawCircle(1288,1135,30,whitePaint);
        canvas.drawCircle(1288,1135,25,yellowPiece);
        //Y4
        canvas.drawCircle(1140,1295,30,whitePaint);
        canvas.drawCircle(1140,1295,25,yellowPiece);




    }

    public void drawStar(int xPos,  int yPos, Canvas canvas){
        Paint whitePaint = new Paint();
        whitePaint.setColor(Color.rgb(255,255,255));
        Point p1 = new Point(xPos+11,yPos+29); //Bottom Left of Lower Star
        Point p2 = new Point(xPos+85,yPos+29); //Bottom Right of Lower Star
        Point p3 = new Point(xPos+47,yPos+87); //Top of Star

        Point p4 = new Point(xPos+11,yPos+66); //Left point of Uppper Star
        Point p5 = new Point(xPos+85,yPos+66); //Right point of Upper Star
        Point p6 = new Point(xPos+47,yPos+8); //Bottom of Star


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

    public void drawDice(int xPos, int yPos, Canvas canvas){
        Paint greyPaint = new Paint();
        Paint redPaint = new Paint();

        greyPaint.setColor(Color.rgb(100,100,100));
        redPaint.setColor(Color.rgb(255,0,0));
        //Drawing the Face of Die
        canvas.drawRect(220.0f, 220.0f, 350.0f, 350.0f, greyPaint);
        //Drawing the Dots
        canvas.drawCircle(306,263,10,redPaint);
        canvas.drawCircle(285,285,10,redPaint);
        canvas.drawCircle(263,307,10,redPaint);
    }


}
