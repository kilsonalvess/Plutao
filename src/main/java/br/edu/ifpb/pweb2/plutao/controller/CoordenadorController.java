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

    @GetMapping
    public ModelAndView home(ModelAndView mav){
        mav.setViewName("/coordenadores/home");
        return mav;
    }

    @GetMapping("processos")
    public ModelAndView listProcessos(ModelAndView mav){
        mav.addObject("processos", processoService.getProcessos());
        mav.setViewName("/coordenadores/list-processo");
        return mav;
    }

    @GetMapping("processos/{idProcesso}")
    public ModelAndView visualizarProcesso(ModelAndView mav, @PathVariable("idProcesso") Integer id){
        mav.addObject("processo", processoService.findById(id));
        mav.setViewName("/coordenadores/processo");
        return mav;
    }

    @PostMapping("processos/{idProcesso}")
    public ModelAndView salvarProcesso(
            ModelAndView mav,
            Processo processo,
            @PathVariable("id")Integer id,
            @PathVariable("idProcesso")Integer idProcesso,
            RedirectAttributes redirectAttributes
    ){
        processoService.atribuirProcesso(processo,idProcesso);
        mav.addObject("processos", processoService.getProcessos());
        mav.setViewName("redirect:/coordenadores/"+id+"/processos");
        redirectAttributes.addFlashAttribute("mensagem", "Processo designado com Sucesso");
        return mav;
    }


    @GetMapping("reunioes")
    public ModelAndView listReuniaos(ModelAndView mav, @PathVariable("id") Integer id){
        Coordenador coordenador = coordenadorService.getCoordenadorPorId(id);
        Colegiado colegiado = colegiadoService.getColegiadoPorCoordenador(coordenador);
        mav.addObject("reunioes", colegiado.getReunioes());
        mav.addObject("professor", coordenador.getProfessor());
        mav.setViewName("/coordenadores/list-reunioes");
        return mav;
    }

    @GetMapping("reunioes/criar")
    public ModelAndView criarReuniao(ModelAndView mav,@PathVariable("id")Integer id){
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
        mav.addObject("colegiado", colegiado);
        mav.addObject("processosEscolhidos", processosEscolhidos);
        mav.addObject("processosDisponiveis", processosDisponiveis);
        mav.addObject("reuniao", reuniao);
        mav.setViewName("/coordenadores/criar-reuniao");
        return mav;
    }

    @PostMapping("reunioes/criar")
    public ModelAndView saveReuniao(
            @Valid Reuniao reuniao,
            BindingResult validation,
            ModelAndView mav,
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
            mav.addObject("colegiado", colegiado);
            mav.addObject("processosEscolhidos", processosEscolhidos);
            mav.addObject("processosDisponiveis", processosDisponiveis);
            mav.addObject("reuniao", reuniao);
            mav.setViewName("/coordenadores/criar-reuniao");
            return mav;
        }
        Coordenador coordenador = coordenadorService.getCoordenadorPorId(id);
        Colegiado colegiado = colegiadoService.getColegiadoPorCoordenador(coordenador);
        reuniao.setColegiado(colegiado);
        reuniaoService.salvarReuniao(reuniao);
        System.out.println(reuniao.getColegiado());
        mav.addObject("reunioes", colegiado.getReunioes());
        mav.setViewName("redirect:/coordenador/"+id+"/reunioes");
        redirectAttributes.addFlashAttribute("mensagem", "Reunião Criada com Sucesso");
        redirectAttributes.addFlashAttribute("reuniaoSalvos", true);
        return mav;
    }

    //REUNIÃO
    @GetMapping("reunioes/{idReuniao}")
    public ModelAndView visualizarReuniao(ModelAndView mav, @PathVariable("id") Integer id,@PathVariable("idReuniao") Integer idReuniao){
        mav.addObject("reuniao", this.reuniaoService.getReuniaoPorId(idReuniao));
        mav.setViewName("/coordenadores/reuniao");
        return mav;
    }

    @PostMapping("reunioes/{idReuniao}/iniciar")
    public ModelAndView iniciarReuniao(Reuniao reuniao,ModelAndView mav, @PathVariable("id") Integer id,@PathVariable("idReuniao") Integer idReuniao, RedirectAttributes redirectAttributes){
        try{
            this.reuniaoService.iniciarReuniao(reuniao,idReuniao);
            mav.addObject("reuniao", this.reuniaoService.getReuniaoPorId(idReuniao));
            mav.setViewName("redirect:/coordenadores/"+id+"/reunioes/"+idReuniao+"/painel");
            return mav;
        }catch(Exception e){
            Coordenador coordenador = coordenadorService.getCoordenadorPorId(id);
            Colegiado colegiado = colegiadoService.getColegiadoPorCoordenador(coordenador);
            mav.addObject("reunioes", colegiado.getReunioes());
            mav.setViewName("redirect:/coordenadores/"+id+"/reunioes");
            redirectAttributes.addFlashAttribute("mensagem", e.getMessage());
            //redirectAttributes.addFlashAttribute("reuniaoIniciada", false);
            return mav;
        }

    }

    @GetMapping("reunioes/{idReuniao}/painel")
    public ModelAndView showReuniaoPainel(ModelAndView mav, @PathVariable("id") Integer id,@PathVariable("idReuniao") Integer idReuniao){
        mav.addObject("reuniao", this.reuniaoService.getReuniaoPorId(idReuniao));
        mav.setViewName("/coordenadores/reuniao");
        return mav;
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


}


