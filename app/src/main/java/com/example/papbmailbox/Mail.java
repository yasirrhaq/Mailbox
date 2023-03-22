package com.example.papbmailbox;

import android.text.TextUtils;

public class Mail {
    private String name, excerpt;

    public Mail(String n, String e) {
        this.name = n;
        this.excerpt = e;
    }

    public String getName() {
        return name;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public String getInitial() {
        if (!TextUtils.isEmpty(this.name))
            return this.name.substring(0, 1).toUpperCase();
        else
            return "-";
    }
}
