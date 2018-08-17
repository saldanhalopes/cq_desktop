package Classes.Modelo;

import java.sql.Timestamp;

/**
 *
 * @author rafael.lopes
 */
public class Coluna {

    private int coluna_id;
    private int codigo_coluna;
    private String codigo_sap_coluna;
    private Setor setor;
    private Metodologia metodologia;
    private Analise analise;
    private String tipo_coluna;
    private String fabricante_coluna;
    private String marca_coluna;
    private String fase_coluna;
    private String tamanho_coluna;
    private String diametro_coluna;
    private String particula_coluna;
    private String part_number_coluna;
    private String serial_number_coluna;
    private Timestamp data_ativacao_coluna;
    private Usuario user_ativacao;
    private Timestamp data_descarte_coluna;
    private Usuario user_descarte;
    private Timestamp data_performance;
    private Usuario user_performance;
    private int vaga_coluna;
    private String obs_coluna;
    private Timestamp data_verificacao;
    private Usuario user_verificacao;
    private String obs_verificacao;
    
    
    //tb_log_coluna
    private int log_coluna_id;
    private Cromatografo cromatografo;
    private Usuario user_log_coluna;
    private Timestamp data_registro_log;
    private Timestamp data_inicio_log;
    private Timestamp data_fim_log;
    private Boolean sentido;
    private Boolean precoluna;
    private Boolean prefiltro;
    private Campanha campanha;

    //tb_vaga_coluna
    private Usuario user_vaga_coluna;
    private String data_vaga_coluna;

    public int getColuna_id() {
        return coluna_id;
    }

    public void setColuna_id(int coluna_id) {
        this.coluna_id = coluna_id;
    }

    public int getCodigo_coluna() {
        return codigo_coluna;
    }

    public void setCodigo_coluna(int codigo_coluna) {
        this.codigo_coluna = codigo_coluna;
    }

    public String getCodigo_sap_coluna() {
        return codigo_sap_coluna;
    }

