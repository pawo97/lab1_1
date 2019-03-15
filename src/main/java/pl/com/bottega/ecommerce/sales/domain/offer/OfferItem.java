/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Objects;

public class OfferItem {

    private ProductData productData;

    private Discount discount;

    private Money money;

    private int quantity;

    private Money totalCost;

    public OfferItem(ProductData productdata, int quantity) {
        this(productdata, quantity, null);
    }

    public OfferItem(ProductData productdata, int quantity, Discount discount) {

        this.productData = productdata;
        this.quantity = quantity;
        this.discount = discount;

        BigDecimal discountValue = new BigDecimal(0);
        if (discount != null) {
            if (!productdata.getMoney()
                            .getCurrency()
                            .equals(discount.getMoney()
                                            .getCurrency())) {
                throw new IllegalArgumentException();
            }
            discountValue = discountValue.add(discount.getMoney()
                                                      .getValue());
        }
        this.totalCost = new Money(productdata.getMoney()
                                              .getCurrency(),
                productdata.getMoney()
                           .getValue()
                           .multiply(new BigDecimal(quantity))
                           .subtract(discountValue));
    }

    public ProductData getProductdata() {
        return productData;
    }

    public Discount getDiscount() {
        return discount;
    }

    public Money getMoney() {
        return money;
    }

    public Money getTotalCost() {
        return totalCost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productData.getMoney()
                                       .getCurrency(),
                discount.getMoney()
                        .getValue(),
                discount.getDiscountCause(), productData.getProductId(), productData.getProductName(), productData.getMoney()
                                                                                                                  .getValue(),
                productData.getProductSnapshotDate(), productData.getProductType(), quantity, totalCost.getValue());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        OfferItem other = (OfferItem) obj;
        return productData.equals(other.productData);

    }

    /**
     *
     * @param item
     * @param delta
     *            acceptable percentage difference
     * @return
     */
    public boolean sameAs(OfferItem other, double delta) {
        if (productData.getMoney()
                       .getValue() == null) {
            if (other.productData.getMoney()
                                 .getValue() != null) {
                return false;
            }
        } else if (!productData.getMoney()
                               .getValue()
                               .equals(other.productData.getMoney()
                                                        .getValue())) {
            return false;
        }
        if (productData.getProductName() == null) {
            if (other.productData.getProductName() != null) {
                return false;
            }
        } else if (!productData.getProductName()
                               .equals(other.productData.getProductName())) {
            return false;
        }

        if (productData.getProductId() == null) {
            if (other.productData.getProductId() != null) {
                return false;
            }
        } else if (!productData.getProductId()
                               .equals(other.productData.getProductId())) {
            return false;
        }
        if (productData.getProductType() != other.productData.getProductType()) {
            return false;
        }

        if (quantity != other.quantity) {
            return false;
        }

        BigDecimal max;
        BigDecimal min;
        if (totalCost.getValue()
                     .compareTo(other.totalCost.getValue()) > 0) {
            max = totalCost.getValue();
            min = other.totalCost.getValue();
        } else {
            max = other.totalCost.getValue();
            min = totalCost.getValue();
        }

        BigDecimal difference = max.subtract(min);
        BigDecimal acceptableDelta = max.multiply(BigDecimal.valueOf(delta / 100));

        return acceptableDelta.compareTo(difference) > 0;
    }

}
