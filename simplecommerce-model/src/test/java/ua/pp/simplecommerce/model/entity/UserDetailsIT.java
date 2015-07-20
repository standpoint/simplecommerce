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

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 *
 * Integration test for UserDetails entity
 *
 * Created by Vladimir Kamenskiy on 20.07.2015.
 */
public class UserDetailsIT extends AbstractPersistentTest {

    @Test
    public void shouldCreateUserDetails() {
        UserDetails userDetails = ObjectFactory.getDefaultUserDetails()
                .setLogin("Unique");
        tx.begin();
        em.persist(userDetails);
        tx.commit();

        assertNotNull("UserDetails id can't be null!", userDetails.getUserDetailsId());
    }

    @Test
    public void shouldFindUserDetailsByEmail() {
        UserDetails userDetailsFirst = ObjectFactory.getDefaultUserDetails()
                .setLogin("first")
                .setEmail("first@mail.com");
        UserDetails userDetailsSecond = ObjectFactory.getDefaultUserDetails()
                .setLogin("second")
                .setEmail("second@mail.com");
        tx.begin();
        em.persist(userDetailsFirst);
        em.persist(userDetailsSecond);
        tx.commit();
        UserDetails details = em.createNamedQuery(UserDetails.FIND_BY_EMAIL, UserDetails.class)
                .setParameter("email", userDetailsSecond.getEmail())
                .getSingleResult();

        assertEquals("second", details.getLogin());
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldRaiseConstraintViolationCauseWrongEmailSyntax() {
        UserDetails userDetails = ObjectFactory.getDefaultUserDetails()
                .setLogin("some_user")
                .setEmail("wrong email@mail.com");
        em.persist(userDetails);
    }
}
