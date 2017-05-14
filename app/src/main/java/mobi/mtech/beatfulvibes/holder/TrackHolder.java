package mobi.mtech.beatfulvibes.holder;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mtech.library.adapter.holder.CoreHolder;
import com.mtech.library.listener.ItemClickListener;
import com.mtech.library.view.CircleImageView;

import mobi.mtech.beatfulvibes.R;
import mobi.mtech.beatfulvibes.model.Track;

/**
 * Copyright (C) 2017 Mtech.mobi. All rights reserved.
 * Created by Malek on 4/8/17.
 */

public class TrackHolder extends CoreHolder<Track> {
    private ImageView mImage;
    private TextView mTitle;
    private CircleImageView mArtistImage;
    private TextView mArtistName;

    public TrackHolder(View itemView, ItemClickListener listener) {
        super(itemView, listener);

        mImage = (ImageView) itemView.findViewById(R.id.image);
        mTitle = (TextView) itemView.findViewById(R.id.title);
        mArtistImage = (CircleImageView) itemView.findViewById(R.id.artist_image);
        mArtistName = (TextView) itemView.findViewById(R.id.artist_name);

    }

    @Override
    public void bindData(Track data) {
        Glide.with(mImage.getContext()).load(data.getThumbnail()).into(mImage);
        if(data.getArtist() != null) {
            Glide.with(mArtistImage.getContext()).load(data.getArtist().getImage()).into(mArtistImage);
            mTitle.setText(data.getName());
            mArtistName.setText(data.getArtist().getName());
        }
    }
}
