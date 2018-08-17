package Classes.Modelo;

import java.sql.Timestamp;

/**
 *
 * @author rafael.lopes
 */
public class Acompanhamento {

    private int registro_acompanhamento_id;
    private int lote_acompanhamento_id;
    private Metodologia metodo;
    private Lote lote;
    private Setor setor;
    private Material material;
    private String registro_acompanhamento;
    private String sigla_registro_acompanhamento;
    private String categoria;
    private int versao;
    private Boolean status;
    private Timestamp data_registro;
    private Usuario user_registro;
    private String codigo_barras;
    private Boolean registro_acompanhamento_ativo;

    public int getRegistro_acompanhamento_id() {
        return registro_acompanhamento_id;
    }

    public void setRegistro_acompanhamento_id(int registro_acompanhamento_id) {
        this.registro_acompanhamento_id = registro_acompanhamento_id;
    }

    public int getLote_acompanhamento_id() {
        return lote_acompanhamento_id;
    }

    public void setLote_acompanhamento_id(int lote_acompanhamento_id) {
        this.lote_acompanhamento_id = lote_acompanhamento_id;
    }

    public Metodologia getMetodo() {
        return metodo;
    }

    public void setMetodo(Metodologia metodo) {
        this.metodo = metodo;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String getRegistro_acompanhamento() {
        return registro_acompanhamento;
    }

    public void setRegistro_acompanhamento(String registro_acompanhamento) {
        this.registro_acompanhamento = registro_acompanhamento;
    }

    public String getSigla_registro_acompanhamento() {
        return sigla_registro_acompanhamento;
    }

    public void setSigla_registro_acompanhamento(String sigla_registro_acompanhamento) {
        this.sigla_registro_acompanhamento = sigla_registro_acompanhamento;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getVersao() {
        return versao;
    }

    public void setVersao(int versao) {
        this.versao = versao;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Timestamp getData_registro() {
        return data_registro;
    }

    public void setData_registro(Timestamp data_registro) {
        this.data_registro = data_registro;
    }

    public Usuario getUser_registro() {
        return user_registro;
    }

    public void setUser_registro(Usuario user_registro) {
        this.user_registro = user_registro;
    }

    public String getCodigo_barras() {
        return codigo_barras;
    }

    public void setCodigo_barras(String codigo_barras) {
        this.codigo_barras = codigo_barras;
    }

    public Boolean getRegistro_acompanhamento_ativo() {
        return registro_acompanhamento_ativo;
    }

    public void setRegistro_acompanhamento_ativo(Boolean registro_acompanhamento_ativo) {
        this.registro_acompanhamento_ativo = registro_acompanhamento_ativo;
    }

    
}
