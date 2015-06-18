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

import ua.pp.simplecommerce.model.entity.enumerations.StockStatus;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Entity 'Product' contains info about the store products, like partnumber, description, qty, price, etc.
 *
 * Created by Vladimir Kamenskiy on 18.03.2015.
 */

@Entity
@NamedQueries({
        @NamedQuery(name = "findAllProducts", query = "SELECT p FROM Product p"),
        @NamedQuery(name = "getNewProduct", query = "SELECT p FROM Product p WHERE p.name = 'New Product'")
})
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long productId;

    @ManyToMany(mappedBy = "products")
    private List<Category> categories;

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
    private Manufacturer manufacturerId;

    @OneToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "LANGUAGE_FK")
    private Language languageId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "PRODUCT_FK")
    private List<Image> images;

    /**
     * For JPA uses only
     */
    public Product(){}

    /**
     * @param categories    list of the categories contains this product
     * @param name          name of the product
     * @param partNumber    product part number
     * @param description   product description
     * @param quantity      quantity at the store warehouse
     * @param price         price
     * @param stockStatus   status of stock
     * @param manufacturer  manufacturer reference
     * @param language      language reference
     * @param images        list of the item images
     */
    public Product(List<Category> categories, String name, String partNumber, String description, int quantity,
                   BigDecimal price, StockStatus stockStatus, Manufacturer manufacturer, Language language,
                   List<Image> images) {
        this.categories = categories;
        this.name = name;
        this.partnumber = partNumber;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.stockStatus = stockStatus;
        this.manufacturerId = manufacturer;
        this.languageId = language;
        this.images = images;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPartnumber() {
        return partnumber;
    }

    public void setPartnumber(String partnumber) {
        this.partnumber = partnumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public StockStatus getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(StockStatus stockStatus) {
        this.stockStatus = stockStatus;
    }

    public Manufacturer getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Manufacturer manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public Language getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Language languageId) {
        this.languageId = languageId;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}

