package mobi.mtech.beatfulvibes.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mtech.library.adapter.CoreListAdapter;
import com.mtech.library.adapter.holder.CoreHolder;

import java.util.List;

import mobi.mtech.beatfulvibes.R;
import mobi.mtech.beatfulvibes.holder.TrackHolder;
import mobi.mtech.beatfulvibes.model.Track;

/**
 * Copyright (C) 2017 Mtech.mobi. All rights reserved.
 * Created by Malek on 4/8/17.
 */

public class FeaturedTrackAdapter extends CoreListAdapter<Track> {
    public FeaturedTrackAdapter(Context context, RecyclerView recyclerView, List<Track> items) {
        super(context, recyclerView, items);
    }

    @Override
    public CoreHolder onCreateViewHolder(ViewGroup parent) {
        return new TrackHolder(inflate(parent, R.layout.view_featured_track), getItemClickListener());
    }

    @Override
    public int getItemCount() {
        return 700;
    }

    @Override
    public void onBindViewHolder(CoreHolder coreHolder, int position) {
        TrackHolder holder = (TrackHolder) coreHolder;
        int pos = position % getItems().size();
        holder.bindData(getItem(pos));
    }
}
