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
import java.util.List;

/**
 * The entity 'Order' has info about customer' order: lines of order, invoice number, bill-to/ship-to address etc.
 *
 * Created by Vladimir Kamenskiy on 23.03.2015.
 */

@Entity
@Table(name = "ORDERS")
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

    /**
     * For JPA uses only
     */
    public Order() {
    }

    /**
     * Creates the Order instance with required fields
     *
     * @param orderLines    list of lines in the order
     * @param invoiceNumber number of the invoice
     * @param billTo        bill-to address
     * @param shipTo        ship-to address
     * @param shippingMethod    method of the shipping
     * @param comment       comment to the order
     * @param amount        total amount of the order
     * @param orderStatus   status of the order
     * @param orderHistoryList  list of the order history updates
     */
    public Order(List<OrderLine> orderLines, String invoiceNumber, Address billTo, Address shipTo,
                 ShippingMethod shippingMethod, String comment, BigDecimal amount, OrderStatus orderStatus,
                 List<OrderHistory> orderHistoryList) {
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
    @Column(name = "ORDER_ID")
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @OneToMany
    @JoinColumn(name = "ORDER_LINES_FK")
    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    @NotNull @Size(max = 20)
    @Column(name = "INVOICE_NUMBER", nullable = false, length = 20)
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_BILL_TO_ADDRESS_FK", nullable = false)
    public Address getBillTo() {
        return billTo;
    }

    public void setBillTo(Address billTo) {
        this.billTo = billTo;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_SHIP_TO_ADDRESS_FK", nullable = false)
    public Address getShipTo() {
        return shipTo;
    }

    public void setShipTo(Address shipTo) {
        this.shipTo = shipTo;
    }

    @Enumerated(value = EnumType.STRING)
    @Column(name = "SHIPPING_METHOD")
    public ShippingMethod getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(ShippingMethod shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    @Size(max = 2000)
    @Column(length = 2000)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @NotNull
    @DecimalMin(value = "0.00") @Digits(integer = 12, fraction = 2)
    @Column(scale = 2, nullable = false)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Enumerated(value = EnumType.STRING)
    @Column(name = "ORDER_STATUS")
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_HISTORY_FK", nullable = false)
    public List<OrderHistory> getOrderHistoryList() {
        return orderHistoryList;
    }

    public void setOrderHistoryList(List<OrderHistory> orderHistoryList) {
        this.orderHistoryList = orderHistoryList;
    }
}
