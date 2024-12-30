package com.jpacourse.rest.exception;

/**
 * Custom exception thrown when an entity (e.g., a patient, address) is not found in the system
 * based on a given ID. This exception is typically thrown by service or controller methods
 * when a requested entity does not exist.
 */
public class EntityNotFoundException extends RuntimeException {

    /**
     * Constructs a new {@link EntityNotFoundException} with a detailed message indicating the entity
     * could not be found by its ID.
     *
     * @param id the unique identifier of the entity that was not found
     */
    public EntityNotFoundException(Long id) {
        super("Could not find entity of id " + id);
    }
}
