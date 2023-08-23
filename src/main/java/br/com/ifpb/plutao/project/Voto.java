package br.com.ifpb.plutao.project;

public class Voto extends Aluno{

    private int id;
    private String TipoVoto;

    private boolean Ausente;


    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getTipoVoto() {
        return TipoVoto;
    }

    public void setTipoVoto(String tipoVoto) {
        TipoVoto = tipoVoto;
    }

    public boolean isAusente() {
        return Ausente;
    }

    public void setAusente(boolean ausente) {
        Ausente = ausente;
    }
}