package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.entity.StyleEntity;
import com.example.spotifyplaylistapp.model.entity.enums.StyleEnum;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StyleServiceImpl {
    private final StyleRepository styleRepository;

    public StyleServiceImpl(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    public void initCategories() {
        if (this.styleRepository.count() == 0) {
            List<StyleEntity> styles = Arrays.stream(StyleEnum.values())
                    .map(kind -> {
                        StyleEntity styleEntity = new StyleEntity(kind);
                        switch (kind.name()) {
                            case "POP":
                                styleEntity.setDescription("POP music is always popular");
                                break;
                            case "ROCK":
                                styleEntity.setDescription("Rock music never dies");
                                break;
                            case "JAZZ":
                                styleEntity.setDescription("Jazz music is for ladies and gentlemen");
                                break;
                        }

                        return styleEntity;
                    })
                    .collect(Collectors.toList());

            this.styleRepository.saveAll(styles);
        }

    }
}
