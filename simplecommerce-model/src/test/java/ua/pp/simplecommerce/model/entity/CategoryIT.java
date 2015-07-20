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

import org.junit.Test;
import ua.pp.simplecommerce.model.util.ObjectFactory;

import javax.validation.ConstraintViolationException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Integration test for Category entity
 *
 * Created by Vladimir Kamenskiy on 05.07.2015.
 */
public class CategoryIT extends AbstractPersistentTest {

    @Test
    public void shouldCreateNewCategory() {
        Category category = ObjectFactory.getDefaultCategory();
        category.setLanguage(em.find(Language.class, START_ID));
        tx.begin();
        em.persist(category);
        tx.commit();

        assertNotNull("Category id can't be null", category.getCategoryId());
    }

    @Test
    public void shouldFindCategoryByDistinctProduct() {
        Set<Product> productSet1 = new HashSet<>();
        Set<Product> productSet2 = new HashSet<>();
        Language language = em.find(Language.class, START_ID);
        Category category1 = ObjectFactory.getDefaultCategory();
        Category category2 = ObjectFactory.getDefaultCategory();
        category1.setProducts(productSet1).setName("Category 1").setLanguage(language);
        category2.setProducts(productSet2).setName("Category 2").setLanguage(language);
        for(int i = 1; i <= 3; i++){
            Product product1 = ObjectFactory.getDefaultProduct();
            Product product2 = ObjectFactory.getDefaultProduct();
            product1.setName("Product #" + i + " in Category #1").setLanguage(language)
                    .setCategories(ObjectFactory.getCategorySet(category1));
            product2.setName("Product #" + i + " in Category #2").setLanguage(language)
                    .setCategories(ObjectFactory.getCategorySet(category2));
            productSet1.add(product1);
            productSet2.add(product2);
        }
        tx.begin();
        em.persist(category1);
        em.persist(category2);
        tx.commit();
        List<Category> categories = em.createNamedQuery(Category.FIND_BY_PRODUCT, Category.class)
                .setParameter("name", "Product #3 in Category #2")
                .getResultList();
        assertEquals(1, categories.size());
        assertEquals("Category 2", categories.get(0).getName());
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldRaiseConstraintViolationCauseNullName() {
        Category category = ObjectFactory.getDefaultCategory().setLanguage(em.find(Language.class, START_ID));
        em.persist(category.setName(null));
    }
}
