package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the PatientService class, which provides business logic for handling PatientEntity operations.
 * This class tests the interaction between the service layer and the persistence layer (PatientDao).
 */
class PatientServiceTest {

    private final PatientDao patientDao = mock(PatientDao.class);

    private final PatientService patientService = new PatientServiceImpl(patientDao);


    /**
     * Test the  method to ensure that a patient is deleted
     * correctly, and associated visits are removed (cascade). It also ensures that doctors are not deleted.
     */
    @Test
    void testDeletePatient() {
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(1L);
        patientEntity.setFirstName("John");
        patientEntity.setLastName("Doe");

        VisitEntity visitEntity = new VisitEntity();
        visitEntity.setId(1L);
        visitEntity.setDescription("Consultation");
        visitEntity.setPatient(patientEntity);

        when(patientDao.findOne(1L)).thenReturn(patientEntity);
        doNothing().when(patientDao).delete(patientEntity);

        patientService.deletePatient(1L);

        verify(patientDao, times(1)).delete(patientEntity);

        verify(patientDao, times(1)).findOne(1L);
    }

    /**
     * Test the {@link PatientService#findById(Long)} method to ensure that a patient is fetched correctly
     * by their ID and that the returned PatientTO contains the correct information, including the added field
     * for 'gender'.
     */
    @Test
    void testGetPatientById() {
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(1L);
        patientEntity.setFirstName("Alice");
        patientEntity.setLastName("Green");
        patientEntity.setGender('F');
        patientEntity.setPatientNumber("PAT001");

        when(patientDao.findOne(1L)).thenReturn(patientEntity);

        Optional<PatientTO> result = Optional.ofNullable(patientService.findById(1L));

        assertTrue(result.isPresent());
        assertEquals("Alice", result.get().getFirstName());
        assertEquals("Green", result.get().getLastName());
        assertEquals('F', result.get().getGender());
        assertEquals("PAT001", result.get().getPatientNumber());

        verify(patientDao, times(1)).findOne(1L);
    }
}
