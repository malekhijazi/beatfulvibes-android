package mobi.mtech.beatfulvibes.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mtech.library.adapter.CoreListAdapter;
import com.mtech.library.fragment.CoreListFragment;
import com.mtech.library.listener.ItemClickListener;
import com.mtech.library.manager.CoreNetworkManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import mobi.mtech.beatfulvibes.R;
import mobi.mtech.beatfulvibes.activity.TrackFragment;
import mobi.mtech.beatfulvibes.adapter.CategoryAdapter;
import mobi.mtech.beatfulvibes.adapter.TrackAdapter;
import mobi.mtech.beatfulvibes.constant.Constant;
import mobi.mtech.beatfulvibes.model.Category;
import mobi.mtech.beatfulvibes.model.Track;
import mobi.mtech.beatfulvibes.model.wrapper.browse.BrowseDataWrapper;
import mobi.mtech.beatfulvibes.network.NetworkManager;

import static mobi.mtech.beatfulvibes.fragment.HomeFragment.mTrackFragment;

/**
 * Copyright (C) 2017 Mtech.mobi. All rights reserved.
 * Created by Malek on 4/8/17.
 */

public class BrowseFragment extends CoreListFragment<Track> {

    private int page = 1;
    private int category_id = 0;
    private View mLastCategorySelected = null;
    private RecyclerView mCategoriesRecyclerView;
    private CategoryAdapter mCategoryAdapter;

    public static BrowseFragment newInstance() {
        return new BrowseFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCategoriesRecyclerView = (RecyclerView) view.findViewById(R.id.rv_categories);

        getTracks();
    }

    /**
     * Define the mCategoryAdapter in here
     */
    @Override
    public CoreListAdapter<Track> initAdapter() {
        return new TrackAdapter(mContext, rvList, new ArrayList<Track>());
    }

    /**
     * Define the adapter in here
     */
    @Override
    public RecyclerView.LayoutManager initLayoutManager() {
        return new GridLayoutManager(mContext, 2);
    }

    /**
     * Return the content view of the fragment
     **/
    @Override
    public int getContentView() {
        return R.layout.fragment_browse;
    }

    /**
     * Return the id of the RV
     **/
    @Override
    public int getRecyclerViewId() {
        return R.id.rv;
    }

    /**
     * Return the id of the Swipe Refresh Layout
     **/
    @Override
    public int getSwipeRefreshLayoutId() {
        return R.id.srl;
    }

    /**
     * Ovverride this method to change what swipe to refresh does
     */
    @Override
    public void onRefresh() {
        setRefreshing(false);
        page = 1;
        getTracks();
    }

    @Override
    public void itemClicked(View view, int integer) {
        Track track = getAdapter().getItem(integer);
        if (mTrackFragment == null) {
            mTrackFragment = TrackFragment.newInstance(track);
            FragmentTransaction ft = this.fm.beginTransaction();
            ft.setCustomAnimations(com.mtech.library.R.anim.slide_up_fade, com.mtech.library.R.anim.slide_down_fade, com.mtech.library.R.anim.slide_up_fade, com.mtech.library.R.anim.slide_down_fade);
            ft.add(R.id.main_frame_layout, mTrackFragment, mTrackFragment.getClass().getCanonicalName());
            ft.commit();
        } else {
            mTrackFragment.changeVideo(track);
        }
    }

    @Override
    public void onLoadMore() {
        page++;
        getTracks();
    }

    @Override
    public void onClick(View view) {

    }

    private void getTracks() {
        try {
            if (getAdapter().getItemCount() == 0) {
                showLoader();
            }
            JSONObject params = new JSONObject();
            params.put(Constant.PAGE, page);
            params.put(Constant.CATEGORY_ID, category_id);
            NetworkManager.browse(mContext, params, new CoreNetworkManager.Listener<BrowseDataWrapper>() {
                @Override
                public void onSuccess(BrowseDataWrapper response) {
                    if (response.getTracks().length < 20) {
                        getAdapter().hideFooterLoader();
                    }else{
                        getAdapter().showFooterLoader();
                    }
                    if (page > 1) {
                        getAdapter().addItems(new ArrayList<>(Arrays.asList(response.getTracks())));
                    } else {
                        setupCategories(response.getCategories());
                        getAdapter().updateItems(new ArrayList<>(Arrays.asList(response.getTracks())));
                        if(category_id == 0) {
                            mCategoriesRecyclerView.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mCategoryAdapter.getItemClickListener().itemClicked(mCategoriesRecyclerView.getChildAt(0), 0);

                                }
                            }, 100);
                        }
                    }
                    hideLoader();
                }

                @Override
                public void onError(Exception e) {
                    e.printStackTrace();
                    hideLoader();
                }
            });

        } catch (JSONException e) {
            hideLoader();
            e.printStackTrace();
        }
    }

    private void setupCategories(Category[] categories) {
        if (mCategoryAdapter == null) {
            mCategoriesRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            ArrayList<Category> list = new ArrayList<>(Arrays.asList(categories));
            list.add(0, new Category(0, "All"));
            mCategoryAdapter = new CategoryAdapter(mContext, rvList, list);
            mCategoryAdapter.setItemClickListener(new ItemClickListener() {
                @Override
                public void itemClicked(View view, int position) {
                    if (mLastCategorySelected != null) {
                        mLastCategorySelected.setSelected(false);
                    }
                    view.setSelected(true);
                    category_id = mCategoryAdapter.getItem(position).getId();
                    page = 1;
                    if (CategoryAdapter.mSelectedPosition != -1) {
                        getAdapter().updateItems(new ArrayList<Track>());
                        getTracks();
                    }
                    CategoryAdapter.mSelectedPosition = position;
                    mLastCategorySelected = view;
                }
            });
            mCategoriesRecyclerView.setAdapter(mCategoryAdapter);
        }
    }
}
