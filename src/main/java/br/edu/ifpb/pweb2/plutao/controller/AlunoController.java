package br.edu.ifpb.pweb2.plutao.controller;

import br.edu.ifpb.pweb2.plutao.model.Aluno;
import br.edu.ifpb.pweb2.plutao.model.Processo;
import br.edu.ifpb.pweb2.plutao.repository.AlunoRepository;
import br.edu.ifpb.pweb2.plutao.repository.AssuntoRepository;
import br.edu.ifpb.pweb2.plutao.repository.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProcessoRepository processoRepository;

    @RequestMapping("/form")
    public ModelAndView getForm(Aluno aluno, ModelAndView mav){
        mav.addObject("aluno", aluno);
        mav.setViewName("alunos/form");
        return mav;
    }
   @PostMapping
    public ModelAndView save(Aluno aluno, ModelAndView mav, RedirectAttributes redAttrs){
        alunoRepository.save(aluno);
        redAttrs.addAttribute("mensagem", "Aluno cadastrado com sucesso!");
        mav.setViewName("redirect:alunos");
        return mav;
    }
    @GetMapping
    public ModelAndView listAll(ModelAndView mav){
        mav.addObject("alunos", alunoRepository.findAll());
        mav.setViewName("alunos/list");
        return mav;
    }

    @RequestMapping("/{id}")
    public ModelAndView getCorrentistaById(@PathVariable(value = "id") Integer id, ModelAndView modelAndView) {
        modelAndView.addObject("aluno", alunoRepository.findById(id));
        modelAndView.setViewName("alunos/form");
        return modelAndView;
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
