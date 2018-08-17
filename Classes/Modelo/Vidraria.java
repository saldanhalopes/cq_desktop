package Classes.Modelo;

import java.sql.Timestamp;

/**
 *
 * @author rafael.lopes
 */
public class Vidraria {

    private int vidraria_id;
    private int vidraria_tipo_id;
    private String vidraria_tipo;
    private Setor setor;
    private String volume;
    private Unidade unidade;
    private String certificado;
    private java.sql.Timestamp data_entrada;
    private java.sql.Timestamp data_saida;
    private String obs_vidraria;
    private String responsavel_entrada;
    private String responsavel_saida;

    public int getVidraria_id() {
        return vidraria_id;
    }

    public void setVidraria_id(int vidraria_id) {
        this.vidraria_id = vidraria_id;
    }

    public int getVidraria_tipo_id() {
        return vidraria_tipo_id;
    }

    public void setVidraria_tipo_id(int vidraria_tipo_id) {
        this.vidraria_tipo_id = vidraria_tipo_id;
    }

    public String getVidraria_tipo() {
        return vidraria_tipo;
    }

    public void setVidraria_tipo(String vidraria_tipo) {
        this.vidraria_tipo = vidraria_tipo;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public String getCertificado() {
        return certificado;
    }

    public void setCertificado(String certificado) {
        this.certificado = certificado;
    }

    public Timestamp getData_entrada() {
        return data_entrada;
    }

    public void setData_entrada(Timestamp data_entrada) {
        this.data_entrada = data_entrada;
    }

    public Timestamp getData_saida() {
        return data_saida;
    }

    public void setData_saida(Timestamp data_saida) {
        this.data_saida = data_saida;
    }

    public String getObs_vidraria() {
        return obs_vidraria;
    }

    public void setObs_vidraria(String obs_vidraria) {
        this.obs_vidraria = obs_vidraria;
    }

    public String getResponsavel_entrada() {
        return responsavel_entrada;
    }

    public void setResponsavel_entrada(String responsavel_entrada) {
        this.responsavel_entrada = responsavel_entrada;
    }

    public String getResponsavel_saida() {
        return responsavel_saida;
    }

    public void setResponsavel_saida(String responsavel_saida) {
        this.responsavel_saida = responsavel_saida;
    }

        

}
