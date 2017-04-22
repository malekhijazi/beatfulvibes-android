package mobi.mtech.beatfulvibes.model;

import mobi.mtech.beatfulvibes.R;

/**
 * Copyright (C) 2017 Mtech.mobi. All rights reserved.
 * Created by Malek on 4/22/17.
 */

public class Setting {
    private int icon;
    private String title;

    public Setting(int icon, String title) {
        this.icon = icon;
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static Setting[] getSettings() {
        Setting[] settings = new Setting[5];

        settings[0] = new Setting(R.drawable.ic_email_white_24dp, "Feedback");
        settings[1] = new Setting(R.drawable.ic_star_white_24dp, "Rates us");
        settings[2] = new Setting(R.drawable.ic_description_white_24dp, "About us");
        settings[3] = new Setting(R.drawable.ic_share_white_24dp, "Share BeatfulVibes");
        settings[4] = new Setting(R.drawable.ic_link_white_24dp, "Website");

        return settings;
    }
}
