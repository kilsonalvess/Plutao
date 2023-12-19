package br.edu.ifpb.pweb2.plutao.controller;

import br.edu.ifpb.pweb2.plutao.enums.StatusReuniao;
import br.edu.ifpb.pweb2.plutao.model.*;
import br.edu.ifpb.pweb2.plutao.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("/coordenador/{id}")
public class CoordenadorController {

    @Autowired
    private ProcessoService processoService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private CoordenadorService coordenadorService;

    @Autowired
    private ColegiadoService colegiadoService;

    @Autowired
    private ReuniaoService reuniaoService;

    @ModelAttribute("relatores")
    public List<Professor> getRelatores(){
        return this.professorService.getProfessoresComProcessos();
    }

    @ModelAttribute("professores")
    public List<Professor> getProfessores(){
        return this.professorService.getProfessoresComColegiado();
    }

    @ModelAttribute("alunos")
    public List<Aluno> getAlunos(){
        return this.alunoService.getAlunosComProcessos();
    }

    @ModelAttribute("coordenador")
    public Coordenador getCoordenador(@PathVariable("id") Integer id){
        return this.coordenadorService.getCoordenadorPorId(id);
    }

    @ModelAttribute("programada")
    public StatusReuniao getProgramada(){
        return StatusReuniao.PROGRAMADA;
    }

    @ModelAttribute("encerrada")
    public StatusReuniao getEncerrada(){
        return StatusReuniao.ENCERRADA;
    }
    @ModelAttribute("emAndamento")
    public StatusReuniao getEmAndamento(){
        return StatusReuniao.EM_ANDAMENTO;
    }

    @GetMapping
    public ModelAndView home(ModelAndView model){
        model.setViewName("/coordenador/home");
        return model;
    }

    @GetMapping("processos")
    public ModelAndView showPainelProcessos(ModelAndView model){
        model.addObject("processos", processoService.getProcessos());
        model.setViewName("/coordenador/painel-processo");
        return model;
    }

    //------ Processos ---------

    @GetMapping("processos/{idProcesso}")
    public ModelAndView showProcesso(ModelAndView model, @PathVariable("idProcesso") Integer id){
        model.addObject("processo", processoService.getProcessoPorId(id));
        model.setViewName("/coordenador/processo");
        return model;
    }

    @PostMapping("processos/{idProcesso}")
    public ModelAndView salvarProcesso(
            ModelAndView model,
            Processo processo,
            @PathVariable("id")Integer id,
            @PathVariable("idProcesso")Integer idProcesso,
            RedirectAttributes redirectAttributes
    ){
        processoService.atribuirProcesso(processo,idProcesso);
        model.addObject("processos", processoService.getProcessos());
        model.setViewName("redirect:/coordenador/"+id+"/processos");
        redirectAttributes.addFlashAttribute("mensagem", "Processo designado com Sucesso");
        return model;
    }

    //------ REUNIÕES ---------

    @GetMapping("reunioes")
    public ModelAndView showPainelReuniaos(ModelAndView model, @PathVariable("id") Integer id){
        Coordenador coordenador = coordenadorService.getCoordenadorPorId(id);
        Colegiado colegiado = colegiadoService.getColegiadoPorCoordenador(coordenador);
        model.addObject("reunioes", colegiado.getReunioes());
        model.addObject("professor", coordenador.getProfessor());
        model.setViewName("/coordenador/painel-reunioes");
        return model;
    }

    @GetMapping("reunioes/criar")
    public ModelAndView createReuniao(ModelAndView model,@PathVariable("id")Integer id){
        List<Processo> processosDisponiveis = new ArrayList<Processo>();
        Coordenador coordenador = coordenadorService.getCoordenadorPorId(id);
        Colegiado colegiado = colegiadoService.getColegiadoPorCoordenador(coordenador);

        for(Processo processo : colegiado.getProcessos()){
            if(processo.getRelator()!= null){
                processosDisponiveis.add(processo);
            }
        }

        List<Processo> processosEscolhidos = new ArrayList<Processo>();
        for(int i=0;i<5;i++){
            processosEscolhidos.add(new Processo());
        }
        Reuniao reuniao = new Reuniao(colegiado,processosEscolhidos);
        System.out.println(reuniao.getColegiado());
        model.addObject("colegiado", colegiado);
        model.addObject("processosEscolhidos", processosEscolhidos);
        model.addObject("processosDisponiveis", processosDisponiveis);
        model.addObject("reuniao", reuniao);
        model.setViewName("/coordenador/criar-reuniao");
        return model;
    }

