package mobi.mtech.beatfulvibes.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import mobi.mtech.beatfulvibes.adapter.ArtistAdapter;
import mobi.mtech.beatfulvibes.constant.Constant;
import mobi.mtech.beatfulvibes.model.Artist;
import mobi.mtech.beatfulvibes.network.NetworkManager;

/**
 * Copyright (C) 2017 Mtech.mobi. All rights reserved.
 * Created by Malek on 4/9/17.
 */

public class ArtistsFragment extends CoreListFragment<Artist> {
    private int page = 1;


    public static ArtistsFragment newInstance(){
        return new ArtistsFragment();
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getArtists();
    }

    /**
     * Define the adapter in here
     */
    @Override
    public CoreListAdapter<Artist> initAdapter() {
        return new ArtistAdapter(mContext, rvList, new ArrayList<Artist>());
    }

    /**
     * Define the adapter in here
     */
    @Override
    public RecyclerView.LayoutManager initLayoutManager() {
        return new GridLayoutManager(mContext, 3);
    }

    /**
     * Return the content view of the fragment
     **/
    @Override
    public int getContentView() {
        return R.layout.fragment_artists;
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
        setRefreshing(false);
    }

    @Override
    public void itemClicked(View view, int pos) {
        openFragment(R.id.main_frame_layout, ArtistFragment.newInstance(getAdapter().getItem(pos)));
    }

    @Override
    public void onLoadMore() {
        page++;
        getArtists();
    }

    @Override
    public void onClick(View view) {

    }

    private void getArtists() {
        if (page == 1) {
            showLoader();
        }
        try {
            JSONObject params = new JSONObject();
            params.put(Constant.PAGE, page);
            NetworkManager.artists(mContext, params, new CoreNetworkManager.Listener<Artist[]>() {
                @Override
                public void onSuccess(Artist[] response) {
                    hideLoader();
                    if (page == 1) {
                        getAdapter().updateItems(new ArrayList<>(Arrays.asList(response)));
                    } else {
                        getAdapter().addItems(new ArrayList<>(Arrays.asList(response)));
                    }
                    if (response.length < 21) {
                        getAdapter().hideFooterLoader();
                    }
                }

                @Override
                public void onError(Exception e) {
                    e.printStackTrace();
                    hideLoader();
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
