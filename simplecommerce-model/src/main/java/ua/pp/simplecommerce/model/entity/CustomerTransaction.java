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
import java.util.Calendar;

/**
 * The entity 'CustomerTransaction' has info about customer' transactions - such as summary info about customer' order
 * (Order entity, summary information, total order amount, date and time when the order has been added)
 *
 * Created by Vladimir Kamenskiy on 23.03.2015.
 */

@Entity
@Table(name = "CUSTOMER_TRANSACTION")
public class CustomerTransaction {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_TRANSACTION_ID")
    private Long customerTransactionId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "CUSTOMER_TRANSACTION_CUSTOMER_FK", referencedColumnName = "CUSTOMER_ID")
    private Customer customer;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "CUSTOMER_TRANSACTION_ORDER_FK")
    private Order order;

    @NotNull @Size(max = 2000)
    private String summary;

    @NotNull
    @Digits(integer = 12 ,fraction = 2) @DecimalMin(value = "0.00")
    private BigDecimal amount;

    @Past @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "DATE_ADDED")
    private Calendar dateAdded;

    /**
     * For JPA uses only
     */
    public CustomerTransaction() {
    }

    /**
     * Creates the CustomerTransaction instance with required fields
     *
     * @param order     order reference
     * @param summary   summary information about the order
     * @param amount    total amount of the order
     * @param dateAdded date and time of this transaction creating
     */
    public CustomerTransaction(Order order, Customer customer, String summary, BigDecimal amount, Calendar dateAdded) {
        this.order = order;
        this.customer = customer;
        this.summary = summary;
        this.amount = amount;
        this.dateAdded = dateAdded;
    }

    public Long getCustomerTransactionId() {
        return customerTransactionId;
    }

    public void setCustomerTransactionId(Long customerTransactionId) {
        this.customerTransactionId = customerTransactionId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Calendar getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Calendar dateAdded) {
        this.dateAdded = dateAdded;
    }
}
