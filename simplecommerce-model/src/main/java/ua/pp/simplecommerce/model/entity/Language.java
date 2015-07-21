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

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Entity 'Language' contains attributes of the language, such as language name, code, locale, path, etc
 *
 * Created by Vladimir Kamenskiy on 11.02.2015.
 */

@Entity
@NamedQueries({
        @NamedQuery(name = Language.FIND_ALL, query = "SELECT l FROM Language l"),
        @NamedQuery(name = Language.FIND_BY_CODE, query = "SELECT l FROM Language l WHERE l.code = :code"),
        @NamedQuery(name = Language.FIND_BY_NAME, query = "SELECT l FROM Language l WHERE l.name = :name")
})
public class Language {

    public static final String FIND_ALL = "findAllLanguages";
    public static final String FIND_BY_CODE = "findLanguagesWithCode";
    public static final String FIND_BY_NAME = "findLanguagesWithName";

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LANGUAGE_ID")
    private Long languageId;

    @NotNull @Size(max = 20)
    private String name;

    @NotNull @Pattern(regexp = "[\\d]{3}")
    private String code;

    @NotNull @Pattern(regexp = "[a-z]{2}_[A-Z]{2}")
    private String locale;

    @OneToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "IMAGE_FK", referencedColumnName = "IMAGE_ID")
    private Image image;
    private boolean status;

    /**
     * For JPA uses only
     */
    public Language(){}

    /**
     * Creates the Language instance with required fields
     *
     * @param name      language name
     * @param code      language digital code
     * @param locale    locale like "en_EN"
     * @param image     language image reference
     * @param status    status of store language (true - enable, false - disable)
     */
    public Language(String name, String code, String locale, Image image, boolean status){
        this.name = name;
        this.code = code;
        this.locale = locale;
        this.image = image;
        this.status = status;
    }

    public Long getLanguageId() {
        return languageId;
    }

    public Language setLanguageId(Long languageId) {
        this.languageId = languageId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Language setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Language setCode(String code) {
        this.code = code;
        return this;
    }

    public String getLocale() {
        return locale;
    }

    public Language setLocale(String locale) {
        this.locale = locale;
        return this;
    }

    public Image getImage() {
        return image;
    }

    public Language setImage(Image image) {
        this.image = image;
        return this;
    }

    public boolean getStatus() {
        return status;
    }

    public Language setStatus(boolean status) {
        this.status = status;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        }
        if(!(o instanceof Language)) {
            return false;
        }
        Language language = (Language) o;
        return Objects.equals(language.code, code);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", languageId)
                .append("name", name)
                .append("code", code)
                .append("locale", locale)
                .append("image", image)
                .append("status", status)
                .toString();
    }
}
