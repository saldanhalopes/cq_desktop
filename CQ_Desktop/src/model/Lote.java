package model;

import java.sql.Timestamp;

/**
 *
 * @author rafael.lopes
 */
public class Lote {

    private Integer lote_id;
    private Material produto;
    private String lote_produto;
    private Double tamanho_lote;
    private java.sql.Timestamp data_entrada;
    private java.sql.Timestamp data_prevista;
    private String lote_status;
    private Analise analise;
    private Metodologia metodo;
    private String lote_obs;
    private String quantidade_amostra;

    public Integer getLote_id() {
        return lote_id;
    }

    public void setLote_id(Integer lote_id) {
        this.lote_id = lote_id;
    }

    public String getLote_produto() {
        return lote_produto;
    }

    public void setLote_produto(String lote_produto) {
        this.lote_produto = lote_produto;
    }

    public Double getTamanho_lote() {
        return tamanho_lote;
    }

    public void setTamanho_lote(Double tamanho_lote) {
        this.tamanho_lote = tamanho_lote;
    }

    public Timestamp getData_entrada() {
        return data_entrada;
    }

    public void setData_entrada(Timestamp data_entrada) {
        this.data_entrada = data_entrada;
    }

    public Timestamp getData_prevista() {
        return data_prevista;
    }

    public void setData_prevista(Timestamp data_prevista) {
        this.data_prevista = data_prevista;
    }

    public String getLote_status() {
        return lote_status;
    }

    public void setLote_status(String lote_status) {
        this.lote_status = lote_status;
    }

    public Material getProduto() {
        return produto;
    }

    public void setProduto(Material produto) {
        this.produto = produto;
    }

    public Analise getAnalise() {
        return analise;
    }

    public void setAnalise(Analise analise) {
        this.analise = analise;
    }

    public String getLote_obs() {
        return lote_obs;
    }

    public void setLote_obs(String lote_obs) {
        this.lote_obs = lote_obs;
    }

    public Metodologia getMetodo() {
        return metodo;
    }

    public void setMetodo(Metodologia metodo) {
        this.metodo = metodo;
    }

    public String getQuantidade_amostra() {
        return quantidade_amostra;
    }

    public void setQuantidade_amostra(String quantidade_amostra) {
        this.quantidade_amostra = quantidade_amostra;
    }

}
