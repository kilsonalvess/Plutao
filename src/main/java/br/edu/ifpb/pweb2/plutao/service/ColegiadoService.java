package br.edu.ifpb.pweb2.plutao.service;


import br.edu.ifpb.pweb2.plutao.model.Colegiado;
import br.edu.ifpb.pweb2.plutao.repository.ColegiadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ColegiadoService implements Service<Colegiado, Integer>{

    @Autowired
    private ColegiadoRepository colegiadoRepository;

    @Override
    public List<Colegiado> findAll() {
        return colegiadoRepository.findAll();
    }

    @Override
    public Colegiado findById(Integer id) {
        Colegiado colegiado = null;
        Optional<Colegiado> opColegiado = colegiadoRepository.findById(id);
        if (opColegiado.isPresent()) {
            colegiado = opColegiado.get();
        }
        return colegiado;
    }

    @Override
    public Colegiado save(Colegiado colegiado) {
        return colegiadoRepository.save(colegiado);
    }

    @Override
    public void deleteById(Integer id) {
        colegiadoRepository.deleteById(id);
    }
}
