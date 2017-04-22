package mobi.mtech.beatfulvibes.model.wrapper.mixes;

/**
 * Copyright (C) 2017 Mtech.mobi. All rights reserved.
 * Created by Malek on 4/9/17.
 */

public class MixesWrapper {
    private boolean success;
    private MixesDataWrapper data;

    public MixesWrapper() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public MixesDataWrapper getData() {
        return data;
    }

    public void setData(MixesDataWrapper data) {
        this.data = data;
    }
}
