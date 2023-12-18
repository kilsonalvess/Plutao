package br.edu.ifpb.pweb2.plutao.controller;

import br.edu.ifpb.pweb2.plutao.model.Colegiado;
import br.edu.ifpb.pweb2.plutao.model.Curso;
import br.edu.ifpb.pweb2.plutao.model.Professor;
import br.edu.ifpb.pweb2.plutao.service.ColegiadoService;
import br.edu.ifpb.pweb2.plutao.service.CursoService;
import br.edu.ifpb.pweb2.plutao.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("colegiados")
public class ColegiadoController {

    @Autowired
    private ColegiadoService colegiadoService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private CursoService cursoService;

    @RequestMapping("/form")
    public ModelAndView getForm(Colegiado colegiado, ModelAndView mav) {
        mav.addObject("colegiado", colegiado);
        mav.setViewName("colegiados/form");
        return mav;
    }
    @ModelAttribute("professoresList")
    public List<Professor> getProfessores() {
        return professorService.findAll();
    }

    @ModelAttribute("cursos")
    public List<Curso> getCursos(){
        return this.cursoService.getCursos();
    }

    @GetMapping
    public ModelAndView listAll(ModelAndView mav) {
        mav.addObject("colegiados", colegiadoService.getColegiados());
        mav.setViewName("colegiados/list");
        return mav;
    }

    @PostMapping
    public ModelAndView saveAluno(@Valid Colegiado colegiado, BindingResult validation, ModelAndView mav, RedirectAttributes attr) {
        if (validation.hasErrors()) {
            mav.addObject("message", "Erros de validação! Corrija-os e tente novamente.");
            mav.setViewName("colegiados/form");
            return mav;
        }
        if (colegiado.getId() == null) {
            attr.addFlashAttribute("mensagem", "Colegiado cadastrado com sucesso!");

        } else {
            attr.addFlashAttribute("mensagem", "Colegiado editado com sucesso!");
        }
        colegiadoService.salvarColegiado(colegiado);
        mav.addObject("colegiados", colegiadoService.getColegiados());
        mav.setViewName("redirect:/colegiados");
        return mav;
    }

    @RequestMapping("/{id}")
    public ModelAndView getColegiadoById(@PathVariable(value = "id") Integer id, ModelAndView mav) {
        mav.addObject("colegiado", colegiadoService.getColegiadoPorId(id));
        mav.addObject("mensagem","Colegiado editado com Sucesso");
        mav.setViewName("colegiados/form");
        return mav;
    }

    @RequestMapping("/{id}/delete")
    public ModelAndView deleteById(@PathVariable(value = "id") Integer id, ModelAndView mav, RedirectAttributes attr) {
        colegiadoService.deletarColegiado(id);
        attr.addFlashAttribute("mensagem", "Colegiado removido com sucesso!");
        mav.setViewName("redirect:/colegiados");
        return mav;
    }
}
