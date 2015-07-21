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
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Integration test for {@link ua.pp.simplecommerce.model.entity.Address} entity
 *
 * Created by Vladimir Kamenskiy on 05.07.2015.
 */
public class AddressIT extends AbstractPersistentTest {

    @Test
    public void shouldCreateNewAddress() {
        Address address = new Address("new street address", new City("new city"), new Country("new country"),"01234");
        tx.begin();
        em.persist(address);
        tx.commit();

        assertNotNull("Address id can't be null", address.getAddressId());
    }

    @Test
    public void shouldFindAddressesWithCertainParameters() {
        Address address1 = new Address("9, 1st Street", new City("The City"), new Country("The Country"), "01234");
        Address address2 = new Address("12, 2st Street", new City("The City"), new Country("The Country"), "01234");
        tx.begin();
        em.persist(address1);
        em.persist(address2);
        tx.commit();

        List<Address> addressList = em.createNamedQuery(Address.FIND_BY_DETAILS, Address.class)
                .setParameter("country", address1.getCountry())
                .setParameter("city", address1.getCity())
                .setParameter("address", address1.getAddress())
                .getResultList();
        assertEquals(1, addressList.size());
        assertEquals("9, 1st Street", addressList.get(0).getAddress());
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldRaiseConstraintViolationCauseWrongPostcode() {
        Address address = ObjectFactory.getDefaultAddress();
        em.persist(address.setPostcode("0xxxx"));
    }
}
