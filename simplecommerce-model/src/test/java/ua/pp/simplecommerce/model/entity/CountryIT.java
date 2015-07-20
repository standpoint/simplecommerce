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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * Integration test for Country entity
 *
 * Created by Vladimir Kamenskiy on 06.07.2015.
 */
public class CountryIT extends AbstractPersistentTest {

    @Test
    public void shouldCreateCountry() {
        Country country = ObjectFactory.getDefaultCountry();
        tx.begin();
        em.persist(country);
        tx.commit();
    }

    @Test
    public void shouldFindCountriesContainsName() {
        List<Country> countries = new ArrayList<>();
        countries.add(new Country("Northern Ireland"));
        countries.add(new Country("Ireland"));
        countries.add(new Country("UK"));
        tx.begin();
        for (Country country : countries) {
            em.persist(country);
        }
        tx.commit();
        List<Country> resultList = em.createNamedQuery(Country.FIND_BY_NAME, Country.class)
                .setParameter("name", "%Ireland%")
                .getResultList();
        Collections.sort(resultList);
        assertEquals("Ireland", resultList.get(0).getCountry());
        assertEquals("Northern Ireland", resultList.get(1).getCountry());
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldRaiseConstraintViolationExceptionCauseNullName() {
        Country c = new Country(null);
        em.persist(c);
    }
}
