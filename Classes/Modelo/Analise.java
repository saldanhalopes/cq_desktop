package Classes.Modelo;

import java.sql.Timestamp;

/**
 *
 * @author rafael.lopes
 */
public class Analise {

    //tb_analise
    private int analise_id;
    private String analise;
    private String sigla_analise;
    private String descricao_analise;
    //tb_analise_amostra
    private int analise_amostra_id;
    private String analise_amostra;
    private String sigla_analise_amostra;
    private String descricao_analise_amostra;
    //tb_analise_finalidade
    private int analise_finalidade_id;
    private String analise_finalidade;
    private String sigla_analise_finalidade;
    private String descricao_analise_finalidade;
    //tb_analise_produtividade
    private int analise_produtividade_id;
    private String analise_produtividade;
    private String sigla_analise_produtividade;
    private String descricao_analise_produtividade;
    private String cor;
    //tb_analise_status
    private int analise_status_id;
    private String analise_status;
    private String sigla_analise_status;
    private String descricao_analise_status;
    //tb_metodologia_analise
    private Boolean metodologia_analise_ativo;
    private int metodologia_analise_id;
    private int metodo_id_metodologia_analise_id;
    private Setor setor;
    private Acompanhamento acompanhamento;
    private Lote lote;
    private Timestamp data_hora_registro;
    private Usuario user_registro;
    private String status;

    public int getAnalise_id() {
        return analise_id;
    }

    public void setAnalise_id(int analise_id) {
        this.analise_id = analise_id;
    }

    public String getAnalise() {
        return analise;
    }

    public void setAnalise(String analise) {
        this.analise = analise;
    }

    public String getSigla_analise() {
        return sigla_analise;
    }

    public void setSigla_analise(String sigla_analise) {
        this.sigla_analise = sigla_analise;
    }

    public String getDescricao_analise() {
        return descricao_analise;
    }

    public void setDescricao_analise(String descricao_analise) {
        this.descricao_analise = descricao_analise;
    }

    public int getAnalise_amostra_id() {
        return analise_amostra_id;
    }

    public void setAnalise_amostra_id(int analise_amostra_id) {
        this.analise_amostra_id = analise_amostra_id;
    }

    public String getAnalise_amostra() {
        return analise_amostra;
    }

    public void setAnalise_amostra(String analise_amostra) {
        this.analise_amostra = analise_amostra;
    }

    public String getSigla_analise_amostra() {
        return sigla_analise_amostra;
    }

    public void setSigla_analise_amostra(String sigla_analise_amostra) {
        this.sigla_analise_amostra = sigla_analise_amostra;
    }

    public String getDescricao_analise_amostra() {
        return descricao_analise_amostra;
    }

    public void setDescricao_analise_amostra(String descricao_analise_amostra) {
        this.descricao_analise_amostra = descricao_analise_amostra;
    }

    public int getAnalise_finalidade_id() {
        return analise_finalidade_id;
    }

    public void setAnalise_finalidade_id(int analise_finalidade_id) {
        this.analise_finalidade_id = analise_finalidade_id;
    }

    public String getAnalise_finalidade() {
        return analise_finalidade;
    }

    public void setAnalise_finalidade(String analise_finalidade) {
        this.analise_finalidade = analise_finalidade;
    }

    public String getSigla_analise_finalidade() {
        return sigla_analise_finalidade;
    }

    public void setSigla_analise_finalidade(String sigla_analise_finalidade) {
        this.sigla_analise_finalidade = sigla_analise_finalidade;
    }

    public String getDescricao_analise_finalidade() {
        return descricao_analise_finalidade;
    }

    public void setDescricao_analise_finalidade(String descricao_analise_finalidade) {
        this.descricao_analise_finalidade = descricao_analise_finalidade;
    }

    public int getAnalise_produtividade_id() {
        return analise_produtividade_id;
    }

    public void setAnalise_produtividade_id(int analise_produtividade_id) {
        this.analise_produtividade_id = analise_produtividade_id;
    }

    public String getAnalise_produtividade() {
        return analise_produtividade;
    }

    public void setAnalise_produtividade(String analise_produtividade) {
        this.analise_produtividade = analise_produtividade;
    }

    public String getSigla_analise_produtividade() {
        return sigla_analise_produtividade;
    }

    public void setSigla_analise_produtividade(String sigla_analise_produtividade) {
        this.sigla_analise_produtividade = sigla_analise_produtividade;
    }

    public String getDescricao_analise_produtividade() {
        return descricao_analise_produtividade;
    }

    public void setDescricao_analise_produtividade(String descricao_analise_produtividade) {
        this.descricao_analise_produtividade = descricao_analise_produtividade;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getAnalise_status_id() {
        return analise_status_id;
    }

    public void setAnalise_status_id(int analise_status_id) {
        this.analise_status_id = analise_status_id;
    }

    public String getAnalise_status() {
        return analise_status;
    }

    public void setAnalise_status(String analise_status) {
        this.analise_status = analise_status;
    }

    public String getSigla_analise_status() {
        return sigla_analise_status;
    }

    public void setSigla_analise_status(String sigla_analise_status) {
        this.sigla_analise_status = sigla_analise_status;
    }

    public String getDescricao_analise_status() {
        return descricao_analise_status;
    }

    public void setDescricao_analise_status(String descricao_analise_status) {
        this.descricao_analise_status = descricao_analise_status;
    }

    public Boolean getMetodologia_analise_ativo() {
        return metodologia_analise_ativo;
    }

    public void setMetodologia_analise_ativo(Boolean metodologia_analise_ativo) {
        this.metodologia_analise_ativo = metodologia_analise_ativo;
    }

    public int getMetodologia_analise_id() {
        return metodologia_analise_id;
    }

    public void setMetodologia_analise_id(int metodologia_analise_id) {
        this.metodologia_analise_id = metodologia_analise_id;
    }

    public int getMetodo_id_metodologia_analise_id() {
        return metodo_id_metodologia_analise_id;
    }

    public void setMetodo_id_metodologia_analise_id(int metodo_id_metodologia_analise_id) {
        this.metodo_id_metodologia_analise_id = metodo_id_metodologia_analise_id;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public Acompanhamento getAcompanhamento() {
        return acompanhamento;
    }

    public void setAcompanhamento(Acompanhamento acompanhamento) {
        this.acompanhamento = acompanhamento;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public Timestamp getData_hora_registro() {
        return data_hora_registro;
    }

    public void setData_hora_registro(Timestamp data_hora_registro) {
        this.data_hora_registro = data_hora_registro;
    }

    public Usuario getUser_registro() {
        return user_registro;
    }

    public void setUser_registro(Usuario user_registro) {
        this.user_registro = user_registro;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}
