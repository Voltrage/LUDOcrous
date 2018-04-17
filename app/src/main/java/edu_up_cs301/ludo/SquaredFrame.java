package edu_up_cs301.ludo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by Luke on 3/26/2018.
 * Based off https://stackoverflow.com/questions/11121963/how-can-i-set-camera-preview-size-to-squared-aspect-ratio-in-a-squared-surfaceview
 */

public class SquaredFrame extends RelativeLayout {
    public SquaredFrame(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = Math.min(getMeasuredWidth(), getMeasuredHeight());
        size = size - size%15;
        setMeasuredDimension(size, size);
    }

}
