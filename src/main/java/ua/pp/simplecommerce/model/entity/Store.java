/**
 * Copyright (C) 2015  SimpleCommerce.pp.ua
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

/**
 * Entity 'Store' contains global properties of the store like Name, Email, Description, etc.
 *
 * Created by Vladimir Kamenskiy on 09.02.2015.
 */

@Entity
public class Store {

    private Long storeId;
    private String name;
    private String url;
    private String description;
    private String about;
    private Address address;
    private String phone1;
    private String phone2;
    private String fax;
    private Language languageId;

    /**
     * For JPA uses only
     */
    public Store() {
    }

    /**
     * Creates the Store instance with required fields
     *
     * @param storeId       unique id-number of the Store
     * @param name          name of the Store
     * @param url           url-link to the Store in the network
     * @param description   short description (means a definition) of the Store (up to 255 characters)
     * @param about         comprehensive description of the Store (up to 2000 characters)
     * @param address       complete address of the Store
     * @param phone1        the first phone number of the Store
     * @param phone2        the second phone number of the Store
     * @param fax           fax number of the Store
     * @param languageId    id-number of the Store language
     */
    public Store(Long storeId, String name, String url, String description, String about, Address address,
                 String phone1, String phone2, String fax, Language languageId) {
        this.storeId = storeId;
        this.name = name;
        this.url = url;
        this.description = description;
        this.about = about;
        this.address = address;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.fax = fax;
        this.languageId = languageId;
    }

    @Id @GeneratedValue
    @Column(name = "STORE_ID")
    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    @Column(nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false, length = 45)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(nullable = false, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(nullable = false, length = 2000)
    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "STORE_FK", nullable = false)
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Column(nullable = false, length = 16)
    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    @Column(nullable = false, length = 16)
    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    @Column(nullable = false, length = 16)
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LANGUAGE_STORE_FK", nullable = false)
    public Language getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Language languageId) {
        this.languageId = languageId;
    }
}
