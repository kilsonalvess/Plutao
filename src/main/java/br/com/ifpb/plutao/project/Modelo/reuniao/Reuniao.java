package br.com.ifpb.plutao.project.Modelo.reuniao;

import br.com.ifpb.plutao.project.Modelo.aluno.Aluno;

import java.util.Date;
public class Reuniao extends Aluno {

    private int id;
    private Date dataReuniao;
    //*duvida na referenciação desse atributo
    private StatusReuniao reuniao;
    private byte[] ata;
}
