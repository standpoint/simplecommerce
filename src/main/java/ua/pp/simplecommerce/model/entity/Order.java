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
import java.util.List;

/**
 * The entity 'Order' has info about customer' order: lines of order, invoice number, bill-to/ship-to address etc.
 *
 * Created by Vladimir Kamenskiy on 23.03.2015.
 */

@Entity
public class Order {

    private Long orderId;
    private List<OrderLine> orderLines;
    private String invoiceNumber;
    private Address billTo;
    private Address shipTo;
    private ShippingMethod shippingMethod;
    private String comment;
    private BigDecimal amount;
    private OrderStatus orderStatus;
    private List<OrderHistory> orderHistoryList;

    public Order() {
    }

    public Order(Long orderId, List<OrderLine> orderLines, String invoiceNumber, Address billTo, Address shipTo,
                 ShippingMethod shippingMethod, String comment, BigDecimal amount, OrderStatus orderStatus,
                 List<OrderHistory> orderHistoryList) {
        this.orderId = orderId;
        this.orderLines = orderLines;
        this.invoiceNumber = invoiceNumber;
        this.billTo = billTo;
        this.shipTo = shipTo;
        this.shippingMethod = shippingMethod;
        this.comment = comment;
        this.amount = amount;
        this.orderStatus = orderStatus;
        this.orderHistoryList = orderHistoryList;
    }

    @Id @GeneratedValue
    @Column(name = "order_id")
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @OneToMany
    @JoinColumn(name = "order_lines_fk", nullable = false)
    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    @Column(name = "invoice_number", nullable = false)
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_bill_to_address_fk", nullable = false)
    public Address getBillTo() {
        return billTo;
    }

    public void setBillTo(Address billTo) {
        this.billTo = billTo;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_ship_to_address_fk", nullable = false)
    public Address getShipTo() {
        return shipTo;
    }

    public void setShipTo(Address shipTo) {
        this.shipTo = shipTo;
    }

    @Enumerated(value = EnumType.STRING)
    public ShippingMethod getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(ShippingMethod shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    @Column(length = 2000)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Column(scale = 2, nullable = false)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Enumerated(value = EnumType.STRING)
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_history_fk", nullable = false)
    public List<OrderHistory> getOrderHistoryList() {
        return orderHistoryList;
    }

    public void setOrderHistoryList(List<OrderHistory> orderHistoryList) {
        this.orderHistoryList = orderHistoryList;
    }
}
