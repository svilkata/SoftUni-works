package com.example.football.models.dto;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "players")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportPlayersRootDTO {

//    @XmlElementWrapper(name = "player")  //има влагания
    @XmlElement(name = "player")
    private List<ImportOnePlayerDTO> players;

    public List<ImportOnePlayerDTO> getPlayers() {
        return players;
    }
}
