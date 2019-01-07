package com.audibene.repository;

import com.audibene.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ClientRepository extends JpaRepository<Client, Long> {
}
