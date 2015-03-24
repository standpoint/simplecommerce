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
import java.util.Calendar;

/**
 * The entity 'OrderHistory' has info about event in the order' life-cycle
 *
 * Created by Vladimir Kamenskiy on 23.03.2015.
 */

@Entity
@Table(name = "order_history")
public class OrderHistory {

    private Long orderHistoryId;
    private String content;
    private Calendar dataAdded;

    public OrderHistory() {
    }

    public OrderHistory(Long orderHistoryId, String content, Calendar dataAdded) {
        this.orderHistoryId = orderHistoryId;
        this.content = content;
        this.dataAdded = dataAdded;
    }

    @Id @GeneratedValue
    @Column(name = "order_history_id")
    public Long getOrderHistoryId() {
        return orderHistoryId;
    }

    public void setOrderHistoryId(Long orderHistoryId) {
        this.orderHistoryId = orderHistoryId;
    }

    @Column(length = 2000)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "date_added", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    public Calendar getDataAdded() {
        return dataAdded;
    }

    public void setDataAdded(Calendar dataAdded) {
        this.dataAdded = dataAdded;
    }
}
