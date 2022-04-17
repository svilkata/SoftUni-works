package softuni.exam.models.dto.xml;

import softuni.exam.models.entity.enums.DayOfWeek;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "forecast")
@XmlAccessorType(XmlAccessType.FIELD)
public class ForecastOneFromXmlDto {
    @XmlElement(name = "day_of_week")
    @NotNull
    private DayOfWeek dayOfWeek;

    @XmlElement(name = "max_temperature")
    @NotNull
    //min and max to check manually in the service!!!
    private double maxTemperature;

    @XmlElement(name = "min_temperature")
    @NotNull
    //min and max to check manually in the service!!!
    private double minTemperature;

    @XmlElement
    @NotNull
    private String sunrise;

    @XmlElement
    @NotNull
    private String sunset;

    @XmlElement(name = "city")
    @NotNull
    private long cityId;

    public ForecastOneFromXmlDto() {
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrize) {
        this.sunrise = sunrize;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }
}
