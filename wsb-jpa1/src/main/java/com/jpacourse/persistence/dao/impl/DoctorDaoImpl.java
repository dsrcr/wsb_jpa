package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import org.springframework.stereotype.Repository;

/**
 * Implementation of the DoctorDao interface for accessing and manipulating doctor data
 * in the persistence layer. Extends the AbstractDao class to inherit basic CRUD operations
 * and adds any specific logic for managing DoctorEntity instances.
 * <p>
 * This class is annotated as a Spring Repository, indicating that it is a DAO component
 * responsible for data access operations in the persistence layer.
 */
@Repository
public class DoctorDaoImpl extends AbstractDao<DoctorEntity, Long> implements DoctorDao {
}
