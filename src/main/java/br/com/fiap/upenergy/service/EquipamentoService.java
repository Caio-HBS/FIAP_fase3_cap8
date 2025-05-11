package br.com.fiap.upenergy.service;

import br.com.fiap.upenergy.dto.EquipamentoCadastroDTO;
import br.com.fiap.upenergy.dto.EquipamentoExibicaoDTO;
import br.com.fiap.upenergy.exception.RecursoNaoEncontradoException;
import br.com.fiap.upenergy.model.Equipamento;
import br.com.fiap.upenergy.model.Usuario;
import br.com.fiap.upenergy.repository.EquipamentoRepository;
import br.com.fiap.upenergy.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipamentoService {

    private final EquipamentoRepository equipamentoRepository;
    private final UsuarioRepository usuarioRepository;

    public EquipamentoService(EquipamentoRepository equipamentoRepository, UsuarioRepository usuarioRepository) {
        this.equipamentoRepository = equipamentoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Equipamento cadastrarEquipamento(EquipamentoCadastroDTO equipamentoCadastroDTO) {

        Optional<Usuario> procurarUsuario = usuarioRepository.findById(equipamentoCadastroDTO.getUsuarioId());

        if (!procurarUsuario.isPresent()) {
            throw new RecursoNaoEncontradoException("Usuario não encontrado.");
        }

        Equipamento novoEquipamento = new Equipamento();
        BeanUtils.copyProperties(equipamentoCadastroDTO, novoEquipamento);
        novoEquipamento.setUsuario(procurarUsuario.get());

        return equipamentoRepository.save(novoEquipamento);
    }

    public List<EquipamentoExibicaoDTO> listarEquipamentos(Long usuarioId) {
        return equipamentoRepository.findByUsuarioId(usuarioId).stream().map(EquipamentoExibicaoDTO::new).toList();
    }

}
