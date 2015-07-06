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
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entity 'Category' contains info about categories of products
 *
 * Created by Vladimir Kamenskiy on 18.03.2015.
 */

@Entity
@NamedQueries({
        @NamedQuery(name = Category.FIND_ALL, query = "SELECT c FROM Category c"),
        @NamedQuery(name = Category.FIND_BY_NAME, query = "SELECT c FROM Category c WHERE c.name = :name"),
        @NamedQuery(name = Category.FIND_BY_PRODUCT, query = "SELECT c FROM Category c INNER JOIN c.products p WHERE p.name = :name")
})
public class Category {

    public static final String FIND_ALL = "findAllCategories";
    public static final String FIND_BY_NAME = "findCategoryByName";
    public static final String FIND_BY_PRODUCT = "findCategoriesWithProduct";

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    private Long categoryId;

    @NotNull @Size(max = 255)
    private String name;

    @NotNull @Size(max = 2000)
    private String description;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LANGUAGE_FK")
    private Language language;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "IMAGE_FK")
    private Image image;

    @ManyToMany(cascade = CascadeType.PERSIST)
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
    public Category(String name, String description, Language language, Image image, Set<Product> products){
        this.name = name;
        this.description = description;
        this.language = language;
        this.image = image;
        this.products = products;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Category setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Category setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Category setDescription(String description) {
        this.description = description;
        return this;
    }

    public Language getLanguage() {
        return language;
    }

    public Category setLanguage(Language language) {
        this.language = language;
        return this;
    }

    public Image getImage() {
        return image;
    }

    public Category setImage(Image image) {
        this.image = image;
        return this;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public Category setProducts(Set<Product> products) {
        this.products = products;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Category)) {
            return false;
        }
        Category category = (Category)o;
        return Objects.equals(category.name, name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return "The category: " + name + "(more info: " + description + ")";
    }
}
