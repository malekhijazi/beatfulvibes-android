package mobi.mtech.beatfulvibes.model;

import java.io.Serializable;

/**
 * Copyright (C) 2017 Mtech.mobi. All rights reserved.
 * Created by Malek on 4/8/17.
 */

public class Artist implements Serializable {

    private static final long serialVersionUID = -417534740385927073L;
    private int id;
    private String name;
    private String image;

    public Artist() {
    }

    public Artist(int id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
