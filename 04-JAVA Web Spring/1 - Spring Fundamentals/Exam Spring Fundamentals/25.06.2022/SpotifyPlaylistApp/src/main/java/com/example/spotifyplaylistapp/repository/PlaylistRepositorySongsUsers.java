package com.example.spotifyplaylistapp.repository;

import com.example.spotifyplaylistapp.model.entity.PlaylistEntitySongsUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepositorySongsUsers extends JpaRepository<PlaylistEntitySongsUsers, Long> {
    List<PlaylistEntitySongsUsers> findAllByOwner_Id(Long id);
}
