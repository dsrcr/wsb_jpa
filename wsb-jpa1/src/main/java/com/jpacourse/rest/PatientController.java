package com.jpacourse.rest;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.rest.exception.EntityNotFoundException;
import com.jpacourse.service.PatientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing patient-related operations.
 * This controller provides endpoints for interacting with patient data through HTTP requests.
 * It delegates the business logic to the {@link PatientService} to retrieve patient data.
 */
@RestController
public class PatientController {

    private final PatientService patientService;

    /**
     * Constructs an instance of {@link PatientController}.
     * This constructor is used to inject the {@link PatientService} dependency into the controller.
     *
     * @param patientService the {@link PatientService} used to retrieve patient data
     */
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    /**
     * Retrieves a patient by their unique identifier (ID).
     * This method handles GET requests to retrieve patient data from the service layer. If the patient is found,
     * it is returned; otherwise, an {@link EntityNotFoundException} is thrown.
     *
     * @param id the unique identifier of the patient to be retrieved
     * @return the {@link PatientTO} object containing the patient's data
     * @throws EntityNotFoundException if no patient is found with the provided ID
     */
    @GetMapping("/patient/{id}")
    PatientTO findById(@PathVariable final Long id) {
        final PatientTO patient = patientService.findById(id);
        if (patient != null) return patient;

        throw new EntityNotFoundException(id);
    }
}
