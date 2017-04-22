package mobi.mtech.beatfulvibes.model.wrapper.browse;

import mobi.mtech.beatfulvibes.model.wrapper.discover.DiscoverDataWrapper;

/**
 * Copyright (C) 2017 Mtech.mobi. All rights reserved.
 * Created by Malek on 4/8/17.
 */

public class BrowseWrapper {
    private boolean success;
    private BrowseDataWrapper data;

    public BrowseWrapper() {
    }

    public BrowseWrapper(boolean success, BrowseDataWrapper data) {
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public BrowseDataWrapper getData() {
        return data;
    }

    public void setData(BrowseDataWrapper data) {
        this.data = data;
    }
}
