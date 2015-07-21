/**
 * Copyright (c) 2015. SimpleCommerce.pp.ua
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package ua.pp.simplecommerce.model.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

/**
 * The entity 'OrderLine' has info about the line of the order: ordered product info, quantity, total amount
 *
 * Created by Vladimir Kamenskiy on 23.03.2015.
 */

@Entity
@Table(name = "ORDER_LINE")
public class OrderLine {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_LINE_ID")
    private Long orderLineId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ORDER_LINE_PRODUCT_FK")
    private Product product;

    @NotNull @Min(value = 0)
    private int quantity;

    @NotNull
    @DecimalMin(value = "0.00") @Digits(integer = 12, fraction = 2)
    private BigDecimal amount;

    /**
     * For JPA uses only
     */
    public OrderLine() {
    }

    /**
     * Creates the OrderLine instance with required fields
     *
     * @param product   product reference
     * @param quantity  number of this product at the line of the order
     * @param amount    amount of products in the line of the order
     */
    public OrderLine(Product product, int quantity, BigDecimal amount) {
        this.product = product;
        this.quantity = quantity;
        this.amount = amount;
    }

    public Long getOrderLineId() {
        return orderLineId;
    }

    public OrderLine setOrderLineId(Long orderLineId) {
        this.orderLineId = orderLineId;
        return this;
    }

    public Product getProduct() {
        return product;
    }

    public OrderLine setProduct(Product product) {
        this.product = product;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public OrderLine setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public OrderLine setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderLine)) return false;
        OrderLine orderLine = (OrderLine) o;
        return new EqualsBuilder()
                .append(quantity, orderLine.quantity)
                .append(product, orderLine.product)
                .append(amount, orderLine.amount)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(product)
                .append(quantity)
                .append(amount)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", orderLineId)
                .append("product", product)
                .append("qty", quantity)
                .append("amount", amount)
                .toString();
    }
}
