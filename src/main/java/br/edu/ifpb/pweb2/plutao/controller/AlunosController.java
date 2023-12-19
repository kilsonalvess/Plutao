package br.edu.ifpb.pweb2.plutao.controller;

import br.edu.ifpb.pweb2.plutao.model.Aluno;
import br.edu.ifpb.pweb2.plutao.service.AlunoService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/alunos")
public class AlunosController {

    @Autowired
    private AlunoService alunoService;

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
}