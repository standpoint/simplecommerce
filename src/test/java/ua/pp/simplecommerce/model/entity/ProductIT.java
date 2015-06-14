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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.pp.simplecommerce.model.entity.enumerations.StockStatus;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * The integration test for the entity {@link ua.pp.simplecommerce.model.entity.Product}
 *
 * Created by volodya on 14.06.2015.
 */
public class ProductIT {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ecommerceTestPU");
    private EntityManager em;
    private EntityTransaction tx;

    @Before
    public void initEntityManager() throws Exception {
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }
    @After
    public void closeEntityManager() throws Exception {
        if (em != null) {
            em.close();
        }
    }
    @Test
    public void shouldFindTheProduct() throws Exception {
        Product product = em.find(Product.class, 1L);
        assertEquals("The Product",product.getName());
    }
    @Test
    public void shouldCreateNewProduct() throws Exception {
        List<Category> categories = new ArrayList<>();
        categories.add(em.find(Category.class, 1L));
        Manufacturer manufacturer = em.find(Manufacturer.class, 1L);
        Language language = em.find(Language.class, 1L);
        Image image = em.find(Image.class, 1L);
        List<Image> images = new ArrayList<>();
        images.add(image);
        Product product = new Product(categories, "New Product", "PN:0002", "a new product description", 1000,
                new BigDecimal(0.99), StockStatus.IN_STOCK, manufacturer, language, images);
        tx.begin();
        em.persist(product);
        tx.commit();
        assertNotNull("The Product ID can't be Null", product.getProductId());
        product = em.createNamedQuery("getNewProduct", Product.class).getSingleResult();
        assertEquals("PN:0002",product.getPartnumber());
    }
    @Test(expected = ConstraintViolationException.class)
    public void shouldRaiseConstraintViolationCauseNullName() {
        List<Category> categories = new ArrayList<>();
        categories.add(em.find(Category.class, 1L));
        Manufacturer manufacturer = em.find(Manufacturer.class, 1L);
        Language language = em.find(Language.class, 1L);
        Image image = em.find(Image.class, 1L);
        List<Image> images = new ArrayList<>();
        images.add(image);
        Product product = new Product(categories, null, "", "empty product name!", 0,
                new BigDecimal(0.01), StockStatus.OUT_OF_STOCK, manufacturer, language, images);
        em.persist(product);
    }
}
