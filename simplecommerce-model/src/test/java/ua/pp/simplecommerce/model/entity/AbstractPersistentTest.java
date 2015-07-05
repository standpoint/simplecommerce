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

import org.junit.After;
import org.junit.Before;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.Random;

/**
 * Declare @Before and @After methods for test where initialized and closed entity and transaction managers
 *
 * Created by Vladimir Kamenskiy on 05.07.2015.
 */
public abstract class AbstractPersistentTest {

    protected final static Long START_ID = 10000000000L;
    protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ecommerceTestPU");
    protected EntityManager em;
    protected EntityTransaction tx;

    @Before
    public void initEntityManager() throws Exception {
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }
    @After
    public void closeEntityManager() throws SQLException {
        if (em != null) {
            em.close();
        }
    }

    protected Long getRandomId() {
        return Math.abs(new Random().nextLong());
    }
}
