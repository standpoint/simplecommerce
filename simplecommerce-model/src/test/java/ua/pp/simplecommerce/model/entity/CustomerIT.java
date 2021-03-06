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

import java.sql.SQLIntegrityConstraintViolationException;

import static junit.framework.TestCase.assertEquals;

/**
 * Integration test for {@link ua.pp.simplecommerce.model.entity.Customer} entity
 *
 * Created by Vladimir Kamenskiy on 20.07.2015.
 */
public class CustomerIT extends AbstractPersistentTest {

    @Test
    public void shouldCreateCustomer() {
        UserDetails customerDetails = ObjectFactory.getDefaultUserDetails()
                .setLogin(getUniqueLogin());
        Customer customer = ObjectFactory.getDefaultCustomer()
                .setCustomerDetails(customerDetails);
        tx.begin();
        em.persist(customer);
        tx.commit();
    }

    @Test
    public void shouldFindCustomersByAnyName() {
        UserDetails firstCustomerDetails = ObjectFactory.getDefaultUserDetails()
                .setFirstName("firstCustomer first name")
                .setLastName("firstCustomer last name")
                .setLogin(getUniqueLogin());
        UserDetails secondCustomerDetails = ObjectFactory.getDefaultUserDetails()
                .setFirstName("secondCustomer first name")
                .setLastName("secondCustomer last name")
                .setLogin(getUniqueLogin());
        UserDetails thirdCustomerDetails = ObjectFactory.getDefaultUserDetails()
                .setFirstName("thirdCustomer first name")
                .setLastName("thirdCustomer last name")
                .setLogin(getUniqueLogin());
        Customer firstCustomer = ObjectFactory.getDefaultCustomer()
                .setCustomerDetails(firstCustomerDetails);
        Customer secondCustomer = ObjectFactory.getDefaultCustomer()
                .setCustomerDetails(secondCustomerDetails);
        Customer thirdCustomer = ObjectFactory.getDefaultCustomer()
                .setCustomerDetails(thirdCustomerDetails);

        tx.begin();
        em.persist(firstCustomer);
        em.persist(secondCustomer);
        em.persist(thirdCustomer);
        tx.commit();

        Customer customer = em.createNamedQuery(Customer.FIND_BY_ANY_NAME, Customer.class)
                .setParameter("name", "%thirdCust%")
                .getSingleResult();

        assertEquals(thirdCustomer, customer);
    }

    @Test(expected = SQLIntegrityConstraintViolationException.class)
    public void shouldConstraintViolationExceptionCauseNonUniqueLogin() throws Throwable {
        Customer customer = ObjectFactory.getDefaultCustomer();
        try {
            tx.begin();
            em.persist(customer);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw ex.getCause().getCause();
        }
    }
}
