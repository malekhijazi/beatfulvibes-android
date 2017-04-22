package mobi.mtech.beatfulvibes.model.wrapper;

import java.io.Serializable;

import mobi.mtech.beatfulvibes.model.Artist;

/**
 * Copyright (C) 2017 Mtech.mobi. All rights reserved.
 * Created by Malek on 4/9/17.
 */

public class ArtistWrapper  implements Serializable{

    private static final long serialVersionUID = -8541019128812261772L;

    private boolean success;
    private Artist[] data;

    public ArtistWrapper() {
    }

    public ArtistWrapper(boolean success, Artist[] data) {
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Artist[] getData() {
        return data;
    }

    public void setData(Artist[] data) {
        this.data = data;
    }
}
