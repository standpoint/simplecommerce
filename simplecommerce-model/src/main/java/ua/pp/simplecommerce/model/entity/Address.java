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

/**
 * Entity 'Address' contains address info. Foreign keys (country_fk,  city_fk and store_fk) are defined
 * at the same entities: Country, City, Store
 *
 * Created by Vladimir Kamenskiy on 16.03.2015.
 */
@Entity
public class Address {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID")
    private Long addressId;

    @NotNull @Size(max = 255)
    private String address;

    @ManyToOne(optional = false)
    @JoinColumn(name = "CITY_FK", referencedColumnName = "CITY_ID")
    private City city;

    @ManyToOne(optional = false)
    @JoinColumn(name = "COUNTRY_FK", referencedColumnName = "COUNTRY_ID")
    private Country country;

    @NotNull @Pattern(regexp = "[\\d]{5}")
    private String postcode;

    /**
     * For JPA uses only
     */
    public Address(){}

    /**
     * Creates the Address instance with required fields
     * @param address   street address (e.g. "I.Lepse av., 8")
     * @param city      the city name
     * @param country   the country name
     * @param postcode  zip/post/-code (must has 5 digits, e.g. "31680")
     */
    public Address(String address, City city, Country country, String postcode){
        this.address = address;
        this.city = city;
        this.country = country;
        this.postcode = postcode;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