    public void setCodigo_sap_coluna(String codigo_sap_coluna) {
        this.codigo_sap_coluna = codigo_sap_coluna;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public Metodologia getMetodologia() {
        return metodologia;
    }

    public void setMetodologia(Metodologia metodologia) {
        this.metodologia = metodologia;
    }

    public Analise getAnalise() {
        return analise;
    }

    public void setAnalise(Analise analise) {
        this.analise = analise;
    }

    public String getTipo_coluna() {
        return tipo_coluna;
    }

    public void setTipo_coluna(String tipo_coluna) {
        this.tipo_coluna = tipo_coluna;
    }

    public String getFabricante_coluna() {
        return fabricante_coluna;
    }

    public void setFabricante_coluna(String fabricante_coluna) {
        this.fabricante_coluna = fabricante_coluna;
    }

    public String getMarca_coluna() {
        return marca_coluna;
    }

    public void setMarca_coluna(String marca_coluna) {
        this.marca_coluna = marca_coluna;
    }

    public String getFase_coluna() {
        return fase_coluna;
    }

    public void setFase_coluna(String fase_coluna) {
        this.fase_coluna = fase_coluna;
    }

    public String getTamanho_coluna() {
        return tamanho_coluna;
    }

    public void setTamanho_coluna(String tamanho_coluna) {
        this.tamanho_coluna = tamanho_coluna;
    }

    public String getDiametro_coluna() {
        return diametro_coluna;
    }

    public void setDiametro_coluna(String diametro_coluna) {
        this.diametro_coluna = diametro_coluna;
    }

    public String getParticula_coluna() {
        return particula_coluna;
    }

    public void setParticula_coluna(String particula_coluna) {
        this.particula_coluna = particula_coluna;
    }

    public String getPart_number_coluna() {
        return part_number_coluna;
    }

    public void setPart_number_coluna(String part_number_coluna) {
        this.part_number_coluna = part_number_coluna;
    }

    public String getSerial_number_coluna() {
        return serial_number_coluna;
    }

    public void setSerial_number_coluna(String serial_number_coluna) {
        this.serial_number_coluna = serial_number_coluna;
    }

    public Timestamp getData_ativacao_coluna() {
        return data_ativacao_coluna;
    }

    public void setData_ativacao_coluna(Timestamp data_ativacao_coluna) {
        this.data_ativacao_coluna = data_ativacao_coluna;
    }

    public Usuario getUser_ativacao() {
        return user_ativacao;
    }

    public void setUser_ativacao(Usuario user_ativacao) {
        this.user_ativacao = user_ativacao;
    }

    public Timestamp getData_descarte_coluna() {
        return data_descarte_coluna;
    }

    public void setData_descarte_coluna(Timestamp data_descarte_coluna) {
        this.data_descarte_coluna = data_descarte_coluna;
    }

    public Usuario getUser_descarte() {
        return user_descarte;
    }

    public void setUser_descarte(Usuario user_descarte) {
        this.user_descarte = user_descarte;
    }

    public Timestamp getData_performance() {
        return data_performance;
    }

    public void setData_performance(Timestamp data_performance) {
        this.data_performance = data_performance;
    }

    public Usuario getUser_performance() {
        return user_performance;
    }

    public void setUser_performance(Usuario user_performance) {
        this.user_performance = user_performance;
    }

    public int getVaga_coluna() {
        return vaga_coluna;
    }

    public void setVaga_coluna(int vaga_coluna) {
        this.vaga_coluna = vaga_coluna;
    }

    public String getObs_coluna() {
        return obs_coluna;
    }

    public void setObs_coluna(String obs_coluna) {
        this.obs_coluna = obs_coluna;
    }

    public Timestamp getData_verificacao() {
        return data_verificacao;
    }

    public void setData_verificacao(Timestamp data_verificacao) {
        this.data_verificacao = data_verificacao;
    }

    public Usuario getUser_verificacao() {
        return user_verificacao;
    }

    public void setUser_verificacao(Usuario user_verificacao) {
        this.user_verificacao = user_verificacao;
    }

    public String getObs_verificacao() {
        return obs_verificacao;
    }

    public void setObs_verificacao(String obs_verificacao) {
        this.obs_verificacao = obs_verificacao;
    }

    public int getLog_coluna_id() {
        return log_coluna_id;
    }

    public void setLog_coluna_id(int log_coluna_id) {
        this.log_coluna_id = log_coluna_id;
    }

    public Cromatografo getCromatografo() {
        return cromatografo;
    }

    public void setCromatografo(Cromatografo cromatografo) {
        this.cromatografo = cromatografo;
    }

    public Usuario getUser_log_coluna() {
        return user_log_coluna;
    }

    public void setUser_log_coluna(Usuario user_log_coluna) {
        this.user_log_coluna = user_log_coluna;
    }

    public Timestamp getData_registro_log() {
        return data_registro_log;
    }

    public void setData_registro_log(Timestamp data_registro_log) {
        this.data_registro_log = data_registro_log;
    }

    public Timestamp getData_inicio_log() {
        return data_inicio_log;
    }

    public void setData_inicio_log(Timestamp data_inicio_log) {
        this.data_inicio_log = data_inicio_log;
    }

    public Timestamp getData_fim_log() {
        return data_fim_log;
    }

    public void setData_fim_log(Timestamp data_fim_log) {
        this.data_fim_log = data_fim_log;
    }

    public Boolean getSentido() {
        return sentido;
    }

    public void setSentido(Boolean sentido) {
        this.sentido = sentido;
    }

    public Boolean getPrecoluna() {
        return precoluna;
    }

    public void setPrecoluna(Boolean precoluna) {
        this.precoluna = precoluna;
    }

    public Boolean getPrefiltro() {
        return prefiltro;
    }

    public void setPrefiltro(Boolean prefiltro) {
        this.prefiltro = prefiltro;
    }

    public Campanha getCampanha() {
        return campanha;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }

    public Usuario getUser_vaga_coluna() {
        return user_vaga_coluna;
    }

    public void setUser_vaga_coluna(Usuario user_vaga_coluna) {
        this.user_vaga_coluna = user_vaga_coluna;
    }

    public String getData_vaga_coluna() {
        return data_vaga_coluna;
    }

    public void setData_vaga_coluna(String data_vaga_coluna) {
        this.data_vaga_coluna = data_vaga_coluna;
    }

    
}
