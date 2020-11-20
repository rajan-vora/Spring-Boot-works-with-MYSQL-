package net.vguides.spapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.vguides.spapi.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
