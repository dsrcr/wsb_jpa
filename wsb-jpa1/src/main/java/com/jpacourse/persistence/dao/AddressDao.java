package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.AddressEntity;

/**
 * Interface for accessing and manipulating address data in the persistence layer.
 * This interface extends the generic Dao interface, providing CRUD operations
 * for the AddressEntity class.
 */
public interface AddressDao extends Dao<AddressEntity, Long> {
}
