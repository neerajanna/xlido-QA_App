package com.crio.xlido.entities;

public class Reply {

    private final long userId;
    private final String text;

    public Reply(long userId, String text) {
        this.userId = userId;
        this.text = text;
    }

    public long getUserId() {
        return userId;
    }

    public String getText() {
        return text;
    }
}

