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

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Entity 'Country' contains names of the countries
 *
 * Created by Vladimir Kamenskiy on 16.03.2015.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = Country.FIND_ALL, query = "SELECT c FROM Country c"),
        @NamedQuery(name = Country.FIND_BY_NAME, query = "SELECT c FROM Country c WHERE c.country LIKE :name")
})
public class Country implements Comparable<Country> {

    public static final String FIND_ALL = "findAllCountries";
    public static final String FIND_BY_NAME = "findCountriesWithName";

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

    public Country setCountryId(Long countryId) {
        this.countryId = countryId;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Country setCountry(String country) {
        this.country = country;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || country == null || !(o instanceof Country)) {
            return false;
        }
        Country c = (Country) o;
        return country.equalsIgnoreCase(c.country);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(country);
    }

    @Override
    public String toString() {
        return country;
    }

    @Override
    public int compareTo(Country c) {
        return country.compareToIgnoreCase(c.country);
    }
}
