package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.DoctorEntity;

/**
 * Interface for accessing and manipulating doctor data in the persistence layer.
 * This interface extends the generic Dao interface, providing CRUD operations
 * for the DoctorEntity class.
 */
public interface DoctorDao extends Dao<DoctorEntity, Long> {
}
