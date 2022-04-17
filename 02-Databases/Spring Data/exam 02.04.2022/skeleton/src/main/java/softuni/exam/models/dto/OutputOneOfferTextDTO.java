package softuni.exam.models.dto;

import java.math.BigDecimal;

public interface OutputOneOfferTextDTO {
    String getFullName();
    long getOfferId();
    double getApartmentArea();
    String getTownName();
    BigDecimal getPrice();
}
