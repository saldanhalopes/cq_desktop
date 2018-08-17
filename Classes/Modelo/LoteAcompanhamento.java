package Classes.Modelo;

import java.sql.Timestamp;

/**
 *
 * @author rafael.lopes
 */
public class LoteAcompanhamento {

    private int lote_acompanhamento_id;
    private Lote lote;
    private Acompanhamento acompanhamento;
    private Material material;
    private Metodologia metodologia;
    private String codigo_barras;
    private Timestamp data_registro;
    private Usuario user_registro;
    private Timestamp data_retirada;
    private Usuario user_retirada;

    public int getLote_acompanhamento_id() {
        return lote_acompanhamento_id;
    }

    public void setLote_acompanhamento_id(int lote_acompanhamento_id) {
        this.lote_acompanhamento_id = lote_acompanhamento_id;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public Acompanhamento getAcompanhamento() {
        return acompanhamento;
    }

    public void setAcompanhamento(Acompanhamento acompanhamento) {
        this.acompanhamento = acompanhamento;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Metodologia getMetodologia() {
        return metodologia;
    }

    public void setMetodologia(Metodologia metodologia) {
        this.metodologia = metodologia;
    }

    public String getCodigo_barras() {
        return codigo_barras;
    }

    public void setCodigo_barras(String codigo_barras) {
        this.codigo_barras = codigo_barras;
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

    public Timestamp getData_retirada() {
        return data_retirada;
    }

    public void setData_retirada(Timestamp data_retirada) {
        this.data_retirada = data_retirada;
    }

    public Usuario getUser_retirada() {
        return user_retirada;
    }

    public void setUser_retirada(Usuario user_retirada) {
        this.user_retirada = user_retirada;
    }

     
    
}
