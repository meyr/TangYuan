package net.mshome.fyon_linux.tangyuan;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by fyon on 11/11/16.
 */

public class CustomViewPager extends ViewPager{

    private boolean enabled;

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.enabled = true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return enabled ? super.onTouchEvent(ev) : false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return enabled ? super.onInterceptTouchEvent(ev) : false;
    }

    public void setPagingEnabled(boolean enabled){
        this.enabled = enabled;
    }

    public boolean isPagingEnable(){
        return  enabled;
    }
}
