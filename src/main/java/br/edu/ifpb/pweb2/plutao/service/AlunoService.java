package br.edu.ifpb.pweb2.plutao.service;

import br.edu.ifpb.pweb2.plutao.model.Aluno;
import br.edu.ifpb.pweb2.plutao.model.Processo;
import br.edu.ifpb.pweb2.plutao.repository.AlunoRepository;
import br.edu.ifpb.pweb2.plutao.repository.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AlunoService implements Service<Aluno, Integer>{

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProcessoRepository processoRepository;

    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    @Override
    public Aluno findById(Integer id) {
        Aluno aluno = null;
        Optional<Aluno> opAluno = alunoRepository.findById(id);
        if(opAluno.isPresent()){
            aluno = opAluno.get();
        }
        return aluno;
    }

    @Override
    public Aluno save(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    @Override
    public void deleteById(Integer id) {
        alunoRepository.deleteById(id);
    }

    public Aluno findByMatricula(String matricula) {
        return alunoRepository.findByMatricula(matricula);
    }

    public List<Processo> consultaProcessos(Integer id){
        return processoRepository.findAllByAlunoId(id);
    }

    public List<Aluno> getAlunosComProcessos(){
        List<Aluno> alunos = new ArrayList<Aluno>();
        for (Aluno aluno : this.alunoRepository.findAll()){
            if(aluno.getProcessos() != null){
                alunos.add(aluno);
            }
        }
        return alunos;
    }

}
