package br.edu.ifpb.pweb2.plutao.controller;

import br.edu.ifpb.pweb2.plutao.model.Aluno;
import br.edu.ifpb.pweb2.plutao.model.Curso;
import br.edu.ifpb.pweb2.plutao.model.Professor;
import br.edu.ifpb.pweb2.plutao.service.CursoService;
import br.edu.ifpb.pweb2.plutao.service.ProfessorService;
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
@RequestMapping("/professores")
public class ProfessoresController {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private CursoService cursoService;

    @ModelAttribute("cursos")
    public List<Curso> getCursos() {
        return this.cursoService.getCursos();
    }

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
    public ModelAndView listAll(ModelAndView mav, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "3") int size){
        Pageable paging = PageRequest.of(page - 1, size);
        Page<Professor> pageProfessores = professorService.findAll(paging);
        NavPage navPage = NavePageBuilder.newNavPage(pageProfessores.getNumber() + 1,
                pageProfessores.getTotalElements(), pageProfessores.getTotalPages(), size);
        mav.addObject("professores", pageProfessores);
        mav.addObject("navPage", navPage);
        mav.setViewName("professores/list");
        return mav;
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editarProfessor(@PathVariable(value = "id") Integer id, ModelAndView mav) {
        mav.addObject("professor", professorService.findById(id));
        mav.setViewName("professores/form");
        return mav;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView deletarProfessor(@PathVariable(value = "id") Integer id, ModelAndView mav, RedirectAttributes attr) {
        professorService.deleteById(id);
        attr.addFlashAttribute("mensagem", "Professor removido com sucesso!");
        mav.setViewName("redirect:/professores");
        return mav;
    }

}
