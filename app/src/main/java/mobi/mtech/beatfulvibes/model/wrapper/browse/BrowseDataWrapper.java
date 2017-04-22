package mobi.mtech.beatfulvibes.model.wrapper.browse;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import mobi.mtech.beatfulvibes.model.Track;
import mobi.mtech.beatfulvibes.model.Category;

/**
 * Copyright (C) 2017 Mtech.mobi. All rights reserved.
 * Created by Malek on 4/8/17.
 */

public class BrowseDataWrapper implements Serializable {

    private static final long serialVersionUID = -4579647024858792392L;

    @SerializedName("total_pages")
    private int totalPages;
    private int page;
    private Track[] tracks;
    private Category[] categories;
    @SerializedName("most_played")
    private Track[] mostPlayed;

    public BrowseDataWrapper() {
    }

    public BrowseDataWrapper(int totalPages, int page, Track[] tracks, Category[] categories, Track[] mostPlayed) {
        this.totalPages = totalPages;
        this.page = page;
        this.tracks = tracks;
        this.categories = categories;
        this.mostPlayed = mostPlayed;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Track[] getTracks() {
        return tracks;
    }

    public void setTracks(Track[] tracks) {
        this.tracks = tracks;
    }

    public Category[] getCategories() {
        return categories;
    }

    public void setCategories(Category[] categories) {
        this.categories = categories;
    }

    public Track[] getMostPlayed() {
        return mostPlayed;
    }

    public void setMostPlayed(Track[] mostPlayed) {
        this.mostPlayed = mostPlayed;
    }
}
