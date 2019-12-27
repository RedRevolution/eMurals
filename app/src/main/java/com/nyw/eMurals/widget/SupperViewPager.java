package com.nyw.eMurals.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class SupperViewPager extends ViewPager {
    private int screenWidth;//屏幕宽度
    public SupperViewPager(Context context) {
        super(context);
    }
    public SupperViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        screenWidth = MeasureSpec.getSize(widthMeasureSpec);//view测量时获取屏幕宽度
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // screenWidth = getResources().getDisplayMetrics().widthPixels;
        System.out.println("屏幕宽度" + screenWidth);
  /*判断屏幕是否满足一定条件，满足则中断时间
  即，两边各留出一定宽度使靠边滑动时可以相应父pagerview 的事件，例如左边有侧滑菜单，右边靠边可以滑到另一个父viewpager的下一个*/
        if (ev.getRawX() > screenWidth / 8 && ev.getRawX() < screenWidth * 7 / 8) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return super.dispatchTouchEvent(ev);
    }
}