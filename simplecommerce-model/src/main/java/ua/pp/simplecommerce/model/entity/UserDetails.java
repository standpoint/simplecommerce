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
@Table(name = "USER_DETAILS",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"LOGIN"})})
@NamedQueries({
        @NamedQuery(name = UserDetails.FIND_ALL, query = "SELECT d FROM UserDetails d"),
        @NamedQuery(name = UserDetails.FIND_BY_LOGIN, query = "SELECT d FROM UserDetails d WHERE d.login = :login"),
        @NamedQuery(name = UserDetails.FIND_BY_LAST_NAME, query = "SELECT d FROM UserDetails d WHERE d.lastName = :lastName"),
        @NamedQuery(name = UserDetails.FIND_BY_EMAIL, query = "SELECT d FROM UserDetails d WHERE d.email = :email"),
        @NamedQuery(name = UserDetails.SORT_BY_DATE_ADDED, query = "SELECT d FROM UserDetails d ORDER BY d.dateAdded")
})
public class UserDetails {

    public static final String FIND_ALL = "findAllUsersDetails";
    public static final String FIND_BY_LOGIN = "findUserDetailsByLogin";
    public static final String FIND_BY_LAST_NAME = "findUsersDetailsByLastNames";
    public static final String FIND_BY_EMAIL = "findUsersDetailsByEmail";
    public static final String SORT_BY_DATE_ADDED = "sortUsersDetailsByDateAdded";

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_DETAILS_ID")
    private Long userDetailsId;

    @NotNull
    @Size(max = 45)
    private String login;

    @NotNull @Size(max = 255)
    private String password;

    @NotNull
    private String salt;

    @Size(max = 255)
    private String firstName;

    @Size(max = 255)
    private String lastName;

    @NotNull @Email(regexp = EMAIL_PATTERN)
    private String email;

    @NotNull @Pattern(regexp = "\\+[\\d]{12}")
    private String phone;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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

    public UserDetails setUserDetailsId(Long userDetailsId) {
        this.userDetailsId = userDetailsId;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public UserDetails setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDetails setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getSalt() {
        return salt;
    }

    public UserDetails setSalt(String salt) {
        this.salt = salt;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDetails setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDetails setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDetails setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserDetails setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public UserDetails setAddress(Address address) {
        this.address = address;
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public UserDetails setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public Calendar getDateAdded() {
        return dateAdded;
    }

    public UserDetails setDateAdded(Calendar dateAdded) {
        this.dateAdded = dateAdded;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserDetails)) {
            return false;
        }
        UserDetails that = (UserDetails) o;
        return new EqualsBuilder()
                .append(login, that.login)
                .append(firstName, that.firstName)
                .append(lastName, that.lastName)
                .append(email, that.email)
                .append(phone, that.phone)
                .append(address, that.address)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(login)
                .append(firstName)
                .append(lastName)
                .append(email)
                .append(phone)
                .append(address)
                .toHashCode();
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
