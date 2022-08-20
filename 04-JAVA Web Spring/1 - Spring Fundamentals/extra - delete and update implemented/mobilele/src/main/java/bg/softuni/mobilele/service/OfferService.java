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
import bg.softuni.mobilele.user.CurrentUser;
import bg.softuni.mobilele.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferService {
    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;
    private final ModelRepository modelRepository;
    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;

    public OfferService(OfferRepository offerRepository, OfferMapper offerMapper, ModelRepository modelRepository,
                        UserRepository userRepository, CurrentUser currentUser, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.offerMapper = offerMapper;
        this.modelRepository = modelRepository;
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
    }

    public void addOffer(AddOfferDTO addOfferDTO) {
        OfferEntity newOffer = this.offerMapper.addOfferDtoToOfferEntity(addOfferDTO);

        //TODO - current user should be logged in

//        UserEntity seller = userRepository.findByEmail(currentUser.getEmail()).orElseThrow();
        Optional<UserEntity> seller = userRepository.findById(1L);
        ModelEntity model = modelRepository.findById(addOfferDTO.getModelId()).orElseThrow();

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
