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
public class Campanha {

    private int log_campanha_id;
    private String nome_campanha;
    private String metodos;
    private Cromatografo cromatografo;
    private Usuario user_inicio;
    private Timestamp data_inicio;
    private Usuario user_fim;
    private Timestamp data_fim;
    private String obs_campanha;
    private int ordem;
    private Setor setor;
    private Analise analise;
    private String cor;
    private Timestamp previsao;

    public int getLog_campanha_id() {
        return log_campanha_id;
    }

    public void setLog_campanha_id(int log_campanha_id) {
        this.log_campanha_id = log_campanha_id;
    }

    public String getNome_campanha() {
        return nome_campanha;
    }

    public void setNome_campanha(String nome_campanha) {
        this.nome_campanha = nome_campanha;
    }

    public String getMetodos() {
        return metodos;
    }

    public void setMetodos(String metodos) {
        this.metodos = metodos;
    }

    public Cromatografo getCromatografo() {
        return cromatografo;
    }

    public void setCromatografo(Cromatografo cromatografo) {
        this.cromatografo = cromatografo;
    }

    public Usuario getUser_inicio() {
        return user_inicio;
    }

    public void setUser_inicio(Usuario user_inicio) {
        this.user_inicio = user_inicio;
    }

    public Timestamp getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Timestamp data_inicio) {
        this.data_inicio = data_inicio;
    }

    public Usuario getUser_fim() {
        return user_fim;
    }

    public void setUser_fim(Usuario user_fim) {
        this.user_fim = user_fim;
    }

    public Timestamp getData_fim() {
        return data_fim;
    }

    public void setData_fim(Timestamp data_fim) {
        this.data_fim = data_fim;
    }

    public String getObs_campanha() {
        return obs_campanha;
    }

    public void setObs_campanha(String obs_campanha) {
        this.obs_campanha = obs_campanha;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public Analise getAnalise() {
        return analise;
    }

    public void setAnalise(Analise analise) {
        this.analise = analise;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Timestamp getPrevisao() {
        return previsao;
    }

    public void setPrevisao(Timestamp previsao) {
        this.previsao = previsao;
    }

   
    
}
