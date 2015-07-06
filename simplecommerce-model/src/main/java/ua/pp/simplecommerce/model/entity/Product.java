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

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import ua.pp.simplecommerce.model.util.ObjectFactory;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Entity 'Product' contains info about the store products, like partnumber, description, qty, price, etc.
 *
 * Created by Vladimir Kamenskiy on 18.03.2015.
 */

@Entity
@NamedQueries({
        @NamedQuery(name = Product.FIND_ALL, query = "SELECT p FROM Product p"),
        @NamedQuery(name = Product.GET_BY_NAME, query = "SELECT p FROM Product p WHERE p.name = :name"),
        @NamedQuery(name = Product.GET_BY_PARTNUMBER, query = "SELECT p FROM Product p WHERE p.partnumber = :partnumber")
})
public class Product {

    public static final String FIND_ALL = "findAllProducts";
    public static final String GET_BY_NAME = "getProductByName";
    public static final String GET_BY_PARTNUMBER = "getProductByPartnumber";

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Size(min = 1)
    @ManyToMany(mappedBy = "products")
    private Set<Category> categories = new HashSet<>();

    @NotNull @Size(max = 255)
    private String name;

    @NotNull @Size(max = 255)
    private String partnumber;

    @NotNull @Size(max = 2000)
    private String description;

    @Min(0)
    private int quantity;

    @NotNull
    @DecimalMin(value = "0.00") @Digits(integer = 12, fraction = 2)
    private BigDecimal price;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "STOCK_STATUS")
    private StockStatus stockStatus;

    @OneToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "MANUFACTURER_FK")
    private Manufacturer manufacturer;

    @OneToOne(optional = false)
    @JoinColumn(name = "LANGUAGE_FK")
    private Language language;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "PRODUCT_FK")
    private List<Image> images;

    /**
     * An implementation of Product' builder.
     * There are a significant number of the optional parameters, so we won't use both of telescoping constructors
     * and getters/setters to instantiate the Product entity.
     */
    public static class Builder {
        private Set<Category> categories;
        private String name;
        private Language language;
        private String partnumber = ObjectFactory.DEFAULT_PARTNUMBER;
        private String description = ObjectFactory.DEFAULT_PRODUCT_DESCRIPTION;
        private int quantity = 0;
        private BigDecimal price = ObjectFactory.DEFAULT_PRICE;
        private StockStatus stockStatus = StockStatus.OUT_OF_STOCK;
        private Manufacturer manufacturer = ObjectFactory.getDefaultManufacturer();
        private List<Image> images = ObjectFactory.getImageList(ObjectFactory.DefaultImages.PRODUCT.getImage());

        /**
         * @param categories    list of the categories contains this product
         * @param name          name of the product
         * @param language      language reference
         */
        public Builder(@NotNull(message = "{validation.not_null}") Set<Category> categories,
                       @NotNull String name, @NotNull Language language) {
            this.categories = categories;
            this.name = name;
            this.language = language;
        }

        public Builder partnumber(String value) {
            partnumber = value;
            return  this;
        }
        public Builder description(String value) {
            description = value;
            return this;
        }
        public Builder quantity(int value) {
            quantity = value;
            return this;
        }
        public Builder price(BigDecimal value) {
            price = value;
            return this;
        }
        public Builder stockStatus(StockStatus value) {
            stockStatus = value;
            return this;
        }
        public Builder manufacturer(Manufacturer value) {
            manufacturer = value;
            return this;
        }
        public Builder images(List<Image> value) {
            images = value;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }


    /**
     * Used by Builder to get instance of the Product entity
     *
     * @param builder   the Builder object
     */
    private Product(Builder builder) {
        this.categories = builder.categories;
        this.name = builder.name;
        this.partnumber = builder.partnumber;
        this.description = builder.description;
        this.quantity = builder.quantity;
        this.price = builder.price;
        this.stockStatus = builder.stockStatus;
        this.manufacturer = builder.manufacturer;
        this.language = builder.language;
        this.images = builder.images;
    }

    /**
     * For JPA uses only
     */
    protected Product() {}

    public Long getProductId() {
        return productId;
    }

    public Product setProductId(Long productId) {
        this.productId = productId;
        return this;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public Product setCategories(Set<Category> categories) {
        this.categories = categories;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public String getPartnumber() {
        return partnumber;
    }

    public Product setPartnumber(String partnumber) {
        this.partnumber = partnumber;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Product setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public StockStatus getStockStatus() {
        return stockStatus;
    }

    public Product setStockStatus(StockStatus stockStatus) {
        this.stockStatus = stockStatus;
        return this;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public Product setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public Language getLanguage() {
        return language;
    }

    public Product setLanguage(Language language) {
        this.language = language;
        return this;
    }

    public List<Image> getImages() {
        return images;
    }

    public Product setImages(List<Image> images) {
        this.images = images;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        }
        if(!(o instanceof Product)) {
            return false;
        }
        Product product = (Product) o;
        return  Objects.equals(product.name, name)
                && Objects.equals(product.partnumber, partnumber)
                && Objects.equals(product.language, language);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 11)
                .append(name)
                .append(partnumber)
                .append(language)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", productId)
                .append("name", name)
                .append("part number", partnumber)
                .append("description", description)
                .append("quantity", quantity)
                .append("price", price)
                .append("stock status", stockStatus)
                .append("manufacurer", manufacturer.getName())
                .toString();
    }
}

