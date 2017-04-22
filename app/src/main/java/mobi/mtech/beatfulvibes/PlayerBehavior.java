package mobi.mtech.beatfulvibes;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.mtech.library.util.Utils;

import mobi.mtech.beatfulvibes.fragment.HomeFragment;

/**
 * Copyright (C) 2017 Mtech.mobi. All rights reserved.
 * Created by Malek on 1/10/17.
 */

public class PlayerBehavior extends CoordinatorLayout.Behavior<TabLayout> {
    private boolean isHidden = false;

    public PlayerBehavior(Context context, AttributeSet attrs) {
        super();
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, TabLayout child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        int totalScroll = (dyConsumed + dyUnconsumed);
        if (totalScroll > 0 && !isHidden) {
            isHidden = true;
            child.animate().translationY(child.getHeight());
            HomeFragment.mTrackFragment.getView().animate().translationY(Utils.dpToPx(48));

        } else if (totalScroll < 0 && isHidden) {
            isHidden = false;
            child.animate().translationY(0);
            HomeFragment.mTrackFragment.getView().animate().translationY(0);
        }
    }


    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, TabLayout child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

}
