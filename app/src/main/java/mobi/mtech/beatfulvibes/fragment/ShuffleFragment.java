package mobi.mtech.beatfulvibes.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mtech.library.adapter.CoreListAdapter;
import com.mtech.library.fragment.CoreListFragment;
import com.mtech.library.manager.CoreNetworkManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import mobi.mtech.beatfulvibes.R;
import mobi.mtech.beatfulvibes.activity.TrackFragment;
import mobi.mtech.beatfulvibes.adapter.TrackAdapter;
import mobi.mtech.beatfulvibes.constant.Constant;
import mobi.mtech.beatfulvibes.model.Track;
import mobi.mtech.beatfulvibes.model.wrapper.mixes.MixesDataWrapper;
import mobi.mtech.beatfulvibes.network.NetworkManager;

import static mobi.mtech.beatfulvibes.fragment.HomeFragment.mTrackFragment;

/**
 * Copyright (C) 2017 Mtech.mobi. All rights reserved.
 * Created by Malek on 4/8/17.
 */

public class ShuffleFragment extends CoreListFragment<Track> {

    private int page = 1;

    public static ShuffleFragment newInstance() {
        return new ShuffleFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getTracks();
    }

    /**
     * Define the mCategoryAdapter in here
     */
    @Override
    public CoreListAdapter<Track> initAdapter() {
        return new TrackAdapter(mContext, rvList, new ArrayList<Track>());
    }

    /**
     * Define the adapter in here
     */
    @Override
    public RecyclerView.LayoutManager initLayoutManager() {
        return new GridLayoutManager(mContext, 2);
    }

    /**
     * Return the content view of the fragment
     **/
    @Override
    public int getContentView() {
        return R.layout.fragment_browse;
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
        page = 1;
        getTracks();
    }

    @Override
    public void itemClicked(View view, int integer) {
        Track track = getAdapter().getItem(integer);
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

    @Override
    public void onLoadMore() {
        page++;
        getTracks();
    }

    @Override
    public void onClick(View view) {

    }

    private void getTracks() {
        if (getAdapter().getItemCount() == 0) {
            showLoader();
        }
        JSONObject params = new JSONObject();
        NetworkManager.shuffle(mContext, params, new CoreNetworkManager.Listener<Track[]>() {
            @Override
            public void onSuccess(Track[] response) {
                getAdapter().hideFooterLoader();
                getAdapter().updateItems(new ArrayList<>(Arrays.asList(response)));
                hideLoader();
                setRefreshing(false);
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
                hideLoader();
            }
        });

    }
}
