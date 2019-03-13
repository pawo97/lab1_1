package pl.com.bottega.ecommerce.sales.domain.offer;

import java.util.Date;

public class ProductData {

    private Money money;

    private String productId;

    private String productName;

    private Date productSnapshotDate;

    private String productType;

    public Money getMoney() {
        return money;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Date getProductSnapshotDate() {
        return productSnapshotDate;
    }

    public String getProductType() {
        return productType;
    }

    public ProductData(Money money, String productId, String productName, Date productSnapshotDate, String productType) {
        super();
        this.money = money;
        this.productId = productId;
        this.productName = productName;
        this.productSnapshotDate = productSnapshotDate;
        this.productType = productType;
    }

}
