package org.example.services;

import org.example.dao.entities.Customer;
import org.example.dao.repositories.CustomerRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class CustomerService {
    private final CustomerRepository customerRepository = new CustomerRepository();

    public void saveCustomer() {
        customerRepository.saveCustomer(buildCustomer());
        System.out.println("Customer saved successfully!");
    }

    public List<Customer> fetchAllCustomers() {
        return customerRepository.fetchAllCustomers();
    }

    public Customer searchCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the customer ID: ");
        Long id = scanner.nextLong();
        return customerRepository.searchCustomer(id);
    }

    public void updateCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the customer ID: ");
        Long id = scanner.nextLong();
        System.out.println("Customer found, please enter the customer info!");
        customerRepository.updateCustomer(id, buildCustomer());
        System.out.println("Customer updated successfully!");
    }

    public void removeCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the customer ID: ");
        Long id = scanner.nextLong();
        customerRepository.removeCustomer(id);
        System.out.println("Customer removed successfully!");
    }

    private Customer buildCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Name: ");
        String name = scanner.next();
        System.out.print("Surname: ");
        String surname = scanner.next();
        System.out.print("Father name: ");
        String fatherName = scanner.next();
        System.out.print("Birth date(year-mont-day): ");
        String birthDate = scanner.next();
        return Customer.builder()
                .name(name)
                .surname(surname)
                .fatherName(fatherName)
                .birthDate(LocalDate.parse(birthDate))
                .createdAt(LocalDate.now())
                .isActive(true)
                .build();
    }
}