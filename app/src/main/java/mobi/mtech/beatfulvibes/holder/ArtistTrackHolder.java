package mobi.mtech.beatfulvibes.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mtech.library.adapter.holder.CoreHolder;
import com.mtech.library.listener.ItemClickListener;

import mobi.mtech.beatfulvibes.R;
import mobi.mtech.beatfulvibes.model.Track;

/**
 * Copyright (C) 2017 Mtech.mobi. All rights reserved.
 * Created by Malek on 4/15/17.
 */

public class ArtistTrackHolder extends CoreHolder<Track> {

    private ImageView mImage;
    private TextView mTitle;

    public ArtistTrackHolder(View itemView, ItemClickListener listener) {
        super(itemView, listener);
        mImage = (ImageView) itemView.findViewById(R.id.image);
        mTitle = (TextView) itemView.findViewById(R.id.title);
    }

    @Override
    public void bindData(Track data) {
        Glide.with(mImage.getContext()).load(data.getThumbnail()).into(mImage);
        mTitle.setText(data.getName());
    }
}
