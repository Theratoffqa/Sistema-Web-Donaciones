package com.tallerweb.sda.repository;

import com.tallerweb.sda.model.Donacion;
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
public class DonacionRepositoryTest {

    @Mock
    private DonacionRepository donacionRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveDonacion() {
        Donacion donacion = new Donacion();
        donacion.setId(1L);

        when(donacionRepository.save(any(Donacion.class))).thenReturn(donacion);

        Donacion resultado = donacionRepository.save(donacion);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo(1L);

        verify(donacionRepository, times(1)).save(donacion);
    }

    @Test
    public void testFindById() {
        Donacion donacion = new Donacion();
        donacion.setId(1L);

        when(donacionRepository.findById(1L)).thenReturn(Optional.of(donacion));

        Optional<Donacion> resultado = donacionRepository.findById(1L);

        assertThat(resultado).isPresent();
        assertThat(resultado.get().getId()).isEqualTo(1L);

        verify(donacionRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindAll() {
        Donacion donacion1 = new Donacion();
        donacion1.setId(1L);

        Donacion donacion2 = new Donacion();
        donacion2.setId(2L);

        List<Donacion> listaDonaciones = new ArrayList<>();
        listaDonaciones.add(donacion1);
        listaDonaciones.add(donacion2);

        when(donacionRepository.findAll()).thenReturn(listaDonaciones);

        List<Donacion> resultado = donacionRepository.findAll();

        assertThat(resultado).isNotNull();
        assertThat(resultado).hasSize(2);
        assertThat(resultado.get(0).getId()).isEqualTo(1L);
        assertThat(resultado.get(1).getId()).isEqualTo(2L);

        verify(donacionRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteById() {
        long donacionId = 1L;

        doNothing().when(donacionRepository).deleteById(donacionId);

        donacionRepository.deleteById(donacionId);

        verify(donacionRepository, times(1)).deleteById(donacionId);
    }

    @Test
    public void testFindByDonanteId() {
        Long donanteId = 1L;

        Donacion donacion1 = new Donacion();
        donacion1.setId(1L);

        Donacion donacion2 = new Donacion();
        donacion2.setId(2L);

        List<Donacion> listaDonaciones = new ArrayList<>();
        listaDonaciones.add(donacion1);
        listaDonaciones.add(donacion2);

        when(donacionRepository.findByDonanteId(donanteId)).thenReturn(listaDonaciones);

        List<Donacion> resultado = donacionRepository.findByDonanteId(donanteId);

        assertThat(resultado).isNotNull();
        assertThat(resultado).hasSize(2);
        assertThat(resultado.get(0).getId()).isEqualTo(1L);
        assertThat(resultado.get(1).getId()).isEqualTo(2L);

        verify(donacionRepository, times(1)).findByDonanteId(donanteId);
    }
}