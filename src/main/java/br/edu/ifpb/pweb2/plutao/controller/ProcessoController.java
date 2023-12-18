package br.edu.ifpb.pweb2.plutao.controller;

import br.edu.ifpb.pweb2.plutao.model.Aluno;
import br.edu.ifpb.pweb2.plutao.model.Assunto;
import br.edu.ifpb.pweb2.plutao.model.Processo;
import br.edu.ifpb.pweb2.plutao.repository.AlunoRepository;
import br.edu.ifpb.pweb2.plutao.repository.ProcessoRepository;
import br.edu.ifpb.pweb2.plutao.service.AlunoService;
import br.edu.ifpb.pweb2.plutao.service.AssuntoService;
import br.edu.ifpb.pweb2.plutao.service.ProcessoService;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/processos")
public class ProcessoController {

    @Autowired
    private ProcessoService processoService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private AssuntoService assuntoService;

    @RequestMapping("/form")
    public ModelAndView getForm( Processo processo, ModelAndView mav){
        mav.addObject("processo", processo);
        mav.setViewName("processos/form");
        return mav;
    }

    @ModelAttribute("alunoItens")
    public List<Aluno> getAlunos() {
        return alunoService.findAll();
    }

    @ModelAttribute("assuntosItens")
    public List<Assunto> getAssuntos() {
        return assuntoService.findAll();
    }

    @PostMapping
    public ModelAndView saveProcesso(@Valid Processo processo, BindingResult validation, ModelAndView mav, RedirectAttributes attr){
        if (validation.hasErrors()) {
            mav.addObject("message", "Erros de validação! Corrija-os e tente novamente.");
            mav.setViewName("processos/form");
            return mav;
        }if (processo.getId() == null) {
            attr.addFlashAttribute("mensagem", "Processo cadastrado com sucesso!");

        } else {
            attr.addFlashAttribute("mensagem", "Processo editado com sucesso!");
        }
        processoService.salvarProcesso(processo);
        mav.setViewName("redirect:/processos");
        return mav;
    }

    @GetMapping
    public ModelAndView listAll(ModelAndView mav) {
        mav.setViewName("processos/list");
        mav.addObject("processos", processoService.getProcessos());
        return mav;
    }

    @RequestMapping("/{id}")
    public ModelAndView getProcessoById(@PathVariable(value = "id") Integer id, ModelAndView mav) {
        mav.addObject("processo", processoService.getProcessoPorId(id));
        mav.setViewName("processos/form");
        return mav;
    }

    @RequestMapping("/{id}/delete")
    public ModelAndView deleteById(@PathVariable(value = "id") Integer id, ModelAndView mav, RedirectAttributes attr) {
        processoService.deletarProcesso(id);
        attr.addFlashAttribute("mensagem", "Processo removido com sucesso!");
        mav.setViewName("redirect:/processos");
        return mav;
    }

}
