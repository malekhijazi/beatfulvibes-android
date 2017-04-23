package mobi.mtech.beatfulvibes.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mtech.library.adapter.CoreListAdapter;
import com.mtech.library.fragment.CoreListFragment;
import com.mtech.library.listener.ItemClickListener;
import com.mtech.library.manager.CoreNetworkManager;
import com.mtech.library.util.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import mobi.mtech.beatfulvibes.R;
import mobi.mtech.beatfulvibes.activity.TrackFragment;
import mobi.mtech.beatfulvibes.adapter.ArtistAdapter;
import mobi.mtech.beatfulvibes.adapter.SearchArtistAdapter;
import mobi.mtech.beatfulvibes.adapter.TrackAdapter;
import mobi.mtech.beatfulvibes.constant.Constant;
import mobi.mtech.beatfulvibes.model.Artist;
import mobi.mtech.beatfulvibes.model.Track;
import mobi.mtech.beatfulvibes.model.wrapper.browse.BrowseDataWrapper;
import mobi.mtech.beatfulvibes.model.wrapper.search.SearchDataWrapper;
import mobi.mtech.beatfulvibes.network.NetworkManager;

import static mobi.mtech.beatfulvibes.fragment.HomeFragment.mTrackFragment;

/**
 * Copyright (C) 2017 Mtech.mobi. All rights reserved.
 * Created by Malek on 4/8/17.
 */

public class SearchFragment extends CoreListFragment<Track> {

    private int page = 1;
    private String text;
    private RecyclerView mArtistsRecyclerView;
    private SearchArtistAdapter mArtistAdapter;
    private TextView mArtistsTitle;
    private TextView mTracksTitle;
    private LinearLayout mEmptyLayout;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hideLoader();
        mEmptyLayout = (LinearLayout) view.findViewById(R.id.empty_screen_layout);

        mArtistsRecyclerView = (RecyclerView) view.findViewById(R.id.rv_artists);
        mArtistAdapter = new SearchArtistAdapter(mContext, rvList, new ArrayList<Artist>());
        mArtistAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void itemClicked(View view, int integer) {
                openFragment(R.id.main_frame_layout, ArtistFragment.newInstance(mArtistAdapter.getItem(integer)));
            }
        });
        mArtistsRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        mArtistsRecyclerView.setAdapter(mArtistAdapter);
        mArtistsTitle = (TextView) view.findViewById(R.id.artists_title);
        mTracksTitle = (TextView) view.findViewById(R.id.tracks_title);
        refreshEnabled(false);
    }

    /**
     * Define the adapter in here
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
        return R.layout.fragment_search;
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

    public void search(String text) {
        this.text = text;
        page = 1;
        getAdapter().reset();
        mArtistAdapter.reset();
        vm.hide(mArtistsTitle, mTracksTitle);
        getTracks();
    }

    private void getTracks() {
        try {
            vm.hide(mEmptyLayout);
            if (getAdapter().getItemCount() == 0) {
                showLoader();
            }
            JSONObject params = new JSONObject();
            params.put(Constant.TEXT, text);
            params.put(Constant.PAGE, page);
            NetworkManager.search(mContext, params, new CoreNetworkManager.Listener<SearchDataWrapper>() {
                @Override
                public void onSuccess(SearchDataWrapper response) {
                    Utils.closeKeyboard(getActivity());
                    if (page == 1) {
                        getAdapter().updateItems(new ArrayList<>(Arrays.asList(response.getTracks())));
                        mArtistAdapter.updateItems(new ArrayList<>(Arrays.asList(response.getArtists())));
                    } else {
                        getAdapter().addItems(new ArrayList<>(Arrays.asList(response.getTracks())));
                    }

                    if (response.getTracks().length == 0 && response.getArtists().length == 0) {
                        vm.show(mEmptyLayout);
                    } else {
                        vm.hide(mEmptyLayout);
                    }
                    if (response.getTracks().length < 20) {
                        getAdapter().hideFooterLoader();
                    }

                    if (response.getArtists().length > 0) {
                        vm.show(mArtistsTitle);
                    }
                    if (response.getTracks().length > 0) {
                        vm.show(mTracksTitle);
                    }
                    setRefreshing(false);
                    hideLoader();
                }

                @Override
                public void onError(Exception e) {
                    e.printStackTrace();
                    hideLoader();
                }
            });

        } catch (JSONException e) {
            hideLoader();
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        page = 1;
    }
}
