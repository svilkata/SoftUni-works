package com.example.spotifyplaylistapp.model.dto.view;

import com.example.spotifyplaylistapp.model.entity.SongEntity;
import com.example.spotifyplaylistapp.security.LoggedUser;

import java.util.ArrayList;
import java.util.List;

public class ViewAllSongsListModel {
    private List<SongLeftViewModel> popSongs;
    private List<SongLeftViewModel> rockSongs;
    private List<SongLeftViewModel> jazzSongs;
    private List<PlaylistSongsUserRightModel> currentUserPlaylistSongs;

    public ViewAllSongsListModel(List<SongLeftViewModel> allsongs,
                                 List<PlaylistSongsUserRightModel> currUserPlaylist) {
        this.popSongs = new ArrayList<>();
        this.rockSongs = new ArrayList<>();
        this.jazzSongs = new ArrayList<>();
        allsongs.forEach(s -> {
            if (s.getStyle().equals("POP")) {
                popSongs.add(s);
            } else if (s.getStyle().equals("ROCK")) {
                rockSongs.add(s);
            } else if (s.getStyle().equals("JAZZ")) {
                jazzSongs.add(s);
            }
        });

        this.currentUserPlaylistSongs = currUserPlaylist;
    }

    public List<SongLeftViewModel> getPopSongs() {
        return popSongs;
    }

    public List<SongLeftViewModel> getRockSongs() {
        return rockSongs;
    }

    public List<SongLeftViewModel> getJazzSongs() {
        return jazzSongs;
    }

    public List<PlaylistSongsUserRightModel> getCurrentUserPlaylistSongs() {
        return currentUserPlaylistSongs;
    }
}
