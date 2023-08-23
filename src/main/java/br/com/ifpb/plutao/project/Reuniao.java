package br.com.ifpb.plutao.project;

import java.util.Date;
public class Reuniao extends Aluno  {

    private int id;
    private Date dataReuniao;
    //*duvida na referenciação desse atributo
    private boolean Reuniao;
    private byte ata;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public Date getDataReuniao() {
        return dataReuniao;
    }

    public void setDataReuniao(Date dataReuniao) {
        this.dataReuniao = dataReuniao;
    }

    public boolean isReuniao() {
        return Reuniao;
    }

    public void setReuniao(boolean reuniao) {
        Reuniao = reuniao;
    }

    public byte getAta() {
        return ata;
    }

    public void setAta(byte ata) {
        this.ata = ata;
    }
}
