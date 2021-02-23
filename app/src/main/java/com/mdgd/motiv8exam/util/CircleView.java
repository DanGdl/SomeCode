package com.mdgd.motiv8exam.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class CircleView extends View {
    private final Paint paint = new Paint();

    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(((ColorDrawable) getBackground()).getColor());
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(((ColorDrawable) getBackground()).getColor());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        final float radius = Math.min(
                getWidth() - getPaddingLeft() + getPaddingRight(),
                getHeight() - getPaddingBottom() - getPaddingTop()) / 2F;

        final float cX = getWidth() / 2F;
        final float cY = getHeight() / 2F;
        canvas.drawCircle(cX, cY, radius, paint);
    }

    @Override
    public void setBackgroundColor(int color) {
        paint.setColor(color);
    }
}
