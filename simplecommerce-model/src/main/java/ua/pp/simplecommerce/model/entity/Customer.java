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

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * The entity 'Customer' has customer' details, such as name, address, email, status, orders etc.
 *
 * Created by Vladimir Kamenskiy on 23.03.2015.
 */

@Entity
@NamedQueries({
        @NamedQuery(name = Customer.FIND_ALL, query = "SELECT c FROM Customer c"),
        @NamedQuery(name = Customer.FIND_BY_ANY_NAME,
                query = "SELECT c FROM Customer c JOIN c.customerDetails d " +
                        "WHERE d.firstName LIKE :name " +
                        "OR d.lastName LIKE :name " +
                        "OR d.login LIKE :name"),
        @NamedQuery(name = Customer.FIND_BY_EMAIL,
                query = "SELECT c FROM Customer c JOIN c.customerDetails d " +
                        "WHERE d.email = :email")
})
public class Customer {

    public static final String FIND_ALL = "findAllCustomers";
    public static final String FIND_BY_ANY_NAME = "findCustomesWithFirstNameOrLastNameOrLogin";
    public static final String FIND_BY_EMAIL = "findCustomersByEmail";

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CUSTOMER_DETAILS_FK")
    private UserDetails customerDetails;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "CUSTOMER_TRANSACTION_FK")
    private List<CustomerTransaction> customerTransactions;

    /**
     * For JPA uses only
     */
    public Customer() {
    }

    /**
     * Creates the Customer instance with required fields.
     *
     * @param customerDetails the detail information about the customer
     * @param customerTransactions customer transactions reference
     */
    public Customer(UserDetails customerDetails, List<CustomerTransaction> customerTransactions) {
        this.customerDetails = customerDetails;
        this.customerTransactions = customerTransactions;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Customer setCustomerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }

    public UserDetails getCustomerDetails() {
        return customerDetails;
    }

    public Customer setCustomerDetails(UserDetails customerDetails) {
        this.customerDetails = customerDetails;
        return this;
    }

    public List<CustomerTransaction> getCustomerTransactions() {
        return customerTransactions;
    }

    public Customer setCustomerTransactions(List<CustomerTransaction> customerTransactions) {
        this.customerTransactions = customerTransactions;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Customer)) {
            return false;
        }
        Customer customer = (Customer) o;
        return Objects.equals(customerDetails, customer.customerDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerDetails);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", customerId)
                .append("customer details", customerDetails)
                .toString();
    }
}
