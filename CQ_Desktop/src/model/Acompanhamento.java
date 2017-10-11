package model;

/**
 *
 * @author rafael.lopes
 */
public class Acompanhamento {

    private int registro_acompanhamento_id;
    private Metodologia metodo;
    private Setor setor;
    private String cod_registro_acompanhamento;
    private String registro_acompanhamento;
    private String sigla_registro_acompanhamento;
    private String categoria;
    private String versao;

    public int getRegistro_acompanhamento_id() {
        return registro_acompanhamento_id;
    }

    public void setRegistro_acompanhamento_id(int registro_acompanhamento_id) {
        this.registro_acompanhamento_id = registro_acompanhamento_id;
    }

    public Metodologia getMetodo() {
        return metodo;
    }

    public void setMetodo(Metodologia metodo) {
        this.metodo = metodo;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public String getCod_registro_acompanhamento() {
        return cod_registro_acompanhamento;
    }

    public void setCod_registro_acompanhamento(String cod_registro_acompanhamento) {
        this.cod_registro_acompanhamento = cod_registro_acompanhamento;
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

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }
    
    
}
