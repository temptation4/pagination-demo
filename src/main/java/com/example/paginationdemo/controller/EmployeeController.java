package com.example.paginationdemo.controller;

import com.example.paginationdemo.entity.Employee;
import com.example.paginationdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for Employee operations.
 *
 * Delegates pagination logic to EmployeeService.
 */
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    /**
     * API to get paginated employees.
     * Example: /employees?page=0&size=5
     *
     * @param page (optional) default 0
     * @param size (optional) default 5
     * @param sortBy (optional) default id
     * @param direction (optional) default desc
     * @return Page<Employee>
     */
    @GetMapping("/employees")
    public Page<Employee> getEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        return service.getEmployees(page,size,sortBy,direction);
    }
}
