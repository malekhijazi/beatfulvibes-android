package mobi.mtech.beatfulvibes.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mtech.library.adapter.CoreListAdapter;
import com.mtech.library.fragment.CoreListFragment;
import com.mtech.library.listener.CommunicationListener;
import com.mtech.library.listener.ItemClickListener;
import com.mtech.library.manager.CoreNetworkManager;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.Orientation;
import com.yarolegovich.discretescrollview.transform.Pivot;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import mobi.mtech.beatfulvibes.R;
import mobi.mtech.beatfulvibes.activity.TrackFragment;
import mobi.mtech.beatfulvibes.adapter.DiscoverAdapter;
import mobi.mtech.beatfulvibes.adapter.FeaturedTrackAdapter;
import mobi.mtech.beatfulvibes.adapter.TrackAdapter;
import mobi.mtech.beatfulvibes.model.Track;
import mobi.mtech.beatfulvibes.model.wrapper.discover.DiscoverDataWrapper;
import mobi.mtech.beatfulvibes.network.NetworkManager;

/**
 * Copyright (C) 2017 Mtech.mobi. All rights reserved.
 * Created by Malek on 4/8/17.
 */

public class HomeFragment extends CoreListFragment<Track> {

    private DiscreteScrollView scrollView;
    private TextView mFeaturedTitle;
    private TextView mNewTitle;

    public static TrackFragment mTrackFragment = null;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFeaturedTitle = (TextView) view.findViewById(R.id.featured_title);
        mNewTitle = (TextView) view.findViewById(R.id.new_title);

        scrollView = (DiscreteScrollView) view.findViewById(R.id.picker);
        scrollView.setOrientation(Orientation.HORIZONTAL); //Sets an orientation of the view
        scrollView.setItemTransformer(new ScaleTransformer.Builder()
                .setMaxScale(1.05f)
                .setMinScale(0.8f)
                .setPivotX(Pivot.X.CENTER) // CENTER is a default one
                .setPivotY(Pivot.Y.BOTTOM) // CENTER is a default one
                .build());

        getData();

    }

    /**
     * Define the adapter in here
     */
    @Override
    public CoreListAdapter<Track> initAdapter() {
        return new DiscoverAdapter(mContext, rvList, new ArrayList<Track>(), new ArrayList<Track>(), new ArrayList<Track>());
    }

    /**
     * Define the adapter in here
     */
    @Override
    public RecyclerView.LayoutManager initLayoutManager() {
        return new GridLayoutManager(mContext, 2);
    }

    private void getData() {
        if (getAdapter().getItemCount() == 0) {
            showLoader();
        }
        NetworkManager.discover(mContext, new JSONObject(), new CoreNetworkManager.Listener<DiscoverDataWrapper>() {
            @Override
            public void onSuccess(DiscoverDataWrapper response) {
                hideLoader();
                setRefreshing(false);
                if(response!= null) {
                    setupFeatured(response);
                    ((DiscoverAdapter) getAdapter()).updateItems(
                            new ArrayList<>(Arrays.asList(response.getFeatured())),
                            new ArrayList<>(Arrays.asList(response.getNew())),
                            new ArrayList<>(Arrays.asList(response.getMostPlayed())));
                    vm.show(mFeaturedTitle, mNewTitle);
                }
            }

            @Override
            public void onError(Exception e) {
                hideLoader();
                setRefreshing(false);
                e.printStackTrace();
            }
        });
    }

    private void setupFeatured(DiscoverDataWrapper response) {
        if(response== null){
            return;
        }
        final FeaturedTrackAdapter adapter = new FeaturedTrackAdapter(mContext, scrollView, new ArrayList<>(Arrays.asList(response.getFeatured())));
        adapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void itemClicked(View view, int integer) {
                handleTrackClicked(adapter.getItem(integer % adapter.getItems().size()));
            }
        });
        scrollView.setAdapter(adapter);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (scrollView.getCurrentItem() + 1 > adapter.getItemCount()) {
                                scrollView.scrollToPosition(0);
                            } else {
                                scrollView.smoothScrollToPosition(scrollView.getCurrentItem() + 1);
                            }
                        }
                    });
                }
            }
        }, 1000, 3000);
    }

    /**
     * Return the content view of the fragment
     **/
    @Override
    public int getContentView() {
        return R.layout.fragment_home;
    }

    /**
     * Return the id of the RV
     **/
    @Override
    public int getRecyclerViewId() {
        return R.id.rv;
    }

    /**
     * Return the id of the Swipe Refresh Layout
     **/
    @Override
    public int getSwipeRefreshLayoutId() {
        return R.id.srl;
    }

    /**
     * Ovverride this method to change what swipe to refresh does
     */
    @Override
    public void onRefresh() {
        setRefreshing(true);
        getData();
    }

    @Override
    public void itemClicked(View view, int position) {
        handleTrackClicked(getAdapter().getItem(position));
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onClick(View view) {

    }

    private void handleTrackClicked(Track track) {
        if (mTrackFragment == null) {
            mTrackFragment = TrackFragment.newInstance(track);
            FragmentTransaction ft = this.fm.beginTransaction();
            ft.setCustomAnimations(com.mtech.library.R.anim.slide_up_fade, com.mtech.library.R.anim.slide_down_fade, com.mtech.library.R.anim.slide_up_fade, com.mtech.library.R.anim.slide_down_fade);
            ft.add(R.id.main_frame_layout, mTrackFragment, mTrackFragment.getClass().getCanonicalName());
            ft.commit();
        } else {
            mTrackFragment.changeVideo(track);
        }
    }

    public void removeTrackFragment() {
        removeFragment(R.id.main_frame_layout, mTrackFragment, false);
        mTrackFragment = null;
    }

    public void minimizeTrackFragment() {
        mTrackFragment.minimize();
    }

    public boolean isTrackFragmentMinimized() {
        return mTrackFragment.isMinimized();
    }
}
