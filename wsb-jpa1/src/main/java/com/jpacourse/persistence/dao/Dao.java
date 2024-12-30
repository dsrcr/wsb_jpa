package com.jpacourse.persistence.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Generic interface for data access operations.
 * This interface defines basic CRUD operations for entities of type {@link T} identified by {@link K}.
 *
 * @param <T> the type of the entity to be persisted
 * @param <K> the type of the entity's identifier, which must be serializable
 */
public interface Dao<T, K extends Serializable> {

    /**
     * Saves a given entity.
     * This method persists the entity to the database.
     * If the entity already exists, it will be updated.
     *
     * @param entity the entity to be saved
     * @return the saved entity
     */
    T save(T entity);

    /**
     * Retrieves an entity by its ID.
     * This method returns a reference to the entity and might not trigger database loading immediately.
     *
     * @param id the identifier of the entity
     * @return the entity with the given ID, or {@code null} if no entity is found
     */
    T getOne(K id);

    /**
     * Finds an entity by its ID.
     * This method fetches the entity from the database, and if the entity does not exist, it returns {@code null}.
     *
     * @param id the identifier of the entity
     * @return the entity with the given ID, or {@code null} if no entity is found
     */
    T findOne(K id);

    /**
     * Retrieves all entities of type {@link T}.
     *
     * @return a list of all entities of type {@link T}
     */
    List<T> findAll();

    /**
     * Updates a given entity.
     * This method updates an existing entity in the database.
     *
     * @param entity the entity to be updated
     * @return the updated entity
     */
    T update(T entity);

    /**
     * Deletes a given entity.
     * This method removes the entity from the database.
     *
     * @param entity the entity to be deleted
     */
    void delete(T entity);

    /**
     * Deletes an entity by its ID.
     * This method removes the entity with the given ID from the database.
     *
     * @param id the identifier of the entity to be deleted
     */
    void delete(K id);

    /**
     * Deletes all entities of type {@link T}.
     * This method removes all entities from the database.
     */
    void deleteAll();

    /**
     * Counts the number of entities of type {@link T}.
     *
     * @return the number of entities of type {@link T}
     */
    long count();

    /**
     * Checks whether an entity exists by its ID.
     *
     * @param id the identifier of the entity
     * @return {@code true} if an entity with the given ID exists, {@code false} otherwise
     */
    boolean exists(K id);
}
