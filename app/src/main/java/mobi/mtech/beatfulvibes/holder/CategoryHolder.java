package mobi.mtech.beatfulvibes.holder;

import android.view.View;
import android.widget.TextView;

import com.mtech.library.adapter.holder.CoreHolder;
import com.mtech.library.listener.ItemClickListener;

import mobi.mtech.beatfulvibes.R;
import mobi.mtech.beatfulvibes.adapter.CategoryAdapter;
import mobi.mtech.beatfulvibes.model.Category;

/**
 * Copyright (C) 2017 Mtech.mobi. All rights reserved.
 * Created by Malek on 4/9/17.
 */

public class CategoryHolder extends CoreHolder<Category> {
    private TextView mTitle;
    public CategoryHolder(View itemView, ItemClickListener listener) {
        super(itemView, listener);
        mTitle = (TextView) itemView.findViewById(R.id.title);
        mTitle.setOnClickListener(this);
    }

    @Override
    public void bindData(Category data) {
        mTitle.setText(data.getName());
        if(CategoryAdapter.mSelectedPosition == getAdapterPosition()){
            mTitle.setSelected(true);
        }else{
            mTitle.setSelected(false);
        }
        if(CategoryAdapter.mSelectedPosition  == -1 && getAdapterPosition() == 0){
            mTitle.setSelected(true);
        }
    }

}
