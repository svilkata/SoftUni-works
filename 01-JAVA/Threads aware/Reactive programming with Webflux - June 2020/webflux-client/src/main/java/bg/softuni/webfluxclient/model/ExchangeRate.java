package bg.softuni.webfluxclient.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

@Document //като @Entity анотацията за релационните база данни, но за MongoDB
public class ExchangeRate {
    @Id
    private String id;

    private String fromCurrency;
    private String toCurrency;
    private Instant time;
    private BigDecimal rate;

    public String getId() {
        return id;
    }

    public ExchangeRate setId(String id) {
        this.id = id;
        return this;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public ExchangeRate setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
        return this;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public ExchangeRate setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
        return this;
    }

    public Instant getTime() {
        return time;
    }

    public ExchangeRate setTime(Instant time) {
        this.time = time;
        return this;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public ExchangeRate setRate(BigDecimal rate) {
        this.rate = rate;
        return this;
    }
}
