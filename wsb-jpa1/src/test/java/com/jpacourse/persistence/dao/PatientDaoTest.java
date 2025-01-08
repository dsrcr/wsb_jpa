package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.OptimisticLockException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class PatientDaoTest {

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private DoctorDao doctorDao;

    @Test
    void testAddVisitToPatient_Success() {
        PatientEntity patientEntity = patientDao.findOne(1L);
        DoctorEntity doctorEntity = doctorDao.findOne(1L);
        VisitEntity visitEntity = new VisitEntity();
        visitEntity.setTime(LocalDateTime.now());
        visitEntity.setDescription("Initial consultation");
        visitEntity.setDoctor(doctorEntity);

        VisitEntity result = patientDao.addVisitToPatient(
                patientEntity.getId(),
                doctorEntity.getId(),
                visitEntity.getTime(),
                visitEntity.getDescription()
        );
        assertNotNull(result);
        assertEquals("Initial consultation", result.getDescription());
        assertEquals(doctorEntity, result.getDoctor());
        assertTrue(patientEntity.getVisits().contains(result));
    }

    @Test
    void testFindPatientsBySurname() {
        List<PatientEntity> patients = patientDao.findByLastName("Green");
        assertNotNull(patients);
        assertEquals(1, patients.size());
        assertEquals("Alice", patients.get(0).getFirstName());
    }

    @Test
    void testFindPatientsByIdCardNumberContaining() {
        List<PatientEntity> patients = patientDao.findPatientsByIdCardNumberContaining("102");
        assertNotNull(patients);
        assertEquals(2, patients.size());
        assertEquals("ID246813102", patients.get(0).getIdCardNumber());
        assertEquals("ID102938475", patients.get(1).getIdCardNumber());
    }

    @Test
    public void testFindByGenderMale() {
        List<PatientEntity> malePatients = patientDao.findByGender('M');

        assertEquals(2, malePatients.size(), "Should find 2 male patients");
        assertEquals("Bob", malePatients.get(0).getFirstName(), "First male patient should be Bob");
        assertEquals("Charlie", malePatients.get(1).getFirstName(), "Second male patient should be Charlie");
    }

    @Test
    public void testFindByGenderFemale() {
        List<PatientEntity> femalePatients = patientDao.findByGender('F');

        assertEquals(3, femalePatients.size(), "Should find 3 female patients");
        assertEquals("Alice", femalePatients.get(0).getFirstName(), "First female patient should be Alice");
        assertEquals("Daisy", femalePatients.get(1).getFirstName(), "Second female patient should be Daisy");
        assertEquals("Eve", femalePatients.get(2).getFirstName(), "Third female patient should be Eve");
    }

    @Test
    public void testOptimisticLockingWhenChangingEmail() {
        PatientEntity patient = patientDao.findOne(1L);
        assertNotNull(patient);
        assertEquals(0, patient.getVersion());

        patient.setEmail("new.email1@example.com");
        patientDao.update(patient);

        PatientEntity patientInThread2 = patientDao.findOne(1L);
        assertNotNull(patientInThread2);
        patientInThread2.setEmail("new.email2@example.com");

        try {
            patientDao.update(patientInThread2);
        } catch (OptimisticLockException e) {
            System.out.println("Optimistic lock exception triggered");
        }

        PatientEntity updatedPatient = patientDao.findOne(1L);
        assertNotNull(updatedPatient);
        assertEquals("new.email2@example.com", updatedPatient.getEmail());
        assertEquals(2, updatedPatient.getVersion());
    }

}
