package com.tallerweb.sda.service;

import com.tallerweb.sda.model.Beneficiario;
import com.tallerweb.sda.repository.BeneficiarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeneficiarioService {
    @Autowired
    private BeneficiarioRepository beneficiarioRepository;

    public List<Beneficiario> getAllBeneficiarios() {
        return beneficiarioRepository.findAll();
    }

    public Optional<Beneficiario> getBeneficiarioById(Long id) {
        return beneficiarioRepository.findById(id);
    }

    public Beneficiario saveBeneficiario(Beneficiario beneficiario) {
        return beneficiarioRepository.save(beneficiario);
    }

    public void deleteBeneficiario(Long id) {
        beneficiarioRepository.deleteById(id);
    }
}