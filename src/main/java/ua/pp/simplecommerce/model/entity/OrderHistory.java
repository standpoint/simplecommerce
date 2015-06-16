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
import java.util.Calendar;

/**
 * The entity 'OrderHistory' has info about event in the order' life-cycle
 *
 * Created by Vladimir Kamenskiy on 23.03.2015.
 */

@Entity
@Table(name = "ORDER_HISTORY")
public class OrderHistory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_HISTORY_ID")
    private Long orderHistoryId;

    @Size(max = 2000)
    private String content;

    @Past @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "DATE_ADDED")
    private Calendar dataAdded;

    /**
     * For JPA uses only
     */
    public OrderHistory() {
    }

    /**
     * Creates the OrderHistory instance with required fields
     *
     * @param content   content of message for order history update
     * @param dataAdded date and time of order history update
     */
    public OrderHistory(String content, Calendar dataAdded) {
        this.content = content;
        this.dataAdded = dataAdded;
    }

    public Long getOrderHistoryId() {
        return orderHistoryId;
    }

    public void setOrderHistoryId(Long orderHistoryId) {
        this.orderHistoryId = orderHistoryId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Calendar getDataAdded() {
        return dataAdded;
    }

    public void setDataAdded(Calendar dataAdded) {
        this.dataAdded = dataAdded;
    }
}
