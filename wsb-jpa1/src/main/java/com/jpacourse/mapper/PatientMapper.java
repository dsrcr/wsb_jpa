package com.jpacourse.mapper;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistence.entity.PatientEntity;

/**
 * Utility class for mapping between PatientEntity and PatientTO (Transfer Object).
 * Provides methods to convert between the persistence layer's entity representation
 * and the transfer object used in business logic or presentation layers.
 */
public final class PatientMapper {

    /**
     * Maps a PatientEntity to a PatientTO.
     *
     * @param patientEntity the PatientEntity to be converted
     * @return a PatientTO object containing the same data as the provided PatientEntity,
     *         or null if the input PatientEntity is null
     */
    public static PatientTO mapToTO(final PatientEntity patientEntity) {
        if (patientEntity == null) return null;

        final PatientTO patientTO = new PatientTO();
        patientTO.setId(patientEntity.getId());
        patientTO.setFirstName(patientEntity.getFirstName());
        patientTO.setLastName(patientEntity.getLastName());
        patientTO.setTelephoneNumber(patientEntity.getTelephoneNumber());
        patientTO.setEmail(patientEntity.getEmail());
        patientTO.setPatientNumber(patientEntity.getPatientNumber());
        patientTO.setDateOfBirth(patientEntity.getDateOfBirth());
        patientTO.setGender(patientEntity.getGender());

        patientTO.setAddress(patientEntity.getAddress());
        patientTO.setVisits(patientEntity.getVisits());

        return patientTO;
    }

    /**
     * Maps a PatientTO to a PatientEntity.
     *
     * @param patientTO the PatientTO to be converted
     * @return a PatientEntity object containing the same data as the provided PatientTO,
     *         or null if the input PatientTO is null
     */
    public static PatientEntity mapToEntity(final PatientTO patientTO) {
        if (patientTO == null) return null;

        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(patientTO.getId());
        patientEntity.setFirstName(patientTO.getFirstName());
        patientEntity.setLastName(patientTO.getLastName());
        patientEntity.setTelephoneNumber(patientTO.getTelephoneNumber());
        patientEntity.setEmail(patientTO.getEmail());
        patientEntity.setPatientNumber(patientTO.getPatientNumber());
        patientEntity.setDateOfBirth(patientTO.getDateOfBirth());
        patientEntity.setGender(patientTO.getGender());

        patientEntity.setAddress(patientTO.getAddress());
        patientEntity.setVisits(patientTO.getVisits());

        return patientEntity;
    }
}
