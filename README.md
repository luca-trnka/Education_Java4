# Offer Management API

## Data Model

### Offer
- `id: Long`
- `description: String`
- `status: OfferStatus`
- `customer: Customer`
- `supplier: Supplier`
- `workers: List<Worker>`

### Customer
- `id: Long`
- `name: String`
- `email: String`
- `offers: List<Offer>`

### Supplier
- `id: Long`
- `name: String`
- `email: String`
- `offers: List<Offer>`
- `workers: List<Worker>`

### Worker
- `id: Long`
- `name: String`
- `email: String`
- `offers: List<Offer>`
- `supplier: Supplier`

## REST API Specification

### /offers
- `POST`: Create a new offer
- `GET`: Get all offers

### /offers/{offerId}
- `GET`: Get an offer by ID
- `PUT`: Update an offer
- `DELETE`: Delete an offer

### /offers/{offerId}/status
- `GET`: Get the status of an offer
- `PUT`: Update the status of an offer

### /offers/{offerId}/workers
- `POST`: Add a worker to an offer
- `DELETE`: Remove a worker from an offer

### /offers/{offerId}/workers/{workerId}
- `DELETE`: Remove a specific worker from an offer

### /customers
- `POST`: Create a new customer
- `GET`: Get all customers

### /customers/{customerId}
- `GET`: Get a customer by ID
- `PUT`: Update a customer
- `DELETE`: Delete a customer

### /suppliers
- `POST`: Create a new supplier
- `GET`: Get all suppliers

### /suppliers/{supplierId}
- `GET`: Get a supplier by ID
- `PUT`: Update a supplier
- `DELETE`: Delete a supplier

### /workers
- `POST`: Create a new worker
- `GET`: Get all workers

### /workers/{workerId}
- `GET`: Get a worker by ID
- `PUT`: Update a worker
- `DELETE`: Delete a worker
