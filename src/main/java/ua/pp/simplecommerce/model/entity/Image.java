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
 * Entity 'Image' contains the pictures which are used in the store.
 * Foreign keys: language_image_fk, product_image_fk, manufacture_image_fk, category_image_fk
 *
 * Created by Vladimir Kamenskiy on 17.03.2015.
 */
@Entity
public class Image {

    @Id @GeneratedValue
    @Column(name = "IMAGE_ID")
    private Long imageId;

    @NotNull
    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition="BLOB NOT NULL")
    private byte[] content;

    /**
     * For JPA uses only
     */
    public Image(){}

    /**
     * Creates the Image instance with required fields
     *
     * @param content   image content
     */
    public Image(byte[] content){
        this.content = content;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
