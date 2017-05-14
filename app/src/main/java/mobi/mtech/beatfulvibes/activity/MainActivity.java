package mobi.mtech.beatfulvibes.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.mtech.library.activity.CoreTabbedFrameActivity;
import com.mtech.library.listener.CommunicationListener;
import com.mtech.library.listener.TabSelectedListener;
import com.mtech.library.util.Utils;
import com.mtech.library.view.button.SquareImageButton;

import java.util.ArrayList;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;
import mobi.mtech.beatfulvibes.R;
import mobi.mtech.beatfulvibes.constant.Constant;
import mobi.mtech.beatfulvibes.fragment.ArtistsFragment;
import mobi.mtech.beatfulvibes.fragment.BrowseFragment;
import mobi.mtech.beatfulvibes.fragment.HomeFragment;
import mobi.mtech.beatfulvibes.fragment.MixesFragment;
import mobi.mtech.beatfulvibes.fragment.SearchFragment;
import mobi.mtech.beatfulvibes.fragment.ShuffleFragment;

/**
 * Copyright (C) 2017 Mtech.mobi. All rights reserved.
 * Created by Malek on 4/8/17.
 */

public class MainActivity extends CoreTabbedFrameActivity implements View.OnClickListener, CommunicationListener, TabSelectedListener {
    private EditText mSearchEditText;
    private SquareImageButton mSearchBack;
    private AppBarLayout mAppBarLayout;

    private boolean isSearchOpened = false;
    private SearchFragment mSearchFragment = SearchFragment.newInstance();
    private HomeFragment mHomeFragment = HomeFragment.newInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeTitle("Discover");

        mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);

        setOptionsMenu(getToolbar(), R.menu.main);
        setupSearchBar();
        setTabSelectedListener(this);
        setTabChangeColor(true);
    }

    @Override
    public int getTabViewLayoutId() {
        return R.layout.library_view_icon_tab;
    }

    /**
     * Override this method to set tab fragments
     */
    @Override
    public ArrayList<Fragment> getTabFragments() {
        ArrayList<Fragment> list = new ArrayList<>();
        list.add(mHomeFragment);
        list.add(BrowseFragment.newInstance());
        list.add(MixesFragment.newInstance());
        list.add(ShuffleFragment.newInstance());
        list.add(ArtistsFragment.newInstance());
        return list;
    }

    /**
     * Override this method to set tab titles
     */
    @Override
    public ArrayList<String> getTabTitles() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Discover");
        list.add("Browse");
        list.add("Mixes");
        list.add("Shuffle");
        list.add("Artists");
        return list;
    }

    @Override
    public ArrayList<Integer> getTabIcons() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.drawable.ic_play_circle_outline_white_24dp);
        list.add(R.drawable.ic_queue_music_white_24dp);
        list.add(R.drawable.ic_equalizer_white_24dp);
        list.add(R.drawable.ic_shuffle_white_24dp);
        list.add(R.drawable.ic_portrait_white_24dp);
        return list;
    }

    /**
     * Return the layout id
     **/
    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    /**
     * Return the tabs id
     **/
    @Override
    public int getTabsId() {
        return R.id.tabs;
    }

    /**
     * Return the viewpager id
     **/
    @Override
    public int getToolbarId() {
        return R.id.toolbar;
    }

    /**
     * Return the viewpager id
     **/
    @Override
    public int getFrameLayoutId() {
        return R.id.tabs_frame_layout;
    }


    private void setupSearchBar() {

        mSearchBack = (SquareImageButton) findViewById(R.id.search_back_btn);

        mSearchEditText = (EditText) findViewById(R.id.search_edittext);

        mSearchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (!mSearchEditText.getText().toString().trim().equals("")) {
                        mSearchFragment.search(mSearchEditText.getText().toString());
                    }
                    return true;
                }
                return false;
            }
        });
        mSearchBack.setOnClickListener(this);
    }

    private void openSearchBar() {
        SupportAnimator animator = getAnimator();
        animator.start();
        isSearchOpened = true;
        mSearchEditText.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mSearchEditText, InputMethodManager.SHOW_IMPLICIT);
        mAppBarLayout.animate().translationY(-mTabLayout.getHeight()).setDuration(300).start();
    }

    private SupportAnimator getAnimator() {
        final View searchView = findViewById(R.id.search_card);
        final View searchBtn = findViewById(R.id.mi_search);
        // get the center for the clipping circle
        int cx = ((searchBtn.getLeft() + searchBtn.getRight()) * 3) / 2;
        int cy = (searchBtn.getTop() + searchBtn.getBottom()) / 2;

        // get the final radius for the clipping circle
        int dx = Math.max(cx, searchView.getWidth() - cx);
        int dy = Math.max(cy, searchView.getHeight() - cy);
        float finalRadius = (float) Math.hypot(dx, dy);

        SupportAnimator animator =
                ViewAnimationUtils.createCircularReveal(searchView, searchView.getWidth() - (cx), cy, 0, finalRadius);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(300);
        animator.addListener(new SupportAnimator.AnimatorListener() {
            @Override
            public void onAnimationStart() {
                searchView.setVisibility(View.VISIBLE);
                searchView.setClickable(true);
            }

            @Override
            public void onAnimationEnd() {
//                mToolbar.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel() {

            }

            @Override
            public void onAnimationRepeat() {

            }
        });
        return animator;
    }

    private void hideSearchBar() {

        final View searchView = findViewById(R.id.search_card);
        SupportAnimator animator = getAnimator().reverse();
        animator.addListener(new SupportAnimator.AnimatorListener() {
            @Override
            public void onAnimationStart() {
            }

            @Override
            public void onAnimationEnd() {
                searchView.setVisibility(View.GONE);
                Utils.closeKeyboard(MainActivity.this);
            }

            @Override
            public void onAnimationCancel() {

            }

            @Override
            public void onAnimationRepeat() {

            }
        });
        animator.start();//.start();
        isSearchOpened = false;
        mAppBarLayout.animate().translationY(0).setDuration(300).start();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_back_btn:
                onBackPressed();
                break;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.mi_search) {
            openSearchBar();
            openFragment(R.id.tabs_frame_layout, mSearchFragment);
            isSearchOpened = true;
            return true;
        } else if (id == R.id.mi_settings) {
            startActivity(SettingsActivity.class);
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if (mHomeFragment != null && HomeFragment.mTrackFragment != null && !mHomeFragment.isTrackFragmentMinimized()) {
            mHomeFragment.minimizeTrackFragment();
            return;
        }
        if (isSearchOpened ) {
            if(fm.getBackStackEntryCount() > 1){
                super.onBackPressed();
            }else {
                hideSearchBar();
                super.onBackPressed();
            }
            return;
        }
        if (fm.getBackStackEntryCount() > 0) {
            super.onBackPressed();
            return;
        }
        if (mTabLayout.getSelectedTabPosition() != 0) {
            mTabLayout.getTabAt(0).select();
            return;
        }
    }

    private void changeTitle(String title) {
        getToolbar().setTitle(title);
    }

    @Override
    public void execute(int type, Object object) {
        if (type == Constant.TYPE_SWIPE) {
            mHomeFragment.removeTrackFragment();
        }
    }

    @Override
    public void tabSelected(int position) {
        switch (position) {
            case 0:
                changeTitle("Discover");
                break;
            case 1:
                changeTitle("Browse");
                break;
            case 2:
                changeTitle("Mixes");
                break;
            case 3:
                changeTitle("Shuffle");
                break;
            case 4:
                changeTitle("Artists");
                break;

        }
        if (isSearchOpened) {
            onBackPressed();
        }
    }

    @Override
    public void tabUnselected(int position) {

    }
}
