package mobi.mtech.beatfulvibes.activity;

import android.support.v4.app.Fragment;

import com.mtech.library.activity.CoreHelpActivity;

import java.util.ArrayList;

import mobi.mtech.beatfulvibes.constant.Constant;
import mobi.mtech.beatfulvibes.fragment.HelpFragment1;

/**
 * Copyright (C) 2017 Mtech.mobi. All rights reserved.
 * Created by Malek on 4/8/17.
 */

public class HelpActivity extends CoreHelpActivity {
    /**
     * @return list of fragments to be shown in the viewpager
     */
    @Override
    public ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> list = new ArrayList<>();
        list.add(HelpFragment1.newInstance());
        list.add(HelpFragment1.newInstance());
        list.add(HelpFragment1.newInstance());
        list.add(HelpFragment1.newInstance());
        return list;
    }

    /**
     * @return the class which should be opened when done btn is clicked
     */
    @Override
    public Class getNextActivity() {
        return MainActivity.class;
    }

    @Override
    protected void onDestroy() {
        mCacheManager.set(Constant.HELP_FINISHED, true);
        super.onDestroy();
    }
}
