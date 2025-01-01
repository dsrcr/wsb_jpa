package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;

import java.time.LocalDateTime;

/**
 * Interface for accessing and manipulating patient data in the persistence layer.
 * Provides methods for basic CRUD operations as well as specific business logic
 * related to patient visits.
 */
public interface PatientDao extends Dao<PatientEntity, Long> {

    /**
     * Adds a new visit to a patient by associating a doctor, visit time, and description to the patient.
     * This method creates a new VisitEntity and links it to the specified patient and doctor.
     *
     * @param patientId   the ID of the patient to whom the visit will be added
     * @param doctorId    the ID of the doctor associated with the visit
     * @param visitTime   the time at which the visit takes place
     * @param description a description or notes related to the visit
     * @return the created VisitEntity object representing the new visit
     */
    VisitEntity addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitTime, String description);
}
