package com.sd.lib.blingbutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class FBlingButton extends View
{
    private final ValueHolder mValueHolder;
    private final Paint mPaint = new Paint();

    private Float mCurrentInside;
    private Float mCurrentOutside;

    private ValueAnimator mAnimatorInsideState;
    private ValueAnimator mAnimatorOutsideState;
    private AnimatorListenerAdapter mAnimatorListenerStateChanged;

    private ValueAnimator mAnimatorInsideBling;
    private ValueAnimator mAnimatorOutsideBling;

    private ValueAnimator.AnimatorUpdateListener mAnimatorUpdateListenerInside;
    private ValueAnimator.AnimatorUpdateListener mAnimatorUpdateListenerOutside;

    private boolean mSelectedTag = false;

    public FBlingButton(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mValueHolder = new ValueHolder(context, attrs);
        mPaint.setAntiAlias(true);
        mPaint.setColor(mValueHolder.mColor);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    private ValueAnimator getAnimatorInsideState()
    {
        if (mAnimatorInsideState == null)
        {
            mAnimatorInsideState = new ValueAnimator();
            mAnimatorInsideState.addUpdateListener(getAnimatorUpdateListenerInside());
            mAnimatorInsideState.setDuration(mValueHolder.mStateDuration);
            mAnimatorInsideState.addListener(getAnimatorListenerStateChanged());
        }
        return mAnimatorInsideState;
    }

    private ValueAnimator getAnimatorOutsideState()
    {
        if (mAnimatorOutsideState == null)
        {
            mAnimatorOutsideState = new ValueAnimator();
            mAnimatorOutsideState.addUpdateListener(getAnimatorUpdateListenerOutside());
            mAnimatorOutsideState.setDuration(mValueHolder.mStateDuration);
        }
        return mAnimatorOutsideState;
    }

    private ValueAnimator getAnimatorInsideBling()
    {
        if (mAnimatorInsideBling == null)
        {
            mAnimatorInsideBling = new ValueAnimator();
            mAnimatorInsideBling.addUpdateListener(getAnimatorUpdateListenerInside());
            mAnimatorInsideBling.setDuration(mValueHolder.mBlingDuration);
            mAnimatorInsideBling.setRepeatMode(ValueAnimator.REVERSE);
            mAnimatorInsideBling.setRepeatCount(-1);
        }
        return mAnimatorInsideBling;
    }

    private ValueAnimator getAnimatorOutsideBling()
    {
        if (mAnimatorOutsideBling == null)
        {
            mAnimatorOutsideBling = new ValueAnimator();
            mAnimatorOutsideBling.addUpdateListener(getAnimatorUpdateListenerOutside());
            mAnimatorOutsideBling.setDuration(mValueHolder.mBlingDuration);
            mAnimatorOutsideBling.setRepeatMode(ValueAnimator.REVERSE);
            mAnimatorOutsideBling.setRepeatCount(-1);
        }
        return mAnimatorOutsideBling;
    }

    private ValueAnimator.AnimatorUpdateListener getAnimatorUpdateListenerInside()
    {
        if (mAnimatorUpdateListenerInside == null)
        {
            mAnimatorUpdateListenerInside = new ValueAnimator.AnimatorUpdateListener()
            {
                @Override
                public void onAnimationUpdate(ValueAnimator animation)
                {
                    mCurrentInside = (Float) animation.getAnimatedValue();
                    invalidate();
                }
            };
        }
        return mAnimatorUpdateListenerInside;
    }

    private ValueAnimator.AnimatorUpdateListener getAnimatorUpdateListenerOutside()
    {
        if (mAnimatorUpdateListenerOutside == null)
        {
            mAnimatorUpdateListenerOutside = new ValueAnimator.AnimatorUpdateListener()
            {
                @Override
                public void onAnimationUpdate(ValueAnimator animation)
                {
                    mCurrentOutside = (Float) animation.getAnimatedValue();
                    invalidate();
                }
            };
        }
        return mAnimatorUpdateListenerOutside;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        mValueHolder.onSizeChanged(w, h, oldw, oldh);

        if (mCurrentInside == null || mCurrentOutside == null)
            setCurrentSizeByState();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom)
    {
        super.onLayout(changed, left, top, right, bottom);

        if (mSelectedTag)
            startSelectedAnimator();
    }

    private void setCurrentSizeByState()
    {
        if (mSelectedTag)
        {
            mCurrentInside = mValueHolder.mSelectedInsideSize.get();
            mCurrentOutside = mValueHolder.mSelectedOutsideSize.get();
        } else
        {
            mCurrentInside = mValueHolder.mNormalInsideSize.get();
            mCurrentOutside = mValueHolder.mNormalOutsideSize.get();
        }
    }

    @Override
    public void setSelected(boolean selected)
    {
        super.setSelected(selected);

        final boolean old = mSelectedTag;
        if (old != selected)
        {
            mSelectedTag = selected;
            stopAnimator();

            if (selected)
            {
                startSelectedAnimator();
            } else
            {
                startNormalAnimator();
            }
        }
    }

    private void startSelectedAnimator()
    {
        getAnimatorInsideState().setFloatValues(mCurrentInside, mValueHolder.mSelectedInsideSize.get());
        getAnimatorInsideState().start();

        getAnimatorOutsideState().setFloatValues(mCurrentOutside, mValueHolder.mSelectedOutsideSize.get());
        getAnimatorOutsideState().start();
    }

    private void startNormalAnimator()
    {
        getAnimatorInsideState().setFloatValues(mCurrentInside, mValueHolder.mNormalInsideSize.get());
        getAnimatorInsideState().start();

        getAnimatorOutsideState().setFloatValues(mCurrentOutside, mValueHolder.mNormalOutsideSize.get());
        getAnimatorOutsideState().start();
    }

    private AnimatorListenerAdapter getAnimatorListenerStateChanged()
    {
        if (mAnimatorListenerStateChanged == null)
        {
            mAnimatorListenerStateChanged = new AnimatorListenerAdapter()
            {
                @Override
                public void onAnimationEnd(Animator animation)
                {
                    super.onAnimationEnd(animation);
                    if (mSelectedTag)
                    {
                        getAnimatorInsideBling().setFloatValues(mValueHolder.mSelectedInsideSize.get(),
                                mValueHolder.mSelectedInsideSize.get() - mValueHolder.mBlingInsideDelta.get());
                        getAnimatorInsideBling().start();

                        getAnimatorOutsideBling().setFloatValues(mValueHolder.mSelectedOutsideSize.get(),
                                mValueHolder.mSelectedOutsideSize.get() + mValueHolder.mBlingOutsideDelta.get());
                        getAnimatorOutsideBling().start();
                    } else
                    {

                    }
                }
            };
        }
        return mAnimatorListenerStateChanged;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        final float strokeWidth = (mCurrentOutside - mCurrentInside) / 2;

        final float x = getWidth() / 2;
        final float y = getHeight() / 2;
        final float radius = mCurrentOutside / 2;
        final RectF rectF = newRectF(x, y, radius);

        rectF.inset(strokeWidth / 2, strokeWidth / 2);
        mPaint.setStrokeWidth(strokeWidth);
        canvas.drawArc(rectF, 0, 360, false, mPaint);
    }

    @Override
    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        stopAnimator();
    }

    private void stopAnimator()
    {
        if (mAnimatorInsideState != null)
            mAnimatorInsideState.cancel();

        if (mAnimatorOutsideState != null)
            mAnimatorOutsideState.cancel();

        if (mAnimatorInsideBling != null)
            mAnimatorInsideBling.cancel();

        if (mAnimatorOutsideBling != null)
            mAnimatorOutsideBling.cancel();
    }

    private static RectF newRectF(float x, float y, float radius)
    {
        final float left = x - radius;
        final float right = x + radius;
        final float top = y - radius;
        final float bottom = y + radius;
        return new RectF(left, top, right, bottom);
    }
}
