package br.com.fiap.upenergy.repository;

import br.com.fiap.upenergy.model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {
}
