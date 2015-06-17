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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @NotNull @Size(max = 45)
    @Column(unique = true)
    private String login;

    @NotNull
    private String password;

    @NotNull
    @Column(name = "FIRST_NAME")
    private String firstName;

    @NotNull
    @Column(name = "SECOND_NAME")
    private String secondName;

    @NotNull
    private String email;

    @NotNull @Pattern(regexp = "\\+[\\d]{12}")
    private String phone;

    @Column(name = "ACTIVE")
    private boolean isActive;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "CUSTOMER_ADDRESS_FK")
    private Address address;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "CUSTOMER_TRANSACTION_FK")
    private List<CustomerTransaction> customerTransactions;

    @Past
    @Column(name = "DATE_ADDED")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Calendar dateAdded;

    /**
     * For JPA uses only
     */
    public Customer() {
    }

    /**
     * Creates the Customer instance with required fields
     *
     * @param login          login name of the customer
     * @param password      password
     * @param firstName     first name
     * @param secondName    second name
     * @param email         valid email address
     * @param phone         phone number (e.g. "+380671234567")
     * @param isActive      user account status (true - user account is active, false - inactive)
     * @param address       address reference
     * @param customerTransactions  customer transactions reference
     * @param dateAdded     the date and time of user account creating
     */
    public Customer(String login, String password, String firstName, String secondName, String email,
                    String phone, boolean isActive, Address address, List<CustomerTransaction> customerTransactions,
                    Calendar dateAdded) {
        this.login = login;
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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String name) {
        this.login = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<CustomerTransaction> getCustomerTransactions() {
        return customerTransactions;
    }

    public void setCustomerTransactions(List<CustomerTransaction> customerTransactions) {
        this.customerTransactions = customerTransactions;
    }

    public Calendar getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Calendar dateAdded) {
        this.dateAdded = dateAdded;
    }
}