    @PostMapping("reunioes/criar")
    public ModelAndView saveReuniao(
            @Valid Reuniao reuniao,
            BindingResult validation,
            ModelAndView model,
            @PathVariable("id")Integer id,
            RedirectAttributes redirectAttributes
    ){
        if (validation.hasErrors()) {
            List<Processo> processosDisponiveis = new ArrayList<Processo>();
            Coordenador coordenador = coordenadorService.getCoordenadorPorId(id);
            Colegiado colegiado = colegiadoService.getColegiadoPorCoordenador(coordenador);

            for(Processo processo : colegiado.getProcessos()){
                if(processo.getRelator()!= null){
                    processosDisponiveis.add(processo);
                }
            }

            List<Processo> processosEscolhidos = new ArrayList<Processo>();
            for(int i=0;i<4;i++){
                processosEscolhidos.add(new Processo());
            }
            model.addObject("colegiado", colegiado);
            model.addObject("processosEscolhidos", processosEscolhidos);
            model.addObject("processosDisponiveis", processosDisponiveis);
            model.addObject("reuniao", reuniao);
            model.setViewName("/coordenador/criar-reuniao");
            return model;
        }
        Coordenador coordenador = coordenadorService.getCoordenadorPorId(id);
        Colegiado colegiado = colegiadoService.getColegiadoPorCoordenador(coordenador);
        reuniao.setColegiado(colegiado);
        reuniaoService.salvarReuniao(reuniao);
        System.out.println(reuniao.getColegiado());
        model.addObject("reunioes", colegiado.getReunioes());
        model.setViewName("redirect:/coordenador/"+id+"/reunioes");
        redirectAttributes.addFlashAttribute("mensagem", "Reunião Criada com Sucesso");
        redirectAttributes.addFlashAttribute("reuniaoSalvos", true);
        return model;
    }

    //REUNIÃO
    @GetMapping("reunioes/{idReuniao}")
    public ModelAndView showReuniao(ModelAndView model, @PathVariable("id") Integer id,@PathVariable("idReuniao") Integer idReuniao){
        model.addObject("reuniao", this.reuniaoService.getReuniaoPorId(idReuniao));
        model.setViewName("/coordenador/reuniao");
        return model;
    }

    @PostMapping("reunioes/{idReuniao}/iniciar")
    public ModelAndView iniciarReuniao(Reuniao reuniao,ModelAndView model, @PathVariable("id") Integer id,@PathVariable("idReuniao") Integer idReuniao, RedirectAttributes redirectAttributes){
        try{
            this.reuniaoService.iniciarReuniao(reuniao,idReuniao);
            model.addObject("reuniao", this.reuniaoService.getReuniaoPorId(idReuniao));
            model.setViewName("redirect:/coordenador/"+id+"/reunioes/"+idReuniao+"/painel");
            return model;
        }catch(Exception e){
            Coordenador coordenador = coordenadorService.getCoordenadorPorId(id);
            Colegiado colegiado = colegiadoService.getColegiadoPorCoordenador(coordenador);
            model.addObject("reunioes", colegiado.getReunioes());
            model.setViewName("redirect:/coordenador/"+id+"/reunioes");
            redirectAttributes.addFlashAttribute("mensagem", e.getMessage());
            //redirectAttributes.addFlashAttribute("reuniaoIniciada", false);
            return model;
        }

    }

    @GetMapping("reunioes/{idReuniao}/painel")
    public ModelAndView showReuniaoPainel(ModelAndView model, @PathVariable("id") Integer id,@PathVariable("idReuniao") Integer idReuniao){
        model.addObject("reuniao", this.reuniaoService.getReuniaoPorId(idReuniao));
        model.setViewName("/coordenador/reuniao");
        return model;
    }

}


