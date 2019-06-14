package com.sd.lib.blingbutton;

/**
 * 闪烁状态内直径相对于选中状态内直径的偏移量
 */
class BlingInsideDelta extends SizeHolder
{
    @Override
    protected float getDefault(float size)
    {
        return size * 0.1f;
    }
}
