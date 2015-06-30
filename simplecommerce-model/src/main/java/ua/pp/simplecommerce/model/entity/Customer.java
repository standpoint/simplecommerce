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
import java.util.List;

/**
 * The entity 'Customer' has customer' details, such as name, address, email, status, orders etc.
 *
 * Created by Vladimir Kamenskiy on 23.03.2015.
 */

@Entity
public class Customer {

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

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<CustomerTransaction> getCustomerTransactions() {
        return customerTransactions;
    }

    public void setCustomerTransactions(List<CustomerTransaction> customerTransactions) {
        this.customerTransactions = customerTransactions;
    }
}
