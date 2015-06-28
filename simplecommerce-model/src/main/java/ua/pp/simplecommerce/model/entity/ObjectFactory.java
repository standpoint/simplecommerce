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

import java.math.BigDecimal;
import java.util.*;

/**
 * The factory of the entities objects.
 * Uses for the tests as lightweight way to create entity with default parameters.
 *
 * Created by Vladimir Kamenskiy on 22.06.2015.
 */
public final class ObjectFactory {

    public static final int DEFAULT_QUANTITY = 100;
    public static final BigDecimal DEFAULT_PRICE = BigDecimal.valueOf(1, 2);
    public static final BigDecimal DEFAULT_AMOUNT = BigDecimal.valueOf(100, 2);
    public static final String DEFAULT_PHONE_NUMBER = "+380123456789";
    public static final String DEFAULT_INVOICE_NO = "INV-" + getDefaultDate().toString();
    public static final String DEFAULT_STORE_NAME = "SimpleCommerce";
    public static final String DEFAULT_STORE_URL = "http://simplecommerce.pp.ua";
    public static final String DEFAULT_STREET_ADDRESS = "I.Lepse av., 8";
    public static final String DEFAULT_POSTCODE = "03680";
    public static final String DEFAULT_PARTNUMBER = "PN:12345";
    public static final String DEFAULT_PRODUCT_DESCRIPTION = "This is a new product. There is no yet description.";
    public static final String DEFAULT_PRODUCT_NAME = "New Product";

    public enum DefaultImages{

        LANGUAGE(new Image("language.jpg")),
        CATEGORY(new Image("category.jpg")),
        PRODUCT(new Image("product.jpg")),
        MANUFACTURER(new Image("manufacturer.jpg"));

        private final Image image;

        private DefaultImages(final Image image){
            this.image = image;
        }

        public Image getImage(){
            return image;
        }
    }

    private ObjectFactory() {}

    public static Address getDefaultAddress() {
        return new Address(DEFAULT_STREET_ADDRESS, getDefaultCity(), getDefaultCountry(), DEFAULT_POSTCODE);
    }

    public static Country getDefaultCountry() {
        return new Country("Ukraine");
    }

    public static City getDefaultCity() {
        return new City("Kiev");
    }

    public static Category getDefaultCategory() {
        return new Category("Default Category", "This is a default category", getDefaultLanguage(),
                DefaultImages.CATEGORY.getImage(), getEmptyProductSet());
    }

    public static Product getDefaultProduct() {
        return new Product.Builder(getCategorySet(getDefaultCategory()), DEFAULT_PRODUCT_NAME, getDefaultLanguage())
                .build();
    }

    public static Manufacturer getDefaultManufacturer() {
        return new Manufacturer("Default Manufacturer", DefaultImages.MANUFACTURER.getImage());
    }

    public static Set<Product> getEmptyProductSet() {
        return new HashSet<>();
    }

    public static Set<Product> getProductList(Product ... products) {
        return new HashSet<>(Arrays.asList(products));
    }

    public static Set<Category> getEmptyCategorySet() {
        return new HashSet<>();
    }

    public static Set<Category> getCategorySet(Category... categories) {
        return new HashSet<>(Arrays.asList(categories));
    }

    public static List<Image> getImageList(Image ... images) {
        return new ArrayList<>(Arrays.asList(images));
    }

    public static Language getDefaultLanguage() {
        return new Language("English", "045", "en_EN", DefaultImages.LANGUAGE.getImage(), true);
    }

    public static Customer getDefaultCustomer() {
        return new Customer("customer", "customer", "Firstname", "Secondname", "customer@mail.com", DEFAULT_PHONE_NUMBER,
                true, getDefaultAddress(), getEmptyCustomerTransactionList(), getDefaultDate());
    }

    public static CustomerTransaction getCustomerTransaction() {
        return new CustomerTransaction(getDefaultOrder(), getDefaultOrder().getSummary(),
                DEFAULT_AMOUNT, getDefaultDate());
    }

    public static Order getDefaultOrder() {
        return new Order(getOrderLines(), DEFAULT_INVOICE_NO, getDefaultAddress(), getDefaultAddress(), ShippingMethod.POST, "A comment", DEFAULT_AMOUNT, OrderStatus.PENDING, getOrderHistoryList());
    }

    public static List<OrderHistory> getOrderHistoryList() {
        return new ArrayList<>(Arrays.asList(getDefaultOrderHistory()));
    }

    public static OrderHistory getDefaultOrderHistory() {
        return new OrderHistory("order history", getDefaultDate());
    }

    public static List<OrderLine> getOrderLines() {
        return new ArrayList<>(Arrays.asList(getDefaultOrderLine()));
    }

    public static OrderLine getDefaultOrderLine() {
        return new OrderLine(getDefaultProduct(), DEFAULT_QUANTITY, DEFAULT_AMOUNT);
    }

    public static Store getDefaultStore() {
        return new Store(DEFAULT_STORE_NAME, DEFAULT_STORE_URL, "a simple online store", "about info", getDefaultAddress(), DEFAULT_PHONE_NUMBER, DEFAULT_PHONE_NUMBER, DEFAULT_PHONE_NUMBER, getDefaultLanguage());
    }

    public static Calendar getDefaultDate() {
        Calendar dateTime = Calendar.getInstance();
        dateTime.set(2001, 1, 1, 0, 0, 0);
        return dateTime;
    }

    public static List<CustomerTransaction> getEmptyCustomerTransactionList() {
        return new ArrayList<>();
    }
}
