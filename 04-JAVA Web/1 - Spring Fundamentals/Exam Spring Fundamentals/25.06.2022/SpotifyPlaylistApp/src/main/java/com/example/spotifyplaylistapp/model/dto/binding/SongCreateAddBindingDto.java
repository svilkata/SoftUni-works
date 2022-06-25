package com.example.spotifyplaylistapp.model.dto.binding;

import com.example.spotifyplaylistapp.model.entity.StyleEntity;
import com.example.spotifyplaylistapp.model.entity.enums.StyleEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class SongCreateAddBindingDto {
    @NotBlank(message = "Cannot be empty")
    @Size(min = 3, max = 20, message = "Name must be between 2 and 20 characters")
    private String performer;

    @NotBlank(message = "Cannot be empty")
    @Size(min = 3, max = 20, message = "Name must be between 2 and 20 characters")
    private String title;

    @NotNull(message = "Cannot be empty")
    @Positive(message = "Song duration seconds should be a positive number")
    private Integer duration;

    @NotNull(message = "Please, enter a date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "The datetime can not be in the future")
    private LocalDate releaseDate;

    @NotNull(message = "You must select song style")
    private StyleEnum style;

    public SongCreateAddBindingDto() {
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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public StyleEnum getStyle() {
        return style;
    }

    public void setStyle(StyleEnum style) {
        this.style = style;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
