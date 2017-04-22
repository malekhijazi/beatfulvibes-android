package mobi.mtech.beatfulvibes.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mtech.library.adapter.CoreListAdapter;
import com.mtech.library.adapter.holder.CoreHolder;

import java.util.List;

import mobi.mtech.beatfulvibes.R;
import mobi.mtech.beatfulvibes.holder.FeaturedTrackHolder;
import mobi.mtech.beatfulvibes.holder.TrackHolder;
import mobi.mtech.beatfulvibes.model.Track;

/**
 * Copyright (C) 2017 Mtech.mobi. All rights reserved.
 * Created by Malek on 4/8/17.
 */

public class DiscoverAdapter extends CoreListAdapter<Track> {
    private final int TYPE_FEATURED = 1;
    private final int TYPE_MOST_PLAYED = 2;
    private final int TYPE_NEW = 3;

    private List<Track> mFeaturedList;
    private List<Track> mMostPlayedList;

    public DiscoverAdapter(Context context, RecyclerView recyclerView, List<Track> featured, List<Track> newTracks, List<Track> mostPlayed) {
        super(context, recyclerView, newTracks);
        spanHeader();
    }

    private void spanHeader() {
//        final GridLayoutManager layoutManager = (GridLayoutManager) getRecyclerView()
//                .getLayoutManager();
//        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return getItemViewType(position) == TYPE_FEATURED ? layoutManager.getSpanCount() : 1;
//            }
//        });
    }

    @Override
    public CoreHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        if (viewType == TYPE_FEATURED) {
//            return new FeaturedTrackHolder(getRecyclerView(), inflate(parent, R.layout.view_featured_track_container), getItemClickListener());
//        } else {
            return new TrackHolder(inflate(parent, R.layout.view_track), getItemClickListener());
//        }
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_FEATURED;
        }
        return TYPE_NEW;
    }

    @Override
    public void onBindViewHolder(CoreHolder coreHolder, int position) {
//        if (getItemViewType(position) == TYPE_FEATURED) {
//            FeaturedTrackHolder holder = (FeaturedTrackHolder) coreHolder;
//            holder.bindData(mFeaturedList);
//        } else {
            TrackHolder holder = (TrackHolder) coreHolder;
            holder.bindData(getItem(position));
//        }
    }

    public void updateItems(List<Track> featured, List<Track> newTracks, List<Track> mostPlayed) {
        this.mFeaturedList = featured;
        this.mMostPlayedList = mostPlayed;
        this.updateItems(newTracks);
    }

    //not used in this case
    @Override
    public CoreHolder onCreateViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public int getItemCount() {
        return getItems().size();// + 1;
    }
}
