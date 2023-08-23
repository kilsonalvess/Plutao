package br.com.ifpb.plutao.project;

import java.util.Date;

public class Processo extends Aluno{

    private int id;
    private String numero;
    private Date dataDaRecepecao;
    private Date dataDistribuicao;
    private Date dataDoParecer;
    private byte Parecer;
    //*duvida nesse atributo decisaoRelator
    private String decisaoRelator;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getDataDaRecepecao() {
        return dataDaRecepecao;
    }

    public void setDataDaRecepecao(Date dataDaRecepecao) {
        this.dataDaRecepecao = dataDaRecepecao;
    }

    public Date getDataDistribuicao() {
        return dataDistribuicao;
    }

    public void setDataDistribuicao(Date dataDistribuicao) {
        this.dataDistribuicao = dataDistribuicao;
    }

    public Date getDataDoParecer() {
        return dataDoParecer;
    }

    public void setDataDoParecer(Date dataDoParecer) {
        this.dataDoParecer = dataDoParecer;
    }

    public byte getParecer() {
        return Parecer;
    }

    public void setParecer(byte parecer) {
        Parecer = parecer;
    }

    public String getDecisaoRelator() {
        return decisaoRelator;
    }

    public void setDecisaoRelator(String decisaoRelator) {
        this.decisaoRelator = decisaoRelator;
    }
}
