package com.jpacourse.service;

import com.jpacourse.dto.AddressTO;
import com.jpacourse.persistence.dao.AddressDao;
import com.jpacourse.persistence.entity.AddressEntity;
import com.jpacourse.service.impl.AddressServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)

class AddressServiceTest {

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private AddressService addressService;

    /**
     * Test the {@link AddressService#findById(Long)} method to ensure that an address is correctly fetched by its ID.
     * This test interacts with the actual AddressDao to retrieve an address by ID and then
     * checks if the returned AddressTO contains the expected city.
     */
    @Test
    void testGetAddressById() {
        AddressEntity entity = new AddressEntity();
        entity.setCity("City");
        entity.setAddressLine1("123 Main St");
        entity.setPostalCode("10001");

        AddressEntity savedEntity = addressDao.save(entity);
        Optional<AddressTO> result = Optional.ofNullable(addressService.findById(savedEntity.getId()));

        assertTrue(result.isPresent());
        assertEquals("City", result.get().getCity());
    }
}
