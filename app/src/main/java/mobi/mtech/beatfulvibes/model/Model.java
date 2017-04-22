package mobi.mtech.beatfulvibes.model;

import com.google.gson.annotations.SerializedName;
import com.mtech.library.CoreModel;

/**
 * Copyright (C) 2017 Mtech.mobi. All rights reserved.
 * Created by Malek on 4/10/17.
 */

public class Model extends CoreModel {
    private int id;
    @SerializedName("first_name")
    private String firstName;

    public Model(int id, String firstName) {
        this.id = id;
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
