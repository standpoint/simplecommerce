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
import java.util.Date;

/**
 * Entity 'User' obtains info about users (or staff) of the store's back-end
 *
 * Created by Vladimir Kamenskiy on 20.03.2015.
 */

@Entity
public class User {

    private Long userId;
    private UserGroup userGroup;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private boolean isActive;
    private Date dateAdded;

}
