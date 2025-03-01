package com.tallerweb.sda.repository;

import com.tallerweb.sda.model.Donante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonanteRepository extends JpaRepository<Donante, Long> {
    Donante findByEmail(String email);
}
