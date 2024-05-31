package org.example.controllers;

import org.example.dtos.CustomerDTO;
import org.example.exceptions.ResourceNotFoundException;
import org.example.models.Customer;
import org.example.models.Offer;
import org.example.services.CustomerService;
import org.example.services.OfferService;
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
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
    }

    @PostMapping
    public CustomerDTO createCustomer(@RequestBody CustomerDTO customerDTO) {
        List<Offer> offers = customerDTO.getOfferIds().stream()
                .map(offerId -> offerService.getOfferById(offerId.intValue()).orElseThrow(() -> new ResourceNotFoundException("Offer not found")))
                .collect(Collectors.toList());
        Customer customer = customerDTO.toEntity();
        customerService.createCustomer(customer);
        return CustomerDTO.fromEntity(customer);
    }

    @PutMapping("/{id}")
    public void updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        List<Offer> offers = customerDTO.getOfferIds().stream()
                .map(offerId -> offerService.getOfferById(offerId.intValue()).orElseThrow(() -> new ResourceNotFoundException("Offer not found")))
                .collect(Collectors.toList());
        Customer customer = customerDTO.toEntity();
        customer.setId(id);
        customerService.updateCustomer(customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(Math.toIntExact(id));
    }
}
