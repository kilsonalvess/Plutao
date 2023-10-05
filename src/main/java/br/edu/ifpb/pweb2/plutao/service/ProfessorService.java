package br.edu.ifpb.pweb2.plutao.service;

import br.edu.ifpb.pweb2.plutao.model.Professor;
import br.edu.ifpb.pweb2.plutao.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProfessorService implements Service<Professor, Integer>{

    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public List<Professor> findAll() {
        return professorRepository.findAll();
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
}
