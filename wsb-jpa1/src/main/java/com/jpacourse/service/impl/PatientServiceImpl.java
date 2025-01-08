package com.jpacourse.service.impl;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.mapper.PatientMapper;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service implementation for managing patients.
 * This class implements the {@link PatientService} interface and provides business logic for patient-related operations.
 * It interacts with the {@link PatientDao} to retrieve, delete, and manipulate patient data.
 */
@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    private final PatientDao patientDao;

    /**
     * Constructs a {@link PatientServiceImpl} with the given {@link PatientDao}.
     * This constructor is used to inject the {@link PatientDao} dependency.
     *
     * @param pPatientDao the {@link PatientDao} to be injected into this service implementation
     */
    @Autowired
    public PatientServiceImpl(PatientDao pPatientDao) {
        this.patientDao = pPatientDao;
    }

    /**
     * Retrieves a patient by their unique identifier (ID).
     * This method fetches a {@link PatientEntity} from the database and converts it to a {@link PatientTO} using the {@link PatientMapper}.
     *
     * @param id the unique identifier of the patient to be fetched
     * @return a {@link PatientTO} object containing the patient's data, or null if no patient is found with the provided ID
     */
    @Override
    public PatientTO findById(Long id) {
        final PatientEntity entity = patientDao.findOne(id);
        return PatientMapper.mapToTO(entity);
    }

    /**
     * @param patientId
     * @return
     */
    @Override
    public List<VisitEntity> findVisitsByPatientId(Long patientId) {
        PatientEntity patient = patientDao.findOne(patientId);
        if (patient != null) return patient.getVisits();
        else throw new RuntimeException("Patient not found with ID: " + patientId);

    }

    /**
     * Deletes a patient from the database by their unique identifier (ID).
     * If the patient exists, they will be removed from the database.
     * If no patient is found with the given ID, a {@link RuntimeException} will be thrown.
     *
     * @param patientId the unique identifier of the patient to be deleted
     * @throws RuntimeException if no patient is found with the provided ID
     */
    @Override
    public void deletePatient(Long patientId) {
        PatientEntity patientEntity = patientDao.findOne(patientId);

        if (patientEntity != null) patientDao.delete(patientEntity);
        else throw new RuntimeException("Patient not found with ID: " + patientId);

    }
}
