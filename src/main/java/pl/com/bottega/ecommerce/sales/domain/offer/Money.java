package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;

public class Money {

    private String currency;
    private BigDecimal value;

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getValue() {
        return value;
    }
}
