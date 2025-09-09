package com.example.Laptop.dao.daoImpl;

import com.example.Laptop.dao.LaptopDAO;
import com.example.Laptop.entity.Laptop;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Scanner;

@Repository
public class LaptopDAOImpl implements LaptopDAO {

    private final EntityManager theManager;

    @Autowired
    public LaptopDAOImpl(EntityManager theManager)
    {
        this.theManager = theManager;
    }

    @Override
    @Transactional
    public void save(Laptop theLaptop) {
        theManager.persist(theLaptop);
    }

    @Override
    public Laptop findById(int id) {
        return theManager.find(Laptop.class,id);
    }

    @Override
    public List<Laptop> fetchAll() {
        Query query = theManager.createQuery("Select l from Laptop l");
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(int id) {
        Scanner sc = new Scanner(System.in);
        Laptop updateLaptop = theManager.find(Laptop.class,id);

        if(updateLaptop != null)
        {
            System.out.println("Enter the new Laptop Processor");
            updateLaptop.setProcessor(sc.nextLine());
        }
        else {
            System.out.println("No such Laptop Exists");
        }
    }

    @Override
    @Transactional
    public void delete(int id) {
        Laptop l = findById(id);

        if(l != null)
        {
            theManager.remove(l);
            System.out.println("Laptop Removed Successfully");
        }
        else {
            System.out.println("Laptop not Found");
        }

    }

    @Override
    @Transactional
    public void deleteByImei(String imeiNo) {

        List<Laptop> result = theManager.createQuery("select l from Laptop l where l.imeiNo = :imeiNo",
                Laptop.class).setParameter("imeiNo",imeiNo).getResultList();

//        Query query = theManager.createQuery("select l from Laptop l where l.imeiNo = : imeiNo");
//        query.setParameter("imeiNo", imeiNo);
//        Laptop l = (Laptop) query.getSingleResult();

        if(!result.isEmpty())
        {
            Laptop l = result.get(0);
            theManager.remove(l);
            System.out.println("Removed Successfully");
        }
        else
        {
            System.out.println("Laptop Not Found");
        }

    }

}
