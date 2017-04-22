package mobi.mtech.beatfulvibes.model.wrapper;

import java.io.Serializable;

import mobi.mtech.beatfulvibes.model.Track;

/**
 * Copyright (C) 2017 Mtech.mobi. All rights reserved.
 * Created by Malek on 4/15/17.
 */

public class ShuffleWrapper implements Serializable {

    private static final long serialVersionUID = 8606448615343029546L;
    private boolean success;
    private Track[] data;

    public ShuffleWrapper() {
    }

    public ShuffleWrapper(boolean success, Track[] data) {
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Track[] getData() {
        return data;
    }

    public void setData(Track[] data) {
        this.data = data;
    }
}
