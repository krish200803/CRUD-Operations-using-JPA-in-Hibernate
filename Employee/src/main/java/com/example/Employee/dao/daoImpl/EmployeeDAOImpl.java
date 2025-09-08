package com.example.Employee.dao.daoImpl;

import com.example.Employee.dao.EmployeeDAO;
import com.example.Employee.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Scanner;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    private final EntityManager theManager;

    @Autowired
    public EmployeeDAOImpl(EntityManager theManager)
    {
        this.theManager = theManager;
    }

    @Override
    @Transactional
    public void save(Employee theEmployee)
    {
        theManager.persist(theEmployee);
    }

    @Override
    public Employee findByID(int id) {
        return theManager.find(Employee.class,id);
    }

    @Override
    public Employee findByPhoneNo(long phoneno) {
        Query query = theManager.createQuery("select e from Employee e where e.phoneno = :phone");
        query.setParameter("phone",phoneno);
        return (Employee) query.getSingleResult();
    }

    @Override
    public List<Employee> fetchAll() {
        Query query = theManager.createQuery("Select e from Employee e");
        return query.getResultList();
    }

    @Override
    @Transactional
    public void updateSalary(int id) {
        Scanner sc= new Scanner(System.in);
        Employee foundEmployee = theManager.find(Employee.class,id);

        if(foundEmployee == null)
        {
            System.out.println("NO Such Employee Exists to Update");
        }
        else {
            System.out.println("Enter the Employee New Salary");
            foundEmployee.setSalary(sc.nextDouble());

//        Here we had updated the Salary without using the merge() method because
//        If you already did find() in the same transaction → just modify fields → no need for merge()

        }
    }

    @Override
    @Transactional
    public void updateDesig(int id) {                        // use merge method
        Scanner sc= new Scanner(System.in);
        Employee foundEmployee = theManager.find(Employee.class,id);

        if(foundEmployee == null)
        {
            System.out.println("NO Such Employee Exists to Update");
        }
        else {
            System.out.println("Enter the Employee New Designation");
            String newDesig = sc.nextLine();

//        You use merge() only when you already have a detached entity (not managed by the current persistence context),
//        and you want Hibernate to bring it back under management and apply its changes to the database.

            // Detach the entity (optional demonstration)
            theManager.detach(foundEmployee);

            // Set the new value
            foundEmployee.setDesignation(newDesig);

            // Now merge the detached entity back
            Employee mergedEmployee = theManager.merge(foundEmployee);

            System.out.println("Updated Employee: " + mergedEmployee);
        }
    }

    @Override
    @Transactional
    public void delete(int id) {
        Employee removeEmployee = theManager.find(Employee.class, id);

        if (removeEmployee != null) {
            theManager.remove(removeEmployee);
            System.out.println("Remove Succesfully");
        }
        else {
            System.out.println("Employee Not Found");
        }
    }

    @Override
    @Transactional
    public void deleteByPhone(long phoneno) {

        List<Employee> results = theManager.createQuery("SELECT e FROM Employee e WHERE e.phoneno = :phone",
                Employee.class).setParameter("phone", phoneno).getResultList();

        if (!results.isEmpty())
        {
            Employee e = results.get(0);   // get the first result
            theManager.remove(e);
            System.out.println("Removed Successfully");
        }
        else
        {
            System.out.println("Employee Not Found");
        }
    }

    @Override
    @Transactional
    public void deleteBySalary(double salary) {

        List<Employee> results = theManager.createQuery("SELECT e FROM Employee e WHERE e.salary = :salary",
                Employee.class).setParameter("salary", salary).getResultList();

        if (!results.isEmpty())
        {
            Employee e = results.get(0);   // get the first result
            theManager.remove(e);
            System.out.println("Removed Successfully");
        }
        else
        {
            System.out.println("Employee Not Found");
        }

//        this code is used to handle error when an user enter the value that is not present in the table(column).

//------------------------------------------------- OR ----------------------------------------------

//        Code is correct but it doesn't handle the error

//        Query query = theManager.createQuery("select e from Employee e where e.salary = : salary");
//        query.setParameter("salary", salary);
//        Employee e = (Employee) query.getSingleResult();
//
//        if (e != null) {
//            theManager.remove(e);
//            System.out.println("Remove Successfully");
//        }
//        else {
//            System.out.println("Employee Not Found");
//        }
    }

}
