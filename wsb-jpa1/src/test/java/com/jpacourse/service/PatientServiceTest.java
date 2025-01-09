package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistence.dao.DoctorDao;
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
    private PatientService patientService;

    @Autowired
    private DoctorDao doctorDao;

    /**
     * Test the {@link PatientService#deletePatient(Long)} method to ensure that when a patient is deleted:
     * 1. The patient's visits are also deleted (cascaded).
     * 2. The patient is no longer available in the system (Patient is removed).
     * 3. The doctors' data is not affected.
     */
    @Test
    public void testShouldDeletePatientAndHisVisits() {
        assertNotNull(patientService.findById(1L));

        int doctorsCountBefore = doctorDao.findAll().size();
        assertNotNull(patientService.findVisitsByPatientId(1L));

        patientService.deletePatient(1L);
        assertNull(patientService.findById(1L));
        assertTrue(patientService.findVisitsByPatientId(1L).isEmpty());
        assertEquals(doctorsCountBefore, doctorDao.findAll().size());
    }

    /**
     * Test the {@link PatientService#findById(Long)} method to ensure that a patient is fetched correctly
     * by their ID and that the returned PatientTO contains the correct information, including:
     * 1. First Name
     * 2. Last Name
     * 3. Patient Number
     * 4. Gender
     * 5. Date of Birth
     * 6. Telephone Number
     * 7. Email Address
     * 8. Address ID
     * 9. ID Card Number
     */
    @Test
    void testGetPatientById() {
        PatientTO patient = patientService.findById(1L);

        assertEquals("Alice", patient.getFirstName());
        assertEquals("Green", patient.getLastName());
        assertEquals("PAT001", patient.getPatientNumber());
        assertEquals('F', patient.getGender());
        assertEquals(java.time.LocalDate.of(1990, 5, 14), patient.getDateOfBirth());

        assertEquals("555-1001", patient.getTelephoneNumber());
        assertEquals("alicegreen@email.com", patient.getEmail());

        assertNotNull(patient.getAddress());
        assertEquals(6L, patient.getAddress().getId());
    }

    /**
     * Test the {@link PatientService#findVisitsByPatientId(Long)} method to ensure that:
     * 1. The method returns the correct list of visits for the specified patient.
     * 2. The number of visits is correct.
     */
    @Test
    void testFindVisitsByPatientId_PatientFound() {
        List<VisitEntity> visits = patientService.findVisitsByPatientId(1L);

        assertNotNull(visits, "The visits list should not be null.");

        assertEquals(2, visits.size(), "There should be exactly 2 visits.");
    }
}
