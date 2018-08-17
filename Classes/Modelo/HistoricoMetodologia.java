/*
 * Copyright (C) 2018 rafael.lopes
 *
 * Este programa é um software livre: você pode redistribuí-lo e / ou modificar
 * sob os termos da GNU General Public License, conforme publicado pela
 * a Free Software Foundation, seja a versão 3 da Licença, quanto
 * qualquer versão posterior.
 *
 * Este programa é distribuído na esperança de que seja útil,
 * mas SEM QUALQUER GARANTIA; sem a garantia implícita de
 * COMERCIALIZAÇÃO OU APTIDÃO PARA UM PROPÓSITO PARTICULAR. Veja o
 * GNU General Public License para obter mais detalhes.
 *
 * Você deve ter recebido uma cópia da GNU General Public License
 *  juntamente com este programa. Caso contrário, veja <http://www.gnu.org/licenses/>.
 */
package Classes.Modelo;

import java.sql.Timestamp;

/**
 *
 * @author rafael.lopes
 */
public class HistoricoMetodologia {

    private int historico_metodologia_id;
    private Metodologia metodologia;
    private String tipo_ajuste;
    private Cromatografo cromatografo;
    private Analise analise;
    private String coluna;
    private String fase_movel;
    private String codicao_inicial;
    private String ajuste;
    private String obs;
    private Usuario user_registro;
    private Timestamp data_registro;
    private Boolean padronizar;

    public int getHistorico_metodologia_id() {
        return historico_metodologia_id;
    }

    public void setHistorico_metodologia_id(int historico_metodologia_id) {
        this.historico_metodologia_id = historico_metodologia_id;
    }

    public Metodologia getMetodologia() {
        return metodologia;
    }

    public void setMetodologia(Metodologia metodologia) {
        this.metodologia = metodologia;
    }

    public String getTipo_ajuste() {
        return tipo_ajuste;
    }

    public void setTipo_ajuste(String tipo_ajuste) {
        this.tipo_ajuste = tipo_ajuste;
    }

    public Cromatografo getCromatografo() {
        return cromatografo;
    }

    public void setCromatografo(Cromatografo cromatografo) {
        this.cromatografo = cromatografo;
    }

    public Analise getAnalise() {
        return analise;
    }

    public void setAnalise(Analise analise) {
        this.analise = analise;
    }

    public String getColuna() {
        return coluna;
    }

    public void setColuna(String coluna) {
        this.coluna = coluna;
    }

    public String getFase_movel() {
        return fase_movel;
    }

    public void setFase_movel(String fase_movel) {
        this.fase_movel = fase_movel;
    }

    public String getCodicao_inicial() {
        return codicao_inicial;
    }

    public void setCodicao_inicial(String codicao_inicial) {
        this.codicao_inicial = codicao_inicial;
    }

    public String getAjuste() {
        return ajuste;
    }

    public void setAjuste(String ajuste) {
        this.ajuste = ajuste;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Usuario getUser_registro() {
        return user_registro;
    }

    public void setUser_registro(Usuario user_registro) {
        this.user_registro = user_registro;
    }

    public Timestamp getData_registro() {
        return data_registro;
    }

    public void setData_registro(Timestamp data_registro) {
        this.data_registro = data_registro;
    }

    public Boolean getPadronizar() {
        return padronizar;
    }

    public void setPadronizar(Boolean padronizar) {
        this.padronizar = padronizar;
    }

    
}
