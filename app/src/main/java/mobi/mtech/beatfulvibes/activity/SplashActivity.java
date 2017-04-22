package mobi.mtech.beatfulvibes.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.crashlytics.android.Crashlytics;
import com.mtech.library.manager.CoreCacheManager;

import io.fabric.sdk.android.Fabric;
import mobi.mtech.beatfulvibes.constant.Constant;

/**
 * Copyright (C) 2017 Mtech.mobi. All rights reserved.
 * Created by Malek on 4/8/17.
 */

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        if (CoreCacheManager.getInstance(getApplicationContext()).getBoolean(Constant.HELP_FINISHED)) {
            startActivity(new Intent(this, MainActivity.class));
            this.finish();
        } else {
            startActivity(new Intent(this, HelpActivity.class));
            finish();
        }

    }
}
