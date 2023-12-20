package br.edu.ifpb.pweb2.plutao.service;

import br.edu.ifpb.pweb2.plutao.enums.StatusProcesso;
import br.edu.ifpb.pweb2.plutao.enums.StatusReuniao;
import br.edu.ifpb.pweb2.plutao.model.Processo;
import br.edu.ifpb.pweb2.plutao.model.Reuniao;
import br.edu.ifpb.pweb2.plutao.repository.ReuniaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReuniaoService {
    @Autowired
    private ReuniaoRepository reuniaoRepository;

    public List<Reuniao> getReunioes(){
        return this.reuniaoRepository.findAll();
    }

    public Reuniao getReuniaoPorId(Integer id){
        return this.reuniaoRepository.findById(id).orElse(null);
    }

    public Reuniao salvarReuniao(Reuniao reuniao){
        List<Processo> processosSelecionados = new ArrayList<Processo>();
        reuniao.setReuniao(StatusReuniao.PROGRAMADA);
        for (Processo processo : reuniao.getProcessos()){
            if (processo != null) {
                processo.setEstado(StatusProcesso.EM_PAUTA);
                processo.setEmPauta(reuniao);
                processosSelecionados.add(processo);
            }
        }
        reuniao.setProcessos(processosSelecionados);
        reuniao.getColegiado().adicionarReuniao(reuniao);
        return this.reuniaoRepository.save(reuniao);
    }

    public Reuniao iniciarReuniao(Reuniao reuniao, Integer id) throws Exception{
        for(Reuniao reuniao2 : this.reuniaoRepository.findAll()){
            if(reuniao2.getReuniao().equals(StatusReuniao.EM_ANDAMENTO)){
                throw new Exception("Já existe uma reunião em andamento!");
            }
        }
        Reuniao reuniaoAtualizada = this.reuniaoRepository.findById(id).orElse(null);
        reuniaoAtualizada.setReuniao(StatusReuniao.EM_ANDAMENTO);
        for(Processo processo : reuniaoAtualizada.getProcessos()){
            processo.setEstado(StatusProcesso.EM_JULGAMENTO);
        }
        return this.reuniaoRepository.save(reuniaoAtualizada);
    }

}