/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Modelo;

import java.sql.Timestamp;

/**
 *
 * @author rafael.lopes
 */
public class Cromatografo {

    private int cromatografo_id;
    private String system_name;
    private String node;
    private Setor setor;
    private String modelo;
    private String tipo;
    private String controladora;
    private String degaseificador;
    private String bomba;
    private String injetor;
    private String forno;
    private String detector;

    //tb_log_cromatografo
    private int log_cromatografo_id;
    private Analise analise;
    private Timestamp data_inicio;
    private Timestamp data_fim;
    private Timestamp data_registro;
    private String descricao;
    private Usuario usuario;
    private String metodos;
    private String obs_log;
    private Coluna coluna;
    private FaseMovel fasemovel;
    private Boolean log_ativo;
    private Campanha campanha;

    public int getCromatografo_id() {
        return cromatografo_id;
    }

    public void setCromatografo_id(int cromatografo_id) {
        this.cromatografo_id = cromatografo_id;
    }

    public String getSystem_name() {
        return system_name;
    }

    public void setSystem_name(String system_name) {
        this.system_name = system_name;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getControladora() {
        return controladora;
    }

    public void setControladora(String controladora) {
        this.controladora = controladora;
    }

    public String getDegaseificador() {
        return degaseificador;
    }

    public void setDegaseificador(String degaseificador) {
        this.degaseificador = degaseificador;
    }

    public String getBomba() {
        return bomba;
    }

    public void setBomba(String bomba) {
        this.bomba = bomba;
    }

    public String getInjetor() {
        return injetor;
    }

    public void setInjetor(String injetor) {
        this.injetor = injetor;
    }

    public String getForno() {
        return forno;
    }

    public void setForno(String forno) {
        this.forno = forno;
    }

    public String getDetector() {
        return detector;
    }

    public void setDetector(String detector) {
        this.detector = detector;
    }

    public int getLog_cromatografo_id() {
        return log_cromatografo_id;
    }

    public void setLog_cromatografo_id(int log_cromatografo_id) {
        this.log_cromatografo_id = log_cromatografo_id;
    }

    public Analise getAnalise() {
        return analise;
    }

    public void setAnalise(Analise analise) {
        this.analise = analise;
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

    public Timestamp getData_registro() {
        return data_registro;
    }

    public void setData_registro(Timestamp data_registro) {
        this.data_registro = data_registro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getMetodos() {
        return metodos;
    }

    public void setMetodos(String metodos) {
        this.metodos = metodos;
    }

    public String getObs_log() {
        return obs_log;
    }

    public void setObs_log(String obs_log) {
        this.obs_log = obs_log;
    }

    public Coluna getColuna() {
        return coluna;
    }

    public void setColuna(Coluna coluna) {
        this.coluna = coluna;
    }

    public FaseMovel getFasemovel() {
        return fasemovel;
    }

    public void setFasemovel(FaseMovel fasemovel) {
        this.fasemovel = fasemovel;
    }

    public Boolean getLog_ativo() {
        return log_ativo;
    }

    public void setLog_ativo(Boolean log_ativo) {
        this.log_ativo = log_ativo;
    }

    public Campanha getCampanha() {
        return campanha;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }

   
}
