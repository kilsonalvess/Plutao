package br.edu.ifpb.pweb2.plutao.controller;

import br.edu.ifpb.pweb2.plutao.enums.TipoDecisao;
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
@RequestMapping("/professor/{id}")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private ProcessoService processoService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private ReuniaoService reuniaoService;


    @ModelAttribute("alunos")
    public List<Aluno> getAlunos() {
        return this.alunoService.getAlunosComProcessos();
    }

    @ModelAttribute("relatores")
    public List<Professor> getRelatores() {
        return this.professorService.getProfessoresComProcessos();
    }

    @GetMapping("/processos")
    public ModelAndView listProcessos(ModelAndView mav, @PathVariable("id") Integer id) {
        Professor professor = this.professorService.findById(id);
        mav.addObject("processos", processoService.getProcessosPorProfessor(professor));
        mav.addObject("professor", professor);
        mav.setViewName("/professores/painel");
        return mav;
    }

    @GetMapping("/processos/{idProcesso}")
    public ModelAndView visualizarProcesso(ModelAndView mav, @PathVariable("idProcesso") Integer idProcesso) {
        mav.addObject("processo", processoService.findById(idProcesso));
        mav.setViewName("/professores/processo");
        return mav;
    }

    @PostMapping("/processos/{idProcesso}")
    public ModelAndView atualizarProcesso(ModelAndView mav, Processo processo, @PathVariable("id") Integer id, @PathVariable("idProcesso") Integer idProcesso) {
        processoService.atualizarProcesso(processo, idProcesso);
        Professor professor = this.professorService.findById(id);
        mav.addObject("processos", processoService.getProcessosPorProfessor(professor));
        mav.setViewName("redirect:/professor/" + id + "/processos");
        return mav;
    }

    @GetMapping("/reunioes")
    public ModelAndView listReunioes(ModelAndView mav,@PathVariable("id") Integer id){
        Professor professor = professorService.findById(id);
        Colegiado colegiado = professor.getColegiados().get(0);
        List<Reuniao> reunioes = colegiado.getReunioes();
        mav.addObject("professor", professor);
        mav.addObject("reunioes", reunioes);
        mav.setViewName("/professores/painel-reunioes");
        return mav;
    }

    @GetMapping("/reunioes/{idReuniao}")
    public ModelAndView visualizarReuniao(ModelAndView mav, @PathVariable("idReuniao") Integer idReuniao){
        mav.addObject("reuniao", this.reuniaoService.getReuniaoPorId(idReuniao));
        mav.setViewName("/professores/reuniao");
        return mav;
    }

}