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
 * Entity 'Language' contains attributes of the language, such as language name, code, locale, path, etc
 *
 * Created by Vladimir Kamenskiy on 11.02.2015.
 */

@Entity
public class Language {

    private Long languageId;
    private String name;
    private String code;
    private String locale;
    private Image image;
    private String directory;
    private String filename;
    private boolean status;

    @Id @GeneratedValue
    @Column(name = "LANGUAGE_ID")
    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    @NotNull @Size(max = 20)
    @Column(nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull @Pattern(regexp = "[\\d]{3}")
    @Column(nullable = false, length = 2)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @NotNull @Pattern(regexp = "[a-z]{2}_[A-Z]{2}")
    @Column(nullable = false, length = 5)
    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LANGUAGE_IMAGE_FK")
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @NotNull @Size(max = 255)
    @Column(nullable = false)
    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    @NotNull @Size(max = 255)
    @Column(nullable = false)
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
