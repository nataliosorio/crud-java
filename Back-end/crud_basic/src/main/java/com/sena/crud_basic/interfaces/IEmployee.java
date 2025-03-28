package com.sena.crud_basic.interfaces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.crud_basic.model.Employee;
@Repository
public interface IEmployee extends JpaRepository<Employee, Integer> {

}
