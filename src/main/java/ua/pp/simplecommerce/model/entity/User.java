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

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
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

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_GROUP_FK")
    private List<UserGroup> userGroups;

    @NotNull @Size(max = 45)
    @Column(nullable = false, unique = true, length = 45)
    private String username;

    @NotNull @Size(max = 255)
    @Column(nullable = false)
    private String password;

    @Size(max = 255)
    private String firstName;

    @Size(max = 255)
    private String lastName;

    @NotNull
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "ACTIVE")
    private boolean isActive;

    @Past
    @Column(name = "DATE_ADDED")
    @Temporal(TemporalType.TIMESTAMP)
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
     * @param username      nic-name of the User
     * @param password      user' password
     * @param firstName     user' first name
     * @param lastName      user' surname
     * @param email         user' email address
     * @param isActive      the status of the User. Must be active(true) or inactive(false)
     * @param dateAdded     date and time of adding the User in the database
     */
    public User(List<UserGroup> userGroups, String username, String password, String firstName,
                String lastName, String email, boolean isActive, Calendar dateAdded) {
        this.userGroups = userGroups;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.isActive = isActive;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Calendar getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Calendar dateAdded) {
        this.dateAdded = dateAdded;
    }
}
