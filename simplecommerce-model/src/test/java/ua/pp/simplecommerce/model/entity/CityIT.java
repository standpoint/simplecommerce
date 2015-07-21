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
import java.util.*;

import static junit.framework.TestCase.assertEquals;

/**
 * Integration test for {@link ua.pp.simplecommerce.model.entity.City} entity
 *
 * Created by Vladimir Kamenskiy on 06.07.2015.
 */
public class CityIT extends AbstractPersistentTest {

    @Test
    public void shouldCreateCity() {
        City city = ObjectFactory.getDefaultCity();
        tx.begin();
        em.persist(city);
        tx.commit();
    }

    @Test
    public void shouldFindCitiesContainsName() {
        List<City> cities = new ArrayList<>();
        cities.add(new City("Kiev"));
        cities.add(new City("Nikolayev"));
        cities.add(new City("Nikolayevka"));
        tx.begin();
        for (City city : cities) {
            em.persist(city);
        }
        tx.commit();
        List<City> resultList = em.createNamedQuery(City.FIND_BY_NAME, City.class)
                .setParameter("name", "%Nikolayev%")
                .getResultList();
        Collections.sort(resultList);
        assertEquals("Nikolayev", resultList.get(0).getCity());
        assertEquals("Nikolayevka", resultList.get(1).getCity());
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldRaiseConstraintViolationExceptionCauseNullName() {
        City c = new City(null);
        em.persist(c);
    }
}
