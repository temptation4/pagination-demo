package com.example.paginationdemo.service;

import com.example.paginationdemo.entity.Employee;
import com.example.paginationdemo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Service layer for Employee entity.
 *
 * This class contains business logic for fetching employees with pagination.
 * We use Spring Data JPA's Pageable and PageRequest to efficiently fetch
 * chunks of data instead of loading everything at once.
 */

/******************************************************************/
/* Spring Data JPA provides built-in support using:
	•	Pageable → Interface for pagination info (page number, size, sort)
	•	PageRequest.of(page, size, sort) → Implementation of Pageable
	•	Page<T> → Represents one page of data (records + metadata like total pages, total elements, etc.)

	In Spring Data JPA, Sort is a helper class that defines the ordering of query results.
It’s part of the org.springframework.data.domain package.
You can pass it to repository methods like findAll(Sort sort) or use it with pagination (PageRequest).
It supports ascending/descending order and multiple fields.
	*/
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    /**
     * Fetch a paginated and sorted list of employees.
     *
     * @param page      The page number (0-based index). Default is 0.
     * @param size      The number of records per page. Default is 20.
     * @param sortBy    The field to sort by (e.g., "id", "name", "department"). Default is "id".
     * @param direction The sort direction: "asc" for ascending, "desc" for descending. Default is "asc".
     * @return Page<Employee> - contains employees, total pages, total elements, and pagination metadata.
     */
    public Page<Employee> getEmployees(int page, int size,String sortBy, String direction) {
        // Create Pageable object with page number and size

        Sort sort = "desc".equalsIgnoreCase(direction) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size,sort);

        // Fetch data from repository
        return repository.findAll(pageable);
    }
}
