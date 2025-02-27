package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementation of the {@link PatientDao} interface.
 * Provides methods for accessing and manipulating patient data,
 * including adding visits to a patient.
 */
@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao {

    /**
     * Adds a new visit to a patient by associating a doctor, visit time, and description to the patient.
     * This method creates a new {@link VisitEntity} and links it to the specified patient and doctor.
     *
     * @param patientId   the ID of the patient to whom the visit will be added
     * @param doctorId    the ID of the doctor associated with the visit
     * @param visitTime   the time at which the visit takes place
     * @param description a description or notes related to the visit
     * @return the created {@link VisitEntity} object representing the new visit
     * @throws IllegalArgumentException if the patient or doctor is not found
     */
    @Override
    @Transactional
    public VisitEntity addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitTime, String description) {
        PatientEntity patient = findOne(patientId);
        if (patient == null) throw new IllegalArgumentException("Patient not found with ID: " + patientId);

        DoctorEntity doctor = entityManager.find(DoctorEntity.class, doctorId);
        if (doctor == null) throw new IllegalArgumentException("Doctor not found with ID: " + doctorId);

        VisitEntity visit = new VisitEntity();
        visit.setTime(visitTime);
        visit.setDescription(description);
        visit.setDoctor(doctor);
        visit.setPatient(patient);
        patient.getVisits().add(visit);
        save(patient);

        return visit;
    }

    /**
     * Finds patients by their last name.
     * This method retrieves a list of patients whose last name matches the provided {@code lastName}.
     * The search is case-sensitive and will return all matching patients.
     *
     * @param lastName the last name of the patient(s) to search for
     * @return a list of {@link PatientEntity} objects representing the patients with the given last name,
     * or an empty list if no patients are found
     */
    @Override
    public List<PatientEntity> findByLastName(String lastName) {
        String jpql = "SELECT p FROM PatientEntity p WHERE p.lastName = :last_name";
        return entityManager.createQuery(jpql, PatientEntity.class).setParameter("last_name", lastName).getResultList();
    }

    /**
     * Finds patients by partial match of their ID card number.
     * This method retrieves a list of patients whose ID card number contains the provided partial value.
     *
     * @param partialIdCardNumber the partial ID card number to search for
     * @return a list of {@link PatientEntity} objects representing the patients with the given partial ID card number,
     * or an empty list if no patients are found
     */
    @Override
    public List<PatientEntity> findPatientsByIdCardNumberContaining(String partialIdCardNumber) {
        String jpql = "SELECT p FROM PatientEntity p WHERE p.idCardNumber LIKE :partialIdCardNumber";
        return entityManager.createQuery(jpql, PatientEntity.class).setParameter("partialIdCardNumber", "%" + partialIdCardNumber + "%").getResultList();
    }

    /**
     * Finds patients by their gender.
     * This method retrieves a list of patients whose gender matches the provided {@code gender}.
     *
     * @param gender the gender of the patients to search for
     * @return a list of {@link PatientEntity} objects representing the patients with the given gender,
     * or an empty list if no patients are found
     */
    @Override
    public List<PatientEntity> findByGender(char gender) {
        String jpql = "SELECT p FROM PatientEntity p WHERE p.gender = :gender";
        return entityManager.createQuery(jpql, PatientEntity.class).setParameter("gender", gender).getResultList();
    }
}
