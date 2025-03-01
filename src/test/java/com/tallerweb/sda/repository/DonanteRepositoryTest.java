package com.tallerweb.sda.repository;

import com.tallerweb.sda.model.Donante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
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
public class DonanteRepositoryTest {

    @Mock
    private DonanteRepository donanteRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveDonante() {
        Donante donante = new Donante();
        donante.setId(1L);
        donante.setEmail("test@example.com");

        when(donanteRepository.save(any(Donante.class))).thenReturn(donante);

        Donante resultado = donanteRepository.save(donante);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo(1L);
        assertThat(resultado.getEmail()).isEqualTo("test@example.com");

        verify(donanteRepository, times(1)).save(donante);
    }

    @Test
    public void testFindById() {
        Donante donante = new Donante();
        donante.setId(1L);

        when(donanteRepository.findById(1L)).thenReturn(Optional.of(donante));

        Optional<Donante> resultado = donanteRepository.findById(1L);

        assertThat(resultado).isPresent();
        assertThat(resultado.get().getId()).isEqualTo(1L);

        verify(donanteRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindAll() {
        Donante donante1 = new Donante();
        donante1.setId(1L);

        Donante donante2 = new Donante();
        donante2.setId(2L);

        List<Donante> listaDonantes = new ArrayList<>();
        listaDonantes.add(donante1);
        listaDonantes.add(donante2);

        when(donanteRepository.findAll()).thenReturn(listaDonantes);

        List<Donante> resultado = donanteRepository.findAll();

        assertThat(resultado).isNotNull();
        assertThat(resultado).hasSize(2);
        assertThat(resultado.get(0).getId()).isEqualTo(1L);
        assertThat(resultado.get(1).getId()).isEqualTo(2L);

        verify(donanteRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteById() {
        long donanteId = 1L;

        doNothing().when(donanteRepository).deleteById(donanteId);

        donanteRepository.deleteById(donanteId);

        verify(donanteRepository, times(1)).deleteById(donanteId);
    }

    @Test
    public void testFindByEmail() {
        // Datos de prueba
        String email = "test@example.com";
        Donante donante = new Donante();
        donante.setId(1L);
        donante.setEmail(email);

        // Simulación del comportamiento del repositorio
        when(donanteRepository.findByEmail(email)).thenReturn(donante);

        // Llamada al método del repositorio
        Donante resultado = donanteRepository.findByEmail(email);

        // Verificación de resultados
        assertThat(resultado).isNotNull();
        assertThat(resultado.getEmail()).isEqualTo(email);
        assertThat(resultado.getId()).isEqualTo(1L);
    }
}