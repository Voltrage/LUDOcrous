package edu_up_cs301.ludo;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.ArrayList;
import java.util.List;

public class TileF extends RectF {

    public int[] onMe;
    private int sum;

    private float scale = 0.35f;

    public TileF(RectF r, int piece_Owner) {
        super(r);
        sum = 0;
        onMe = new int[] {0,0,0,0};
        add(piece_Owner);
    }

    public void add(int pieceOwner) {
        sum++;
        //index equals player number, we are counting their tokens
        onMe[pieceOwner]++;

    }

    public int sum(){
        return sum;
    }

}
