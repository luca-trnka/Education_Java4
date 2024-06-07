package org.example.controllers;

import jakarta.validation.Valid;
import org.example.dtos.CustomerDTO;
import org.example.exceptions.ResourceNotFoundException;
import org.example.models.Customer;
import org.example.models.Offer;
import org.example.services.CustomerService;
import org.example.services.OfferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final OfferService offerService;

    public CustomerController(CustomerService customerService, OfferService offerService) {
        this.customerService = customerService;
        this.offerService = offerService;
    }

    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers().stream()
                .map(CustomerDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(Math.toIntExact(id))
                .map(CustomerDTO::fromEntity)
                .orElseThrow(() -> new ResourceNotFoundException("Customer with " + id + " not found"));
    }

    @PostMapping
    public CustomerDTO createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        if (customerService.customerExistsByName(customerDTO.getName())) {
            throw new IllegalArgumentException("A customer with this name already exists");
        }
        if (customerService.customerExistsByEmail(customerDTO.getEmail())) {
            throw new IllegalArgumentException("A customer with this email already exists");
        }
        Customer customer = customerDTO.toEntity();
        customerService.createCustomer(customer);
        return CustomerDTO.fromEntity(customer);
    }

    @PutMapping("/{id}")
    public void updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        if (!customerService.customerExists(Math.toIntExact(id))) {
            throw new ResourceNotFoundException("Customer with id " + id + " not found");
        }
        Customer customer = customerDTO.toEntity();
        customer.setId(id);
        customerService.updateCustomer(customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        if (!customerService.customerExists(Math.toIntExact(id))) {
            throw new ResourceNotFoundException("Customer with id " + id + " not found");
        }
        customerService.deleteCustomer(Math.toIntExact(id));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
