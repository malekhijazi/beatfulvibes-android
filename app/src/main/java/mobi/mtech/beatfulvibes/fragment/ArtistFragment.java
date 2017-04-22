package mobi.mtech.beatfulvibes.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.mtech.library.adapter.CoreListAdapter;
import com.mtech.library.fragment.CoreListFragment;
import com.mtech.library.manager.CoreNetworkManager;
import com.mtech.library.util.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import mobi.mtech.beatfulvibes.R;
import mobi.mtech.beatfulvibes.activity.TrackFragment;
import mobi.mtech.beatfulvibes.adapter.ArtistTrackAdapter;
import mobi.mtech.beatfulvibes.constant.Constant;
import mobi.mtech.beatfulvibes.model.Artist;
import mobi.mtech.beatfulvibes.model.Track;
import mobi.mtech.beatfulvibes.network.NetworkManager;

import static android.R.attr.data;
import static mobi.mtech.beatfulvibes.fragment.HomeFragment.mTrackFragment;

/**
 * Copyright (C) 2017 Mtech.mobi. All rights reserved.
 * Created by Malek on 4/15/17.
 */

public class ArtistFragment extends CoreListFragment<Track> {

    private Artist mArtist;
    private ImageView mImage;

    public static ArtistFragment newInstance(Artist artist) {

        ArtistFragment fragment = new ArtistFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constant.ARTIST, artist);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Define the adapter in here
     */
    @Override
    public CoreListAdapter<Track> initAdapter() {
        return new ArtistTrackAdapter(mContext, rvList, new ArrayList<Track>());
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
        return R.layout.fragment_artist;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mArtist = (Artist) args.getSerializable(Constant.ARTIST);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        setBackButtonEnabled(toolbar);
        toolbar.setTitle(mArtist.getName());
        mImage = (ImageView) view.findViewById(R.id.image);
        Glide.with(mImage.getContext()).load(mArtist.getImage()).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                mImage.setBackgroundColor(ContextCompat.getColor(mImage.getContext(), R.color.track_footer));
                mImage.setImageResource(R.drawable.default_artist);
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                return false;
            }
        }).into(mImage);

        rvList.setPadding(0, Utils.getDisplayHeight(getActivity()) / 2, 0, 0);
        getTracks();
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

    }

    @Override
    public void onClick(View view) {

    }

    private void getTracks() {
        if (getAdapter().getItemCount() == 0) {
            showLoader();
        }
        showLoader();
        try {
            JSONObject params = new JSONObject();
            params.put(Constant.ARTIST_ID, mArtist.getId());
            NetworkManager.artist(mContext, params, new CoreNetworkManager.Listener<Track[]>() {
                @Override
                public void onSuccess(Track[] response) {
                    getAdapter().updateItems(new ArrayList<Track>(Arrays.asList(response)));
                    hideLoader();
                }

                @Override
                public void onError(Exception e) {
                    hideLoader();
                }
            });

        } catch (JSONException e) {
            hideLoader();
            e.printStackTrace();
        }
    }
}
