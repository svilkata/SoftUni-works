package com.example.battleships.models.dto.service;

public class FightingDetailsServiceModelDto {
    private Long attackerShipId;
    private Long defenderShipId;

    public FightingDetailsServiceModelDto() {
    }

    public Long getAttackerShipId() {
        return attackerShipId;
    }

    public void setAttackerShipId(Long attackerShipId) {
        this.attackerShipId = attackerShipId;
    }

    public Long getDefenderShipId() {
        return defenderShipId;
    }

    public void setDefenderShipId(Long defenderShipId) {
        this.defenderShipId = defenderShipId;
    }
}
