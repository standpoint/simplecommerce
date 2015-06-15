/*
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

    @Id @GeneratedValue
    @Column(name = "ORDER_LINE_ID", nullable = false)
    private Long orderLineId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ORDER_LINE_PRODUCT_FK")
    private Product product;

    @NotNull @Min(value = 0)
    @Column(nullable = false)
    private int quantity;

    @NotNull
    @DecimalMin(value = "0.00") @Digits(integer = 12, fraction = 2)
    @Column(scale = 2, nullable = false)
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

    public void setOrderLineId(Long orderLineId) {
        this.orderLineId = orderLineId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
