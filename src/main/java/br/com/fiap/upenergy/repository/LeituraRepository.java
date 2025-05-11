package br.com.fiap.upenergy.repository;

import br.com.fiap.upenergy.model.Leitura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeituraRepository extends JpaRepository<Leitura, Long> {

    @Query("SELECT e FROM Leitura e WHERE e.sensor.sensorId = :id")
    List<Leitura> findBySensorId(Long id);

}
