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
import java.math.BigDecimal;
import java.util.List;

/**
 * Entity 'Product' contains info about the store products, like partnumber, description, qty, price, etc.
 *
 * Created by Vladimir Kamenskiy on 18.03.2015.
 */

@Entity
public class Product {

    private Long productId;
    private List<Category> categories;
    private String name;
    private String partnumber;
    private String description;
    private int quantity;
    private BigDecimal price;
    private StockStatus stockStatus;
    private Manufacturer manufacturerId;
    private Language languageId;
    private List<Image> images;

    @Id @GeneratedValue
    @Column(name = "PRODUCT_ID")
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @ManyToMany(mappedBy = "products")
    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @NotNull @Size(max = 255)
    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull @Size(max = 255)
    @Column(nullable = false)
    public String getPartnumber() {
        return partnumber;
    }

    public void setPartnumber(String partnumber) {
        this.partnumber = partnumber;
    }

    @NotNull @Size(max = 2000)
    @Column(nullable = false, length = 2000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Min(0)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @NotNull
    @DecimalMin(value = "0.00") @Digits(integer = 12, fraction = 2)
    @Column(nullable = false, scale = 2)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Enumerated(value = EnumType.STRING)
    @Column(name = "STOCK_STATUS")
    public StockStatus getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(StockStatus stockStatus) {
        this.stockStatus = stockStatus;
    }

    @OneToOne(optional = false)
    @JoinColumn(name = "PRODUCT_MANUFACTURER_FK")
    public Manufacturer getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Manufacturer manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    @OneToOne(optional = false)
    @JoinColumn(name = "PRODUCT_LANGUAGE_FK")
    public Language getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Language languageId) {
        this.languageId = languageId;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_IMAGE_FK")
    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}

