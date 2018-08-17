package Classes.Modelo;

/**
 *
 * @author rafael.lopes
 */
public class Amostra {

    //tb_amostra
    private int amostra_id;
    private Lote lote;
    private Material produto;
    private Analise analise;
    private Metodologia metodo;
    private Setor setor;
    private String quantidade_amostra;
    private String obs_amostra;

    //tb_analise_status
    private int amostra_status_id;
    private String amostra_status;
    private String sigla_amostra_status;
    private String descricao_amostra_status;

    public int getAmostra_id() {
        return amostra_id;
    }

    public void setAmostra_id(int amostra_id) {
        this.amostra_id = amostra_id;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
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

    public String getQuantidade_amostra() {
        return quantidade_amostra;
    }

    public void setQuantidade_amostra(String quantidade_amostra) {
        this.quantidade_amostra = quantidade_amostra;
    }

    public String getObs_amostra() {
        return obs_amostra;
    }

    public void setObs_amostra(String obs_amostra) {
        this.obs_amostra = obs_amostra;
    }

    public int getAmostra_status_id() {
        return amostra_status_id;
    }

    public void setAmostra_status_id(int amostra_status_id) {
        this.amostra_status_id = amostra_status_id;
    }

    public String getAmostra_status() {
        return amostra_status;
    }

    public void setAmostra_status(String amostra_status) {
        this.amostra_status = amostra_status;
    }

    public String getSigla_amostra_status() {
        return sigla_amostra_status;
    }

    public void setSigla_amostra_status(String sigla_amostra_status) {
        this.sigla_amostra_status = sigla_amostra_status;
    }

    public String getDescricao_amostra_status() {
        return descricao_amostra_status;
    }

    public void setDescricao_amostra_status(String descricao_amostra_status) {
        this.descricao_amostra_status = descricao_amostra_status;
    }

    
}
