package com.sd.lib.blingbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

class ValueHolder
{
    public static final float EMPTY_VALUE = Float.MIN_VALUE;
    public static final int DEFAULT_DURATION_STATE = 300;
    public static final int DEFAULT_DURATION_BLING = 500;

    public final NormalInsideSize mNormalInsideSize = new NormalInsideSize();
    public final NormalOutsideSize mNormalOutsideSize = new NormalOutsideSize();

    public final SelectedInsideSize mSelectedInsideSize = new SelectedInsideSize();
    public final SelectedOutsideSize mSelectedOutsideSize = new SelectedOutsideSize();

    public final BlingInsideDelta mBlingInsideDelta = new BlingInsideDelta();
    public final BlingOutsideDelta mBlingOutsideDelta = new BlingOutsideDelta();

    public final int mStateDuration;
    public final int mBlingDuration;

    public ValueHolder(Context context, AttributeSet attrs)
    {
        if (attrs != null)
        {
            final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LibBlingButton);

            if (a.hasValue(R.styleable.LibBlingButton_bbNormalInside))
                mNormalInsideSize.set(a.getDimension(R.styleable.LibBlingButton_bbNormalInside, EMPTY_VALUE));

            if (a.hasValue(R.styleable.LibBlingButton_bbNormalOutside))
                mNormalOutsideSize.set(a.getDimension(R.styleable.LibBlingButton_bbNormalOutside, EMPTY_VALUE));

            if (a.hasValue(R.styleable.LibBlingButton_bbSelectedInside))
                mSelectedInsideSize.set(a.getDimension(R.styleable.LibBlingButton_bbSelectedInside, EMPTY_VALUE));

            if (a.hasValue(R.styleable.LibBlingButton_bbSelectedOutside))
                mSelectedOutsideSize.set(a.getDimension(R.styleable.LibBlingButton_bbSelectedOutside, EMPTY_VALUE));

            if (a.hasValue(R.styleable.LibBlingButton_bbBlingInsideDelta))
                mBlingInsideDelta.set(a.getDimension(R.styleable.LibBlingButton_bbBlingInsideDelta, EMPTY_VALUE));

            if (a.hasValue(R.styleable.LibBlingButton_bbBlingOutsideDelta))
                mBlingOutsideDelta.set(a.getDimension(R.styleable.LibBlingButton_bbBlingOutsideDelta, EMPTY_VALUE));

            mStateDuration = a.getInteger(R.styleable.LibBlingButton_bbStateDuration, DEFAULT_DURATION_STATE);
            mBlingDuration = a.getInteger(R.styleable.LibBlingButton_bbBlingDuration, DEFAULT_DURATION_BLING);

            a.recycle();
        } else
        {
            mStateDuration = DEFAULT_DURATION_STATE;
            mBlingDuration = DEFAULT_DURATION_BLING;
        }
    }

    public void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        final float oldSize = Math.min(oldw, oldh);
        final float newSize = Math.min(w, h);

        mNormalInsideSize.update(oldSize, newSize);
        mNormalOutsideSize.update(oldSize, newSize);

        mSelectedInsideSize.update(oldSize, newSize);
        mSelectedOutsideSize.update(oldSize, newSize);

        mBlingInsideDelta.update(oldSize, newSize);
        mBlingOutsideDelta.update(oldSize, newSize);
    }
}
