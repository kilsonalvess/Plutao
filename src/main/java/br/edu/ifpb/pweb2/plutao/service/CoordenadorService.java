package br.edu.ifpb.pweb2.plutao.service;

import br.edu.ifpb.pweb2.plutao.model.Aluno;
import br.edu.ifpb.pweb2.plutao.model.Assunto;
import br.edu.ifpb.pweb2.plutao.model.Coordenador;
import br.edu.ifpb.pweb2.plutao.repository.CoordenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CoordenadorService implements Service<Coordenador, Integer> {

    @Autowired
    private CoordenadorRepository coordenadorRepository;

    @Override
    public Page<Coordenador> findAll(Pageable page) {
        return coordenadorRepository.findAll(page);
    }

    @Override
    public Coordenador findById(Integer id) {
        Coordenador coordenador = null;
        Optional<Coordenador> optionalCoordenador = coordenadorRepository.findById(id);
        if (optionalCoordenador.isPresent()) {
            coordenador = optionalCoordenador.get();
        }
        return coordenador;
    }

    @Override
    public Coordenador save(Coordenador coordenador) {
        return coordenadorRepository.save(coordenador);
    }

    @Override
    public void deleteById(Integer id) {
        coordenadorRepository.deleteById(id);
    }

    public List<Coordenador> getCoordenadores(){
        return this.coordenadorRepository.findAll();
    }

    public Coordenador getCoordenadorPorId(Integer id){
        return this.coordenadorRepository.findById(id).orElse(null);
    }

}