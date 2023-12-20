package br.edu.ifpb.pweb2.plutao.controller;

import java.util.List;

import br.edu.ifpb.pweb2.plutao.model.Aluno;
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

import br.edu.ifpb.pweb2.plutao.model.Coordenador;
import br.edu.ifpb.pweb2.plutao.model.Curso;
import br.edu.ifpb.pweb2.plutao.service.CoordenadorService;
import br.edu.ifpb.pweb2.plutao.service.CursoService;
import br.edu.ifpb.pweb2.plutao.service.ProfessorService;
import jakarta.validation.Valid;
import br.edu.ifpb.pweb2.plutao.model.Professor;


@Controller
@RequestMapping("/coordenadores")
public class CoordenadoresController {
    @Autowired
    private CoordenadorService coordenadorService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private CursoService cursoService;

    @ModelAttribute("professores")
    public List<Professor> getProfessores(){
        return this.professorService.getProfessores();
    }

    @ModelAttribute("cursos")
    public List<Curso> getCursos(){
        return this.cursoService.getCursos();
    }

    @GetMapping
    public ModelAndView listCoordenadores(ModelAndView mav, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "3") int size){
        Pageable paging = PageRequest.of(page - 1, size);
        Page<Coordenador> pageCoordenadores = coordenadorService.findAll(paging);
        NavPage navPage = NavePageBuilder.newNavPage(pageCoordenadores.getNumber() + 1,
                pageCoordenadores.getTotalElements(), pageCoordenadores.getTotalPages(), size);
        mav.addObject("coordenadores", pageCoordenadores);
        mav.addObject("navPage", navPage);
        mav.setViewName("coordenador/list");
        return mav;
    }

    @GetMapping("criar")
    public ModelAndView createCoordenador(ModelAndView model, RedirectAttributes redirectAttributes ){
        model.addObject("coordenador", new Coordenador());
        model.addObject("acao", "salvar");
        model.setViewName("coordenador/form");
        return model;
    }

    @PostMapping("criar")
    public ModelAndView saveCoordenador(
            @Valid Coordenador coordenador,
            BindingResult validation,
            ModelAndView model,
            RedirectAttributes redirectAttributes
    ){
        if (validation.hasErrors()) {
            model.setViewName("coordenador/form");
            model.addObject("acao", "salvar");
            return model;
        }
        coordenadorService.salvarCoordenador(coordenador);
        model.addObject("coordenadores", professorService.getProfessores());
        model.setViewName("redirect:/coordenadores");
        redirectAttributes.addFlashAttribute("mensagem", "Coordenador Criado com Sucesso");
        redirectAttributes.addFlashAttribute("coordenadoresSalvo", true);
        return model;
    }

    @GetMapping("{id}")
    public ModelAndView editCoordenador(@PathVariable("id") Integer id, ModelAndView model){
        model.addObject("coordenador", coordenadorService.getCoordenadorPorId(id));
        model.addObject("acao", "editar");
        model.setViewName("coordenador/form");
        return model;
    }

    @PostMapping("{id}")
    public ModelAndView updateCoordenador(
            @Valid Coordenador coordenador,
            BindingResult validation,
            @PathVariable("id") Integer id,
            ModelAndView model,
            RedirectAttributes redirectAttributes
    ){
        if (validation.hasErrors()) {
            model.addObject("coordenador", coordenadorService.getCoordenadorPorId(id));
            model.setViewName("redirect:/professores/"+id);
            return model;
        }
        coordenadorService.salvarCoordenador(coordenador);
        model.addObject("coordenadores", coordenadorService.getCoordenadores());
        model.setViewName("redirect:/coordenadores");
        redirectAttributes.addFlashAttribute("mensagem", "Coordenador Editado com Sucesso");
        redirectAttributes.addFlashAttribute("coordenadoresEditado", true);
        return model;
    }

    @RequestMapping("{id}/delete")
    public ModelAndView deleteCoordenador(@PathVariable("id") Integer id, ModelAndView model, RedirectAttributes redirectAttributes){
        coordenadorService.deletarCoordenador(id);
        model.addObject("coordenadores", coordenadorService.getCoordenadores());
        model.setViewName("redirect:/coordenadores");
        redirectAttributes.addFlashAttribute("mensagem","Coordenador Deletado com Sucesso");
        redirectAttributes.addFlashAttribute("coordenadoresDeletado", true);
        return model;
    }
}