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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity ''UserGroup' has info about users' groups, such as group name and permission
 *
 * Created by Vladimir Kamenskiy on 20.03.2015.
 */

@Entity
@Table(name = "USER_GROUP")
public class UserGroup {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GROUP_ID")
    private Long groupId;

    @NotNull @Size(max = 45)
    @Column(unique = true)
    private String name;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @ManyToMany(mappedBy = "userGroups")
    private List<User> users = new ArrayList<>();

    /**
     * For JPA uses only
     */
    public UserGroup() {
    }

    /**
     * Creates the UserGroup instance with required fields
     *
     * @param name      name of the UserGroup
     * @param role      the UserGroup role for access, such as Administrator, Demo, Employee, Employer
     */
    public UserGroup(String name, Role role) {
        this.name = name;
        this.role = role;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", groupId)
                .append("name", name)
                .append("role", role)
                .append("users", users)
                .toString();
    }
}
