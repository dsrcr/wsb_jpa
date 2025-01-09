package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistence.entity.VisitEntity;

import java.util.List;

/**
 * The PatientService interface provides methods for interacting with the patient data.
 * It offers functionality to retrieve patient details by ID and delete a patient.
 */
public interface PatientService {

    /**
     * Finds a patient by their unique ID and returns their data transfer object (DTO).
     *
     * @param id the unique identifier of the patient
     * @return the PatientTO object containing patient details, or null if no patient is found with the given ID
     */
    PatientTO findById(final Long id);

    List<VisitEntity> findVisitsByPatientId(Long patientId);


    /**
     * Deletes a patient identified by the given ID.
     * This method removes the patient from the database and also deletes any associated visits, if configured to do so.
     *
     * @param patientId the unique identifier of the patient to be deleted
     */
    void deletePatient(Long patientId);
}
