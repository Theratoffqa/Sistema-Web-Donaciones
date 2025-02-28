package com.tallerweb.sda.repository;

import com.tallerweb.sda.model.Donacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonacionRepository extends JpaRepository<Donacion, Long> {
}
