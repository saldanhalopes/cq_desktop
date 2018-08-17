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
public class RegistroRf {

    private int registro_rf_id;
    private Material material;
    private int caixa_rf_id;
    private String centro_custo;
    private String deposito;
    private Float quantidade;
    private String lote;
    private String rpm;
    private String caixa;
    private Timestamp data_registro;
    private Usuario user_registro;
    private Timestamp data_inicio;
    private Usuario user_inicio;
    private Timestamp data_fim;
    private Usuario user_fim;

    public int getRegistro_rf_id() {
        return registro_rf_id;
    }

    public void setRegistro_rf_id(int registro_rf_id) {
        this.registro_rf_id = registro_rf_id;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public int getCaixa_rf_id() {
        return caixa_rf_id;
    }

    public void setCaixa_rf_id(int caixa_rf_id) {
        this.caixa_rf_id = caixa_rf_id;
    }

    public String getCentro_custo() {
        return centro_custo;
    }

    public void setCentro_custo(String centro_custo) {
        this.centro_custo = centro_custo;
    }

    public String getDeposito() {
        return deposito;
    }

    public void setDeposito(String deposito) {
        this.deposito = deposito;
    }

    public Float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Float quantidade) {
        this.quantidade = quantidade;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getRpm() {
        return rpm;
    }

    public void setRpm(String rpm) {
        this.rpm = rpm;
    }

    public String getCaixa() {
        return caixa;
    }

    public void setCaixa(String caixa) {
        this.caixa = caixa;
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

    public Timestamp getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Timestamp data_inicio) {
        this.data_inicio = data_inicio;
    }

    public Usuario getUser_inicio() {
        return user_inicio;
    }

    public void setUser_inicio(Usuario user_inicio) {
        this.user_inicio = user_inicio;
    }

    public Timestamp getData_fim() {
        return data_fim;
    }

    public void setData_fim(Timestamp data_fim) {
        this.data_fim = data_fim;
    }

    public Usuario getUser_fim() {
        return user_fim;
    }

    public void setUser_fim(Usuario user_fim) {
        this.user_fim = user_fim;
    }

    
    
}
