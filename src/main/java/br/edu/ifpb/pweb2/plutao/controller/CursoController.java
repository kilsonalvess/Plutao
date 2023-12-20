package br.edu.ifpb.pweb2.plutao.controller;


import br.edu.ifpb.pweb2.plutao.ui.NavPage;
import br.edu.ifpb.pweb2.plutao.ui.NavePageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
    public ModelAndView listCursos(ModelAndView mav, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "3") int size){
        Pageable paging = PageRequest.of(page - 1, size);
        Page<Curso> pageCursos = cursoService.findAll(paging);
        NavPage navPage = NavePageBuilder.newNavPage(pageCursos.getNumber() + 1,
                pageCursos.getTotalElements(), pageCursos.getTotalPages(), size);
        mav.addObject("cursos", pageCursos);
        mav.addObject("navPage", navPage);
        mav.addObject("curso", new Curso());
        mav.setViewName("cursos/list");
        return mav;
    }

    @GetMapping("criar")
    public ModelAndView criarCurso(ModelAndView mav, RedirectAttributes redirectAttributes ){
        mav.addObject("curso", new Curso());
        mav.addObject("acao", "salvar");
        mav.setViewName("cursos/form");
        return mav;
    }

    @PostMapping("criar")
    public ModelAndView salvarCurso(
            @Valid Curso curso,
            BindingResult validation,
            ModelAndView mav,
            RedirectAttributes redirectAttributes
    ){
        if (validation.hasErrors()) {
            mav.setViewName("cursos/form");
            mav.addObject("acao", "salvar");
            return mav;
        }
        cursoService.save(curso);
        mav.addObject("cursos", cursoService.getCursos());
        mav.setViewName("redirect:/cursos");
        redirectAttributes.addFlashAttribute("mensagem", "Curso criado com Sucesso");
        redirectAttributes.addFlashAttribute("cursoSalvo", true);
        return mav;
    }

    @GetMapping("{id}")
    public ModelAndView editarCurso(@PathVariable("id") Integer id, ModelAndView mav, RedirectAttributes redirectAttributes){
        mav.addObject("curso", cursoService.getCursoPorId(id));
        mav.addObject("acao", "editar");
        mav.setViewName("cursos/form");
        redirectAttributes.addFlashAttribute("mensagem","Curso editado com Sucesso");
        redirectAttributes.addFlashAttribute("cursosEditado", true);
        return mav;
    }

    @PostMapping("{id}")
    public ModelAndView atualizarCurso(
            @Valid Curso curso,
            BindingResult validation,
            @PathVariable("id") Integer id,
            ModelAndView mav,
            RedirectAttributes redirectAttributes
    ){
        if (validation.hasErrors()) {
            mav.addObject("curso", cursoService.getCursoPorId(id));
            mav.setViewName("redirect:/assuntos/"+id);
            return mav;
        }
        cursoService.save(curso);
        mav.addObject("assuntos", cursoService.getCursos());
        mav.setViewName("redirect:/assuntos");
        redirectAttributes.addFlashAttribute("mensagem", "Curso editado com Sucesso");
        redirectAttributes.addFlashAttribute("cursosEditado", true);
        return mav;
    }


    @RequestMapping("{id}/delete")
    public ModelAndView deletarCurso(@PathVariable("id") Integer id, ModelAndView mav, RedirectAttributes redirectAttributes){
        cursoService.deleteById(id);
        mav.addObject("cursos", cursoService.getCursos());
        mav.addObject("curso", new Assunto());
        mav.setViewName("redirect:/cursos");
        redirectAttributes.addFlashAttribute("mensagem", "Curso deletado com Sucesso");
        redirectAttributes.addFlashAttribute("cursosDeletado", true);
        return mav;
    }


}