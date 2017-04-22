package mobi.mtech.beatfulvibes.holder;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.mtech.library.adapter.holder.CoreHolder;
import com.mtech.library.listener.ItemClickListener;

import mobi.mtech.beatfulvibes.R;
import mobi.mtech.beatfulvibes.model.Artist;

/**
 * Copyright (C) 2017 Mtech.mobi. All rights reserved.
 * Created by Malek on 4/9/17.
 */

public class ArtistHolder extends CoreHolder<Artist> {
    private ImageView mImage;
    private TextView mTitle;

    public ArtistHolder(View itemView, ItemClickListener listener) {
        super(itemView, listener);
        mImage = (ImageView) itemView.findViewById(R.id.image);
        mTitle = (TextView) itemView.findViewById(R.id.title);
    }

    @Override
    public void bindData(Artist data) {
        if(data.getImage() == null || data.getImage().equals("")){
            mImage.setBackgroundColor(ContextCompat.getColor(mImage.getContext(), R.color.track_footer));
            mImage.setImageResource(R.drawable.default_artist);

        }else {
            mImage.setBackgroundColor(ContextCompat.getColor(mImage.getContext(), R.color.colorAccent));
            mImage.setBackground(null);
            Glide.with(mImage.getContext()).load(data.getImage()).listener(new RequestListener<String, GlideDrawable>() {
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
        }
        mTitle.setText(data.getName());
    }
}
