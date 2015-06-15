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
 * Entity 'City' contains names of the cities
 *
 * Created by Vladimir Kamenskiy on 16.03.2015.
 */
@Entity
public class City {

    @Id @GeneratedValue
    @Column(name = "CITY_ID")
    private Long cityId;

    @NotNull @Size(max = 45)
    @Column(nullable = false, length = 45)
    private String city;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "CITY_FK")
    private List<Address> addresses;

    /**
     * For JPA uses only
     */
    public City(){}

    /**
     * Creates the City instance with required fields
     *
     * @param city      name of the city
     * @param addresses list of the existing addresses for this city
     */
    public City(String city, List<Address> addresses){
        this.city = city;
        this.addresses = addresses;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
