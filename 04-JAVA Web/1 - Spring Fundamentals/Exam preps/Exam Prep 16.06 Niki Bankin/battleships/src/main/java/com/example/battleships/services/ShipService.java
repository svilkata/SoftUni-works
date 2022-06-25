package com.example.battleships.services;

import com.example.battleships.models.dto.binding.CreateShipDto;
import com.example.battleships.models.dto.service.FightingDetailsServiceModelDto;
import com.example.battleships.models.entities.CategoryEntity;
import com.example.battleships.models.entities.ShipEntity;
import com.example.battleships.models.entities.UserEntity;
import com.example.battleships.models.entities.enums.ShipType;
import com.example.battleships.repositories.CategoryRepository;
import com.example.battleships.repositories.ShipRepository;
import com.example.battleships.repositories.UserRepository;
import com.example.battleships.security.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShipService {
    private ShipRepository shipRepository;
    private CategoryRepository categoryRepository;
    private final LoggedUser loggedUser;
    private UserRepository userRepository;

    @Autowired
    public ShipService(ShipRepository shipRepository, CategoryRepository categoryRepository,
                       LoggedUser loggedUser, UserRepository userRepository) {
        this.shipRepository = shipRepository;
        this.categoryRepository = categoryRepository;
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
    }

    public boolean create(CreateShipDto createShipDto) {
        Optional<ShipEntity> shipByNameOpt = this.shipRepository.findByName(createShipDto.getName());

        if (shipByNameOpt.isPresent()) {
            return false;
        }

        ShipType shipType = switch (createShipDto.getCategory()) {
            case 0 -> ShipType.BATTLE;
            case 1 -> ShipType.CARGO;
            case 2 -> ShipType.PATROL;
            default -> null;
        };

        Optional<CategoryEntity> categoryOpt = this.categoryRepository.findByName(shipType);

        Optional<UserEntity> owner = this.userRepository.findById(this.loggedUser.getId());

        ShipEntity ship = new ShipEntity();
        ship.setName(createShipDto.getName());
        ship.setPower(createShipDto.getPower());
        ship.setHealth(createShipDto.getHealth());
        ship.setCreated(createShipDto.getCreated());
        ship.setCategory(categoryOpt.get());
        ship.setUser(owner.get());

        this.shipRepository.save(ship);

        return true;
    }

    public List<ShipEntity> findAllShips() {
        List<ShipEntity> currentAllShips = this.shipRepository.findAll()
                .stream()
                .sorted((f, s) -> {
                    int res = f.getName().compareTo(s.getName());
                    if (res == 0) {
                        res = Long.compare(f.getHealth(), s.getHealth());
                        if (res == 0) {
                            res = Long.compare(f.getPower(), s.getPower());
                            return res;
                        } else {
                            return res;
                        }

                    } else {
                        return res;
                    }
                })
                .collect(Collectors.toList());

        //TODO check if a ship should be deleted

        return currentAllShips;
    }

    public void fire(FightingDetailsServiceModelDto fightingDetailsServiceModelDtoModel) {
        ShipEntity attacker = this.shipRepository
                .findById(fightingDetailsServiceModelDtoModel.getAttackerShipId()).get();

        ShipEntity defender = this.shipRepository
                .findById(fightingDetailsServiceModelDtoModel.getDefenderShipId()).get();

        defender.setHealth(defender.getHealth() - attacker.getPower());
        if (defender.getHealth() <= 0){
            this.shipRepository.delete(defender);
        } else {
            this.shipRepository.save(defender);
        }
    }
}
