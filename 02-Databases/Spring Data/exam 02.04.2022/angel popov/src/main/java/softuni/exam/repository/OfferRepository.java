package softuni.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import softuni.exam.models.entity.Offer;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer,Long> {

    @Query("SELECT o FROM Offer o " +
            "JOIN o.apartment as a " +
            "WHERE a.apartmentType = 1 " +
            "ORDER BY a.area DESC, o.price ASC")
    List<Offer> findBestOffers();

}
