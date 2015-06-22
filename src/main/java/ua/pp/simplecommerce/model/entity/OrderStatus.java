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

/**
 * An enumeration of the available order' statuses. Uses by the Order entity.
 *
 * Created by Vladimir Kamenskiy on 23.03.2015.
 */
public enum OrderStatus {
    CANCELED,
    CHARGE_BACK,
    COMPLETE,
    DENIED,
    EXPIRED,
    PENDING,
    PROCESSED,
    SHIPPED
}
