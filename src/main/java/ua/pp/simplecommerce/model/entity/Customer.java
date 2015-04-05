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
import java.util.Calendar;
import java.util.List;

/**
 * The entity 'Customer' has customer' details, such as name, address, email, status, orders etc.
 *
 * Created by Vladimir Kamenskiy on 23.03.2015.
 */

@Entity
public class Customer {

    private Long customerId;
    private String name;
    private String password;
    private String firstName;
    private String secondName;
    private String email;
    private String phone;
    private boolean isActive;
    private Address address;
    private List<CustomerTransaction> customerTransactions;
    private Calendar dateAdded;

    /**
     * For JPA uses only
     */
    public Customer() {
    }

    /**
     * Creates the Customer instance with required fields
     *
     * @param name          login name of the customer
     * @param password      password
     * @param firstName     first name
     * @param secondName    second name
     * @param email         valid email address
     * @param phone         phone number (e.g. "+380671234567")
     * @param isActive      user account status (true - user account is active, false - disactive)
     * @param address       address reference
     * @param customerTransactions  customer transactions reference
     * @param dateAdded     the date and time of user account creating
     */
    public Customer(String name, String password, String firstName, String secondName, String email,
                    String phone, boolean isActive, Address address, List<CustomerTransaction> customerTransactions,
                    Calendar dateAdded) {
        this.name = name;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.phone = phone;
        this.isActive = isActive;
        this.address = address;
        this.customerTransactions = customerTransactions;
        this.dateAdded = dateAdded;
    }

    @Id @GeneratedValue
    @Column(name = "CUSTOMER_ID")
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @NotNull @Size(max = 45)
    @Column(unique = true, nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull
    @Column(name = "FIRST_NAME", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    @Column(name = "SECOND_NAME", nullable = false)
    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @NotNull
    @Column(nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull @Pattern(regexp = "\\+[\\d]{12}")
    @Column(nullable = false)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ADDRESS_FK")
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @OneToMany
    @JoinColumn(name = "CUSTOMER_TRANSACTION_FK")
    public List<CustomerTransaction> getCustomerTransactions() {
        return customerTransactions;
    }

    public void setCustomerTransactions(List<CustomerTransaction> customerTransactions) {
        this.customerTransactions = customerTransactions;
    }

    @Past
    @Column(name = "DATE_ADDED", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    public Calendar getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Calendar dateAdded) {
        this.dateAdded = dateAdded;
    }
}
