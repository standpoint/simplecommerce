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
 * Entity 'City' contains names of the cities
 *
 * Created by Vladimir Kamenskiy on 16.03.2015.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = City.FIND_ALL, query = "SELECT c FROM City c"),
        @NamedQuery(name = City.FIND_BY_NAME, query = "SELECT c FROM City c WHERE c.city LIKE :name")
})
public class City implements Comparable<City> {

    public static final String FIND_ALL = "findAllCities";
    public static final String FIND_BY_NAME = "findCitiesWithName";

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CITY_ID")
    private Long cityId;

    @NotNull @Size(max = 45)
    private String city;

    /**
     * For JPA uses only
     */
    public City(){}

    /**
     * Creates the City instance with required fields
     *
     * @param city      name of the city
     */
    public City(String city){
        this.city = city;
    }

    public Long getCityId() {
        return cityId;
    }

    public City setCityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    public String getCity() {
        return city;
    }

    public City setCity(String city) {
        this.city = city;
        return this;
    }

    @Override
    public int compareTo(City c) {
        return city.compareToIgnoreCase(c.city);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || city == null || !(o instanceof City)) {
            return false;
        }
        City c = (City) o;
        return city.equalsIgnoreCase(c.city);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(city);
    }

    @Override
    public String toString(){
        return city;
    }
}
