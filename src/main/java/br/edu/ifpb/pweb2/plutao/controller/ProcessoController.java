package br.edu.ifpb.pweb2.plutao.controller;

import br.edu.ifpb.pweb2.plutao.model.Aluno;
import br.edu.ifpb.pweb2.plutao.model.Assunto;
import br.edu.ifpb.pweb2.plutao.model.Colegiado;
import br.edu.ifpb.pweb2.plutao.model.Processo;
import br.edu.ifpb.pweb2.plutao.repository.AlunoRepository;
import br.edu.ifpb.pweb2.plutao.repository.ProcessoRepository;
import br.edu.ifpb.pweb2.plutao.service.AlunoService;
import br.edu.ifpb.pweb2.plutao.service.AssuntoService;
import br.edu.ifpb.pweb2.plutao.service.ColegiadoService;
import br.edu.ifpb.pweb2.plutao.service.ProcessoService;
import br.edu.ifpb.pweb2.plutao.ui.NavPage;
import br.edu.ifpb.pweb2.plutao.ui.NavePageBuilder;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
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
@RequestMapping("/processos")
public class ProcessoController {

    @Autowired
    private ProcessoService processoService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private AssuntoService assuntoService;

    @Autowired
    private ColegiadoService colegiadoService;

    @RequestMapping("/form")
    public ModelAndView getForm( Processo processo, ModelAndView mav){
        mav.addObject("processo", processo);
        mav.setViewName("processos/form");
        return mav;
    }

    @ModelAttribute("alunoItens")
    public Page<Aluno> getAlunos(Pageable page) {
        return alunoService.findAll(page);
    }

    @ModelAttribute("assuntosItens")
    public Page<Assunto> getAssuntos(Pageable page) {
        return assuntoService.findAll(page);
    }

    @ModelAttribute("colegiados")
    public List<Colegiado> getColegiados() {
        return colegiadoService.getColegiados();
    }

    @PostMapping
    public ModelAndView saveProcesso(@Valid Processo processo, BindingResult validation, ModelAndView mav, RedirectAttributes attr){
        if (validation.hasErrors()) {
            mav.addObject("message", "Erros de validação! Corrija-os e tente novamente.");
            mav.setViewName("processos/form");
            return mav;
        }if (processo.getId() == null) {
            attr.addFlashAttribute("mensagem", "Processo cadastrado com sucesso!");

        } else {
            attr.addFlashAttribute("mensagem", "Processo editado com sucesso!");
        }
        processoService.salvarProcesso(processo);
        mav.setViewName("redirect:/processos");
        return mav;
    }

    @GetMapping
    public ModelAndView listAll(ModelAndView mav, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "3") int size) {
        Pageable paging = PageRequest.of(page - 1, size);
        Page<Processo> pageProcessos = processoService.findAll(paging);
        NavPage navPage = NavePageBuilder.newNavPage(pageProcessos.getNumber() + 1,
                pageProcessos.getTotalElements(), pageProcessos.getTotalPages(), size);
        mav.addObject("processos", pageProcessos);
        mav.addObject("navPage", navPage);
        mav.setViewName("processos/list");
        return mav;
    }

    @RequestMapping("/{id}")
    public ModelAndView atualizarProcesso(@PathVariable(value = "id") Integer id, ModelAndView mav) {
        mav.addObject("processo", processoService.findById(id));
        mav.setViewName("processos/form");
        return mav;
    }

    @RequestMapping("/{id}/delete")
    public ModelAndView deletarProcesso(@PathVariable(value = "id") Integer id, ModelAndView mav, RedirectAttributes attr) {
        processoService.deleteById(id);
        attr.addFlashAttribute("mensagem", "Processo removido com sucesso!");
        mav.setViewName("redirect:/processos");
        return mav;
    }

}
