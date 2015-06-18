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
import javax.validation.constraints.Size;

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
    private String country;

    /**
     * For JPA uses only
     */
    public Country(){}

    /**
     * Creates the Country instance with required fields
     *
     * @param country   name of the country
     */
    public Country(String country){
        this.country = country;
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

}
