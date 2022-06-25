package com.example.spotifyplaylistapp.repository;

import com.example.spotifyplaylistapp.model.entity.SongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AllSongsRepository extends JpaRepository<SongEntity, Long> {
    Optional<SongEntity> findByTitleAndPerformer(String title, String performer);
}
