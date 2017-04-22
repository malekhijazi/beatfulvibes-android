package mobi.mtech.beatfulvibes.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mtech.library.adapter.CoreListAdapter;
import com.mtech.library.adapter.holder.CoreHolder;

import java.util.List;

import mobi.mtech.beatfulvibes.R;
import mobi.mtech.beatfulvibes.holder.SettingHolder;
import mobi.mtech.beatfulvibes.model.Setting;

/**
 * Copyright (C) 2017 Mtech.mobi. All rights reserved.
 * Created by Malek on 4/22/17.
 */

public class SettingAdapter extends CoreListAdapter<Setting> {
    public SettingAdapter(Context context, RecyclerView recyclerView, List<Setting> items) {
        super(context, recyclerView, items);
    }

    @Override
    public CoreHolder onCreateViewHolder(ViewGroup parent) {
        return new SettingHolder(inflate(parent, R.layout.view_setting), getItemClickListener());
    }
}
