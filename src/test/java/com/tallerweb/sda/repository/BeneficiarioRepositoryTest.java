package com.tallerweb.sda.repository;

import com.tallerweb.sda.model.Beneficiario;
import com.tallerweb.sda.model.Solicitud;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BeneficiarioRepositoryTest {

    @Mock
    private BeneficiarioRepository beneficiarioRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveBeneficiario() {
        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setId(1L);

        when(beneficiarioRepository.save(any(Beneficiario.class))).thenReturn(beneficiario);

        Beneficiario resultado = beneficiarioRepository.save(beneficiario);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo(1L);

        verify(beneficiarioRepository, times(1)).save(beneficiario);
    }

    @Test
    public void testFindById() {
        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setId(1L);

        when(beneficiarioRepository.findById(1L)).thenReturn(Optional.of(beneficiario));

        Optional<Beneficiario> resultado = beneficiarioRepository.findById(1L);

        assertThat(resultado).isPresent();
        assertThat(resultado.get().getId()).isEqualTo(1L);

        verify(beneficiarioRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindAll() {
        Beneficiario beneficiario1 = new Beneficiario();
        beneficiario1.setId(1L);

        Beneficiario beneficiario2 = new Beneficiario();
        beneficiario2.setId(2L);

        List<Beneficiario> listaBeneficiarios = new ArrayList<>();
        listaBeneficiarios.add(beneficiario1);
        listaBeneficiarios.add(beneficiario2);

        when(beneficiarioRepository.findAll()).thenReturn(listaBeneficiarios);

        List<Beneficiario> resultado = beneficiarioRepository.findAll();

        assertThat(resultado).isNotNull();
        assertThat(resultado).hasSize(2);
        assertThat(resultado.get(0).getId()).isEqualTo(1L);
        assertThat(resultado.get(1).getId()).isEqualTo(2L);

        verify(beneficiarioRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteById() {
        long beneficiarioId = 1L;

        doNothing().when(beneficiarioRepository).deleteById(beneficiarioId);

        beneficiarioRepository.deleteById(beneficiarioId);

        verify(beneficiarioRepository, times(1)).deleteById(beneficiarioId);
    }
}