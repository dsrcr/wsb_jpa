package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the PatientDao interface.
 * This class tests the interaction between the PatientDao and the persistence layer.
 */
class PatientDaoTest {

    private final PatientDao patientDao = mock(PatientDao.class);

    private DoctorEntity doctorEntity;
    private VisitEntity visitEntity;

    @BeforeEach
    void setUp() {
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(1L);

        doctorEntity = new DoctorEntity();
        doctorEntity.setId(1L);

        visitEntity = new VisitEntity();
        visitEntity.setTime(LocalDateTime.now());
        visitEntity.setDescription("Routine check-up");
        visitEntity.setDoctor(doctorEntity);
    }

    /**
     * Test the {@link PatientDao#addVisitToPatient(Long, Long, LocalDateTime, String)} method
     * to ensure that a new visit is added correctly to the patient.
     */
    @Test
    void testAddVisitToPatient_Success() {
        when(patientDao.addVisitToPatient(1L, 1L, visitEntity.getTime(), visitEntity.getDescription())).thenReturn(visitEntity);

        VisitEntity result = patientDao.addVisitToPatient(1L, 1L, visitEntity.getTime(), visitEntity.getDescription());

        assertNotNull(result);
        assertEquals("Routine check-up", result.getDescription());
        assertEquals(doctorEntity, result.getDoctor());

        verify(patientDao, times(1)).addVisitToPatient(1L, 1L, visitEntity.getTime(), visitEntity.getDescription());
    }

    /**
     * Test the scenario where the patient does not exist, expecting an IllegalArgumentException.
     */
    @Test
    void testAddVisitToPatient_PatientNotFound() {
        when(patientDao.addVisitToPatient(1L, 1L, visitEntity.getTime(), visitEntity.getDescription())).thenThrow(new IllegalArgumentException("Patient not found with ID: 1"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            patientDao.addVisitToPatient(1L, 1L, visitEntity.getTime(), visitEntity.getDescription());
        });

        assertEquals("Patient not found with ID: 1", exception.getMessage());
    }

    /**
     * Test the scenario where the doctor does not exist, expecting an IllegalArgumentException.
     */
    @Test
    void testAddVisitToPatient_DoctorNotFound() {
        when(patientDao.addVisitToPatient(1L, 1L, visitEntity.getTime(), visitEntity.getDescription())).thenThrow(new IllegalArgumentException("Doctor not found with ID: 1"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            patientDao.addVisitToPatient(1L, 1L, visitEntity.getTime(), visitEntity.getDescription());
        });

        assertEquals("Doctor not found with ID: 1", exception.getMessage());
    }
}
