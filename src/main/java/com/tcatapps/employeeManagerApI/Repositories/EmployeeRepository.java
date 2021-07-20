package com.tcatapps.employeeManagerApI.Repositories;


import com.tcatapps.employeeManagerApI.Models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //These are called Query methods. They were created by Spring using the naming convention
    void deleteEmployeeById(Long id);
    Optional<Employee> findEmployeeById(Long id);
}
