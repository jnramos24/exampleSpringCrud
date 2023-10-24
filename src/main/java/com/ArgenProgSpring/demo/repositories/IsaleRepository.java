package com.ArgenProgSpring.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ArgenProgSpring.demo.models.Sale;

@Repository
public interface IsaleRepository extends JpaRepository<Sale, Integer>{

}
