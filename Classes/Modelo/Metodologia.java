package Classes.Modelo;

/**
 *
 * @author rafael.lopes
 */
public class Metodologia {

    private int metodo_id;
    private String cod_metodo;
    private String metodo;
    private String categoria;
    private int versao;
    private String link;
    private Boolean ajuda;

    public int getMetodo_id() {
        return metodo_id;
    }

    public void setMetodo_id(int metodo_id) {
        this.metodo_id = metodo_id;
    }

    public String getCod_metodo() {
        return cod_metodo;
    }

    public void setCod_metodo(String cod_metodo) {
        this.cod_metodo = cod_metodo;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Boolean getAjuda() {
        return ajuda;
    }

    public void setAjuda(Boolean ajuda) {
        this.ajuda = ajuda;
    }

    
}
