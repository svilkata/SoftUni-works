package bg.softuni.intro.cats.service;

import bg.softuni.intro.cats.model.dto.CreateOwnerDto;
import org.springframework.stereotype.Service;


public interface OwnerService {

    void createOwner(CreateOwnerDto createOwnerDto);
}
