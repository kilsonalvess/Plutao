package br.edu.ifpb.pweb2.plutao.service;


import br.edu.ifpb.pweb2.plutao.model.Colegiado;
import br.edu.ifpb.pweb2.plutao.model.Coordenador;
import br.edu.ifpb.pweb2.plutao.model.Professor;
import br.edu.ifpb.pweb2.plutao.repository.ColegiadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ColegiadoService implements Service<Colegiado, Integer> {

    @Autowired
    private ColegiadoRepository colegiadoRepository;

    public List<Colegiado> getColegiados(){
        return this.colegiadoRepository.findAll();
    }

    public Colegiado getColegiadoPorId(Integer id){
        return this.colegiadoRepository.findById(id).orElse(null);
    }

    public Colegiado getColegiadoPorCoordenador(Coordenador coordenador){
        return this.colegiadoRepository.findByCoordenador(coordenador).get(0);
    }

    public Colegiado salvarColegiado(Colegiado colegiado){
        for(Professor professor : colegiado.getMembros() ){
            professor.adicionarColegiado(colegiado);
        }
        colegiado.setDataInicio(new Date());
        return this.colegiadoRepository.save(colegiado);
    }

    public void deletarColegiado(Integer id){
        this.colegiadoRepository.deleteById(id);
    }

    @Override
    public Page<Colegiado> findAll(Pageable page) {
        return colegiadoRepository.findAll(page);
    }

    @Override
    public Colegiado findById(Integer integer) {
        return null;
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
