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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long orderId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ORDER_LINES_FK")
    private List<OrderLine> orderLines;

    @NotNull @Size(max = 20)
    @Column(name = "INVOICE_NUMBER")
    private String invoiceNumber;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ORDER_BILL_TO_ADDRESS_FK")
    private Address billTo;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "ORDER_SHIP_TO_ADDRESS_FK")
    private Address shipTo;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "SHIPPING_METHOD")
    private ShippingMethod shippingMethod;

    @Size(max = 2000)
    private String comment;

    @NotNull
    @DecimalMin(value = "0.00") @Digits(integer = 12, fraction = 2)
    private BigDecimal amount;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "ORDER_STATUS")
    private OrderStatus orderStatus;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ORDER_HISTORY_FK")
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

    public String getSummary() {

        //TODO : there is need to find out the better concept, look and feel and implementation of the 'Order Summary'

        StringBuilder summary = new StringBuilder("Order summary: \n");
        List<OrderLine> orderLines = getOrderLines();
        int pos = 0;
        if(orderLines != null && !orderLines.isEmpty()) {
            for (OrderLine orderLine : orderLines) {
                summary.append(++pos + ".\t" + orderLine.toString() + "\n");
            }
            summary.append("Amount: " + getAmount());
            return summary.toString();
        }
        return "";
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Address getBillTo() {
        return billTo;
    }

    public void setBillTo(Address billTo) {
        this.billTo = billTo;
    }

    public Address getShipTo() {
        return shipTo;
    }

    public void setShipTo(Address shipTo) {
        this.shipTo = shipTo;
    }

    public ShippingMethod getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(ShippingMethod shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderHistory> getOrderHistoryList() {
        return orderHistoryList;
    }

    public void setOrderHistoryList(List<OrderHistory> orderHistoryList) {
        this.orderHistoryList = orderHistoryList;
    }
}
