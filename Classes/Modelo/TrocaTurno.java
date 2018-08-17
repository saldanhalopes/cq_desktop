package Classes.Modelo;

import java.sql.Timestamp;

/**
 *
 * @author rafael.lopes
 */
public class TrocaTurno {

    private int troca_turno_id;
    private Cromatografo cromatografo;
    private String turno;
    private String descricao_turno;
    private Campanha campanha;
    private Timestamp data_registro;
    private Usuario user_name;

    public int getTroca_turno_id() {
        return troca_turno_id;
    }

    public void setTroca_turno_id(int troca_turno_id) {
        this.troca_turno_id = troca_turno_id;
    }

    public Cromatografo getCromatografo() {
        return cromatografo;
    }

    public void setCromatografo(Cromatografo cromatografo) {
        this.cromatografo = cromatografo;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getDescricao_turno() {
        return descricao_turno;
    }

    public void setDescricao_turno(String descricao_turno) {
        this.descricao_turno = descricao_turno;
    }

    public Campanha getCampanha() {
        return campanha;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }

    public Timestamp getData_registro() {
        return data_registro;
    }

    public void setData_registro(Timestamp data_registro) {
        this.data_registro = data_registro;
    }

    public Usuario getUser_name() {
        return user_name;
    }

    public void setUser_name(Usuario user_name) {
        this.user_name = user_name;
    }

   
    
}
