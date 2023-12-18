package br.edu.ifpb.pweb2.plutao.controller;

import br.edu.ifpb.pweb2.plutao.model.*;
import br.edu.ifpb.pweb2.plutao.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/professore/{id}")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private ProcessoService processoService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private ReuniaoService reuniaoService;

    @ModelAttribute("cursos")
    public List<Curso> getCursos() {
        return this.cursoService.getCursos();
    }

    @ModelAttribute("alunos")
    public List<Aluno> getAlunos() {
        return this.alunoService.getAlunosComProcessos();
    }

    @ModelAttribute("relatores")
    public List<Professor> getRelatores() {
        return this.professorService.getProfessoresComProcessos();
    }

    @GetMapping("/processos")
    public ModelAndView showPainelProcessos(ModelAndView model, @PathVariable("id") Integer id) {
        Professor professor = this.professorService.getProfessorPorId(id);
        model.addObject("processos", processoService.getProcessosPorProfessor(professor));
        model.setViewName("/professor/painel-processos");
        return model;
    }

    @GetMapping("/processos/{idProcesso}")
    public ModelAndView showProcesso(ModelAndView model, @PathVariable("idProcesso") Integer idProcesso) {
        model.addObject("processo", processoService.getProcessoPorId(idProcesso));
        model.setViewName("/professor/processo");
        return model;
    }

    @PostMapping("/processos/{idProcesso}")
    public ModelAndView atualizarProcesso(ModelAndView model, Processo processo, @PathVariable("id") Integer id, @PathVariable("idProcesso") Integer idProcesso) {
        processoService.atualizarProcesso(processo, idProcesso);
        Professor professor = this.professorService.getProfessorPorId(id);
        model.addObject("processos", processoService.getProcessosPorProfessor(professor));
        model.setViewName("redirect:/professor/" + id + "/processos");
        return model;
    }

    @GetMapping("/reunioes")
    public ModelAndView showPainelReunioes(ModelAndView model,@PathVariable("id") Integer id){
        Professor professor = professorService.getProfessorPorId(id);
        Colegiado colegiado = professor.getColegiados().get(0);
        List<Reuniao> reunioes = colegiado.getReunioes();
        model.addObject("reunioes", reunioes);
        model.setViewName("/professor/painel-reunioes");
        return model;
    }

    @GetMapping("/reunioes/{idReuniao}")
    public ModelAndView showReuniao(ModelAndView model, @PathVariable("idReuniao") Integer idReuniao){
        model.addObject("reuniao", this.reuniaoService.getReuniaoPorId(idReuniao));
        model.setViewName("/professor/reuniao");
        return model;
    }

}