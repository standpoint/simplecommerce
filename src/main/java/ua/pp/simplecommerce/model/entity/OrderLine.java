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
import java.math.BigDecimal;

/**
 * The entity 'OrderLine' has info about the line of the order: ordered product info, quantity, total amount
 *
 * Created by Vladimir Kamenskiy on 23.03.2015.
 */

@Entity
@Table(name = "order_line")
public class OrderLine {

    private Long orderLineId;
    private Product product;
    private int quantity;
    private BigDecimal amount;

    public OrderLine() {
    }

    public OrderLine(Long orderLineId, Product product, int quantity, BigDecimal amount) {
        this.orderLineId = orderLineId;
        this.product = product;
        this.quantity = quantity;
        this.amount = amount;
    }

    @Id @GeneratedValue
    @Column(name = "order_line_id", nullable = false)
    public Long getOrderLineId() {
        return orderLineId;
    }

    public void setOrderLineId(Long orderLineId) {
        this.orderLineId = orderLineId;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_line_product_fk", nullable = false)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Column(nullable = false)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Column(scale = 2, nullable = false)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
