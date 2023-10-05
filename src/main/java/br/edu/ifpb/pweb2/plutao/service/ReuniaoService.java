package br.edu.ifpb.pweb2.plutao.service;

import br.edu.ifpb.pweb2.plutao.model.Reuniao;
import br.edu.ifpb.pweb2.plutao.repository.ReuniaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ReuniaoService implements Service<Reuniao, Integer>{

    @Autowired
    private ReuniaoRepository reuniaoRepository;

    @Override
    public List<Reuniao> findAll() {
        return reuniaoRepository.findAll();
    }

    @Override
    public Reuniao findById(Integer id) {
        Reuniao reuniao = null;
        Optional<Reuniao> opReuniao = reuniaoRepository.findById(id);
        if (opReuniao.isPresent()) {
            reuniao = opReuniao.get();
        }
        return reuniao;
    }

    @Override
    public Reuniao save(Reuniao reuniao) {
        return reuniaoRepository.save(reuniao);
    }

    @Override
    public void deleteById(Integer id) {
        reuniaoRepository.deleteById(id);
    }
}
