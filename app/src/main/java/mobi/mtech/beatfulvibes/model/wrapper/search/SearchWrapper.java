package mobi.mtech.beatfulvibes.model.wrapper.search;

import java.io.Serializable;

import mobi.mtech.beatfulvibes.model.Track;

/**
 * Copyright (C) 2017 Mtech.mobi. All rights reserved.
 * Created by Malek on 4/9/17.
 */

public class SearchWrapper implements Serializable{

    private static final long serialVersionUID = -7637508854608620781L;
    private boolean success;
    private SearchDataWrapper data;

    public SearchWrapper() {
    }

    public SearchWrapper(boolean success, SearchDataWrapper data) {
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public SearchDataWrapper getData() {
        return data;
    }

    public void setData(SearchDataWrapper data) {
        this.data = data;
    }
}
