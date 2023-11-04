package br.edu.ifpb.pweb2.plutao.service;

import br.edu.ifpb.pweb2.plutao.enums.StatusProcesso;
import br.edu.ifpb.pweb2.plutao.model.Aluno;
import br.edu.ifpb.pweb2.plutao.model.Assunto;
import br.edu.ifpb.pweb2.plutao.model.Processo;
import br.edu.ifpb.pweb2.plutao.model.Professor;
import br.edu.ifpb.pweb2.plutao.repository.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class ProcessoService implements Service<Processo, Integer>{

    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private AlunoService alunoService;

    @Override
    public List<Processo> findAll() {
        return processoRepository.findAll();
    }

    @Override
    public Processo findById(Integer id) {
        Processo processo = null;
        Optional<Processo> opProcesso = processoRepository.findById(id);
        if (opProcesso.isPresent()) {
            processo = opProcesso.get();
        }
        return processo;
    }

    @Override
    public Processo save(Processo processo) {
        return processoRepository.save(processo);
    }

    @Override
    public void deleteById(Integer id) {
        processoRepository.deleteById(id);
    }

    public List<Processo> consultarProcessosPorStatusEAluno(boolean status, Integer idAluno) {
        Aluno aluno = alunoService.findById(idAluno);
        return processoRepository.findByParecerAndAluno(status, aluno);
    }

    public List<Processo> consultarProcessosPorProfessor(Integer idProfessor, boolean isCoordenador) {
        Professor professor = professorService.findById(idProfessor);
        if(professor.isCoordenador() && !isCoordenador){
            return new ArrayList<>();
        }else {
            return processoRepository.findByRelator(professor);
        }
    }

//    public List{
//        return lista de alunos do repositório,
//                criar for para varrer a lista,
//                if para saber se é o assunto ou status
//    }

    public List<Processo> getProcessosPorAluno(Aluno aluno){
        return this.processoRepository.findByAluno(aluno);
    }

    public Processo salvarProcesso(Processo processo){
        processo.getAluno().adicionarProcesso(processo);
        processo.setEstado(StatusProcesso.CRIADO);
        processo.setDataCriacao(new Date());
        processo.setNumero(""+new Date().getTime());
        return this.processoRepository.save(processo);
    }
}
