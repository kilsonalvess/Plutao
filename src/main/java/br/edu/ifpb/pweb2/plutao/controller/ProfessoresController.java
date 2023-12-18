package br.edu.ifpb.pweb2.plutao.controller;

import br.edu.ifpb.pweb2.plutao.model.Professor;
import br.edu.ifpb.pweb2.plutao.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/professores")
public class ProfessoresController {

    @Autowired
    private ProfessorService professorService;
    @GetMapping("/form")
    public ModelAndView getForm(Professor professor, ModelAndView mav){
        mav.addObject("professor", professor);
        mav.setViewName("professores/form");
        return mav;
    }

    @PostMapping
    public ModelAndView saveProfessor(@Valid Professor professor, BindingResult validation, ModelAndView mav, RedirectAttributes attr){
        if (validation.hasErrors()) {
            mav.addObject("message", "Erros de validação! Corrija-os e tente novamente.");
            mav.setViewName("professores/form");
            return mav;
        }
        if (professor.getId() == null) {
            attr.addFlashAttribute("mensagem", "Professor cadastrado com sucesso!");

        } else {
            attr.addFlashAttribute("mensagem", "Professor editado com sucesso!");
        }
        professorService.save(professor);
        mav.setViewName("redirect:/professores");
        return mav;
    }

    @GetMapping
    public ModelAndView listAll(ModelAndView mav){
        mav.addObject("professores", professorService.findAll());
        mav.setViewName("professores/list");
        return mav;
    }

    @GetMapping("/{id}/editar")
    public ModelAndView getProfessorById(@PathVariable(value = "id") Integer id, ModelAndView mav) {
        mav.addObject("professor", professorService.findById(id));
        mav.setViewName("professores/form");
        return mav;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView deleteById(@PathVariable(value = "id") Integer id, ModelAndView mav, RedirectAttributes attr) {
        professorService.deleteById(id);
        attr.addFlashAttribute("mensagem", "Professor removido com sucesso!");
        mav.setViewName("redirect:/professores");
        return mav;
    }

}
