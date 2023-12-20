package br.edu.ifpb.pweb2.plutao.service;

import br.edu.ifpb.pweb2.plutao.model.Professor;
import br.edu.ifpb.pweb2.plutao.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProfessorService implements Service<Professor, Integer>{

    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public Page<Professor> findAll(Pageable page) {
        return professorRepository.findAll(page);
    }

    @Override
    public Professor findById(Integer id) {
        Professor conta = null;
        Optional<Professor> opProfessor = professorRepository.findById(id);
        if (opProfessor.isPresent()) {
            conta = opProfessor.get();
        }
        return conta;
    }

    @Override
    public Professor save(Professor professor) {
        return professorRepository.save(professor);
    }

    @Override
    public void deleteById(Integer id) {
        professorRepository.deleteById(id);
    }

    public List<Professor> getProfessores(){
        return this.professorRepository.findAll();
    }
    public List<Professor> getProfessoresComColegiado(){
        List<Professor> professores = new ArrayList<Professor>();
        for (Professor professor : this.professorRepository.findAll()){
            if(professor.getColegiados() != null){
                professores.add(professor);
            }
        }
        return professores;
    }

    public List<Professor> getProfessoresComProcessos(){
        List<Professor> professores = new ArrayList<Professor>();
        for (Professor professor : this.professorRepository.findAll()){
            if(professor.getProcessos().size() > 0){
                professores.add(professor);
            }
        }
        return professores;
    }

}
