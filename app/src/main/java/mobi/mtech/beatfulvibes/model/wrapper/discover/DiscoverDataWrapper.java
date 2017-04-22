package mobi.mtech.beatfulvibes.model.wrapper.discover;

import java.io.Serializable;

import mobi.mtech.beatfulvibes.model.Track;

/**
 * Copyright (C) 2017 Mtech.mobi. All rights reserved.
 * Created by Malek on 4/8/17.
 */

public class DiscoverDataWrapper implements Serializable {

    private static final long serialVersionUID = 5567502066641953222L;
    private Track[] New;
    private Track[] Featured;
    private Track[] MostPlayed;

    public DiscoverDataWrapper() {
    }

    public DiscoverDataWrapper(Track[] aNew, Track[] featured, Track[] mostPlayed) {
        New = aNew;
        Featured = featured;
        MostPlayed = mostPlayed;
    }

    public Track[] getNew() {
        return New;
    }

    public void setNew(Track[] aNew) {
        New = aNew;
    }

    public Track[] getFeatured() {
        return Featured;
    }

    public void setFeatured(Track[] featured) {
        Featured = featured;
    }

    public Track[] getMostPlayed() {
        return MostPlayed;
    }

    public void setMostPlayed(Track[] mostPlayed) {
        MostPlayed = mostPlayed;
    }
}
