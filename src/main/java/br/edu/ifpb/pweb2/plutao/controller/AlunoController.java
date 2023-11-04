package br.edu.ifpb.pweb2.plutao.controller;

import br.edu.ifpb.pweb2.plutao.enums.StatusProcesso;
import br.edu.ifpb.pweb2.plutao.model.Aluno;
import br.edu.ifpb.pweb2.plutao.model.Assunto;
import br.edu.ifpb.pweb2.plutao.model.Processo;
import br.edu.ifpb.pweb2.plutao.service.AlunoService;
import br.edu.ifpb.pweb2.plutao.service.AssuntoService;
import br.edu.ifpb.pweb2.plutao.service.ProcessoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private AssuntoService assuntoService;

    @Autowired
    private ProcessoService processoService;

    @RequestMapping("/form")
    public ModelAndView getForm(Aluno aluno, ModelAndView mav){
        mav.addObject("aluno", aluno);
        mav.setViewName("alunos/form");
        return mav;
    }
   @PostMapping
    public ModelAndView saveAluno(@Valid Aluno aluno, BindingResult validation, ModelAndView mav, RedirectAttributes attr){
       if (validation.hasErrors()) {
           mav.addObject("message", "Erros de validação! Corrija-os e tente novamente.");
           mav.setViewName("alunos/form");
           return mav;
       }
       if (aluno.getId() == null) {
           attr.addFlashAttribute("mensagem", "Aluno cadastrado com sucesso!");

       } else {
           attr.addFlashAttribute("mensagem", "Aluno editado com sucesso!");
       }
        alunoService.save(aluno);
        mav.setViewName("redirect:/alunos");
        return mav;
    }
    @GetMapping
    public ModelAndView listAll(ModelAndView mav){
        mav.addObject("alunos", alunoService.findAll());
        mav.setViewName("alunos/list");
        return mav;
    }

    @RequestMapping("/{id}")
    public ModelAndView getAlunoById(@PathVariable(value = "id") Integer id, ModelAndView mav) {
        mav.addObject("aluno", alunoService.findById(id));
        mav.setViewName("alunos/form");
        return mav;
    }

    @RequestMapping("/{id}/delete")
    public ModelAndView deleteById(@PathVariable(value = "id") Integer id, ModelAndView mav, RedirectAttributes attr) {
        alunoService.deleteById(id);
        attr.addFlashAttribute("mensagem", "Aluno removido com sucesso!");
        mav.setViewName("redirect:/alunos");
        return mav;
    }

    @RequestMapping("/estadoProcessos")
    public ModelAndView alunoEstadoProcessos(Aluno aluno , ModelAndView mav){
        mav.setViewName("processos");
        return mav;
    }

    @RequestMapping("/{id}/processos")
    public ModelAndView getProcessos(@PathVariable(value = "id") Integer id, ModelAndView mav) {
        mav.addObject("aluno", alunoService.findById(id));
        mav.addObject("processos", alunoService.consultaProcessos(id));
        mav.setViewName("alunos/processos");
        return mav;
    }

    @RequestMapping("/{id}/processos/criar")
    public ModelAndView criarProcesso(@PathVariable(value = "id") Integer id, ModelAndView mav, RedirectAttributes attr) {
        Aluno aluno = this.alunoService.findById(id);
        mav.addObject("aluno", aluno);
        mav.addObject("processo", new Processo(aluno,new Assunto()));
        mav.setViewName("alunos/criar-processo");
        return mav;
    }

    @RequestMapping("/{id}/processos/save")
    public ModelAndView saveProcesso(@Valid Processo processo, BindingResult validation, @PathVariable(value = "id") Integer id, ModelAndView mav, RedirectAttributes attr) {

        Aluno aluno = this.alunoService.findById(id);
        if (validation.hasErrors()) {
            mav.addObject("aluno", aluno);
            mav.addObject("processo", new Processo(aluno,new Assunto()));
            mav.setViewName("/alunos/criar-processo");
            return mav;
        }
        processo.setAluno(aluno);
        processoService.salvarProcesso(processo);
        mav.addObject("aluno", aluno);
        mav.addObject("processos", processoService.getProcessosPorAluno(aluno));
        mav.setViewName("redirect:/alunos/"+id+"/processos");
        attr.addFlashAttribute("mensagem", "Processo criado com Sucesso");
        return mav;
    }

    @ModelAttribute("assuntosItens")
    public List<Assunto> getAssuntos() {
        return assuntoService.findAll();
    }

    @ModelAttribute("statusItens")
    public List<StatusProcesso> getStatus() {
        return List.of(StatusProcesso.values());
    }

}
