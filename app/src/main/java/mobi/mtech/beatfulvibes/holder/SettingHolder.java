package mobi.mtech.beatfulvibes.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mtech.library.adapter.holder.CoreHolder;
import com.mtech.library.listener.ItemClickListener;

import mobi.mtech.beatfulvibes.R;
import mobi.mtech.beatfulvibes.model.Setting;

/**
 * Copyright (C) 2017 Mtech.mobi. All rights reserved.
 * Created by Malek on 4/22/17.
 */

public class SettingHolder extends CoreHolder<Setting> {

    private TextView mTitle;
    private ImageView mIcon;

    public SettingHolder(View itemView, ItemClickListener listener) {
        super(itemView, listener);
        mTitle = (TextView) itemView.findViewById(R.id.title);
        mIcon = (ImageView) itemView.findViewById(R.id.icon);
    }

    @Override
    public void bindData(Setting data) {
        mTitle.setText(data.getTitle());
        mIcon.setImageResource(data.getIcon());
    }
}
