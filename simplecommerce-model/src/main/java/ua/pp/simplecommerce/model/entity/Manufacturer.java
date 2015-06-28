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
import javax.validation.constraints.*;

/**
 * Entity 'Manufacturer' has info about vendors of the products
 *
 * Created by Vladimir Kamenskiy on 18.03.2015.
 */

@Entity
public class Manufacturer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MANUFACTURER_ID")
    private Long manufacturerId;

    @NotNull @Size(max = 255)
    private String name;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "IMAGE_FK")
    private Image imageId;

    /**
     * For JPA uses only
     */
    public Manufacturer(){}

    /**
     * Creates the Manufacturer instance with required fields
     *
     * @param name  manufacturer name
     * @param image manufacturer image reference
     */
    public Manufacturer(String name, Image image){
        this.name = name;
        this.imageId = image;
    }

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getImageId() {
        return imageId;
    }

    public void setImageId(Image imageId) {
        this.imageId = imageId;
    }
}
