package com.example.springintro.model.entity.projection;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.EditionType;

import java.math.BigDecimal;

public interface BookReducedInfo {
    String getTitle();
    EditionType getEditionType();
    AgeRestriction getAgeRestriction();
    BigDecimal getPrice();
}
