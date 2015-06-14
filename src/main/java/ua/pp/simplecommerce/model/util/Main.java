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

import ua.pp.simplecommerce.model.entity.*;
import ua.pp.simplecommerce.model.entity.enumerations.StockStatus;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

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
        Image image = new Image(new byte[]{1});
        Manufacturer manufacturer = new Manufacturer("The Manufacturer", image);
        Language language = new Language("English","045","en_EN",image,true);
        Category category = new Category("The Category", "category description", language, image,
                new ArrayList<Product>());
        List<Category> categories = new ArrayList<>();
        categories.add(category);

        Product product = new Product(categories, "X", "pn:00001", "the product 'X'", 5, new BigDecimal(1.00),
                StockStatus.IN_STOCK, manufacturer, language, new ArrayList<Image>());

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ecommercePU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr = em.getTransaction();

        try {
            tr.begin();
            em.persist(address);
            em.persist(city);
            em.persist(country);
            em.persist(image);
            em.persist(manufacturer);
            em.persist(category);
            tr.commit();

            tr.begin();
            em.persist(language);
            tr.commit();

            tr.begin();
            em.persist(product);
            tr.commit();
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
            for (ConstraintViolation<?> constraintViolation : constraintViolations){
                System.out.println(constraintViolation.getRootBeanClass().getName()+"."+constraintViolation.getPropertyPath() + " " +constraintViolation.getMessage());
            }
            System.out.println(Arrays.toString(e.getStackTrace()));
            tr.rollback();
        } finally {
            em.close();
        }

    }
}
