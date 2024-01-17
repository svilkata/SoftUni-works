package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OfferService {
    public boolean isOwner(String userName, UUID offerId) {
        boolean isOwner = offerRepository
                .findById(offerId)
                .filter(o -> o.getSeller().getEmail().equals(userName))
                .isPresent();

        if (isOwner) {
            return true;
        }

        return userRepository.
                findByEmail(userName)
                .filter(this::isAdmin)
                .isPresent();
    }

    private boolean isAdmin(UserEntity user) {
        return user.getUserRoles()
                .stream()
                .anyMatch(r -> r.getUserRole() == UserRoleEnum.ADMIN);
    }

    public void deleteOfferById(UUID uuid) {

    }
}
