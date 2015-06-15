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
import java.util.List;

/**
 * Entity 'Country' contains names of the countries
 *
 * Created by Vladimir Kamenskiy on 16.03.2015.
 */
@Entity
public class Country {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COUNTRY_ID")
    private Long countryId;

    @NotNull @Size(max = 45)
    @Column(nullable = false, length = 45)
    private String country;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "COUNTRY_FK")
    private List<Address> addresses;

    /**
     * For JPA uses only
     */
    public Country(){}

    /**
     * Creates the Country instance with required fields
     *
     * @param country   name of the country
     * @param addresses list of the existing addresses in this country
     */
    public Country(String country, List<Address> addresses){
        this.country = country;
        this.addresses = addresses;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
