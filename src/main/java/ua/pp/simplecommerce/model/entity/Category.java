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
 * Entity 'Category' contains info about categories of products
 *
 * Created by Vladimir Kamenskiy on 18.03.2015.
 */

@Entity
public class Category {

    private Long categoryId;
    private String name;
    private String description;
    private Language languageId;
    private Image imageId;
    private List<Product> products;

    @Id @GeneratedValue
    @Column(name = "CATEGORY_ID")
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @NotNull
    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull @Size(max = 2000)
    @Column(nullable = false, length = 2000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CATEGORY_LANGUAGE_FK", nullable = false)
    public Language getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Language languageId) {
        this.languageId = languageId;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_IMAGE_FK")
    public Image getImageId() {
        return imageId;
    }

    public void setImageId(Image imageId) {
        this.imageId = imageId;
    }

    @ManyToMany
    @JoinTable(name = "JND_PRODUCT_CATEGORY",
            joinColumns = @JoinColumn(name = "CATEGORY_FK"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_FK"))
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
