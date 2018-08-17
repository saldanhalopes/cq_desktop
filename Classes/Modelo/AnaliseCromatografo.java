/*
 * Copyright (C) 2017 rafael.lopes
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package Classes.Modelo;

import java.sql.Timestamp;

/**
 *
 * @author rafael.lopes
 */
public class AnaliseCromatografo {

    private int log_lote_id;
    private Analise analise;
    private Lote lote;
    private Cromatografo cromatografo;
    private Timestamp data_hora_inicio;
    private Usuario user_inicio;
    private Timestamp data_hora_fim;
    private Usuario user_fim;
    private String status;
    private String obs;
    private Timestamp data_hora_registro;
    private Usuario user_registro;
    private Boolean retrabalho_lote;
    private String motivo_retrabalho;
    private String categoria_retrabalho;
    private String tipo_retrabalho;
    private String gerador_retrabalho;
    private Usuario user_retrabalho;
    private Timestamp data_hora_registro_retrabalho;
    private Usuario user_registro_retrabalho;
    private Material material;
    private Campanha campanha;
    private int ordem;

    public int getLog_lote_id() {
        return log_lote_id;
    }

    public void setLog_lote_id(int log_lote_id) {
        this.log_lote_id = log_lote_id;
    }

    public Analise getAnalise() {
        return analise;
    }

    public void setAnalise(Analise analise) {
        this.analise = analise;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public Cromatografo getCromatografo() {
        return cromatografo;
    }

    public void setCromatografo(Cromatografo cromatografo) {
        this.cromatografo = cromatografo;
    }

    public Timestamp getData_hora_inicio() {
        return data_hora_inicio;
    }

    public void setData_hora_inicio(Timestamp data_hora_inicio) {
        this.data_hora_inicio = data_hora_inicio;
    }

    public Usuario getUser_inicio() {
        return user_inicio;
    }

    public void setUser_inicio(Usuario user_inicio) {
        this.user_inicio = user_inicio;
    }

    public Timestamp getData_hora_fim() {
        return data_hora_fim;
    }

    public void setData_hora_fim(Timestamp data_hora_fim) {
        this.data_hora_fim = data_hora_fim;
    }

    public Usuario getUser_fim() {
        return user_fim;
    }

    public void setUser_fim(Usuario user_fim) {
        this.user_fim = user_fim;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
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

    public Boolean getRetrabalho_lote() {
        return retrabalho_lote;
    }

    public void setRetrabalho_lote(Boolean retrabalho_lote) {
        this.retrabalho_lote = retrabalho_lote;
    }

    public String getMotivo_retrabalho() {
        return motivo_retrabalho;
    }

    public void setMotivo_retrabalho(String motivo_retrabalho) {
        this.motivo_retrabalho = motivo_retrabalho;
    }

    public String getCategoria_retrabalho() {
        return categoria_retrabalho;
    }

    public void setCategoria_retrabalho(String categoria_retrabalho) {
        this.categoria_retrabalho = categoria_retrabalho;
    }

    public String getTipo_retrabalho() {
        return tipo_retrabalho;
    }

    public void setTipo_retrabalho(String tipo_retrabalho) {
        this.tipo_retrabalho = tipo_retrabalho;
    }

    public String getGerador_retrabalho() {
        return gerador_retrabalho;
    }

    public void setGerador_retrabalho(String gerador_retrabalho) {
        this.gerador_retrabalho = gerador_retrabalho;
    }

    public Usuario getUser_retrabalho() {
        return user_retrabalho;
    }

    public void setUser_retrabalho(Usuario user_retrabalho) {
        this.user_retrabalho = user_retrabalho;
    }

    public Timestamp getData_hora_registro_retrabalho() {
        return data_hora_registro_retrabalho;
    }

    public void setData_hora_registro_retrabalho(Timestamp data_hora_registro_retrabalho) {
        this.data_hora_registro_retrabalho = data_hora_registro_retrabalho;
    }

    public Usuario getUser_registro_retrabalho() {
        return user_registro_retrabalho;
    }

    public void setUser_registro_retrabalho(Usuario user_registro_retrabalho) {
        this.user_registro_retrabalho = user_registro_retrabalho;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Campanha getCampanha() {
        return campanha;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

   

}
