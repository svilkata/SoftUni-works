package com.example.spotifyplaylistapp.model.dto.view;

import com.example.spotifyplaylistapp.model.entity.UserEntity;

public class PlaylistSongsUserRightModel {
    private String performer;
    private String title;
    private Integer duration;
    private long userId;

    public PlaylistSongsUserRightModel() {
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
