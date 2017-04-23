package mobi.mtech.beatfulvibes.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mtech.library.activity.CoreListActivity;
import com.mtech.library.adapter.CoreListAdapter;
import com.mtech.library.view.SimpleDividerItemDecoration;

import java.util.ArrayList;
import java.util.Arrays;

import mobi.mtech.beatfulvibes.R;
import mobi.mtech.beatfulvibes.adapter.SettingAdapter;
import mobi.mtech.beatfulvibes.model.Setting;

/**
 * Copyright (C) 2017 Mtech.mobi. All rights reserved.
 * Created by Malek on 4/22/17.
 */

public class SettingsActivity extends CoreListActivity<Setting> {

    @Override
    public int getContentView() {
        return R.layout.activity_settings;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rvList.addItemDecoration(new SimpleDividerItemDecoration(getResources(),1, R.drawable.settings_divider));
        setBackButtonEnabled(getToolbar());
        setTitle("Settings");

    }

    /**
     * Define the adapter in here
     */
    @Override
    public CoreListAdapter<Setting> initAdapter() {
        return new SettingAdapter(mContext, rvList, new ArrayList<>(Arrays.asList(Setting.getSettings())));
    }

    /**
     * Define the Layout manager in here
     */
    @Override
    public RecyclerView.LayoutManager initLayoutManager() {
        return new LinearLayoutManager(mContext);
    }

    /**
     * Ovverride this method to change what swipe to refresh does
     */
    @Override
    public void onRefresh() {

    }

    @Override
    public void itemClicked(View view, int position) {
        switch (position) {
            case 0:
                sendEmail();
                break;
            case 1:
                openAppStore();
                break;
            case 2:
                openAboutUs();
                break;
            case 3:
                shareApp();
                break;
            case 4:
                openWebsite();
                break;
        }
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onClick(View view) {

    }

    private void openAppStore() {
        Uri uri = Uri.parse("market://details?id=" + mContext.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + mContext.getPackageName())));
        }
    }

    private void sendEmail() {

        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"beatfulvibes@gmail.com"});
        email.putExtra(Intent.EXTRA_SUBJECT, "Hello BeatfulVibes");
        email.setType("message/rfc822");

        startActivity(Intent.createChooser(email, "Choose an Email client :"));
    }

    private void openAboutUs() {
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://beatfulvibes.com/about")));
    }
    private void openWebsite() {
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://beatfulvibes.com")));
    }

    private void shareApp() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Check out this awesome app! http://play.google.com/store/apps/details?id=" + mContext.getPackageName();
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "BeatfulVibes");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }
}
