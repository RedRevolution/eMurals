package com.nyw.eMurals.theme;

import android.content.Context;
import android.content.res.Resources;
import android.support.design.widget.AppBarLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhouhu on 2018/1/9.
 */

public class ColorAppbarLayout extends AppBarLayout implements ColorUiInterface{

    private int attr_background = -1;

    public ColorAppbarLayout(Context context) {
        super(context);
    }

    public ColorAppbarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.attr_background = ViewAttributeUtil.getBackgroundAttibute(attrs);
    }
    @Override
    public View getView() {
        return this;
    }

    @Override
    public void setTheme(Resources.Theme themeId) {
        if (attr_background != -1) {
            ViewAttributeUtil.applyBackgroundDrawable(this, themeId, attr_background);
        }
    }
}
