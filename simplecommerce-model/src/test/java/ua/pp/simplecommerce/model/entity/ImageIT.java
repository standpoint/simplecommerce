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

import javax.validation.ConstraintViolationException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Integration test for {@link ua.pp.simplecommerce.model.entity.Image} entity
 *
 * Created by Vladimir Kamenskiy on 21.07.2015.
 */
public class ImageIT extends AbstractPersistentTest {

    @Test
    public void shouldCreateImage() {
        Image image = new Image("imageUrl");
        tx.begin();
        em.persist(image);
        tx.commit();

        assertNotNull("Image id can't be null!", image.getImageId());
    }

    @Test
    public void shouldFindImageByFilename() {
        String filename = "specificFileName";
        Image image = new Image(filename);
        tx.begin();
        em.persist(image);
        tx.commit();
        Image foundImage = em.createNamedQuery(Image.FIND_BY_FILENAME, Image.class)
                .setParameter("filename", filename)
                .getSingleResult();

        assertEquals(image.getUrl(), foundImage.getUrl());
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldRaiseConstraintViolationCauseNullUrl() {
        em.persist(new Image());
    }
}
