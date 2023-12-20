package br.edu.ifpb.pweb2.plutao.service;

import br.edu.ifpb.pweb2.plutao.enums.StatusProcesso;
import br.edu.ifpb.pweb2.plutao.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.edu.ifpb.pweb2.plutao.repository.ProcessoRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class ProcessoService implements Service<Processo, Integer> {
    @Autowired
    private ProcessoRepository processoRepository;

    public List<Processo> getProcessos(){
        return this.processoRepository.findAll();
    }

    public List<Processo> getProcessosPorAluno(Aluno aluno){
        return this.processoRepository.findByAluno(aluno);
    }

    public List<Processo> getProcessosPorProfessor(Professor professor){
        return this.processoRepository.findByRelator(professor);
    }

    public Processo getProcessoPorId(Integer id){
        return this.processoRepository.findById(id).orElse(null);
    }

    public Processo salvarProcesso(Processo processo){
        processo.getAluno().adicionarProcesso(processo);
        processo.setEstado(StatusProcesso.CRIADO);
        processo.setDataCriacao(new Date());
        processo.setNumero(""+new Date().getTime());
        return this.processoRepository.save(processo);
    }

    public Processo atualizarProcesso(Processo processo, Integer id){
        Processo processoAtualizado = this.processoRepository.findById(id).orElse(new Processo());
        processoAtualizado.setJustificativaRelator(processo.getJustificativaRelator());
        processoAtualizado.setDecisaoRelator(processo.getDecisaoRelator());
        return this.processoRepository.save(processoAtualizado);
    }

    public Processo atribuirProcesso(Processo processo,Integer id){
        Processo processoAtualizado = this.processoRepository.findById(id).orElse(new Processo());
        processoAtualizado.setRelator(processo.getRelator());
        for (Colegiado colegiado : processo.getRelator().getColegiados()){
            if(colegiado.getCurso() == processo.getRelator().getCurso()){
                processoAtualizado.setColegiado(colegiado);
                break;
            }
        }
        processoAtualizado.setEstado(StatusProcesso.DISTRIBUIDO);
        processoAtualizado.setDataDistribuicao(new Date());
        return this.processoRepository.save(processoAtualizado);
    }

    public void deletarProcesso(Integer id){
        processoRepository.deleteById(id);
    }

    @Override
    public Page<Processo> findAll(Pageable page) {
        return processoRepository.findAll(page);
    }

    @Override
    public Processo findById(Integer id) {
        Processo processo = null;
        Optional<Processo> opAssunto = processoRepository.findById(id);
        if (opAssunto.isPresent()) {
            processo = opAssunto.get();
        }
        return processo;
    }

    @Override
    public Processo save(Processo processo) {
        return processoRepository.save(processo);
    }

    @Override
    public void deleteById(Integer id) { processoRepository.deleteById(id);

    }
}