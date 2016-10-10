package com.example.huangqihua.mytransparentpopuwindow.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by huangqihua on 16/10/10.
 * 圆角ListView
 */
public class HQHRoundCornerListView extends ListView {

    // is round
    private boolean bRoundCorner;
    // background color
    private int mBackgroundColor;
    // round corner
    private int mRoundCorner;
    // press color
    private int mPressColor;
    // stroke color
    private int mStrokeColor;
    // stroke width
    private float mStrokeWidth;

    public HQHRoundCornerListView(Context context) {
        super(context);
        initDefaultValue();
    }

    public HQHRoundCornerListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDefaultValue();
        mBackgroundColor = Color.parseColor("#cc666666");

    }

    @SuppressWarnings("deprecation")
    private void initDefaultValue() {
        bRoundCorner = true;
        mBackgroundColor = Color.parseColor("#FFFFFF");
        mRoundCorner = 4;
        mPressColor = Color.parseColor("#1FA9E0");
        mStrokeColor = Color.parseColor("#DFE0E5");
        mStrokeWidth = 0.2f;
        if (bRoundCorner) {
            setBackgroundDrawable(getShapeBackround(mBackgroundColor, new float[] { dip2px(mRoundCorner),
                    dip2px(mRoundCorner), dip2px(mRoundCorner), dip2px(mRoundCorner), dip2px(mRoundCorner),
                    dip2px(mRoundCorner), dip2px(mRoundCorner), dip2px(mRoundCorner) }));
        }
    }

    /**
     * @param bool
     */
    public void setBooleanRoundConre(boolean bool) {
        this.bRoundCorner = bool;

    }

    /**
     * @param color
     */
    @SuppressWarnings("deprecation")
    public void setBackgroundColor(int color) {
        this.mBackgroundColor = color;
        if (bRoundCorner) {
            setBackgroundDrawable(getShapeBackround(mBackgroundColor, new float[] { dip2px(mRoundCorner),
                    dip2px(mRoundCorner), dip2px(mRoundCorner), dip2px(mRoundCorner), dip2px(mRoundCorner),
                    dip2px(mRoundCorner), dip2px(mRoundCorner), dip2px(mRoundCorner) }));
        } else {
            setBackgroundDrawable(getShapeBackround(mBackgroundColor, new float[] { dip2px(0), dip2px(0), dip2px(0),
                    dip2px(0), dip2px(0), dip2px(0), dip2px(0), dip2px(0) }));
        }
    }

    /**
     * @param corner
     */
    public void setRoundCorner(int corner) {
        this.mRoundCorner = corner;
    }

    /**
     * @param color
     */
    public void setPressedColor(int color) {
        this.mPressColor = color;
    }

    /**
     * @param color
     */
    public void setStrokeColor(int color) {
        this.mStrokeColor = color;
    }

    /**
     * @param width
     */
    public void setStokeWidth(int width) {
        this.mStrokeWidth = width;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (bRoundCorner) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    int x = (int) ev.getX();
                    int y = (int) ev.getY();
                    int itemnum = pointToPosition(x, y);
                    if (itemnum == AdapterView.INVALID_POSITION)
                        break;
                    else {
                        if (itemnum == 0) {
                            if (itemnum == (getAdapter().getCount() - 1)) {
                                // 只有一项
                                setSelector(newSelector(Color.TRANSPARENT, mPressColor, Color.WHITE, Color.WHITE,
                                        new float[] { dip2px(mRoundCorner), dip2px(mRoundCorner), dip2px(mRoundCorner),
                                                dip2px(mRoundCorner), dip2px(mRoundCorner), dip2px(mRoundCorner),
                                                dip2px(mRoundCorner), dip2px(mRoundCorner) }));
                            } else {
                                // 第一项
                                setSelector(newSelector(Color.TRANSPARENT, mPressColor, Color.BLUE, Color.WHITE,
                                        new float[] { dip2px(mRoundCorner), dip2px(mRoundCorner), dip2px(mRoundCorner),
                                                dip2px(mRoundCorner), 0, 0, 0, 0 }));

                            }
                        } else if (itemnum == (getAdapter().getCount() - 1))
                            // 最后一项
                            setSelector(newSelector(Color.TRANSPARENT, mPressColor, Color.BLUE, Color.WHITE, new float[] {
                                    0, 0, 0, 0, dip2px(mRoundCorner), dip2px(mRoundCorner), dip2px(mRoundCorner),
                                    dip2px(mRoundCorner) }));
                        else {
                            // 中间项
                            setSelector(newSelector(Color.TRANSPARENT, mPressColor, Color.BLUE, Color.WHITE, new float[] {
                                    0, 0, 0, 0, 0, 0, 0, 0 }));
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    public StateListDrawable newSelector(int idNormal, int idPressed, int idFocused, int idUnable, float[] radii) {
        StateListDrawable bg = new StateListDrawable();

        // View.PRESSED_ENABLED_STATE_SET
        bg.addState(new int[] { android.R.attr.state_pressed, android.R.attr.state_enabled },
                getItemShape(idPressed, radii));
        // View.ENABLED_FOCUSED_STATE_SET
        bg.addState(new int[] { android.R.attr.state_enabled, android.R.attr.state_focused },
                getItemShape(idFocused, radii));
        // View.ENABLED_STATE_SET
        bg.addState(new int[] { android.R.attr.state_enabled }, getItemShape(idNormal, radii));
        // View.FOCUSED_STATE_SET
        bg.addState(new int[] { android.R.attr.state_focused }, getItemShape(idFocused, radii));
        // View.WINDOW_FOCUSED_STATE_SET
        bg.addState(new int[] { android.R.attr.state_window_focused }, getItemShape(idUnable, radii));
        // View.EMPTY_STATE_SET
        bg.addState(new int[] {}, getItemShape(idNormal, radii));
        return bg;
    }

    private GradientDrawable getItemShape(int color, float[] radii) {
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(color);
        gd.setCornerRadii(radii);
        gd.setStroke(dip2px(0), mStrokeColor);
        return gd;
    }

    private GradientDrawable getShapeBackround(int color, float[] radii) {
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(color);
        gd.setCornerRadii(radii);
        gd.setStroke(dip2px(mStrokeWidth), mStrokeColor);
        return gd;
    }

    /**
     * @param dpValue
     * @return dip 转换成px
     */
    public int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
