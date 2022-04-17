package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.dto.OutputOneOfferTextDTO;
import softuni.exam.models.entity.Offer;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    @Query("SELECT CONCAT(o.agent.firstName, ' ', o.agent.lastName) AS fullName," +
            " o.id AS offerId, o.apartment.area AS apartmentArea," +
            " o.apartment.town.townName AS townName, o.price AS price" +
            " FROM Offer o " +
            " WHERE o.apartment.apartmentType = 'three_rooms'" +
            " ORDER BY apartmentArea DESC, price ASC")
    List<OutputOneOfferTextDTO> findBestOffers();

    @Query("SELECT ofr FROM Offer ofr" +
            " WHERE ofr.agent.id =:agentId AND ofr.agent.firstName =:agentFirstName AND ofr.agent.email =:email AND ofr.apartment.id =:apartmentId")
    Optional<Offer> findByAgentIdAndApartmentId(long agentId, String agentFirstName, String email, long apartmentId);
}
