package com.sd.lib.blingbutton;

/**
 * 选中状态内直径
 */
class SelectedInsideSize extends SizeHolder
{
    @Override
    protected float getDefault(float size)
    {
        return size * 0.9f;
    }
}
