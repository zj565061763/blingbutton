# About
Android 闪烁按钮，仿映客录制小视频按钮

# Gradle
[![](https://jitpack.io/v/zj565061763/blingbutton.svg)](https://jitpack.io/#zj565061763/blingbutton)

# Demo

# 支持的属性
```xml
<resources>

    <declare-styleable name="LibBlingButton">
        <!-- 正常状态内边直径，默认0 -->
        <attr name="bbNormalInside" format="dimension" />

        <!-- 正常状态外边直径，默认70% -->
        <attr name="bbNormalOutside" format="dimension" />

        <!-- 选中状态内边直径，默认90% -->
        <attr name="bbSelectedInside" format="dimension" />

        <!-- 选中状态外边直径，默认100% -->
        <attr name="bbSelectedOutside" format="dimension" />

        <!-- 闪烁状态内边直径相对于选中状态内边直径的偏移量，默认10% -->
        <attr name="bbBlingInsideDelta" format="dimension" />

        <!-- 闪烁状态外边直径相对于选中状态外边直径的偏移量，默认0 -->
        <attr name="bbBlingOutsideDelta" format="dimension" />

        <!-- 状态切换的动画时长，默认300毫秒 -->
        <attr name="bbStateDuration" format="integer" />

        <!-- 闪烁一次动画时长，默认500毫秒 -->
        <attr name="bbBlingDuration" format="integer" />

        <!-- 颜色，默认白色 -->
        <attr name="bbColor" format="color" />
    </declare-styleable>

</resources>
```