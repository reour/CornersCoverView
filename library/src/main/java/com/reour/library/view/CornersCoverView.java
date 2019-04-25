package com.reour.library.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.View;

/**
 * Android corners cover view.
 * Created by reour.
 */
public class CornersCoverView extends View {

    private int mLeftTopRadius;
    private int mLeftBottomRadius;
    private int mRightTopRadius;
    private int mRightBottomRadius;
    private int mLeftTopColor;
    private int mLeftBottomColor;
    private int mRightTopColor;
    private int mRightBottomColor;

    private Paint mPaint;
    private PorterDuffXfermode mPorterDuffXfermode;

    public CornersCoverView(Context context) {
        this(context, null, 0);
    }

    public CornersCoverView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CornersCoverView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CornersCoverView);
        int cornersRadius = typedArray.getDimensionPixelSize(R.styleable.CornersCoverView_corners_radius, 0);
        mLeftTopRadius = typedArray.getDimensionPixelSize(R.styleable.CornersCoverView_left_top_radius, cornersRadius);
        mLeftBottomRadius = typedArray.getDimensionPixelSize(R.styleable.CornersCoverView_left_bottom_radius, cornersRadius);
        mRightTopRadius = typedArray.getDimensionPixelSize(R.styleable.CornersCoverView_right_top_radius, cornersRadius);
        mRightBottomRadius = typedArray.getDimensionPixelSize(R.styleable.CornersCoverView_right_bottom_radius, cornersRadius);
        int cornersColor = typedArray.getColor(R.styleable.CornersCoverView_corners_color, Color.TRANSPARENT);
        mLeftTopColor = typedArray.getColor(R.styleable.CornersCoverView_left_top_color, cornersColor);
        mLeftBottomColor = typedArray.getColor(R.styleable.CornersCoverView_left_bottom_color, cornersColor);
        mRightTopColor = typedArray.getColor(R.styleable.CornersCoverView_right_top_color, cornersColor);
        mRightBottomColor = typedArray.getColor(R.styleable.CornersCoverView_right_bottom_color, cornersColor);
        typedArray.recycle();
        initView();
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setFilterBitmap(false);
        mPaint.setStyle(Paint.Style.FILL);
        mPorterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT);
    }

    /**
     * set radius of corners.
     */
    public void setRadius(int cornersRadius) {
        setRadius(cornersRadius, cornersRadius, cornersRadius, cornersRadius);
    }

    /**
     * set radius of corners.
     */
    public void setRadius(int leftTopRadius, int rightTopRadius, int leftBottomRadius, int rightBottomRadius) {
        this.mLeftTopRadius = leftTopRadius;
        this.mRightTopRadius = rightTopRadius;
        this.mLeftBottomRadius = leftBottomRadius;
        this.mRightBottomRadius = rightBottomRadius;
    }

    /**
     * set color of corners.
     */
    public void setCornersColor(@ColorInt int cornersColor) {
        setCornersColor(cornersColor, cornersColor, cornersColor, cornersColor);
    }

    /**
     * set color of corners.
     */
    public void setCornersColor(@ColorInt int leftTopColor, @ColorInt int rightTopColor,
                                @ColorInt int leftBottomColor, @ColorInt int rightBottomColor) {
        this.mLeftTopColor = leftTopColor;
        this.mRightTopColor = rightTopColor;
        this.mLeftBottomColor = leftBottomColor;
        this.mRightBottomColor = rightBottomColor;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int sc = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(drawArc(getWidth(), getHeight()), 0, 0, mPaint);
        mPaint.setXfermode(mPorterDuffXfermode);
        canvas.drawBitmap(drawRect(getWidth(), getHeight()), 0, 0, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(sc);
    }

    private Bitmap drawArc(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(0xFFFFFFFF);
        c.drawArc(new RectF(0, 0, mLeftTopRadius * 2, mLeftTopRadius * 2),
                180, 90, true, p);
        c.drawArc(new RectF(0, getHeight() - mLeftBottomRadius * 2, mLeftBottomRadius * 2, getHeight()),
                90, 90, true, p);
        c.drawArc(new RectF(getWidth() - mRightTopRadius * 2, 0, getWidth(), mRightTopRadius * 2),
                270, 90, true, p);
        c.drawArc(new RectF(getWidth() - mRightBottomRadius * 2, getHeight() - mRightBottomRadius * 2, getWidth(), getHeight()),
                0, 90, true, p);
        return bm;
    }

    private Bitmap drawRect(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(mLeftTopColor);
        c.drawRect(new RectF(0, 0, mLeftTopRadius, mLeftTopRadius), p);
        p.setColor(mLeftBottomColor);
        c.drawRect(new RectF(0, getHeight() - mLeftBottomRadius, mLeftBottomRadius, getHeight()), p);
        p.setColor(mRightTopColor);
        c.drawRect(new RectF(getWidth() - mRightTopRadius, 0, getWidth(), mRightTopRadius), p);
        p.setColor(mRightBottomColor);
        c.drawRect(new RectF(getWidth() - mRightBottomRadius, getHeight() - mRightBottomRadius,
                getWidth(), getHeight()), p);
        return bm;
    }
}
