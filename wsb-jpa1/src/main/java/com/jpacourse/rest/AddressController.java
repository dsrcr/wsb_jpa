package com.jpacourse.rest;

import com.jpacourse.dto.AddressTO;
import com.jpacourse.rest.exception.EntityNotFoundException;
import com.jpacourse.service.AddressService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing address-related operations.
 * This controller provides endpoints for interacting with address data through HTTP requests.
 * It delegates business logic to the {@link AddressService} to retrieve address data.
 */
@RestController
public class AddressController {

    private final AddressService addressService;

    /**
     * Constructs an instance of {@link AddressController}.
     * This constructor is used to inject the {@link AddressService} dependency into the controller.
     *
     * @param addressService the {@link AddressService} used to retrieve address data
     */
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    /**
     * Retrieves an address by its unique identifier (ID).
     * This method handles GET requests to retrieve address data from the service layer. If the address is found,
     * it is returned; otherwise, an {@link EntityNotFoundException} is thrown.
     *
     * @param id the unique identifier of the address to be retrieved
     * @return the {@link AddressTO} object containing the address data
     * @throws EntityNotFoundException if no address is found with the provided ID
     */
    @GetMapping("/address/{id}")
    AddressTO findById(@PathVariable final Long id) {
        final AddressTO address = addressService.findById(id);
        if (address != null) return address;

        throw new EntityNotFoundException(id);
    }
}
