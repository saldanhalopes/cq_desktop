/*
 * Copyright (C) 2018 rafael.lopes
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
public class Treinamento {

    //tb_treinamento
    private int treinamento_id;
    private Metodologia metodologia1;
    private Metodologia metodologia2;
    private Metodologia metodologia3;
    private Metodologia metodologia4;
    private Usuario user;
    private Timestamp data_treinamento;
    private Usuario user_treinamento;
    private Timestamp data_registro;
    private Usuario user_registro;
    private Boolean aprovado;
    private String titulo;
    private String conteudo;
    private Boolean vigente;
    private int treinamento_user_id;
    private Setor setor;
    private String turno;
    private int numero_usuarios;

    public int getTreinamento_id() {
        return treinamento_id;
    }

    public void setTreinamento_id(int treinamento_id) {
        this.treinamento_id = treinamento_id;
    }

    public Metodologia getMetodologia1() {
        return metodologia1;
    }

    public void setMetodologia1(Metodologia metodologia1) {
        this.metodologia1 = metodologia1;
    }

    public Metodologia getMetodologia2() {
        return metodologia2;
    }

    public void setMetodologia2(Metodologia metodologia2) {
        this.metodologia2 = metodologia2;
    }

    public Metodologia getMetodologia3() {
        return metodologia3;
    }

    public void setMetodologia3(Metodologia metodologia3) {
        this.metodologia3 = metodologia3;
    }

    public Metodologia getMetodologia4() {
        return metodologia4;
    }

    public void setMetodologia4(Metodologia metodologia4) {
        this.metodologia4 = metodologia4;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Timestamp getData_treinamento() {
        return data_treinamento;
    }

    public void setData_treinamento(Timestamp data_treinamento) {
        this.data_treinamento = data_treinamento;
    }

    public Usuario getUser_treinamento() {
        return user_treinamento;
    }

    public void setUser_treinamento(Usuario user_treinamento) {
        this.user_treinamento = user_treinamento;
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

    public Boolean getAprovado() {
        return aprovado;
    }

    public void setAprovado(Boolean aprovado) {
        this.aprovado = aprovado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Boolean getVigente() {
        return vigente;
    }

    public void setVigente(Boolean vigente) {
        this.vigente = vigente;
    }

    public int getTreinamento_user_id() {
        return treinamento_user_id;
    }

    public void setTreinamento_user_id(int treinamento_user_id) {
        this.treinamento_user_id = treinamento_user_id;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public int getNumero_usuarios() {
        return numero_usuarios;
    }

    public void setNumero_usuarios(int numero_usuarios) {
        this.numero_usuarios = numero_usuarios;
    }
    
    
}
