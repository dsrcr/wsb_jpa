package com.jpacourse.service.impl;

import com.jpacourse.dto.AddressTO;
import com.jpacourse.mapper.AddressMapper;
import com.jpacourse.persistence.dao.AddressDao;
import com.jpacourse.persistence.entity.AddressEntity;
import com.jpacourse.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service implementation for managing addresses.
 * This class implements the {@link AddressService} interface and provides business logic for address-related operations.
 * It interacts with the {@link AddressDao} to retrieve, save, and delete address data.
 */
@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    private final AddressDao addressDao;

    /**
     * Constructs a {@link AddressServiceImpl} with the given {@link AddressDao}.
     * This constructor is used to inject the {@link AddressDao} dependency.
     *
     * @param pAddressDao the {@link AddressDao} to be injected into this service implementation
     */
    @Autowired
    public AddressServiceImpl(AddressDao pAddressDao) {
        addressDao = pAddressDao;
    }

    /**
     * Retrieves an address by its unique identifier (ID).
     * This method fetches an {@link AddressEntity} from the database and converts it to an {@link AddressTO} using the {@link AddressMapper}.
     *
     * @param id the unique identifier of the address to be fetched
     * @return an {@link AddressTO} object containing the address data, or null if no address is found with the provided ID
     */
    @Override
    public AddressTO findById(Long id) {
        final AddressEntity entity = addressDao.findOne(id);
        return AddressMapper.mapToTO(entity);
    }

    /**
     * Saves the provided address.
     * This method will be used to persist a new or updated {@link AddressTO} in the database.
     *
     * @param address the {@link AddressTO} to be saved
     */
    @Override
    public void saveAddress(AddressTO address) {
        // Implementation should persist the address
    }

    /**
     * Deletes an address by its unique identifier (ID).
     * This method removes the address from the database if it exists.
     *
     * @param id the unique identifier of the address to be deleted
     */
    @Override
    public void deleteAddress(Long id) {
        // Implementation should delete the address by its ID
    }
}
