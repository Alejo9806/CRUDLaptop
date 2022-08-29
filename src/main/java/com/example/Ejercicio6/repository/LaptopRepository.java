package com.example.Ejercicio6.repository;

import com.example.Ejercicio6.entitie.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {

}
