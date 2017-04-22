package mobi.mtech.beatfulvibes.model.wrapper.mixes;

import com.google.gson.annotations.SerializedName;

import mobi.mtech.beatfulvibes.model.Track;

/**
 * Copyright (C) 2017 Mtech.mobi. All rights reserved.
 * Created by Malek on 4/9/17.
 */

public class MixesDataWrapper {
    private Track[] tracks;
    @SerializedName("most_played")
    private Track[] mostPlayed;

    public MixesDataWrapper() {
    }

    public MixesDataWrapper(Track[] tracks, Track[] mostPlayed) {
        this.tracks = tracks;
        this.mostPlayed = mostPlayed;
    }

    public Track[] getTracks() {
        return tracks;
    }

    public void setTracks(Track[] tracks) {
        this.tracks = tracks;
    }

    public Track[] getMostPlayed() {
        return mostPlayed;
    }

    public void setMostPlayed(Track[] mostPlayed) {
        this.mostPlayed = mostPlayed;
    }
}
