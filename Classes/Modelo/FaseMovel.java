package Classes.Modelo;

import java.sql.Timestamp;

/**
 *
 * @author rafael.lopes
 */
public class FaseMovel {

    private int fase_movel_id;
    private String fase_movel_a;
    private int fase_movel_a_qt;
    private String fase_movel_a_lote;
    private Timestamp fase_movel_a_validade;
    private String fase_movel_b;
    private int fase_movel_b_qt;
    private String fase_movel_b_lote;
    private Timestamp fase_movel_b_validade;
    private String fase_movel_c;
    private int fase_movel_c_qt;
    private String fase_movel_c_lote;
    private Timestamp fase_movel_c_validade;
    private String fase_movel_d;
    private int fase_movel_d_qt;
    private String fase_movel_d_lote;
    private Timestamp fase_movel_d_validade;
    private String fase_movel_e;
    private String fase_movel_e_lote;
    private Timestamp fase_movel_e_validade;
    private String fase_movel_f;
    private String fase_movel_f_lote;
    private Timestamp fase_movel_f_validade;
    private Usuario user;
    private Timestamp data_registro;
    private Timestamp data_inicio;
    private Timestamp data_fim;
    private Cromatografo cromatografo;
    private Campanha campanha;

    public int getFase_movel_id() {
        return fase_movel_id;
    }

    public void setFase_movel_id(int fase_movel_id) {
        this.fase_movel_id = fase_movel_id;
    }

    public String getFase_movel_a() {
        return fase_movel_a;
    }

    public void setFase_movel_a(String fase_movel_a) {
        this.fase_movel_a = fase_movel_a;
    }

    public int getFase_movel_a_qt() {
        return fase_movel_a_qt;
    }

    public void setFase_movel_a_qt(int fase_movel_a_qt) {
        this.fase_movel_a_qt = fase_movel_a_qt;
    }

    public String getFase_movel_a_lote() {
        return fase_movel_a_lote;
    }

    public void setFase_movel_a_lote(String fase_movel_a_lote) {
        this.fase_movel_a_lote = fase_movel_a_lote;
    }

    public Timestamp getFase_movel_a_validade() {
        return fase_movel_a_validade;
    }

    public void setFase_movel_a_validade(Timestamp fase_movel_a_validade) {
        this.fase_movel_a_validade = fase_movel_a_validade;
    }

    public String getFase_movel_b() {
        return fase_movel_b;
    }

    public void setFase_movel_b(String fase_movel_b) {
        this.fase_movel_b = fase_movel_b;
    }

    public int getFase_movel_b_qt() {
        return fase_movel_b_qt;
    }

    public void setFase_movel_b_qt(int fase_movel_b_qt) {
        this.fase_movel_b_qt = fase_movel_b_qt;
    }

    public String getFase_movel_b_lote() {
        return fase_movel_b_lote;
    }

    public void setFase_movel_b_lote(String fase_movel_b_lote) {
        this.fase_movel_b_lote = fase_movel_b_lote;
    }

    public Timestamp getFase_movel_b_validade() {
        return fase_movel_b_validade;
    }

    public void setFase_movel_b_validade(Timestamp fase_movel_b_validade) {
        this.fase_movel_b_validade = fase_movel_b_validade;
    }

    public String getFase_movel_c() {
        return fase_movel_c;
    }

    public void setFase_movel_c(String fase_movel_c) {
        this.fase_movel_c = fase_movel_c;
    }

    public int getFase_movel_c_qt() {
        return fase_movel_c_qt;
    }

    public void setFase_movel_c_qt(int fase_movel_c_qt) {
        this.fase_movel_c_qt = fase_movel_c_qt;
    }

    public String getFase_movel_c_lote() {
        return fase_movel_c_lote;
    }

    public void setFase_movel_c_lote(String fase_movel_c_lote) {
        this.fase_movel_c_lote = fase_movel_c_lote;
    }

    public Timestamp getFase_movel_c_validade() {
        return fase_movel_c_validade;
    }

    public void setFase_movel_c_validade(Timestamp fase_movel_c_validade) {
        this.fase_movel_c_validade = fase_movel_c_validade;
    }

    public String getFase_movel_d() {
        return fase_movel_d;
    }

    public void setFase_movel_d(String fase_movel_d) {
        this.fase_movel_d = fase_movel_d;
    }

    public int getFase_movel_d_qt() {
        return fase_movel_d_qt;
    }

    public void setFase_movel_d_qt(int fase_movel_d_qt) {
        this.fase_movel_d_qt = fase_movel_d_qt;
    }

    public String getFase_movel_d_lote() {
        return fase_movel_d_lote;
    }

    public void setFase_movel_d_lote(String fase_movel_d_lote) {
        this.fase_movel_d_lote = fase_movel_d_lote;
    }

    public Timestamp getFase_movel_d_validade() {
        return fase_movel_d_validade;
    }

    public void setFase_movel_d_validade(Timestamp fase_movel_d_validade) {
        this.fase_movel_d_validade = fase_movel_d_validade;
    }

    public String getFase_movel_e() {
        return fase_movel_e;
    }

    public void setFase_movel_e(String fase_movel_e) {
        this.fase_movel_e = fase_movel_e;
    }

    public String getFase_movel_e_lote() {
        return fase_movel_e_lote;
    }

    public void setFase_movel_e_lote(String fase_movel_e_lote) {
        this.fase_movel_e_lote = fase_movel_e_lote;
    }

    public Timestamp getFase_movel_e_validade() {
        return fase_movel_e_validade;
    }

    public void setFase_movel_e_validade(Timestamp fase_movel_e_validade) {
        this.fase_movel_e_validade = fase_movel_e_validade;
    }

    public String getFase_movel_f() {
        return fase_movel_f;
    }

    public void setFase_movel_f(String fase_movel_f) {
        this.fase_movel_f = fase_movel_f;
    }

    public String getFase_movel_f_lote() {
        return fase_movel_f_lote;
    }

    public void setFase_movel_f_lote(String fase_movel_f_lote) {
        this.fase_movel_f_lote = fase_movel_f_lote;
    }

    public Timestamp getFase_movel_f_validade() {
        return fase_movel_f_validade;
    }

    public void setFase_movel_f_validade(Timestamp fase_movel_f_validade) {
        this.fase_movel_f_validade = fase_movel_f_validade;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Timestamp getData_registro() {
        return data_registro;
    }

    public void setData_registro(Timestamp data_registro) {
        this.data_registro = data_registro;
    }

    public Timestamp getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Timestamp data_inicio) {
        this.data_inicio = data_inicio;
    }

    public Timestamp getData_fim() {
        return data_fim;
    }

    public void setData_fim(Timestamp data_fim) {
        this.data_fim = data_fim;
    }

    public Cromatografo getCromatografo() {
        return cromatografo;
    }

    public void setCromatografo(Cromatografo cromatografo) {
        this.cromatografo = cromatografo;
    }

    public Campanha getCampanha() {
        return campanha;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }

    

    
}
