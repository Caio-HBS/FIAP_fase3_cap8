package br.com.fiap.upenergy.repository;

import br.com.fiap.upenergy.model.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {

    @Query("SELECT e FROM Equipamento e WHERE e.usuario.usuarioId = :id")
    List<Equipamento> findByUsuarioId(Long id);

}
