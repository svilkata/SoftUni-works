package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.AddOfferDTO;
import bg.softuni.mobilele.model.dto.OfferDetailsViewDTO;
import bg.softuni.mobilele.model.dto.OfferUpdateModelDTO;
import bg.softuni.mobilele.model.entity.ModelEntity;
import bg.softuni.mobilele.model.entity.OfferEntity;
import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.model.mapper.OfferMapper;
import bg.softuni.mobilele.repository.ModelRepository;
import bg.softuni.mobilele.repository.OfferRepository;
import bg.softuni.mobilele.repository.UserRepository;
import bg.softuni.mobilele.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class OfferService {
    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;
    private final ModelRepository modelRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public OfferService(OfferRepository offerRepository, OfferMapper offerMapper, ModelRepository modelRepository,
                        UserRepository userRepository, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.offerMapper = offerMapper;
        this.modelRepository = modelRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

//    public void addOffer(AddOfferDTO addOfferDTO, Principal principal) {
    public void addOffer(AddOfferDTO addOfferDTO, String ownerId) {
//        OfferAddServiceModel offerAddServiceModel = modelMapper.map(addOfferDTO, OfferAddServiceModel.class);
//        OfferEntity newOffer = modelMapper.map(offerAddServiceModel, OfferEntity.class);
//        newOffer.setCreated(Instant.now());
//        newOffer.setSeller(userRepository.findByUsername(principal.getName()).orElseThrow());
//        newOffer.setSeller(userRepository.findByUsername(ownerId).orElseThrow());
//        ModelEntity model = modelRepository.getById(addOfferDTO.getModelId());
//        newOffer.setModel(model);

        OfferEntity newOffer = this.offerMapper.addOfferDtoToOfferEntity(addOfferDTO);
        //TODO - current user should be logged in

        Optional<UserEntity> seller = userRepository.findById(1L);
        ModelEntity model = modelRepository.findById(addOfferDTO.getModelId()).orElseThrow();
//        ModelEntity model = modelRepository.findById(ownerId).orElseThrow();


//        OfferEntity newOffer = modelMapper.map();

        newOffer.setModel(model);
        newOffer.setSeller(seller.get());

        offerRepository.save(newOffer);
    }

    public List<OfferEntity> findAllOffers() {
        return offerRepository.findAll();
    }

    public OfferDetailsViewDTO findById(Long id) {
        OfferEntity offerEntityOpt = offerRepository.findById(id).get();
        OfferDetailsViewDTO offerDetailsViewDTO = modelMapper.map(offerEntityOpt, OfferDetailsViewDTO.class);

        return offerDetailsViewDTO;
    }

    public void deleteOffer(Long id) {
        offerRepository.deleteById(id);
    }

    public void updateOffer(OfferUpdateModelDTO offerModel) {
        OfferEntity offerEntity = offerRepository.findById(offerModel.getId()).orElseThrow(() ->
                new ObjectNotFoundException("Offer with id " + offerModel.getId() + " not found!"));

        offerEntity.setPrice(offerModel.getPrice())
                .setDescription(offerModel.getDescription())
                .setEngine(offerModel.getEngine())
                .setImageUrl(offerModel.getImageUrl())
                .setMileage(offerModel.getMileage())
                .setTransmission(offerModel.getTransmission())
                .setYear(offerModel.getYear());

        offerRepository.save(offerEntity);
    }
}
