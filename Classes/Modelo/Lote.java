package Classes.Modelo;

import java.sql.Timestamp;

/**
 *
 * @author rafael.lopes
 */
public class Lote {

    private int lote_id;
    private Material produto;
    private String lote_produto;
    private int tamanho_lote;
    private java.sql.Timestamp data_entrada;
    private java.sql.Timestamp data_prevista;
    private String lote_status;
    private Analise analise;
    private Metodologia metodo;
    private Metodologia metodo2;
    private String etapa;
    private String lote_obs;
    private String quantidade_amostra;
    private Timestamp data_registro;
    private Usuario user;

    public int getLote_id() {
        return lote_id;
    }

    public void setLote_id(int lote_id) {
        this.lote_id = lote_id;
    }

    public Material getProduto() {
        return produto;
    }

    public void setProduto(Material produto) {
        this.produto = produto;
    }

    public String getLote_produto() {
        return lote_produto;
    }

    public void setLote_produto(String lote_produto) {
        this.lote_produto = lote_produto;
    }

    public int getTamanho_lote() {
        return tamanho_lote;
    }

    public void setTamanho_lote(int tamanho_lote) {
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

    public Analise getAnalise() {
        return analise;
    }

    public void setAnalise(Analise analise) {
        this.analise = analise;
    }

    public Metodologia getMetodo() {
        return metodo;
    }

    public void setMetodo(Metodologia metodo) {
        this.metodo = metodo;
    }

    public Metodologia getMetodo2() {
        return metodo2;
    }

    public void setMetodo2(Metodologia metodo2) {
        this.metodo2 = metodo2;
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

    public String getLote_obs() {
        return lote_obs;
    }

    public void setLote_obs(String lote_obs) {
        this.lote_obs = lote_obs;
    }

    public String getQuantidade_amostra() {
        return quantidade_amostra;
    }

    public void setQuantidade_amostra(String quantidade_amostra) {
        this.quantidade_amostra = quantidade_amostra;
    }

    public Timestamp getData_registro() {
        return data_registro;
    }

    public void setData_registro(Timestamp data_registro) {
        this.data_registro = data_registro;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    

}
