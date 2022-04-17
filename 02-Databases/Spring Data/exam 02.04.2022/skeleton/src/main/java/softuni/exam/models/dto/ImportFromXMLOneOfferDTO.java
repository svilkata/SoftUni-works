package softuni.exam.models.dto;

import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.time.LocalDate;

@XmlRootElement(name = "offer")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportFromXMLOneOfferDTO {
    @XmlElement(name = "price")
    @Positive
    private BigDecimal price;

    @XmlElement(name = "agent")
    private ImportFromXMLOneOfferAgentNameDTO agentName;

    @XmlElement(name = "apartment")
    private ImportFromXMLOneOfferApartmentIdDTO apartmentId;

    @XmlElement(name = "publishedOn")
    private String publishedOn;

    public ImportFromXMLOneOfferDTO() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ImportFromXMLOneOfferAgentNameDTO getAgentName() {
        return agentName;
    }

    public void setAgentName(ImportFromXMLOneOfferAgentNameDTO agentName) {
        this.agentName = agentName;
    }

    public ImportFromXMLOneOfferApartmentIdDTO getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(ImportFromXMLOneOfferApartmentIdDTO apartmentId) {
        this.apartmentId = apartmentId;
    }

    public String getPublishedOn() {
        return publishedOn;
    }

    public void setPublishedOn(String publishedOn) {
        this.publishedOn = publishedOn;
    }
}
