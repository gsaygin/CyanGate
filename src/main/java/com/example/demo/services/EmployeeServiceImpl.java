package com.example.demo.services;

import com.example.demo.models.Employee;
import com.example.demo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    //@Autowired
    private EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Employee> findAll() {
        return (List<Employee>)repository.findAll();
    }

    @Override
    public Employee insert(Employee p) {
        return repository.save(p);
    }

    @Override
    public boolean delete(long id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Employee findById(long id) {
        Optional<Employee> result = repository.findById(id);
        return result.orElse(null);
    }

    @Override
    public boolean update(Employee e) {
        // Check the existence of the employee, throws ResponseStatusException (404)
        Employee employeeToUpdate = getEmployeeIfExists(e.getId());

        employeeToUpdate.setName(e.getName());
        employeeToUpdate.setSurname(e.getSurname());
        repository.save(employeeToUpdate);
        return true;
    }

    private Employee getEmployeeIfExists(long employeeId) throws ResponseStatusException {
        Optional<Employee> employeeDb = repository.findById(employeeId);
        if (employeeDb.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Could not find player with ID: " + employeeId
            );
        }
        return employeeDb.get();
    }
}
