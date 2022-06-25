package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dto.binding.SongCreateAddBindingDto;
import com.example.spotifyplaylistapp.model.dto.view.PlaylistSongsUserRightModel;
import com.example.spotifyplaylistapp.model.dto.view.SongLeftViewModel;
import com.example.spotifyplaylistapp.model.dto.view.ViewAllSongsListModel;
import com.example.spotifyplaylistapp.model.entity.PlaylistEntitySongsUsers;
import com.example.spotifyplaylistapp.model.entity.SongEntity;
import com.example.spotifyplaylistapp.model.entity.StyleEntity;
import com.example.spotifyplaylistapp.model.entity.UserEntity;
import com.example.spotifyplaylistapp.repository.AllSongsRepository;
import com.example.spotifyplaylistapp.repository.PlaylistRepositorySongsUsers;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import com.example.spotifyplaylistapp.repository.UserRepository;
import com.example.spotifyplaylistapp.security.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SongsServiceImpl {
    private final AllSongsRepository allSongsRepository;
    private final PlaylistRepositorySongsUsers playlistRepositorySongsUsers;
    private final StyleRepository styleRepository;
    private UserRepository userRepository;
    private LoggedUser loggedUser;
    private final ModelMapper modelMapper;

    public SongsServiceImpl(AllSongsRepository allSongsRepository, PlaylistRepositorySongsUsers playlistRepositorySongsUsers, StyleRepository styleRepository, UserRepository userRepository, LoggedUser loggedUser, ModelMapper modelMapper) {
        this.allSongsRepository = allSongsRepository;
        this.playlistRepositorySongsUsers = playlistRepositorySongsUsers;
        this.styleRepository = styleRepository;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.modelMapper = modelMapper;
    }

    public boolean createProduct(SongCreateAddBindingDto songCreateAddBindingDto) {
        Optional<SongEntity> songByTitleAndPerformerOpt = this.allSongsRepository
                .findByTitleAndPerformer(songCreateAddBindingDto.getTitle(), songCreateAddBindingDto.getPerformer());

        if (songByTitleAndPerformerOpt.isPresent()) {
            return false;
        }

        Optional<StyleEntity> style = this.styleRepository.findByName(songCreateAddBindingDto.getStyle());
        //TODO - check if style does not exist

        SongEntity songToCreateAdd = new SongEntity();
        songToCreateAdd.setTitle(songCreateAddBindingDto.getTitle());
        songToCreateAdd.setPerformer(songCreateAddBindingDto.getPerformer());
        songToCreateAdd.setDuration(songCreateAddBindingDto.getDuration());
        songToCreateAdd.setReleaseDate(songCreateAddBindingDto.getReleaseDate());
        songToCreateAdd.setStyle(style.get());

        this.allSongsRepository.save(songToCreateAdd);

        return true;
    }

    public ViewAllSongsListModel getAllSongs() {
        List<SongEntity> all = this.allSongsRepository.findAll();
        List<SongLeftViewModel> allLeft = new ArrayList<>();
        all.forEach(sng -> {
            SongLeftViewModel leftViewModel = new SongLeftViewModel();
            leftViewModel.setStyle(sng.getStyle().getName().name());
            leftViewModel.setPerformer(sng.getPerformer());
            leftViewModel.setTitle(sng.getTitle());
            leftViewModel.setDuration(sng.getDuration());
            leftViewModel.setId(sng.getId());
            allLeft.add(leftViewModel);
        });

        List<PlaylistEntitySongsUsers> allPlaylistSongs = this.playlistRepositorySongsUsers.findAll();
        List<PlaylistSongsUserRightModel> allRight = new ArrayList<>();
        allPlaylistSongs.forEach(sng -> {
            if (loggedUser.getId() == sng.getOwner().getId()) {
                PlaylistSongsUserRightModel rightModel = new PlaylistSongsUserRightModel();
                rightModel.setTitle(sng.getTitle());
                rightModel.setPerformer(sng.getPerformer());
                rightModel.setDuration(sng.getDuration());
                rightModel.setUserId(sng.getOwner().getId());
                allRight.add(rightModel);
            }
        });


        return new ViewAllSongsListModel(allLeft, allRight);
    }


    public void addSongToPlaylist(Long songId) {
        //TODO check if same song is already added to current user's song playlist
        SongEntity songById = this.allSongsRepository.findById(songId).get();

        PlaylistEntitySongsUsers playlistOfCurrentUser = new PlaylistEntitySongsUsers();
        playlistOfCurrentUser.setTitle(songById.getTitle());
        playlistOfCurrentUser.setPerformer(songById.getPerformer());
        playlistOfCurrentUser.setDuration(songById.getDuration());
        playlistOfCurrentUser.setId(songById.getId());

        UserEntity userEntityCurrent = this.userRepository.findById(this.loggedUser.getId()).get();
        playlistOfCurrentUser.setOwner(userEntityCurrent);


        this.playlistRepositorySongsUsers.save(playlistOfCurrentUser);
    }


    public String getAllMinutesPlaylistSongs() {
        Integer[] sumSecond = new Integer[1];
        sumSecond[0] = 0;
        List<PlaylistEntitySongsUsers> allByOwner = this.playlistRepositorySongsUsers.findAllByOwner_Id(this.loggedUser.getId());

        allByOwner
                .forEach(s -> {
                    sumSecond[0] += s.getDuration();
                });

        return sumSecond[0].toString();
    }

    public void removeAllSongsFromUserPlaylist() {
        List<PlaylistEntitySongsUsers> allByOwner = this.playlistRepositorySongsUsers.findAllByOwner_Id(this.loggedUser.getId());
        this.playlistRepositorySongsUsers.deleteAll(allByOwner);
    }
}
