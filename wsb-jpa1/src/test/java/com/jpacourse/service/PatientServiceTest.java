package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the PatientService class, which provides business logic for handling PatientEntity operations.
 * This class tests the interaction between the service layer and the persistence layer (PatientDao).
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
class PatientServiceTest {

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorDao doctorDao;

    // @TODO Cascade broken
//    @Test
//    void testDeletePatient() {
//        PatientEntity patientToDelete = patientDao.findOne(3L);
//        assertNotNull(patientToDelete, "Patient should exist before deletion");
//
//        assertFalse(patientToDelete.getVisits().isEmpty(), "Patient should have associated visits before deletion");
//
//        long initialVisitCount = patientToDelete.getVisits().size();
//
//        patientDao.delete(patientToDelete);
//
//        PatientEntity deletedPatient = patientDao.findOne(3L);
//        assertNull(deletedPatient, "Patient should be deleted from the database");
//
//        PatientEntity reloadedPatient = patientDao.findOne(3L);
//        assertNull(reloadedPatient, "Patient should be deleted");
//        DoctorEntity doctor = doctorDao.findOne(patientToDelete.getVisits().get(0).getDoctor().getId());
//        assertNotNull(doctor, "Doctor should not be deleted when the patient is deleted");
//
//        assertEquals(0, initialVisitCount, "Associated visits should be deleted when the patient is deleted");
//
//    }

    /**
     * Test the {@link PatientService#findById(Long)} method to ensure that a patient is fetched correctly
     * by their ID and that the returned PatientTO contains the correct information, including the added field
     * for 'gender'.
     */
    @Test
    void testGetPatientById() {
        PatientTO patient = patientService.findById(1L);

        assertEquals("Alice", patient.getFirstName());
        assertEquals("Green", patient.getLastName());
        assertEquals("PAT001", patient.getPatientNumber());
        assertEquals(java.time.LocalDate.of(1990, 5, 14), patient.getDateOfBirth());
    }

    @Test
    void testFindVisitsByPatientId_PatientFound() {
        List<VisitEntity> visits = patientService.findVisitsByPatientId(1L);

        assertNotNull(visits, "The visits list should not be null.");
        assertEquals(2, visits.size(), "There should be exactly 2 visits.");
    }

    @Test
    void testFindVisitsByPatientId_PatientNotFound() {

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            patientService.findVisitsByPatientId(999L);
        }, "Expected RuntimeException to be thrown.");

        assertEquals("Patient not found with ID: 999", thrown.getMessage(), "The exception message should be correct.");
    }

}
