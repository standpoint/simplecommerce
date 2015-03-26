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
import javax.validation.constraints.NotNull;

/**
 * Entity 'Address' contains address info. Foreign keys (country_fk,  city_fk and store_fk) are defined
 * at the same entities: Country, City, Store
 *
 * Created by Vladimir Kamenskiy on 16.03.2015.
 */
@Entity
public class Address {

    private Long addressId;
    private String address;
    private int postcode;

    @Id @GeneratedValue
    @Column(name = "ADDRESS_ID")
    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    @Column(nullable = false, length = 255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(nullable = false)
    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }
}
