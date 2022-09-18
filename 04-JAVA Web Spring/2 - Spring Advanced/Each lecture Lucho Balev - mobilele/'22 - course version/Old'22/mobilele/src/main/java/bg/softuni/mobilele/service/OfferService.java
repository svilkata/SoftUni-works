package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.AddOfferDTO;
import bg.softuni.mobilele.model.entity.ModelEntity;
import bg.softuni.mobilele.model.entity.OfferEntity;
import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.model.mapper.OfferMapper;
import bg.softuni.mobilele.repository.ModelRepository;
import bg.softuni.mobilele.repository.OfferRepository;
import bg.softuni.mobilele.repository.UserRepository;
import bg.softuni.mobilele.user.CurrentUser;
import org.springframework.stereotype.Service;

@Service
public class OfferService {
    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;
    private final ModelRepository modelRepository;
    private final UserRepository userRepository;
    private final CurrentUser currentUser;

    public OfferService(OfferRepository offerRepository, OfferMapper offerMapper, ModelRepository modelRepository,
                        UserRepository userRepository, CurrentUser currentUser) {
        this.offerRepository = offerRepository;
        this.offerMapper = offerMapper;
        this.modelRepository = modelRepository;
        this.userRepository = userRepository;
        this.currentUser = currentUser;
    }

    public void addOffer(AddOfferDTO addOfferDTO) {
        OfferEntity newOffer = this.offerMapper.addOfferDtoToOfferEntity(addOfferDTO);

        //TODO - current user should be logged in

        UserEntity seller = userRepository.findByEmail(currentUser.getEmail()).orElseThrow();
        ModelEntity model = modelRepository.findById(addOfferDTO.getModelId()).orElseThrow();

        newOffer.setModel(model);
        newOffer.setSeller(seller);

        offerRepository.save(newOffer);
    }



}
