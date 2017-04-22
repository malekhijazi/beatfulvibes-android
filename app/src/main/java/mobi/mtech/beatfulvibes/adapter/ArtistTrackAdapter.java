package mobi.mtech.beatfulvibes.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mtech.library.adapter.CoreListAdapter;
import com.mtech.library.adapter.holder.CoreHolder;

import java.util.List;

import mobi.mtech.beatfulvibes.R;
import mobi.mtech.beatfulvibes.holder.ArtistTrackHolder;
import mobi.mtech.beatfulvibes.model.Track;

/**
 * Copyright (C) 2017 Mtech.mobi. All rights reserved.
 * Created by Malek on 4/15/17.
 */

public class ArtistTrackAdapter extends CoreListAdapter<Track> {

    public ArtistTrackAdapter(Context context, RecyclerView recyclerView, List<Track> items) {
        super(context, recyclerView, items);
    }

    @Override
    public CoreHolder onCreateViewHolder(ViewGroup parent) {
        return new ArtistTrackHolder(inflate(parent, R.layout.view_artist_track), getItemClickListener());
    }
}
