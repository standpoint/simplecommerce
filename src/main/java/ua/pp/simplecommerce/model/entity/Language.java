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
import java.io.Serializable;

/**
 * Describes the entity named 'language'
 * Contains attributes of the store language, such as language name, code, locale, path, etc
 *
 * Created by Vladimir Kamenskiy on 11.02.2015.
 */

@Entity
@Table(name = "language")
public class Language implements Serializable {

    private static final long serialVersionUID = 7619931821923119320L;

    private Long language_id;
    private String name;
    private String code;
    private String locale;
    private byte[] image;
    private String directory;
    private String filename;
    private String status;


}
