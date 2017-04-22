package mobi.mtech.beatfulvibes.model.wrapper.search;

import java.io.Serializable;

import mobi.mtech.beatfulvibes.model.Artist;
import mobi.mtech.beatfulvibes.model.Track;

/**
 * Copyright (C) 2017 Mtech.mobi. All rights reserved.
 * Created by Malek on 4/9/17.
 */

public class SearchDataWrapper implements Serializable{

    private static final long serialVersionUID = -9176282403337613227L;
    private Track[] tracks;
    private Artist[] artists;

    public SearchDataWrapper() {
    }

    public SearchDataWrapper(Track[] tracks, Artist[] artists) {
        this.tracks = tracks;
        this.artists = artists;
    }

    public Track[] getTracks() {
        return tracks;
    }

    public void setTracks(Track[] tracks) {
        this.tracks = tracks;
    }

    public Artist[] getArtists() {
        return artists;
    }

    public void setArtists(Artist[] artists) {
        this.artists = artists;
    }
}
