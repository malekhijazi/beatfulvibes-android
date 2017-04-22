package mobi.mtech.beatfulvibes.model.wrapper.discover;

import java.io.Serializable;

import mobi.mtech.beatfulvibes.model.wrapper.discover.DiscoverDataWrapper;

/**
 * Copyright (C) 2017 Mtech.mobi. All rights reserved.
 * Created by Malek on 4/8/17.
 */

public class DiscoverWrapper  implements Serializable{

    private static final long serialVersionUID = 4433744708993668003L;
    private boolean success;
    private DiscoverDataWrapper data;

    public DiscoverWrapper() {
    }

    public DiscoverWrapper(boolean success, DiscoverDataWrapper data) {
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DiscoverDataWrapper getData() {
        return data;
    }

    public void setData(DiscoverDataWrapper data) {
        this.data = data;
    }
}
