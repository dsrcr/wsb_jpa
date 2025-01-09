package com.jpacourse.service;

import com.jpacourse.dto.AddressTO;

/**
 * Service interface for managing Address operations.
 * This interface defines methods for retrieving, saving, and deleting address data.
 * It is part of the service layer and interacts with the persistence layer to manage Address entities.
 */
public interface AddressService {

    /**
     * Finds an address by its ID and returns the corresponding AddressTO object.
     *
     * @param id the ID of the address to be found
     * @return the AddressTO corresponding to the given ID, or null if no address is found
     */
    AddressTO findById(final Long id);
}
