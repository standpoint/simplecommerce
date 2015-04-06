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

package ua.pp.simplecommerce.model.util;

import ua.pp.simplecommerce.model.entity.Address;
import ua.pp.simplecommerce.model.entity.City;
import ua.pp.simplecommerce.model.entity.Country;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir Kamenskiy on 25.03.2015.
 */
public class Main {
    public static void main(String[] args) {

        Persistence.generateSchema("ecommercePU", null);

        Address address = new Address("I.Lepse av., 8", "03680");
        List<Address> addresses = new ArrayList<>();
        addresses.add(address);
        City city = new City("Kiev", addresses);
        Country country = new Country("Ukraine", city.getAddresses());

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ecommercePU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        em.persist(address);
        em.persist(city);
        em.persist(country);
        tr.commit();
        em.close();
    }
}
