package br.edu.ifpb.pweb2.plutao.controller;

import br.edu.ifpb.pweb2.plutao.model.Aluno;
import br.edu.ifpb.pweb2.plutao.model.Processo;
import br.edu.ifpb.pweb2.plutao.model.Professor;
import br.edu.ifpb.pweb2.plutao.service.AlunoService;
import br.edu.ifpb.pweb2.plutao.service.ProcessoService;
import br.edu.ifpb.pweb2.plutao.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
@Controller
@RequestMapping("/coordenador/processos")
public class CoordenadorController {

    @Autowired
    private ProcessoService processoService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private AlunoService alunoService;

    @ModelAttribute("relatores")
    public List<Professor> getRelatores(){
        return this.professorService.getProfessoresComProcessos();
    }

    @ModelAttribute("professores")
    public List<Professor> getProfessores(){
        return this.professorService.getProfessoresComColegiado();
    }

    @ModelAttribute("alunos")
    public List<Aluno> getAlunos(){
        return this.alunoService.getAlunosComProcessos();
    }

    @GetMapping
    public ModelAndView showPainelProcessos(ModelAndView model){
        model.addObject("processos", processoService.findAll());
        model.setViewName("/coordenador/painel");
        return model;
    }

    @GetMapping("{id}")
    public ModelAndView showProcesso(ModelAndView model, @PathVariable("id") Integer id){
        model.addObject("processo", processoService.getProcessoPorId(id));
        model.setViewName("/coordenador/processo");
        return model;
    }

    @PostMapping("{id}")
    public ModelAndView salvarProcesso(
            ModelAndView model,
            Processo processo,
            @PathVariable("id")Integer id,
            RedirectAttributes redirectAttributes
    ){
        processoService.atribuirProcesso(processo, id);
        model.addObject("processos", processoService.findAll());
        model.setViewName("redirect:/coordenador/processos");
        redirectAttributes.addFlashAttribute("mensagem", "Processo designado com Sucesso");
        return model;
    }

}

