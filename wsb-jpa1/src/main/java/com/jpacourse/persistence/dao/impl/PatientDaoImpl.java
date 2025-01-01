package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

/**
 * Implementation of the PatientDao interface.
 * Provides methods for accessing and manipulating patient data,
 * including adding visits to a patient.
 */
@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao {

    @PersistenceContext
    private EntityManager entityManager;

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
    @Override
    @Transactional
    public VisitEntity addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitTime, String description) {
        PatientEntity patient = entityManager.find(PatientEntity.class, patientId);
        if (patient == null) throw new IllegalArgumentException("Patient not found with ID: " + patientId);

        DoctorEntity doctor = entityManager.find(DoctorEntity.class, doctorId);
        if (doctor == null) throw new IllegalArgumentException("Doctor not found with ID: " + doctorId);

        VisitEntity visit = new VisitEntity();
        visit.setTime(visitTime);
        visit.setDescription(description);
        visit.setDoctor(doctor);

        patient.getVisits().add(visit);

        entityManager.merge(patient);

        return visit;
    }
}
