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
 * Integration test for {@link ua.pp.simplecommerce.model.entity.Language} entity
 *
 * Created by Vladimir Kamenskiy on 21.07.2015.
 */
public class LanguageIT extends AbstractPersistentTest {

    @Test
    public void shouldCreateLanguage() {
        Language language = ObjectFactory.getDefaultLanguage();
        tx.begin();
        em.persist(language);
        tx.commit();

        assertNotNull("Language id can't be null!", language.getLanguageId());
    }

    @Test
    public void shouldFindLanguageByCode() {
        Language language = ObjectFactory.getDefaultLanguage()
                .setCode("555");
        tx.begin();
        em.persist(language);
        tx.commit();
        Language result = em.createNamedQuery(Language.FIND_BY_CODE, Language.class)
                .setParameter("code", "555")
                .getSingleResult();
        assertEquals(language, result);
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldRaseConstraintViolationCauseByWrongLocale() {
        Language language = ObjectFactory.getDefaultLanguage()
                .setLocale("EN_EN");
        em.persist(language);
    }
}
