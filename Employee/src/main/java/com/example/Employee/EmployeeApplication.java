package com.example.Employee;

import com.example.Employee.dao.EmployeeDAO;
import com.example.Employee.entity.Employee;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class EmployeeApplication {

    @Bean
    public CommandLineRunner commandLineRunner(EmployeeDAO emp)
    {
        return runner->
        {
            Scanner sc = new Scanner(System.in);

            while (true)
            {
                System.out.println("1. Add the Employees");
                System.out.println("2. Find the Employee by ID");
                System.out.println("3. Find the Employee by Phone Number");
                System.out.println("4. Display all the Details of Employee");
                System.out.println("5. Update the Salary of Employee");
                System.out.println("6. Update the Designation of Employee");
                System.out.println("7. Delete the Employee by ID");
                System.out.println("8. Delete the Employee by Phone Number");
                System.out.println("9. Delete the Employee by Salary");
                System.out.println("10. Exit Application");

                System.out.println("Enter Your Choice");
                int ch = sc.nextInt();

                if(ch == 10)
                {
                    break;
                }
                switch (ch)
                {
                    case 1:
                        System.out.println("Enter the No. OF Employee that you want to Add");
                        int n = sc.nextInt();
                        sc.nextLine();

                        for(int i=0;i<n;i++)
                        {
                            System.out.println("Enter the First Name");
                            String firstName = sc.nextLine();

                            System.out.println("Enter the Last Name");
                            String lastName = sc.nextLine();

                            System.out.println("Enter Employee Contact");
                            long contact = sc.nextLong();

                            System.out.println("Enter Employee Paisa");
                            double salary = sc.nextDouble();
                            sc.nextLine();

                            System.out.println("Enter Employee Designation");
                            String designation = sc.nextLine();


                            emp.save(new Employee(firstName, lastName, contact, salary, designation));
                            System.out.println("Employee Saved Successfully");
                        }
                        break;

                    case 2:
                        System.out.println("Enter the Employee ID");
                        Employee findEmp = emp.findByID(sc.nextInt());

                        if(findEmp!=null)
                        {
                            System.out.println(findEmp.toString());
                        }
                        else {
                            System.out.println("Enter Valid Employee ID");
                        }
                        break;

                    case 3:
                        System.out.println("Enter the Employee PhoneNO");
                        Employee findByphone = emp.findByPhoneNo(sc.nextLong());

                        if(findByphone != null)
                        {
                            System.out.println(findByphone.toString());
                        }
                        else {
                            System.out.println("Employee not present with this phone no");
                        }
                        break;

                    case 4:
                        List<Employee> employees = emp.fetchAll();
                        for(Employee e: employees)           // For Each Loop
                        {
                            System.out.println(e);
                        }
//                        for(int i=0;i<employees.size();i++)   // For Loop
//                        {
//                            System.out.println(employees.get(i));
//                        }
                        break;

                    case 5:
                        System.out.println("Enter the Id Of Employee to Update");
                        emp.updateSalary(sc.nextInt());
                        break;

                    case 6:
                        System.out.println("Enter the Id Of Employee to Update");
                        emp.updateDesig(sc.nextInt());
                        break;

                    case 7:
                        System.out.println("Enter Employee ID to Delete Employee: ");
                        emp.delete(sc.nextInt());
                        break;

                    case 8:
                        System.out.println("Enter the Phone number to delete employee");
                        emp.deleteByPhone(sc.nextLong());
                        break;

                    case 9:
                        System.out.println("Enter the Salary that you want to delete");
                        emp.deleteBySalary(sc.nextDouble());
                        break;
                }
            }

        };
    }

	public static void main(String[] args) {

        SpringApplication.run(EmployeeApplication.class, args);
	}

}
