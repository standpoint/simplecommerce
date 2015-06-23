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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Entity 'Category' contains info about categories of products
 *
 * Created by Vladimir Kamenskiy on 18.03.2015.
 */

@Entity
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    private Long categoryId;

    @NotNull @Size(max = 255)
    private String name;

    @NotNull @Size(max = 2000)
    private String description;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LANGUAGE_FK")
    private Language languageId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "IMAGE_FK")
    private Image imageId;

    @ManyToMany
    @JoinTable(name = "JND_PRODUCT_CATEGORY",
            joinColumns = @JoinColumn(name = "CATEGORY_FK"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_FK"))
    private Set<Product> products = new HashSet<>();

    /**
     * For JPA uses only
     */
    public Category(){}

    /**
     * Creates the Category instance with required fields
     *
     * @param name          name of the product category
     * @param description   description
     * @param language      language reference
     * @param image         image reference
     * @param products      list of the products in this category
     */
    public Category(String name, String description, Language language,
                    Image image, Set<Product> products){
        this.name = name;
        this.description = description;
        this.languageId = language;
        this.imageId = image;
        this.products = products;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Language getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Language languageId) {
        this.languageId = languageId;
    }

    public Image getImageId() {
        return imageId;
    }

    public void setImageId(Image imageId) {
        this.imageId = imageId;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Category) && o != null && name != null && ((Category) o).name.equals(name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
