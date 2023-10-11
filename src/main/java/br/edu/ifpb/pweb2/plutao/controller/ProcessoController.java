package br.edu.ifpb.pweb2.plutao.controller;

import br.edu.ifpb.pweb2.plutao.model.Aluno;
import br.edu.ifpb.pweb2.plutao.model.Processo;
import br.edu.ifpb.pweb2.plutao.repository.AlunoRepository;
import br.edu.ifpb.pweb2.plutao.repository.ProcessoRepository;
import br.edu.ifpb.pweb2.plutao.service.AlunoService;
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
    public ModelAndView saveProcesso(@Valid Processo processo, BindingResult validation, ModelAndView mav){
        if (validation.hasErrors()) {
            mav.addObject("message", "Erros de validação! Corrija-os e tente novamente.");
            mav.setViewName("processos/form");
            return mav;
        }
        processoService.save(processo);
        mav.setViewName("redirect:/processos");
        return mav;
    }

    @GetMapping
    public ModelAndView listAll(ModelAndView mav) {
        mav.setViewName("processos/list");
        mav.addObject("processos", processoService.findAll());
        return mav;
    }

    @RequestMapping("/{id}/delete")
    public ModelAndView deleteById(@PathVariable(value = "id") Integer id, ModelAndView mav, RedirectAttributes attr) {
        processoService.deleteById(id);
        attr.addFlashAttribute("mensagem", "Processo removido com sucesso!");
        mav.setViewName("redirect:/processos");
        return mav;
    }
}
