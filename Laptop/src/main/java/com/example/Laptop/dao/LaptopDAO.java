package com.example.Laptop.dao;

import com.example.Laptop.entity.Laptop;

import java.util.List;

public interface LaptopDAO {

    public void save(Laptop theLaptop);

    public Laptop findById(int id);

    public List<Laptop> fetchAll();

    public void update(int id); // update processor

    public void delete(int id);

    public void deleteByImei(String imei);
}
