package com.example.Laptop;

import com.example.Laptop.dao.LaptopDAO;
import com.example.Laptop.entity.Laptop;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class LaptopApplication {

    @Bean
    public CommandLineRunner commandLineRunner(LaptopDAO laptop)
    {
        return runner->
        {
            Scanner sc = new Scanner(System.in);

            while (true)
            {
                System.out.println("1. Add Laptop");
                System.out.println("2. Get the Details of Laptop");
                System.out.println("3. Update the Laptop Processor");
                System.out.println("4. Delete the Data by ID");
                System.out.println("5. Delete the Data by IMEI Number");
                System.out.println("6. Display all the Data");
                System.out.println("7. Exit Application");
                System.out.println("Enter Your Choice: ");
                int ch = sc.nextInt();
                sc.nextLine();

                if(ch == 7)
                {
                    break;
                }
                switch (ch)
                {
                    case 1:
                        System.out.println("Enter the No. of Laptop that you want to Add");
                        int n = sc.nextInt();
                        sc.nextLine();

                        for(int i=0;i<n;i++)
                        {
                            System.out.println("Enter the Laptop Brand");
                            String brand = sc.nextLine();

                            System.out.println("Enter the Laptop Model");
                            String model = sc.nextLine();

                            System.out.println("Enter the Laptop IMEI NO.");
                            String imeiNo = sc.nextLine();

                            System.out.println("Enter the Laptop Processor");
                            String processor = sc.nextLine();

                            System.out.println("Enter the Laptop Price");
                            double price = sc.nextDouble();
                            sc.nextLine();

                            laptop.save(new Laptop(brand,model,imeiNo,processor,price));
                            System.out.println("Laptop Saves Successfully");
                        }
                        break;

                    case 2:
                        System.out.println("Enter the Id to find the Laptop");
                        Laptop findById = laptop.findById(sc.nextInt());

                        if (findById != null)
                        {
                            System.out.println(findById);
                        }
                        else {
                            System.out.println("Enter valid Laptop ID");
                        }
                        break;

                    case 3:
                        System.out.println("Enter the ID of Laptop that you want to Update");
                        laptop.update(sc.nextInt());
                        break;

                    case 4:
                        System.out.println("Enter the ID that ypu want to Remove");
                        laptop.delete(sc.nextInt());
                        break;

                    case 5:
                        System.out.println("Enter the IMEI No that you want to Remove");
                        laptop.deleteByImei(sc.nextLine());
                        break;

                    case 6:
                        List<Laptop> laptops = laptop.fetchAll();
                        for(Laptop e: laptops)           // For Each Loop
                        {
                            System.out.println(e);
                        }
//                        for(int i=0;i<Laptop.size();i++)   // For Loop
//                        {
//                            System.out.println(laptops.get(i));
//                        }
                        break;

                }
            }



        };
    }

	public static void main(String[] args) {
		SpringApplication.run(LaptopApplication.class, args);
	}

}
