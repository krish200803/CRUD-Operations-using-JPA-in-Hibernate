package com.example.Employee.dao;

import com.example.Employee.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    public void save(Employee theEmployee);

    public Employee findByID(int id);

    public Employee findByPhoneNo(long phoneno);

    public List<Employee> fetchAll();

    public void updateSalary(int id);

    public void updateDesig(int id);

    public void delete(int id);

    public void deleteByPhone(long phone);

    public void deleteBySalary(double salary);
}
