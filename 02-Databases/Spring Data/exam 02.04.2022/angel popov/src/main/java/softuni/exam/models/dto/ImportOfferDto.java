package softuni.exam.models.dto;

import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.util.Date;

@XmlRootElement(name = "offer")
public class ImportOfferDto {

    @XmlElement(name = "price")
    @Positive
    private BigDecimal price;

    @XmlElement(name = "agent")
    private AgentNameDto agentNameDto;

    @XmlElement(name = "apartment")
    private ApartmentIdDto apartmentIdDto;

    @XmlElement(name = "publishedOn")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date publishedOn;

    public BigDecimal getPrice() {
        return price;
    }

    public AgentNameDto getAgentNameDto() {
        return agentNameDto;
    }

    public ApartmentIdDto getApartmentIdDto() {
        return apartmentIdDto;
    }

    public Date getPublishedOn() {
        return publishedOn;
    }
}
