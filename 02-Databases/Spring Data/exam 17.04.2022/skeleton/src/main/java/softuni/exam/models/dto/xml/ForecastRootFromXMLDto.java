package softuni.exam.models.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "forecasts")
@XmlAccessorType(XmlAccessType.FIELD)
public class ForecastRootFromXMLDto {

    @XmlElement(name = "forecast")
    private List<ForecastOneFromXmlDto> forecasts;

    public ForecastRootFromXMLDto() {
    }

    public List<ForecastOneFromXmlDto> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<ForecastOneFromXmlDto> forecasts) {
        this.forecasts = forecasts;
    }
}
