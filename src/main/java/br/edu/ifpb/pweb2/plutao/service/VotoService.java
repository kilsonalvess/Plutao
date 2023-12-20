package br.edu.ifpb.pweb2.plutao.service;

import br.edu.ifpb.pweb2.plutao.model.Voto;
import br.edu.ifpb.pweb2.plutao.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class VotoService implements Service<Voto, Integer>{

    @Autowired
    private VotoRepository votoRepository;
    @Override
    public Page<Voto> findAll(Pageable page) {
        return votoRepository.findAll(page);
    }

    @Override
    public Voto findById(Integer id) {
        Voto voto = null;
        Optional<Voto> opVoto = votoRepository.findById(id);
        if (opVoto.isPresent()) {
            voto = opVoto.get();
        }
        return voto;
    }

    @Override
    public Voto save(Voto voto) {
        return votoRepository.save(voto);
    }

    @Override
    public void deleteById(Integer id) {
        deleteById(id);
    }
}
