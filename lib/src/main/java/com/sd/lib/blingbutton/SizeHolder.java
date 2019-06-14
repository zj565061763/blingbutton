package com.sd.lib.blingbutton;

abstract class SizeHolder
{
    private Float mSize;

    public final void set(float size)
    {
        mSize = size;
    }

    public final Float get()
    {
        if (mSize == null)
            throw new RuntimeException("Size is not ready: " + getClass().getSimpleName());
        return mSize;
    }

    public final void update(float oldSize, float newSize)
    {
        if (newSize <= 0)
            return;

        if (oldSize == newSize)
            return;

        final float scale = oldSize == 0 ? 1.0f : newSize / oldSize;

        if (mSize == null || mSize == ValueHolder.EMPTY_VALUE)
            mSize = getDefault(newSize);
        else
            mSize = mSize * scale;

        mSize = Math.min(mSize, newSize);
    }

    protected abstract float getDefault(float size);
}
