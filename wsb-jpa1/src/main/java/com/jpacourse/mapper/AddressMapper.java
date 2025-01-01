package com.jpacourse.mapper;

import com.jpacourse.dto.AddressTO;
import com.jpacourse.persistence.entity.AddressEntity;

/**
 * Utility class that provides methods for mapping between {@link AddressEntity} and {@link AddressTO}.
 * This class is designed to convert between the entity (used for database operations) and transfer object (used for data transfer).
 */
public final class AddressMapper {

    /**
     * Maps an {@link AddressEntity} to an {@link AddressTO}.
     * This method converts an entity object, which is typically used for persistence, to a transfer object used in the application layer.
     *
     * @param addressEntity the {@link AddressEntity} object to be converted to {@link AddressTO}
     * @return the corresponding {@link AddressTO} object, or null if the input entity is null
     */
    public static AddressTO mapToTO(final AddressEntity addressEntity) {
        if (addressEntity == null) return null;

        final AddressTO addressTO = new AddressTO();
        addressTO.setId(addressEntity.getId());
        addressTO.setAddressLine1(addressEntity.getAddressLine1());
        addressTO.setAddressLine2(addressEntity.getAddressLine2());
        addressTO.setCity(addressEntity.getCity());
        addressTO.setPostalCode(addressEntity.getPostalCode());
        return addressTO;
    }

    /**
     * Maps an {@link AddressTO} to an {@link AddressEntity}.
     * This method converts a transfer object, which is used for communication or data transfer, to an entity object used for database operations.
     *
     * @param addressTO the {@link AddressTO} object to be converted to {@link AddressEntity}
     * @return the corresponding {@link AddressEntity} object, or null if the input transfer object is null
     */
    public static AddressEntity mapToEntity(final AddressTO addressTO) {
        if (addressTO == null) return null;

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(addressTO.getId());
        addressEntity.setAddressLine1(addressTO.getAddressLine1());
        addressEntity.setAddressLine2(addressTO.getAddressLine2());
        addressEntity.setCity(addressTO.getCity());
        addressEntity.setPostalCode(addressTO.getPostalCode());
        return addressEntity;
    }
}
