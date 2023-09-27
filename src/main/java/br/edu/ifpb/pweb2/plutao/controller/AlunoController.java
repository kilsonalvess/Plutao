package br.edu.ifpb.pweb2.plutao.controller;

import br.edu.ifpb.pweb2.plutao.model.Aluno;
import br.edu.ifpb.pweb2.plutao.model.Processo;
import br.edu.ifpb.pweb2.plutao.repository.AlunoRepository;
import br.edu.ifpb.pweb2.plutao.repository.AssuntoRepository;
import br.edu.ifpb.pweb2.plutao.repository.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProcessoRepository processoRepository;

    @RequestMapping("/form")
    public ModelAndView getForm(Aluno aluno, ModelAndView mav){
        mav.setViewName("alunos/form");
        mav.addObject("aluno", aluno);
        return mav;
    }
    @RequestMapping("/save")
    public ModelAndView save(Aluno aluno, ModelAndView mav){
        alunoRepository.save(aluno);
        mav.setViewName("alunos/list");
        mav.addObject("alunos", alunoRepository.findAll());
        return mav;
    }
    @RequestMapping("/list")
    public ModelAndView liste(ModelAndView mav){
        mav.setViewName("alunos/list");
        mav.addObject("alunos", alunoRepository.findAll());
        return mav;
    }

    @RequestMapping("/processo")
    public ModelAndView formProcesso(Processo processo, ModelAndView mav) {
        mav.setViewName("/alunos/processo");
        mav.addObject("processo", processo);
        return mav;
    }

    @RequestMapping("/saveprocesso")
    public void cadastrarProcesso(Processo processo, ModelAndView mav) {
        processoRepository.save(processo);

    }
}
