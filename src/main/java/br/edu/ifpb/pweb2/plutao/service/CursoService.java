package br.edu.ifpb.pweb2.plutao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.plutao.model.Curso;
import br.edu.ifpb.pweb2.plutao.repository.CursoRepository;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> getCursos(){
        return this.cursoRepository.findAll();
    }

    public Curso getCursoPorId(Long id){
        return this.cursoRepository.findById(id).orElse(null);
    }

    public Curso salvarCurso(Curso assunto){
        return this.cursoRepository.save(assunto);
    }

    public void deletarCurso(Long id){
        this.cursoRepository.deleteById(id);
    }
}