package mobi.mtech.beatfulvibes.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Copyright (C) 2017 Mtech.mobi. All rights reserved.
 * Created by Malek on 4/8/17.
 */

public class Track implements Serializable {
    private static final long serialVersionUID = -5343339701397258426L;

    private int id;
    private String name;
    @SerializedName("artist_id")
    private int artistId;
    @SerializedName("category_id")
    private int categoryId;
    private String link;
    @SerializedName("youtube_id")
    private String youtubeId;
    @SerializedName("played_count")
    private int playedCount;
    private String thumbnail;
    private int type;
    private int nsfw;
    private Artist artist;

    public Track() {
    }

    public Track(int id, String name, int artistId, int categoryId, String link, String youtubeId, int playedCount, String thumbnail, int type, int nsfw, Artist artist) {
        this.id = id;
        this.name = name;
        this.artistId = artistId;
        this.categoryId = categoryId;
        this.link = link;
        this.youtubeId = youtubeId;
        this.playedCount = playedCount;
        this.thumbnail = thumbnail;
        this.type = type;
        this.nsfw = nsfw;
        this.artist = artist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    public int getPlayedCount() {
        return playedCount;
    }

    public void setPlayedCount(int playedCount) {
        this.playedCount = playedCount;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isNsfw() {
        if (this.nsfw == 1) {
            return true;
        } else {
            return false;
        }
    }

    public void setNsfw(boolean nsfw) {
        if (nsfw) {
            this.nsfw = 1;
        } else {
            this.nsfw = 0;
        }

    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
