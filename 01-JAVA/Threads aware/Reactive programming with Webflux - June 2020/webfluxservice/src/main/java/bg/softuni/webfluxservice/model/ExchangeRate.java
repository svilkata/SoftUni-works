package bg.softuni.webfluxservice.model;

import java.math.BigDecimal;
import java.time.Instant;

public class ExchangeRate {
    private String fromCurrency;
    private String toCurrency;
    private Instant time;
    private BigDecimal rate;

    public ExchangeRate(String fromCurrency, String toCurrency, Instant time, BigDecimal rate) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.time = time;
        this.rate = rate;
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
