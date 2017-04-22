package mobi.mtech.beatfulvibes.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mtech.library.adapter.CoreListAdapter;
import com.mtech.library.adapter.holder.CoreHolder;

import java.util.List;

import mobi.mtech.beatfulvibes.R;
import mobi.mtech.beatfulvibes.holder.ArtistHolder;
import mobi.mtech.beatfulvibes.model.Artist;

/**
 * Copyright (C) 2017 Mtech.mobi. All rights reserved.
 * Created by Malek on 4/9/17.
 */

public class ArtistAdapter extends CoreListAdapter<Artist> {
    /**
     * The constructor for the CoreListAdapter
     *
     * @param context      application context
     * @param recyclerView recycler view assosicated with this adapter (for setting up infiniate
     *                     scrolling and scroll state change listeners)
     * @param items        the list of items of type T passed in the class
     */
    public ArtistAdapter(Context context, RecyclerView recyclerView, List<Artist> items) {
        super(context, recyclerView, items);
        enableInfiniteScrolling();
    }

    /**
     * @param parent
     * @return returns the view holder for the requested item. This is called after automatically checking
     * if the view type is normal or loader, if its a loader it is automatically handled, the normal
     * view holder is handled using this function.
     */
    @Override
    public CoreHolder onCreateViewHolder(ViewGroup parent) {
        return new ArtistHolder(inflate(parent, R.layout.view_artist), getItemClickListener());
    }
}
