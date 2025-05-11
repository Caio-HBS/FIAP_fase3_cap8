package br.com.fiap.upenergy.repository;

import br.com.fiap.upenergy.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SensorRepository extends JpaRepository<Sensor, Long> {

    @Query("SELECT e FROM Sensor e WHERE e.equipamentoId.equipamentoId = :id")
    List<Sensor> findByEquipamentoId(Long id);

}
