package br.com.ifpb.plutao.project;

import java.util.Date;
public class Colegiado extends Aluno{
    private int id;
    private Date getDataInicio;
    private Date dataFim;
    private String descricao;
    private String portaria;
    private String curso;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public Date getGetDataInicio() {
        return getDataInicio;
    }

    public void setGetDataInicio(Date getDataInicio) {
        this.getDataInicio = getDataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPortaria() {
        return portaria;
    }

    public void setPortaria(String portaria) {
        this.portaria = portaria;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
