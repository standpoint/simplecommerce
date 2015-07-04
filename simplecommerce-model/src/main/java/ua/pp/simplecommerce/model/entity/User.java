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
import java.util.ArrayList;
import java.util.List;

/**
 * Entity 'User' obtains info about users of the store's back-end
 * (the same as a store staff or operator)
 *
 * Created by Vladimir Kamenskiy on 20.03.2015.
 */

@Entity
@Table(name = "USERS")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_DETAILS_FK")
    private UserDetails userDetails;

    @ManyToMany
    @JoinTable(name = "JND_USER_GROUP_USER",
            joinColumns = @JoinColumn(name = "USER_FK"),
            inverseJoinColumns = @JoinColumn(name = "USER_GROUP_FK"))
    private List<UserGroup> userGroups = new ArrayList<>();

    /**
     * For JPA uses only
     */
    public User() {
    }

    /**
     * Creates the User instance with required fields
     *
     * @param userDetails    Detail information about the user
     * @param userGroups    User Group
     */
    public User(UserDetails userDetails, List<UserGroup> userGroups) {
        this.userDetails = userDetails;
        this.userGroups = userGroups;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public List<UserGroup> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(List<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).reflectionToString(this);
    }
}
