package br.edu.ifpb.pweb2.plutao.controller;

import br.edu.ifpb.pweb2.plutao.model.Aluno;
import br.edu.ifpb.pweb2.plutao.model.Assunto;
import br.edu.ifpb.pweb2.plutao.model.Processo;
import br.edu.ifpb.pweb2.plutao.service.AssuntoService;
import br.edu.ifpb.pweb2.plutao.service.ProcessoService;
import br.edu.ifpb.pweb2.plutao.ui.NavPage;
import br.edu.ifpb.pweb2.plutao.ui.NavePageBuilder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/assuntos")
public class AssuntoController {

    @Autowired
    private AssuntoService assuntoService;

    @Autowired
    private ProcessoService processoService;

    @RequestMapping("/form")
    public ModelAndView getForm(Assunto assunto, ModelAndView mav){
        mav.addObject("assunto", assunto);
        mav.setViewName("assuntos/form");
        return mav;
    }

    @ModelAttribute("processoItens")
    public List<Processo> getProcessos() {
        return processoService.getProcessos();
    }

    @PostMapping
    public ModelAndView saveAssunto(@Valid Assunto assunto, BindingResult validation, ModelAndView mav, RedirectAttributes attr){
        if (validation.hasErrors()) {
            mav.addObject("message", "Erros de validação! Corrija-os e tente novamente.");
            mav.setViewName("assuntos/form");
            return mav;
        }
        if (assunto.getId() == null) {
            attr.addFlashAttribute("mensagem", "Assunto cadastrado com sucesso!");

        } else {
            attr.addFlashAttribute("mensagem", "Assunto editado com sucesso!");
        }
        assuntoService.save(assunto);
        mav.setViewName("redirect:/assuntos");
        return mav;
    }

    @GetMapping
    public ModelAndView listAll(ModelAndView mav, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "3") int size){
        Pageable paging = PageRequest.of(page - 1, size);
        Page<Assunto> pageAssuntos = assuntoService.findAll(paging);
        NavPage navPage = NavePageBuilder.newNavPage(pageAssuntos.getNumber() + 1,
                pageAssuntos.getTotalElements(), pageAssuntos.getTotalPages(), size);
        mav.addObject("assuntos", pageAssuntos);
        mav.addObject("navPage", navPage);
        mav.setViewName("assuntos/list");
        return mav;
    }

    @RequestMapping("/{id}")
    public ModelAndView getAssuntoById(@PathVariable(value = "id") Integer id, ModelAndView mav) {
        mav.addObject("assunto", assuntoService.findById(id));
        mav.setViewName("assuntos/form");
        return mav;
    }

    @RequestMapping("/{id}/delete")
    public ModelAndView deleteById(@PathVariable(value = "id") Integer id, ModelAndView mav, RedirectAttributes attr) {
        assuntoService.deleteById(id);
        attr.addFlashAttribute("mensagem", "Assunto removido com sucesso!");
        mav.setViewName("redirect:/assuntos");
        return mav;
    }
}
