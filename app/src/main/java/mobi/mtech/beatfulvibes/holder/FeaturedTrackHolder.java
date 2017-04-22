package mobi.mtech.beatfulvibes.holder;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mtech.library.adapter.holder.CoreHolder;
import com.mtech.library.listener.ItemClickListener;
import com.mtech.library.view.CustomCirclePageIndicator;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.Orientation;

import java.util.List;

import mobi.mtech.beatfulvibes.R;
import mobi.mtech.beatfulvibes.adapter.TrackAdapter;
import mobi.mtech.beatfulvibes.model.Track;

/**
 * Copyright (C) 2017 Mtech.mobi. All rights reserved.
 * Created by Malek on 4/8/17.
 */

public class FeaturedTrackHolder extends CoreHolder<List<Track>> {
    private ViewPager mViewpager;
    private CustomCirclePageIndicator mIndicator;
    private DiscreteScrollView scrollView;
    private RecyclerView mRecyclerView;
    public FeaturedTrackHolder(RecyclerView recyclerView, View itemView, ItemClickListener listener) {
        super(itemView, listener);
        mRecyclerView = recyclerView;
//        mViewpager = (ViewPager) itemView.findViewById(R.id.featured_viewpager);
//        mIndicator = (CustomCirclePageIndicator) itemView.findViewById(com.mtech.library.R.id.indicator);
        scrollView = (DiscreteScrollView) itemView.findViewById(R.id.picker);
    }

    @Override
    public void bindData(List<Track> data) {
        if (data != null) {
            scrollView.setAdapter(new TrackAdapter(scrollView.getContext(), mRecyclerView, data));
            scrollView.setOrientation(Orientation.HORIZONTAL); //Sets an orientation of the view


//            mViewpager.setOffscreenPageLimit(data.size());
//            mViewpager.setClipToPadding(false);
//            // set padding manually, the more you set the padding the more you see of prev & next page
//            int dp = Utils.dpToPx(10);
//            mViewpager.setPadding(dp, 0, dp, 0);
//            // sets a margin b/w individual pages to ensure that there is a gap b/w them
//            mViewpager.setPageMargin(15);
//            mViewpager.setAdapter(adapter);
//            mViewpager.setCurrentItem(data.size() * 10);
//
//            Timer timer = new Timer();
//            timer.schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    if (mViewpager != null) {
//                        ((Activity) mViewpager.getContext()).runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                mViewpager.setCurrentItem(mViewpager.getCurrentItem() + 1, true);
//                            }
//                        });
//                    }
//                }
//            }, 3000, 3000);
//            mIndicator.setViewPager(mViewpager);
//            mIndicator.setSnap(true);
        }

    }
}
