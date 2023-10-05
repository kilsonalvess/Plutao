package br.edu.ifpb.pweb2.plutao.controller;

import br.edu.ifpb.pweb2.plutao.model.Aluno;
import br.edu.ifpb.pweb2.plutao.model.Processo;
import br.edu.ifpb.pweb2.plutao.repository.AlunoRepository;
import br.edu.ifpb.pweb2.plutao.repository.ProcessoRepository;
import br.edu.ifpb.pweb2.plutao.service.AlunoService;
import br.edu.ifpb.pweb2.plutao.service.ProcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/processos")
public class ProcessoController {

    @Autowired
    private ProcessoService processoService;

    @Autowired
    private AlunoService alunoService;

    @RequestMapping("/form")
    public ModelAndView getForm( Processo processo, ModelAndView mav){
        mav.addObject("processo", processo);
        mav.setViewName("processos/form");
        return mav;
    }

    @ModelAttribute("alunoItems")
    public List<Aluno> getAlunos() {
        return alunoService.findAll();
    }

    @PostMapping
    public ModelAndView saveProcesso(Processo processo, ModelAndView mav){
        processoService.save(processo);
        mav.setViewName("redirect:processos/{id}");
        return mav;
    }

    @GetMapping
    public ModelAndView listAll(ModelAndView mav) {
        mav.setViewName("processos/list");
        mav.addObject("processos", processoService.findAll());
        return mav;
    }
    @RequestMapping("/{id}")
    public ModelAndView getProcessoById(@PathVariable(value = "id") Integer id, ModelAndView mav) {
        mav.addObject("processo", processoService.findById(id));
        mav.setViewName("processos/list");
        return mav;
    }

}
