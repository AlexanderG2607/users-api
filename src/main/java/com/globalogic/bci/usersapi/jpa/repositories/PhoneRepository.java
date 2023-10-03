package com.globalogic.bci.usersapi.jpa.repositories;

import com.globalogic.bci.usersapi.jpa.domains.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
}