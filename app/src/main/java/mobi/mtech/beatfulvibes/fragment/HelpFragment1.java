package mobi.mtech.beatfulvibes.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mtech.library.fragment.CoreFragment;

import mobi.mtech.beatfulvibes.R;

/**
 * Copyright (C) 2017 Mtech.mobi. All rights reserved.
 * Created by Malek on 4/8/17.
 */

public class HelpFragment1 extends CoreFragment {

    public static HelpFragment1 newInstance(){
        return new HelpFragment1();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help1, container, false);
        return view;
    }
}
