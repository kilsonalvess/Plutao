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
        mav.setViewName("coordenadores/list");
        return mav;
    }

    @GetMapping("criar")
    public ModelAndView criarCoordenador(ModelAndView mav, RedirectAttributes redirectAttributes ){
        mav.addObject("coordenador", new Coordenador());
        mav.addObject("acao", "salvar");
        mav.setViewName("coordenadores/form");
        return mav;
    }

    @PostMapping("criar")
    public ModelAndView saveCoordenador(
            @Valid Coordenador coordenador,
            BindingResult validation,
            ModelAndView mav,
            RedirectAttributes redirectAttributes
    ){
        if (validation.hasErrors()) {
            mav.setViewName("coordenadores/form");
            mav.addObject("acao", "salvar");
            return mav;
        }
        coordenadorService.save(coordenador);
        mav.addObject("coordenadores", professorService.getProfessores());
        mav.setViewName("redirect:/coordenadores");
        redirectAttributes.addFlashAttribute("mensagem", "Coordenador Criado com Sucesso");
        redirectAttributes.addFlashAttribute("coordenadoresSalvo", true);
        return mav;
    }

    @GetMapping("{id}")
    public ModelAndView editarCoordenador(@PathVariable("id") Integer id, ModelAndView mav){
        mav.addObject("coordenador", coordenadorService.getCoordenadorPorId(id));
        mav.addObject("acao", "editar");
        mav.setViewName("coordenadores/form");
        return mav;
    }

    @PostMapping("{id}")
    public ModelAndView atualizarCoordenador(
            @Valid Coordenador coordenador,
            BindingResult validation,
            @PathVariable("id") Integer id,
            ModelAndView mav,
            RedirectAttributes redirectAttributes
    ){
        if (validation.hasErrors()) {
            mav.addObject("coordenador", coordenadorService.getCoordenadorPorId(id));
            mav.setViewName("redirect:/professores/"+id);
            return mav;
        }
        coordenadorService.save(coordenador);
        mav.addObject("coordenadores", coordenadorService.getCoordenadores());
        mav.setViewName("redirect:/coordenadores");
        redirectAttributes.addFlashAttribute("mensagem", "Coordenador Editado com Sucesso");
        redirectAttributes.addFlashAttribute("coordenadoresEditado", true);
        return mav;
    }

    @RequestMapping("{id}/delete")
    public ModelAndView deletarCoordenador(@PathVariable("id") Integer id, ModelAndView mav, RedirectAttributes redirectAttributes){
        coordenadorService.deleteById(id);
        mav.addObject("coordenadores", coordenadorService.getCoordenadores());
        mav.setViewName("redirect:/coordenadores");
        redirectAttributes.addFlashAttribute("mensagem","Coordenador Deletado com Sucesso");
        redirectAttributes.addFlashAttribute("coordenadoresDeletado", true);
        return mav;
    }
}