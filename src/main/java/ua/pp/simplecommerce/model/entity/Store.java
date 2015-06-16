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
import javax.validation.constraints.*;

/**
 * Entity 'Store' contains global properties of the store like Name, Email, Description, etc.
 *
 * Created by Vladimir Kamenskiy on 09.02.2015.
 */

@Entity
public class Store {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STORE_ID")
    private Long storeId;

    @NotNull @Size(max = 45)
    private String name;

    @NotNull @Size(max = 45)
    private String url;

    @NotNull @Size(max = 255)
    private String description;

    @NotNull @Size(max = 2000)
    private String about;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "STORE_FK")
    private Address address;

    @NotNull @Size(min = 16, max = 16)
    private String phone1;

    @Size(min = 16, max = 16)
    private String phone2;

    @Size(min = 16, max = 16)
    private String fax;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LANGUAGE_STORE_FK")
    private Language languageId;

    /**
     * For JPA uses only
     */
    public Store() {
    }

    /**
     * Creates the Store instance with required fields
     *
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
    public Store(String name, String url, String description, String about, Address address,
                 String phone1, String phone2, String fax, Language languageId) {
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

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Language getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Language languageId) {
        this.languageId = languageId;
    }
}
