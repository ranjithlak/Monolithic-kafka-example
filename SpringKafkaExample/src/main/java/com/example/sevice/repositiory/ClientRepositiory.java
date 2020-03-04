package com.example.sevice.repositiory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sevice.model.Client;

@Repository
public interface ClientRepositiory  extends JpaRepository<Client,Long> {

}
