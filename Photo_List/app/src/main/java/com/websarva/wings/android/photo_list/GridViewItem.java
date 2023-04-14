package com.websarva.wings.android.photo_list;

import android.content.Context;
import android.util.AttributeSet;

import android.widget.RelativeLayout;

public class GridViewItem extends RelativeLayout {

    private boolean mAdjustWidth;

    boolean expanded = false;

    private static final int COLUMN_SIZE = 150;

    public GridViewItem(Context context) { super(context); }

    public GridViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridViewItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

}
