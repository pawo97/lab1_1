package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;

public class Discount {

    private Money money;

    private String discountCause;

    public String getDiscountCause() {
        return discountCause;
    }

    public Discount(Money money, String discountCause, BigDecimal discount) {
        super();
        this.money = money;
        this.discountCause = discountCause;
    }

    public Money getMoney() {
        return money;
    }

}
