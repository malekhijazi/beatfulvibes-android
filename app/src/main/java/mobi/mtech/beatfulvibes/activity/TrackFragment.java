package mobi.mtech.beatfulvibes.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.pedrovgs.DraggableListener;
import com.github.pedrovgs.DraggableView;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.mtech.library.adapter.CoreListAdapter;
import com.mtech.library.fragment.CoreFragment;
import com.mtech.library.fragment.CoreListFragment;
import com.mtech.library.listener.CommunicationListener;
import com.mtech.library.manager.CoreNetworkManager;
import com.mtech.library.util.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import mobi.mtech.beatfulvibes.R;
import mobi.mtech.beatfulvibes.adapter.TrackAdapter;
import mobi.mtech.beatfulvibes.constant.Constant;
import mobi.mtech.beatfulvibes.model.Track;
import mobi.mtech.beatfulvibes.model.wrapper.discover.DiscoverDataWrapper;
import mobi.mtech.beatfulvibes.network.NetworkManager;

/**
 * Copyright (C) 2017 Mtech.mobi. All rights reserved.
 * Created by Malek on 4/8/17.
 */

public class TrackFragment extends CoreListFragment<Track> implements YouTubePlayer.OnInitializedListener {

    private static final String API_KEY = "AIzaSyAmXf9pXxH36ng9l1IbA_Ccaqn1oH0o45s";

    private DraggableView mDraggableView;
    private YouTubePlayer mYouTubePlayer;
    private Track mTrack;
    private boolean mPlayerInitialized = false;
    private CommunicationListener mCommunicationListener;
    private YouTubePlayerSupportFragment mYoutubePlayerFragment;

    public static TrackFragment newInstance(Track track) {
        Bundle args = new Bundle();
        args.putSerializable(Constant.TRACK, track);
        TrackFragment fragment = new TrackFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mTrack = (Track) args.getSerializable(Constant.TRACK);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDraggableView = (DraggableView) view.findViewById(R.id.draggable_view);
        mDraggableView.setDraggableListener(new DraggableListener() {
            @Override
            public void onMaximized() {
            }

            @Override
            public void onMinimized() {
            }

            @Override
            public void onClosedToLeft() {
//                mYouTubePlayer.pause();
                mCommunicationListener.execute(Constant.TYPE_SWIPE, null);
            }

            @Override
            public void onClosedToRight() {
//                mYouTubePlayer.pause();
                mCommunicationListener.execute(Constant.TYPE_SWIPE, null);
            }
        });

        mDraggableView.setClickToMaximizeEnabled(true);
        mDraggableView.setHorizontalAlphaEffectEnabled(false);
        mDraggableView.setTopViewMarginBottom(Utils.dpToPx(16));
        mDraggableView.setTopViewMarginRight(Utils.dpToPx(16));
        mDraggableView.setXTopViewScaleFactor(2.3f);
        mDraggableView.setYTopViewScaleFactor(2.3f);



        getSimilar();
    }

    private void getSimilar() {
        getAdapter().reset();
        try {
            showLoader();
            JSONObject params = new JSONObject();
            params.put(Constant.TRACK_ID, mTrack.getId());
            NetworkManager.related(getContext(), params, new CoreNetworkManager.Listener<Track[]>() {
                @Override
                public void onSuccess(Track[] response) {
                    getAdapter().updateItems(new ArrayList<>(Arrays.asList(response)));
                    hideLoader();
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

    /**
     * Define the adapter in here
     */
    @Override
    public CoreListAdapter<Track> initAdapter() {
        return new TrackAdapter(mContext, rvList, new ArrayList<Track>(), false);
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
        return R.layout.fragment_track;
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
        return 0;
    }

    /**
     * Ovverride this method to change what swipe to refresh does
     */
    @Override
    public void onRefresh() {
    }

    @Override
    public void itemClicked(View view, int position) {
        changeVideo(getAdapter().getItem(position));
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        mPlayerInitialized = !b;
        if (!b) {
            mYouTubePlayer = youTubePlayer;
            youTubePlayer.loadVideo(mTrack.getYoutubeId());
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(getActivity(), "fail", Toast.LENGTH_SHORT).show();
    }

    public void changeVideo(Track track) {
        if (mPlayerInitialized && track != null && mYouTubePlayer != null) {
            mYouTubePlayer.loadVideo(track.getYoutubeId());
            getSimilar();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mYouTubePlayer == null) {
            FragmentManager fragmentManager = getChildFragmentManager();
            mYoutubePlayerFragment = (YouTubePlayerSupportFragment) fragmentManager.findFragmentById(R.id.youtube_fragment);
            mYoutubePlayerFragment.initialize(API_KEY, this);
        }
    }

    @Override
    public void onAttach(Context context) {
        if (context != null) {
            mCommunicationListener = (CommunicationListener) context;
        }
        super.onAttach(context);
    }

    public void minimize() {
        mDraggableView.minimize();
    }

    public boolean isMinimized() {
        if (mDraggableView != null) {
            return mDraggableView.isMinimized();
        }
        return true;
    }
}
