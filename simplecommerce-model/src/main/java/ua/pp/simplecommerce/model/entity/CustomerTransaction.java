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
import java.util.Calendar;

/**
 * The entity 'CustomerTransaction' has info about customer' transactions - such as summary info about customer' order
 * (Order entity, summary information, total order amount, date and time when the order has been added)
 *
 * Created by Vladimir Kamenskiy on 23.03.2015.
 */

@Entity
@Table(name = "CUSTOMER_TRANSACTION")
@NamedQueries({
        @NamedQuery(name = CustomerTransaction.FIND_ALL, query = "SELECT ct FROM CustomerTransaction ct"),
        @NamedQuery(name = CustomerTransaction.FIND_BY_CUSTOMER_EMAIL,
                query = "SELECT ct FROM CustomerTransaction ct " +
                        "INNER JOIN Customer c INNER JOIN UserDetails d " +
                        "WHERE d.email = :email")
})
public class CustomerTransaction {

    public static final String FIND_ALL = "findAllTransactions";
    public static final String FIND_BY_CUSTOMER_EMAIL = "findCustomerTransactionsByEmail";

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_TRANSACTION_ID")
    private Long customerTransactionId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "CUSTOMER_TRANSACTION_CUSTOMER_FK", referencedColumnName = "CUSTOMER_ID")
    private Customer customer;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "CUSTOMER_TRANSACTION_ORDER_FK")
    private Order order;

    @NotNull @Size(max = 5000)
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

    public CustomerTransaction setOrder(Order order) {
        this.order = order;
        return this;
    }

    public Customer getCustomer() {
        return customer;
    }

    public CustomerTransaction setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public CustomerTransaction setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public CustomerTransaction setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public Calendar getDateAdded() {
        return dateAdded;
    }

    public CustomerTransaction setDateAdded(Calendar dateAdded) {
        this.dateAdded = dateAdded;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof CustomerTransaction)) return false;

        CustomerTransaction that = (CustomerTransaction) o;

        return new EqualsBuilder()
                .append(customer, that.customer)
                .append(order, that.order)
                .append(summary, that.summary)
                .append(amount, that.amount)
                .append(dateAdded, that.dateAdded)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(customer)
                .append(order)
                .append(summary)
                .append(amount)
                .append(dateAdded)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", customerTransactionId)
                .append("customer", customer)
                .append("order", order)
                .append("summary", summary)
                .append("amount", amount)
                .append("date added", dateAdded)
                .toString();
    }
}
