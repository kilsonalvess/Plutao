package br.edu.ifpb.pweb2.plutao.service;

import br.edu.ifpb.pweb2.plutao.model.Assunto;
import br.edu.ifpb.pweb2.plutao.repository.AssuntoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AssuntoService implements Service<Assunto, Integer>{

    @Autowired
    private AssuntoRepository assuntoRepository;
    @Override
    public Page<Assunto> findAll(Pageable page) {
        return assuntoRepository.findAll(page);
    }

    @Override
    public Assunto findById(Integer id) {
        Assunto assunto = null;
        Optional<Assunto> opAssunto = assuntoRepository.findById(id);
        if (opAssunto.isPresent()) {
            assunto = opAssunto.get();
        }
        return assunto;
    }

    @Override
    public Assunto save(Assunto assunto) {
        return assuntoRepository.save(assunto);
    }

    @Override
    public void deleteById(Integer id) {
        assuntoRepository.deleteById(id);
    }
}
