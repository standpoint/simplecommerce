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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Integration test for {@link ua.pp.simplecommerce.model.entity.CustomerTransaction} entity
 *
 * Created by Vladimir Kamenskiy on 20.07.2015.
 */
public class CustomerTransactionIT extends AbstractPersistentTest {

    @Test
    public void shouldCreateCustomerTransaction() {
        Product product = createAndPersistProduct();
        Customer customer = createAndPersistCustomer(getUniqueLogin());
        Order order = createAndPersistOrder(product);
        CustomerTransaction transaction = ObjectFactory.getCustomerTransaction()
                .setCustomer(customer)
                .setOrder(order);
        tx.begin();
        em.persist(transaction);
        tx.commit();

        assertNotNull("CustomerTransaction id can't be null!", transaction.getCustomerTransactionId());
    }

    @Test
    public void shouldFindTransactionsByCustomerEmail() {
        String login = getUniqueLogin().substring(6);
        String email = login.concat("@mail.com");
        CustomerTransaction existTransaction = em.find(CustomerTransaction.class, START_ID);
        existTransaction.getCustomer()
                .getCustomerDetails()
                .setLogin(login)
                .setEmail(email);
        tx.begin();
        em.persist(existTransaction);
        tx.commit();
        CustomerTransaction transaction = em.createNamedQuery(CustomerTransaction.FIND_BY_CUSTOMER_EMAIL, CustomerTransaction.class)
                .setParameter("email", email)
                .getResultList().get(0);
        assertEquals(existTransaction, transaction);
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldRaiseConstraintViolationExceptionCauseNegativeAmount() {
        CustomerTransaction transaction = ObjectFactory.getCustomerTransaction()
                .setOrder(createAndPersistOrder(createAndPersistProduct()))
                .setCustomer(createAndPersistCustomer(getUniqueLogin()))
                .setAmount(BigDecimal.valueOf(-1,2));
        em.persist(transaction);
    }

    private Order createAndPersistOrder(Product product) {
        OrderLine orderLine = ObjectFactory.getDefaultOrderLine()
                .setProduct(product);
        List<OrderLine> orderLines = new ArrayList<>();
        orderLines.add(orderLine);
        Order order = ObjectFactory.getDefaultOrder().setOrderLines(orderLines);
        tx.begin();
        em.persist(order);
        tx.commit();
        return order;
    }

    private Customer createAndPersistCustomer(String uniqueLogin) {
        UserDetails customerDetails = ObjectFactory.getDefaultUserDetails()
                .setLogin(uniqueLogin);
        Customer customer = ObjectFactory.getDefaultCustomer()
                .setCustomerDetails(customerDetails);
        tx.begin();
        em.persist(customer);
        tx.commit();
        return customer;
    }

    private Product createAndPersistProduct() {
        Set<Category> categories = new HashSet<>();
        categories.add(em.find(Category.class, START_ID));
        Language language = em.find(Language.class, START_ID);
        Product product = new Product.Builder(categories, "New product", language).build();
        tx.begin();
        em.persist(product);
        tx.commit();
        return product;
    }
}
