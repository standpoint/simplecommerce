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
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Calendar;

/**
 * Entity 'UserDetails' contains the detail information about store users and customers
 *
 * Created by Vladimir Kamenskiy on 30.06.2015.
 */
@Entity
@Table(name = "USER_DETAILS")
public class UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_DETAILS_ID")
    private Long userDetailsId;

    @NotNull
    @Size(max = 45)
    @Column(unique = true)
    private String login;

    @NotNull @Size(max = 255)
    private String password;

    @NotNull
    private String salt;

    @Size(max = 255)
    private String firstName;

    @Size(max = 255)
    private String lastName;

    @NotNull @Email
    private String email;

    @NotNull @Pattern(regexp = "\\+[\\d]{12}")
    private String phone;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ADDRESS_FK")
    private Address address;

    @Column(name = "ENABLED")
    private boolean enabled;

    @Past
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_ADDED")
    private Calendar dateAdded;

    /**
     * For JPA uses only
     */
    public UserDetails() {
    }

    /**
     * @param login login name of the customer
     * @param password password
     * @param salt salt
     * @param firstName first name
     * @param lastName last name
     * @param email e-mail address
     * @param phone phone number
     * @param address post address
     * @param enabled user account status (true - user account is enabled, false - inactive)
     * @param dateAdded the date of the user account creation
     */
    public UserDetails(String login, String password, String salt, String firstName, String lastName, String email,
                       String phone, Address address, boolean enabled, Calendar dateAdded) {
        this.login = login;
        this.password = password;
        this.salt = salt;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.enabled = enabled;
        this.dateAdded = dateAdded;
    }

    public Long getUserDetailsId() {
        return userDetailsId;
    }

    public void setUserDetailsId(Long userDetailsId) {
        this.userDetailsId = userDetailsId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Calendar getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Calendar dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", userDetailsId)
                .append("login", login)
                .append("first name", firstName)
                .append("last name", lastName)
                .append("email", email)
                .append("phone number", phone)
                .append("address", address)
                .append("is enabled", enabled)
                .append("date added", dateAdded)
                .toString();
    }
}
