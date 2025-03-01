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
public class SolicitudRepositoryTest {

    @Mock
    private SolicitudRepository solicitudRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveSolicitud() {
        Solicitud solicitud = new Solicitud();
        solicitud.setId(1L);

        when(solicitudRepository.save(any(Solicitud.class))).thenReturn(solicitud);

        Solicitud resultado = solicitudRepository.save(solicitud);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo(1L);

        verify(solicitudRepository, times(1)).save(solicitud);
    }

    @Test
    public void testFindById() {
        Solicitud solicitud = new Solicitud();
        solicitud.setId(1L);

        when(solicitudRepository.findById(1L)).thenReturn(Optional.of(solicitud));

        Optional<Solicitud> resultado = solicitudRepository.findById(1L);

        assertThat(resultado).isPresent();
        assertThat(resultado.get().getId()).isEqualTo(1L);

        verify(solicitudRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindAll() {
        Solicitud solicitud1 = new Solicitud();
        solicitud1.setId(1L);

        Solicitud solicitud2 = new Solicitud();
        solicitud2.setId(2L);

        List<Solicitud> listaSolicitudes = new ArrayList<>();
        listaSolicitudes.add(solicitud1);
        listaSolicitudes.add(solicitud2);

        when(solicitudRepository.findAll()).thenReturn(listaSolicitudes);

        List<Solicitud> resultado = solicitudRepository.findAll();

        assertThat(resultado).isNotNull();
        assertThat(resultado).hasSize(2);
        assertThat(resultado.get(0).getId()).isEqualTo(1L);
        assertThat(resultado.get(1).getId()).isEqualTo(2L);

        verify(solicitudRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteById() {
        long solicitudId = 1L;

        doNothing().when(solicitudRepository).deleteById(solicitudId);

        solicitudRepository.deleteById(solicitudId);

        verify(solicitudRepository, times(1)).deleteById(solicitudId);
    }

    @Test
    public void testFindByBeneficiarioId() {
        Long beneficiarioId = 1L;
        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setId(beneficiarioId);

        Solicitud solicitud1 = new Solicitud();
        solicitud1.setId(1L);
        solicitud1.setBeneficiario(beneficiario);

        Solicitud solicitud2 = new Solicitud();
        solicitud2.setId(2L);
        solicitud2.setBeneficiario(beneficiario);

        when(solicitudRepository.findByBeneficiarioId(beneficiarioId)).thenReturn(List.of(solicitud1, solicitud2));

        List<Solicitud> resultado = solicitudRepository.findByBeneficiarioId(beneficiarioId);

        assertThat(resultado).isNotNull();
        assertThat(resultado).hasSize(2);
        assertThat(resultado.get(0).getId()).isEqualTo(1L);
        assertThat(resultado.get(1).getId()).isEqualTo(2L);

        verify(solicitudRepository, times(1)).findByBeneficiarioId(beneficiarioId);
    }
}