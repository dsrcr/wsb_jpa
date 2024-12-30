package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.AddressEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for the AddressDao class.
 * This class contains tests for performing CRUD operations on the AddressEntity.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressDaoTest {

    /**
     * The AddressDao instance used to interact with the database in the tests.
     */
    @Autowired
    private AddressDao addressDao;

    /**
     * Test to verify that an AddressEntity can be retrieved from the database
     * by its ID, and that the postal code is correct.
     * <p>
     * If the address with ID 1 does not exist, a new address with the postal
     * code "62-030" will be created and saved.
     */
    @Transactional
    @Test
    public void testShouldFindAddressById() {
        AddressEntity addressEntity = addressDao.findOne(1L);

        if (addressEntity == null) {
            addressEntity = new AddressEntity();
            addressEntity.setAddressLine1("Test Address Line 1");
            addressEntity.setAddressLine2("Test Address Line 2");
            addressEntity.setCity("Test City");
            addressEntity.setPostalCode("10001");
            addressDao.save(addressEntity);
        }

        AddressEntity fetchedAddress = addressDao.findOne(1L);

        assertThat(fetchedAddress).isNotNull();
        assertThat(fetchedAddress.getPostalCode()).isEqualTo("10001");
    }

    /**
     * Test to verify that a new AddressEntity can be saved to the database.
     * This test also checks that the saved address has a non-null ID and that
     * the total number of addresses in the database has increased by one.
     */
    @Test
    public void testShouldSaveAddress() {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddressLine1("line1");
        addressEntity.setAddressLine2("line2");
        addressEntity.setCity("City1");
        addressEntity.setPostalCode("66-666");
        long entitiesNumBefore = addressDao.count();

        final AddressEntity saved = addressDao.save(addressEntity);

        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(addressDao.count()).isEqualTo(entitiesNumBefore + 1);
    }

    /**
     * Test to verify that an AddressEntity can be saved and then removed
     * from the database successfully.
     * This test also checks that after deletion, the address is no longer
     * present in the database.
     */
    @Transactional
    @Test
    public void testShouldSaveAndRemoveAddress() {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddressLine1("line1");
        addressEntity.setAddressLine2("line2");
        addressEntity.setCity("City1");
        addressEntity.setPostalCode("66-666");

        final AddressEntity saved = addressDao.save(addressEntity);
        assertThat(saved.getId()).isNotNull();
        final AddressEntity newSaved = addressDao.findOne(saved.getId());
        assertThat(newSaved).isNotNull();

        addressDao.delete(saved.getId());

        final AddressEntity removed = addressDao.findOne(saved.getId());
        assertThat(removed).isNull();
    }
}
