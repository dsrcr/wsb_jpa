package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;

import java.time.LocalDateTime;
import java.util.List;

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

    /**
     * Finds patients by their last name.
     * This method retrieves a list of patients whose last name matches the provided {@code lastName}.
     * The search is case-sensitive and will return all matching patients.
     *
     * @param lastName the last name of the patient(s) to search for
     * @return a list of {@link PatientEntity} objects representing the patients with the given last name,
     * or an empty list if no patients are found
     */
    List<PatientEntity> findByLastName(String lastName);

    /**
     * Finds patients who have more than a specified number of visits.
     * This method retrieves a list of patients who have more than {@code visitCount} visits. The number
     * of visits is determined by counting the records associated with each patient in the visits table.
     *
     * @param visitCount the minimum number of visits a patient must have to be included in the result
     * @return a list of {@link PatientEntity} objects representing the patients who have more than the specified
     * number of visits, or an empty list if no patients meet the criteria
     */
    List<PatientEntity> findPatientsWithMoreThanXVisits(int visitCount);

    List<PatientEntity> findPatientsByIdCardNumberContaining(String partialIdCardNumber);

    List<PatientEntity> findByGender(char gender);
}
