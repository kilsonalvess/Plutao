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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/aluno/{id}")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private AssuntoService assuntoService;

    @Autowired
    private ProcessoService processoService;

    @RequestMapping("/processos")
    public ModelAndView getProcessos(@PathVariable(value = "id") Integer id, ModelAndView mav) {
        mav.addObject("aluno", alunoService.findById(id));
        mav.addObject("processos", alunoService.consultaProcessos(id));
        mav.setViewName("alunos/processos");
        return mav;
    }

    @RequestMapping("/processos/criar")
    public ModelAndView criarProcesso(@PathVariable(value = "id") Integer id, ModelAndView mav, RedirectAttributes attr) {
        Aluno aluno = this.alunoService.findById(id);
        mav.addObject("aluno", aluno);
        mav.addObject("processo", new Processo(aluno,new Assunto()));
        mav.setViewName("alunos/criar-processo");
        return mav;
    }

    @RequestMapping("/processos/salvar")
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
    @RequestMapping("/estadoProcessos")
    public ModelAndView alunoEstadoProcessos(Aluno aluno , ModelAndView mav){
        mav.setViewName("processos");
        return mav;
    }

    @ModelAttribute("assuntosItens")
    public Page<Assunto> getAssuntos(Pageable page) {
        return assuntoService.findAll(page);
    }

    @ModelAttribute("statusItens")
    public List<StatusProcesso> getStatus() {
        return List.of(StatusProcesso.values());
    }

}
