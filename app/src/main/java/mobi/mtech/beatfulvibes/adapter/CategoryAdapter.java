package mobi.mtech.beatfulvibes.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mtech.library.adapter.CoreListAdapter;
import com.mtech.library.adapter.holder.CoreHolder;

import java.util.List;

import mobi.mtech.beatfulvibes.R;
import mobi.mtech.beatfulvibes.holder.CategoryHolder;
import mobi.mtech.beatfulvibes.model.Category;

/**
 * Copyright (C) 2017 Mtech.mobi. All rights reserved.
 * Created by Malek on 4/9/17.
 */

public class CategoryAdapter extends CoreListAdapter<Category> {
    public static int mSelectedPosition = -1;
    public CategoryAdapter(Context context, RecyclerView recyclerView, List<Category> items) {
        super(context, recyclerView, items);
    }

    @Override
    public CoreHolder onCreateViewHolder(ViewGroup parent) {
        return new CategoryHolder(inflate(parent, R.layout.view_category), getItemClickListener());
    }
}
