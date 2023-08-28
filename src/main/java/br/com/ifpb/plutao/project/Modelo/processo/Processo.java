package br.com.ifpb.plutao.project.Modelo.processo;

import br.com.ifpb.plutao.project.Modelo.aluno.Aluno;

import java.util.Date;

public class Processo extends Aluno {

    private int id;
    private String numero;
    private Date dataRecepecao;
    private Date dataDistribuicao;
    private Date dataParecer;
    private byte parecer;
    //*duvida nesse atributo decisaoRelator
    private TipoDecisao decisaoRelator;

}
