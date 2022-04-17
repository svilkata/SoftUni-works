package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sellers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SellersSeedRootDto {

    @XmlElement(name = "seller")
    private List<SellerSeedOneDto> sellers;

    public SellersSeedRootDto() {
    }

    public List<SellerSeedOneDto> getSellers() {
        return sellers;
    }

    public void setSellers(List<SellerSeedOneDto> sellers) {
        this.sellers = sellers;
    }
}
