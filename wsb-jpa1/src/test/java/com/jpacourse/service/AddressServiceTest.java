package com.jpacourse.service;

import com.jpacourse.dto.AddressTO;
import com.jpacourse.persistence.dao.AddressDao;
import com.jpacourse.persistence.entity.AddressEntity;
import com.jpacourse.service.impl.AddressServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the AddressService class, which provides business logic for handling AddressEntity operations.
 * This class tests the interaction between the service layer and the persistence layer (AddressDao).
 */
class AddressServiceTest {

    private final AddressDao addressDao = mock(AddressDao.class);

    private final AddressService addressService = new AddressServiceImpl(addressDao);

    /**
     * Test the {@link AddressService#findById(Long)} method to ensure that an address is correctly fetched by its ID.
     * This test simulates the interaction with the AddressDao to retrieve an address by ID and then
     * checks if the returned AddressTO contains the expected city.
     */
    @Test
    void testGetAddressById() {
        AddressEntity entity = new AddressEntity();
        entity.setId(1L);
        entity.setCity("City");

        when(addressDao.findOne(1L)).thenReturn(entity);

        Optional<AddressTO> result = Optional.ofNullable(addressService.findById(1L));

        Assertions.assertTrue(result.isPresent());
        assertEquals("City", result.get().getCity());

        verify(addressDao, times(1)).findOne(1L);
    }
}
