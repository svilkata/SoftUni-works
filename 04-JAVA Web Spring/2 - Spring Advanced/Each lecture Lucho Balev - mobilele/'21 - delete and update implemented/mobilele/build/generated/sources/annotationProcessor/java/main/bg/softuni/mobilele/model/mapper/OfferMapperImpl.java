package bg.softuni.mobilele.model.mapper;

import bg.softuni.mobilele.model.dto.AddOfferDTO;
import bg.softuni.mobilele.model.entity.OfferEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-12T16:39:09+0300",
    comments = "version: 1.5.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.1.jar, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class OfferMapperImpl implements OfferMapper {

    @Override
    public OfferEntity addOfferDtoToOfferEntity(AddOfferDTO addOfferDTO) {
        if ( addOfferDTO == null ) {
            return null;
        }

        OfferEntity offerEntity = new OfferEntity();

        offerEntity.setEngine( addOfferDTO.getEngine() );
        offerEntity.setImageUrl( addOfferDTO.getImageUrl() );
        if ( addOfferDTO.getMileage() != null ) {
            offerEntity.setMileage( addOfferDTO.getMileage() );
        }
        offerEntity.setPrice( addOfferDTO.getPrice() );
        offerEntity.setTransmission( addOfferDTO.getTransmission() );
        if ( addOfferDTO.getYear() != null ) {
            offerEntity.setYear( addOfferDTO.getYear() );
        }
        offerEntity.setDescription( addOfferDTO.getDescription() );

        return offerEntity;
    }
}
