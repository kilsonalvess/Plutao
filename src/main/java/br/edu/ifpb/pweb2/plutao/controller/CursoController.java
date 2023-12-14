package br.edu.ifpb.pweb2.plutao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.plutao.model.Assunto;
import br.edu.ifpb.pweb2.plutao.model.Curso;
import br.edu.ifpb.pweb2.plutao.service.CursoService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ModelAndView listCursos(ModelAndView model){
        model.addObject("cursos", cursoService.getCursos());
        model.addObject("curso", new Curso());
        model.setViewName("cursos/list");
        return model;
    }

    @GetMapping("criar")
    public ModelAndView createCurso(ModelAndView model, RedirectAttributes redirectAttributes ){
        model.addObject("curso", new Curso());
        model.addObject("acao", "salvar");
        model.setViewName("cursos/form");
        return model;
    }

    @PostMapping("criar")
    public ModelAndView saveCurso(
            @Valid Curso curso,
            BindingResult validation,
            ModelAndView model,
            RedirectAttributes redirectAttributes
    ){
        if (validation.hasErrors()) {
            model.setViewName("cursos/form");
            model.addObject("acao", "salvar");
            return model;
        }
        cursoService.salvarCurso(curso);
        model.addObject("cursos", cursoService.getCursos());
        model.setViewName("redirect:/cursos");
        redirectAttributes.addFlashAttribute("mensagem", "Curso Criado com Sucesso");
        redirectAttributes.addFlashAttribute("cursoSalvo", true);
        return model;
    }

    @GetMapping("{id}")
    public ModelAndView editCurso(@PathVariable("id") long id, ModelAndView model, RedirectAttributes redirectAttributes){
        model.addObject("curso", cursoService.getCursoPorId(id));
        model.addObject("acao", "editar");
        model.setViewName("cursos/form");
        redirectAttributes.addFlashAttribute("mensagem","Curso Editado com Sucesso");
        redirectAttributes.addFlashAttribute("cursosEditado", true);
        return model;
    }

    @PostMapping("{id}")
    public ModelAndView updateCurso(
            @Valid Curso curso,
            BindingResult validation,
            @PathVariable("id") Long id,
            ModelAndView model,
            RedirectAttributes redirectAttributes
    ){
        if (validation.hasErrors()) {
            model.addObject("curso", cursoService.getCursoPorId(id));
            model.setViewName("redirect:/assuntos/"+id);
            return model;
        }
        cursoService.salvarCurso(curso);
        model.addObject("assuntos", cursoService.getCursos());
        model.setViewName("redirect:/assuntos");
        redirectAttributes.addFlashAttribute("mensagem", "Curso Editado com Sucesso");
        redirectAttributes.addFlashAttribute("cursosEditado", true);
        return model;
    }


    @RequestMapping("{id}/delete")
    public ModelAndView deleteCurso(@PathVariable("id") Long id, ModelAndView model, RedirectAttributes redirectAttributes){
        cursoService.deletarCurso(id);
        model.addObject("cursos", cursoService.getCursos());
        model.addObject("curso", new Assunto());
        model.setViewName("redirect:/cursos");
        redirectAttributes.addFlashAttribute("mensagem", "Curso Deletado com Sucesso");
        redirectAttributes.addFlashAttribute("cursosDeletado", true);
        return model;
    }


}