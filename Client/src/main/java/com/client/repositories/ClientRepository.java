package com.client.repositories;

import com.client.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// JpaRepository fournit directement les opérations CRUD
public interface ClientRepository extends JpaRepository<Client, Long> {

}

