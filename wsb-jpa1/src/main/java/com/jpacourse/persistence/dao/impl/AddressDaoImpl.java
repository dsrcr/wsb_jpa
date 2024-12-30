package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.AddressDao;
import com.jpacourse.persistence.entity.AddressEntity;
import org.springframework.stereotype.Repository;

/**
 * Implementation of the {@link AddressDao} interface for accessing and manipulating {@link AddressEntity} data.
 * This class provides the actual implementation of the methods defined in the {@link AddressDao} interface.
 * It extends the generic {@link AbstractDao} to inherit basic CRUD operations.
 */
@Repository
public class AddressDaoImpl extends AbstractDao<AddressEntity, Long> implements AddressDao {

}
