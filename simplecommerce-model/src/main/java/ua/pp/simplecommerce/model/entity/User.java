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

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Entity 'User' obtains info about users (or staff) of the store's back-end
 *
 * Created by Vladimir Kamenskiy on 20.03.2015.
 */

@Entity
@Table(name = "USERS")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userId;

    @ManyToMany
    @JoinTable(name = "JND_USER_GROUP_USER",
            joinColumns = @JoinColumn(name = "USER_FK"),
            inverseJoinColumns = @JoinColumn(name = "USER_GROUP_FK"))
    private List<UserGroup> userGroups = new ArrayList<>();

    @NotNull @Size(max = 45)
    @Column(unique = true)
    private String login;

    @NotNull @Size(max = 255)
    private String password;

    @Size(max = 255)
    private String firstName;

    @Size(max = 255)
    private String lastName;

    @NotNull @Email
    private String email;

    @Column(name = "ENABLED")
    private boolean enabled;

    @Past @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_ADDED")
    private Calendar dateAdded;

    /**
     * For JPA uses only
     */
    public User() {
    }

    /**
     * Creates the User instance with required fields
     *
     * @param userGroups    User Group
     * @param login      nic-name of the User
     * @param password      user' password
     * @param firstName     user' first name
     * @param lastName      user' surname
     * @param email         user' email address
     * @param enabled      the status of the User
     * @param dateAdded     date and time of adding the User in the database
     */
    public User(List<UserGroup> userGroups, String login, String password, String firstName,
                String lastName, String email, boolean enabled, Calendar dateAdded) {
        this.userGroups = userGroups;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.enabled = enabled;
        this.dateAdded = dateAdded;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<UserGroup> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(List<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String username) {
        this.login = username;
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
}
